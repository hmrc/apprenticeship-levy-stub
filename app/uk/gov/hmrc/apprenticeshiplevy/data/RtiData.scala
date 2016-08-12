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
    }.orElse {
      val path = s"/sandbox_data/employers/$e/declarations.json"
      Option(this.getClass.getResourceAsStream(path)).flatMap { is =>
        val json = scala.io.Source.fromInputStream(is).getLines().mkString("\n")
        Json.parse(json).\("declarations").asOpt[Seq[LevyDeclaration]].map { decls =>
          decls.map(convertToEPS(EmpRefs.fromEmpref(empref), _))
        }
      }
    }
  }

  def convertToEPS(emprefs: EmpRefs, decl: LevyDeclaration): EmployerPaymentSummary = {
    decl match {
      case LevyDeclaration(id, ts, Some(ceasedDate), _, _, _, _, _, _) =>
        EmployerPaymentSummary(id, ts, emprefs, None, None, None, None, None, Some(levy(decl)),
          None, decl.payrollPeriod.get.year,
          Some(FinalSubmission(Some("Yes"), decl.dateCeased, decl.payrollPeriod.map(_.year))))
    }
  }

  def levy(decl: LevyDeclaration) = ApprenticeshipLevy(decl.levyDueYTD.get, decl.payrollPeriod.get.month, decl.levyAllowanceForFullYear.get)
  
}