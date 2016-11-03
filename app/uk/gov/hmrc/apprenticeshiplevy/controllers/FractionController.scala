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

import org.joda.time.LocalDate
import play.api.libs.json.Json
import play.api.mvc.Action
import uk.gov.hmrc.apprenticeshiplevy.data.{FractionCalculation, FractionData}
import uk.gov.hmrc.play.microservice.controller.BaseController
import java.net.URLDecoder

object FractionController extends FractionController

trait FractionController extends BaseController with DateFiltering {
  protected lazy val emprefParts = "^(\\d{3})([0-9A-Z]{1,10})$".r
  protected def toSlashFormat(empref: String): String = URLDecoder.decode(empref, "UTF-8") match {
    case emprefParts(part1, part2) => s"${part1}/${part2}"
    case _ => empref
  }

  def fractions(empref: String, fromDate: Option[LocalDate], toDate: Option[LocalDate]) = Action { implicit request =>
    implicit val dateExtractor = (f: FractionCalculation) => f.calculatedAt

    FractionData.fractions(toSlashFormat(empref)) match {
      case Some(fractions) =>
        val filtered = fractions.fractionCalculations.filterDate(fromDate, toDate).toList
        Ok(Json.toJson(fractions.copy(fractionCalculations = filtered)))
      case None => NotFound
    }
  }

  def fractionCalculationDate = Action {
    Ok(Json.toJson(new LocalDate))
  }
}
