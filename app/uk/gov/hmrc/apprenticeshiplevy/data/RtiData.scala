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

import org.joda.time.{LocalDate, LocalDateTime}

object RtiData {

  private val account = Account(
    accountHoldersName = "Mr. James T Bacon",
    accountNo = "12345678",
    sortCode = "123456",
    buildingSocRef = Some("SMITH-(123)")
  )

  private val refs = EmpRefs(officeNumber = "123", payeRef = "123/AB12345", aoRef = "123PQ7654321X")

  val record1 = EmployerPaymentSummary(
    12345678,
    new LocalDateTime("2016-07-14T16:05:23.123"),
    refs,
    None, None, None, None, None,
    Some(ApprenticeshipLevy(600, 11, 15000)),
    Some(account),
    "16-17",
    None)

  val record2 = EmployerPaymentSummary(
    12345679,
    new LocalDateTime("2015-04-07T16:05:23.123"),
    refs,
    Some("yes"), Some(DateRange(new LocalDate("2016-03-06"), new LocalDate("2016-04-05"))), None, None, None,
    None,
    Some(account),
    "15-16",
    None
  )

  val record3 = EmployerPaymentSummary(
    12345680,
    new LocalDateTime("2016-05-07T16:05:23.123"),
    refs,
    None, None, None, None, None,
    Some(ApprenticeshipLevy(500, 1, 15000)),
    Some(account),
    "16-17",
    None)

  val record4 = EmployerPaymentSummary(
    12345681,
    new LocalDateTime("2016-06-07T16:05:23.123"),
    refs,
    None, None, None, None, None,
    Some(ApprenticeshipLevy(1000, 2, 15000)),
    Some(account),
    "16-17",
    None)

  val record5 = EmployerPaymentSummary(
    12345682,
    new LocalDateTime("2016-06-15T16:20:23"),
    refs,
    None, None, None, None, None,
    Some(ApprenticeshipLevy(200, 2, 15000)),
    Some(account),
    "16-17",
    None)

  val record6 = EmployerPaymentSummary(
    12345683,
    new LocalDateTime("2016-07-15T16:05:23.123"),
    refs,
    None, None, Some(DateRange(new LocalDate("2016-06-06"), new LocalDate("2016-09-05"))), None, None,
    None,
    Some(account),
    "16-17",
    None)

  val record7 = EmployerPaymentSummary(
    12345684,
    new LocalDateTime("2016-10-15T16:05:23.123"),
    refs,
    None, None, None, None, None,
    None,
    Some(account),
    "16-17",
    Some(FinalSubmission(
      becauseSchemeCeased = Some("yes"),
      dateSchemeCeased = Some(new LocalDate("2016-09-05")),
      forYear = Some("yes")
    )))

  val data: Map[String, Seq[EmployerPaymentSummary]] = Map(
    "123/AB12345" -> Seq(record7, record6, record5, record4, record3, record2, record1)
  )
}
