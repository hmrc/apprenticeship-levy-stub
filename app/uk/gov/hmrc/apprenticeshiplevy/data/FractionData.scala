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
import play.api.libs.json.Json

object FractionData {

  def fractions(empref: String): Option[Fractions] = {
    val e = empref.replace("/", "")
    data.get(empref).orElse(loadFractionData(empref))
  }

  def loadFractionData(empref: String): Option[Fractions] = {
    val e = empref.replace("/", "")
    val path = s"/sandbox_data/employers/$e/fractions.json"

    Option(this.getClass.getResourceAsStream(path)).flatMap { is =>
      val json = scala.io.Source.fromInputStream(is).getLines().mkString("\n")
      Json.parse(json).asOpt[Fractions]
    }
  }

  val data =
    Map("123/AB12345" -> Fractions("123/AB12345", List(
      FractionCalculation(new LocalDate(2016, 3, 15), List(
        Fraction("England", 0.83),
        Fraction("Scotland", 0.11),
        Fraction("Wales", 0.06),
        Fraction("Northern Ireland", 0)
      )),
      FractionCalculation(new LocalDate(2015, 11, 18), List(
        Fraction("England", 0.78),
        Fraction("Scotland", 0.16),
        Fraction("Wales", 0.06),
        Fraction("Northern Ireland", 0)
      ))
    )))
}
