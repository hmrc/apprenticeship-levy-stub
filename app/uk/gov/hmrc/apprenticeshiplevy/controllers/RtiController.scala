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
import uk.gov.hmrc.apprenticeshiplevy.data.{EmployerPaymentSummary, RtiData}
import uk.gov.hmrc.play.microservice.controller.BaseController


trait RtiController extends BaseController with DateFiltering {
  def eps(empref: String, fromDate: Option[LocalDate], toDate: Option[LocalDate]) = Action { implicit request =>
    implicit val dateExtractor = (e: EmployerPaymentSummary) => e.submissionTime.toLocalDate
    RtiData.eps(empref) match {
      case Some(eps) => Ok(Json.toJson(eps.filterDate(fromDate, toDate)))
      case _ => NotFound
    }
  }
}

object RtiController extends RtiController
