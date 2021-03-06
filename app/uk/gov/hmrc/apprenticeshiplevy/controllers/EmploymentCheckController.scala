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
import play.api.libs.json.JsString
import play.api.mvc.Action
import uk.gov.hmrc.play.microservice.controller.BaseController

object EmploymentCheckController extends EmploymentCheckController

trait EmploymentCheckController extends BaseController {
  def check(empref: String, nino: String, atDate: Option[LocalDate]) = Action { implicit request =>
    Ok(s"""{
  "empref": "${empref}",
  "nino": "${nino}",
  "fromDate": "2001-01-01",
  "toDate": "2001-01-02",
  "employed": true
}""")
  }
}
