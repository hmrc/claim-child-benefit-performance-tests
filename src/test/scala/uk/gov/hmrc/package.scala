/*
 * Copyright 2023 HM Revenue & Customs
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
    FormPage("Do you want to sign in using Government Gateway?", "sign-in-to-government-gateway", answerNo),
    ContentPage("Claim Child Benefit", "task-list"),

    FormPage("Do you know your National Insurance number?", "know-your-national-insurance-number", answerNo),
    FormPage("What is your name?", "your-name", "firstName" -> "Foo", "lastName" -> "bar"),
    FormPage("Have you been known by any other last names or family names?", "have-previous-family-name", answerNo),
    FormPage("What is your date of birth?", "your-date-of-birth", fieldsForDate(applicantDob): _*),
    FormPage("What is your telephone number?", "your-telephone-number", "value" -> "07777777777"),
    FormPage("What is your nationality?", "your-nationality/1", "value" -> "British"),
    FormPage("You have added 1 nationality", "add-nationality", answerNo),
    FormPage("Where have you lived?", "where-have-you-lived", "value" -> "alwaysUk"),
    FormPage("What is your current address?", "your-current-uk-address", "line1" -> "1 Test Street", "town" -> "Test Town", "postcode" -> "ZZ1 1ZZ"),
    FormPage("Have you lived at this address for more than a year?", "lived-at-current-address-a-year", answerYes),
    FormPage("Are you a member of HM Forces or a civil servant working abroad?", "hm-forces-or-civil-servant-abroad", answerNo),
    FormPage("Are you claiming Child Benefit right now?", "currently-receiving-child-benefit", "value" -> "notClaiming"),
    ContentPage("Check your answers for this section", "check-your-details"),

    FormPage("Relationship Status", "relationship-status", "value" -> "single"),
    ContentPage("Check your answers for this section", "check-partners-details"),

    FormPage("What is the name of the child you want to claim for?", "child-name/1", "firstName" -> "Baz", "lastName" -> "Quux"),
    FormPage("Has this child been known by any other names?", "child-has-previous-name/1", answerNo),
    FormPage("What is this child's date of birth", "child-date-of-birth/1", fieldsForDate(childDob): _*),
    FormPage("What is this child's sex as written on their birth certificate?", "child-biological-sex/1", "value" -> "female"),
    FormPage("Where was this child's birth registered?", "child-birth-registration-country/1", "value" -> "other"),
    FormPage("Are you adopting or planning to adopt this child through a local authority?", "adopting-through-local-authority/1", answerNo),
    FormPage("How are you related to this child?", "your-relationship-to-child/1", "value" -> "birthChild"),
    FormPage("Have you or anyone else claimed Child Benefit for this child previously?", "anyone-claimed-for-child-before/1", answerNo),
    FormPage("Does this child live with you?", "child-lives-with-you/1", answerYes),
    FormPage("In the last year, has this child lived with anyone else that has a different address to you?", "child-lived-with-anyone-else/1", answerNo),
    ContentPage("Check your details for this child", "check-child-details/1"),
    FormPage("You have added 1 child", "add-child", answerNo),
    ContentPage("Claim Child Benefit", "task-list"),

    FormPage("What is your adjusted net income each year?", "your-income", "value" -> "belowLowerThreshold"),
    FormPage("How the tax charge works for you", "want-to-be-paid-child-benefit", answerYes),
    FormPage("Do you get any of these benefits?", "your-benefits", "value[5]" -> "none"),
    FormPage("How often do you want to be paid Child Benefit?", "how-often-want-to-be-paid", "value" -> "everyFourWeeks"),
    FormPage("Do you have a UK bank or building society account that Child Benefit can be paid to?", "have-suitable-bank-account", answerYes),
    FormPage("Are you the account holder?", "bank-account-holder", "value" -> "applicant"),
    FormPage("What bank account or building society details do you know?", "account-type", "value" -> "sortCodeAndAccountNumber"),
    FormPage("What are the bank or building society account details that Child Benefit can be paid to?", "bank-account-details", "firstName" -> "a", "lastName" -> "b", "sortCode" -> "00-11-22", "accountNumber" -> "123456"),
    ContentPage("Check your answers for this section", "check-payment-details"),

    FormPage("Review and send your application", "task-list"),
    FormPage("Declaration", "declaration"),
    ContentPage("Your Child Benefit claim form is ready to be posted", "next-steps")
  )
}
