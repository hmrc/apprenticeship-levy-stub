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

import play.api.libs.json.Json
import play.api.mvc.Action
import uk.gov.hmrc.apprenticeshiplevy.data.epaye.{DesignatoryDetails, DesignatoryDetailsData, HodName}
import uk.gov.hmrc.play.microservice.controller.BaseController

object EpayeController extends EpayeController


trait EpayeController extends BaseController {

  import DesignatoryDetails._

  def designatoryDetails(empref: String) = Action { implicit request =>
    val details = DesignatoryDetails(Some(DesignatoryDetailsData(Some(HodName(Some("Foo Bar Ltd."), None)), None, None)), None)

    if (empref == "123/AB12345") Ok(Json.toJson(details))
    else NotFound
  }
}
