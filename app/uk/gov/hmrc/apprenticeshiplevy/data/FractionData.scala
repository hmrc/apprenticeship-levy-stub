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

import org.joda.time.LocalDate

object FractionData {
  val data =
    Map("123/AB12345" -> List(
      EnglishFraction(BigDecimal(0.79), new LocalDate(2016, 2, 5)),
      EnglishFraction(BigDecimal(0.88), new LocalDate(2016, 1, 5))
    ))
}
