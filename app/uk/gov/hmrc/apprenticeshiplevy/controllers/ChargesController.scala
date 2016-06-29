/*
 * Copyright 2016 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.apprenticeshiplevy.controllers


import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import play.Play
import play.api.libs.json._
import play.api.mvc.Action
import uk.gov.hmrc.apprenticeshiplevy.controllers.ErrorResponse._
import uk.gov.hmrc.play.microservice.controller.BaseController

import scala.io.{BufferedSource, Source}

object ChargesController extends ChargesController

trait ChargesController extends BaseController {
  val ldtWrites = new Writes[LocalDateTime] {
    val fmt = DateTimeFormat.forPattern("dd-MM-YYYY'T'HH:mm:ss")

    override def writes(ldt: LocalDateTime): JsValue = JsString(fmt.print(ldt))
  }

  def charges(empref: String, taxYear: String) = Action { implicit request =>
    resourceAsResponse(empref, taxYear) match {
      case Some(cs) => toResult(cs, empref, taxYear)
      case None => NotFound
    }
  }

  def toResult(r: JsObject, empref: String, taxYear: String) =
    ErrorResponse.fmt.reads(r).asOpt match {
      case Some(error: ErrorResponse) => Status(error.httpStatusCode)(r)
      case _ => Ok(addFields(r, empref, taxYear))
    }


  def addFields(r: JsObject, empref: String, taxYear: String): JsObject = {
    val headers = JsObject(
      Seq(
        "chargeResponseType" -> JsString("CHARGES"),
        "responseTimestamp" -> ldtWrites.writes(LocalDateTime.now()),
        "requestTaxYear" -> JsString(taxYear),
        "requestEmpref" -> JsString(empref)
      )
    )

    headers.deepMerge(r)
  }

  def resourceAsResponse(empref: String, taxYear: String): Option[JsObject] = {
    val ResourceNotFoundEmpRef = "0000"

    empref match {
      case error if empref == ResourceNotFoundEmpRef => Some(Json.toJson(ErrorNotFound).as[JsObject])
      case _ =>
        val os = openFile(path(empref, taxYear))
        val r = os.map(_.mkString).map(Json.parse(_).as[JsObject])
        os.foreach(_.close)
        r
    }
  }

  def path(empref: String, taxYear: String) = s"sandbox_data/employers/$empref/charges/taxyear/$taxYear"

  def openFile(path: String): Option[BufferedSource] = Option(Play.application.resource(path)).map(Source.fromURL)
}
