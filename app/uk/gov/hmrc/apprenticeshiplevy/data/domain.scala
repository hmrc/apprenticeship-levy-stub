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

case class PayrollMonth(year: Int, month: Int)

object PayrollMonth {
  implicit val formats = Json.format[PayrollMonth]
}

case class Fraction(region: String, value: BigDecimal)

object Fraction {
  implicit val formats = Json.format[Fraction]
}

case class FractionCalculation(calculatedAt: LocalDate, fractions: List[Fraction])

object FractionCalculation {
  implicit val formats = Json.format[FractionCalculation]
}

case class LevyDeclaration(payrollMonth: PayrollMonth, amount: BigDecimal, submissionType: String, submissionDate: String)

object LevyDeclaration {
  implicit val formats = Json.format[LevyDeclaration]
}

case class LevyDeclarations(empref: String, schemeCessationDate: Option[LocalDate], declarations: Seq[LevyDeclaration])

object LevyDeclarations {
  implicit val formats = Json.format[LevyDeclarations]
}

case class Fractions(empref: String, fractionCalculations: List[FractionCalculation])

object Fractions {
  implicit val formats = Json.format[Fractions]
}

// EPS data

case class EmpRefs(officeNumber: String, payeRef: String, aoRef: String)

object EmpRefs {
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

case class FinalSubmission(becauseSchemeCeased: Yes, dateSchemeCeased: Option[LocalDate], forYear: Yes)

object FinalSubmission {
  implicit val formats = Json.format[FinalSubmission]
}

case class EmployerPaymentSummary(empRefs: EmpRefs,
                                  noPaymentForPeriod: Yes,
                                  noPaymentDates: Option[DateRange],
                                  periodOfInactivity: Option[DateRange],
                                  empAllceInd: YesNo,
                                  recoverableAmountsYTD: Option[RecoverableAmountsYTD],
                                  account: Option[Account],
                                  relatedTaxYear: String,
                                  finalSubmission: Option[FinalSubmission])

object EmployerPaymentSummary {
  implicit val formats = Json.format[EmployerPaymentSummary]
}
