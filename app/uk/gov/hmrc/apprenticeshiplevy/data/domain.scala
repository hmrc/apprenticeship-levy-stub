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

import org.joda.time.format.DateTimeFormat
import org.joda.time.{LocalDate, LocalDateTime}
import play.api.libs.json._

case class Fraction(region: String, value: BigDecimal)

object Fraction {
  implicit val formats = Json.format[Fraction]
}

case class FractionCalculation(calculatedAt: LocalDate, fractions: List[Fraction])

object FractionCalculation {
  implicit val formats = Json.format[FractionCalculation]
}

case class Fractions(empref: String, fractionCalculations: List[FractionCalculation])

object Fractions {
  implicit val formats = Json.format[Fractions]
}

// EPS data

case class EmpRefs(officeNumber: String, payeRef: String, aoRef: String)

object EmpRefs {
  def fromEmpref(empref: String) = {
    val parts = empref.split("/")
    EmpRefs("", parts(0), parts(1))
  }

  implicit val formats = Json.format[EmpRefs]
}

case class DateRange(from: LocalDate, to: LocalDate)

object DateRange {
  implicit val formats = Json.format[DateRange]
}

case class RecoverableAmountsYTD(taxMonth: Option[Int] = None,
                                 smpRecovered: Option[BigDecimal] = None,
                                 sppRecovered: Option[BigDecimal] = None,
                                 sapRecovered: Option[BigDecimal] = None,
                                 shppRecovered: Option[BigDecimal] = None,
                                 nicCompensationOnSMP: Option[BigDecimal] = None,
                                 nicCompensationOnSPP: Option[BigDecimal] = None,
                                 nicCompensationOnSAP: Option[BigDecimal] = None,
                                 nicCompensationOnShPP: Option[BigDecimal] = None,
                                 cisDeductionsSuffered: Option[BigDecimal] = None)

case class ApprenticeshipLevy(levyDueYTD: BigDecimal, taxMonth: Int, annualAllce: BigDecimal)

object ApprenticeshipLevy {
  implicit val formats = Json.format[ApprenticeshipLevy]
}

object RecoverableAmountsYTD {
  implicit val formats = Json.format[RecoverableAmountsYTD]
}

case class Account(accountHoldersName: String,
                   accountNo: String,
                   sortCode: String,
                   buildingSocRef: Option[String] = None)

object Account {
  implicit val formats = Json.format[Account]
}

case class FinalSubmission(becauseSchemeCeased: Option[String], dateSchemeCeased: Option[LocalDate], forYear: Option[String])

object FinalSubmission {
  implicit val formats = Json.format[FinalSubmission]
}

case class EmployerPaymentSummary(eventId: Long,
                                  submissionTime: LocalDateTime,
                                  empRefs: EmpRefs,
                                  noPaymentForPeriod: Option[String],
                                  noPaymentDates: Option[DateRange],
                                  periodOfInactivity: Option[DateRange],
                                  empAllceInd: Option[String],
                                  recoverableAmountsYTD: Option[RecoverableAmountsYTD],
                                  apprenticeshipLevy: Option[ApprenticeshipLevy],
                                  account: Option[Account],
                                  relatedTaxYear: String,
                                  finalSubmission: Option[FinalSubmission])

object EmployerPaymentSummary {

  implicit val ldtFormats = new Format[LocalDateTime] {
    val fmt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")

    override def reads(json: JsValue): JsResult[LocalDateTime] = implicitly[Reads[JsString]].reads(json).map { js =>
      fmt.parseDateTime(js.value).toLocalDateTime
    }

    override def writes(o: LocalDateTime): JsValue = JsString(fmt.print(o))
  }

  implicit val formats = Json.format[EmployerPaymentSummary]
}
