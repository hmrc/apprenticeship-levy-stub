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

import org.joda.time.{DateTime, LocalDate}

object RtiData {


  val epsRecord = EmployerPaymentSummary(
    0,
    new DateTime(2016, 4, 7, 16, 5, 23),
    EmpRefs(officeNumber = "123", payeRef = "Ref/001<Q>", aoRef = "123PQ7654321X"),
    Some("yes"),
    Some(DateRange(from = new LocalDate(2016, 4, 6), to = new LocalDate(2017, 4, 5))),
    Some(DateRange(from = new LocalDate(2015, 4, 6), to = new LocalDate(2015, 5, 5))),
    Some("yes"),
    Some(RecoverableAmountsYTD(
      taxMonth = Some(7),
      smpRecovered = Some(BigDecimal("13.12")),
      sppRecovered = Some(BigDecimal("13.12")),
      sapRecovered = Some(BigDecimal("13.12")),
      shppRecovered = Some(BigDecimal("13.12")),
      nicCompensationOnSMP = Some(BigDecimal("13.12")),
      nicCompensationOnSPP = Some(BigDecimal("13.12")),
      nicCompensationOnSAP = Some(BigDecimal("13.12")),
      nicCompensationOnShPP = Some(BigDecimal("13.12")),
      cisDeductionsSuffered = Some(BigDecimal("13.12"))
    )),
    Some(ApprenticeshipLevy(600, 7, 15000)),
    Some(Account(
      accountHoldersName = "Mr. James T Bacon",
      accountNo = "12345678",
      sortCode = "123456",
      buildingSocRef = Some("SMITH-(123)")
    )),
    "16-17",
    Some(FinalSubmission(
      becauseSchemeCeased = Some("yes"),
      dateSchemeCeased = Some(new LocalDate(2017, 1, 1)),
      forYear = Some("yes")
    )))

  val data: Map[String, Seq[EmployerPaymentSummary]] = Map(
    "123/AB12345" -> Seq(epsRecord, epsRecord.copy(submissionTime = new DateTime(2016, 5, 7, 16, 5, 23)))
  )
}
