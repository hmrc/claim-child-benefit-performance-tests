/*
 * Copyright 2022 HM Revenue & Customs
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

package uk.gov

import uk.gov.hmrc.Page.{ContentPage, DownloadPdf, FormPage}
import uk.gov.hmrc.performance.conf.ServicesConfiguration

import java.time.LocalDate

package object hmrc extends ServicesConfiguration {

  val baseUrl: String = baseUrlFor("claim-child-benefit-frontend")
  val route: String = "/fill-online/claim-child-benefit"

  private val applicantDob: LocalDate = LocalDate.now.minusYears(21)
  private val childDob: LocalDate = LocalDate.now.minusYears(1)

  private val answerYes = "value" -> "true"
  private val answerNo = "value" -> "false"

  private def fieldsForDate(date: LocalDate): List[(String, String)] = List(
    "value.day"   -> date.getDayOfMonth.toString,
    "value.month" -> date.getMonthValue.toString,
    "value.year"  -> date.getYear.toString
  )

  val journey: List[Page] = List(
    ContentPage("Navigate To Start Page", ""),
    FormPage("Recently Claimed Child Benefit", "recently-claimed-child-benefit", answerNo),
    FormPage("Applicant Name", "your-name", "firstName" -> "Foo", "lastName" -> "bar"),
    FormPage("Relationship Status", "relationship-status", "value" -> "single"),
    FormPage("Always Lived in UK", "always-lived-in-uk", answerYes),
    FormPage("Applicant Income", "your-income", "value" -> "belowLowerThreshold"),
    FormPage("Applicant Benefits", "your-benefits", "value[5]" -> "none"),
    FormPage("Currently Receiving Child Benefit", "currently-receiving-child-benefit", "value" -> "notClaiming"),
    FormPage("Want to Be Paid Child Benefit", "want-to-be-paid-child-benefit", answerNo),
    FormPage("Does Applicant Have Previous Family Name", "have-previous-family-name", answerNo),
    FormPage("Does Applicant Know Their NINO", "know-your-national-insurance-number", answerNo),
    FormPage("Applicant Date of Birth", "your-date-of-birth", fieldsForDate(applicantDob): _*),
    FormPage("Applicant Current Address", "your-current-uk-address", "line1" -> "1 Test Street", "town" -> "Test Town", "postcode" -> "ZZ1 1ZZ"),
    FormPage("Applicant Lived At Current Address For a Year", "lived-at-current-address-a-year", answerYes),
    FormPage("Applicant Telephone Number", "your-telephone-number", "value" -> "07777777777"),
    FormPage("Applicant Nationality", "your-nationality", "value" -> "british"),
    FormPage("Child Name", "child-name/1", "firstName" -> "Baz", "lastName" -> "Quux"),
    FormPage("Child Has Previous Name", "child-has-previous-name/1", answerNo),
    FormPage("Child Biological Sex", "child-biological-sex/1", "value" -> "female"),
    FormPage("Child Date Of Birth", "child-date-of-birth/1", fieldsForDate(childDob): _*),
    FormPage("Child Registration Country", "child-birth-registration-country/1", "value" -> "other"),
    FormPage("Adopting Through Local Authority", "adopting-through-local-authority/1", answerNo),
    FormPage("Applicant Relationship to Child", "your-relationship-to-child/1", "value" -> "birthChild"),
    FormPage("Anyone Claimed For Child Before", "anyone-claimed-for-child-before/1", answerNo),
    FormPage("Child Lives With Applicant", "child-lives-with-you/1", answerYes),
    FormPage("Child Lives With Applicant", "child-lived-with-anyone-else/1", answerNo),
    ContentPage("Check Child Details", "check-child-details/1"),
    FormPage("Add Child", "add-child", answerNo),
    FormPage("Additional Information", "additional-information", "value" -> "information"),
    ContentPage("Check Your Answers", "check-your-answers"),
    ContentPage("Next steps", "next-steps"),
    DownloadPdf("Download PDF", "print-form")
  )
}
