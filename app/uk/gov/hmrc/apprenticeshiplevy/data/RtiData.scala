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

package uk.gov.hmrc.apprenticeshiplevy.data

import play.api.libs.json.Json

object RtiData {

  def eps(empref: String): Option[Seq[EmployerPaymentSummary]] = {
    val e = empref.replace("/", "")
    val path = s"/sandbox_data/employers/$e/eps.json"
    Option(this.getClass.getResourceAsStream(path)).flatMap { is =>
      val json = scala.io.Source.fromInputStream(is).getLines().mkString("\n")
      Json.parse(json).asOpt[Seq[EmployerPaymentSummary]]
    }
  }
}
