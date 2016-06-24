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

package uk.gov.hmrc.apprenticeshiplevy.data.charges

import org.joda.time.LocalDateTime

object ChargeData {
  val charges =
    List(
      Charges("CHARGES", new LocalDateTime(), "2015_16", "123/AB12345", List(
        Charge("In Year RTI Charge - APPRENTICESHIP LEVY", "FPS", List(
          Period(Some("2016-01-02"), Some("2016-01-31"), 9876.45, 99.96, 123.45),
          Period(Some("2016-02-04"), Some("2016-02-10"), 9876.45, 99.96, 123.45)
        )),
        Charge("PAYE EYU - APPRENTICESHIP LEVY", "EYU", List(
          Period(None, None, 9876.45, 99.96, 123.45),
          Period(Some("2016-02-04"), Some("2016-02-10"), 9876.45, 99.96, 123.45)
        ))
      ))
    )

  val data: Map[String, Charges] = charges.groupBy(_.requestEmpref).map(e => e._1 -> e._2.head)
}
