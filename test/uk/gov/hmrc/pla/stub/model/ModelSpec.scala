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

package uk.gov.hmrc.pla.stub.model

import java.time.LocalDateTime

import uk.gov.hmrc.play.test.UnitSpec
import java.util.Random
import play.api.libs.json._

object Generator {
  import uk.gov.hmrc.domain.Generator

  val rand = new Random()
  val ninoGenerator = new Generator(rand)

  def randomNino: String = ninoGenerator.nextNino.nino.replaceFirst("MA", "AA")
  def randomProtectionID = rand.nextLong
  def randomFP16ProtectionReference=("FP16" + Math.abs(rand.nextLong)).substring(0,9) + "C"
  def randomIP16ProtectionReference=("IP16" + Math.abs(rand.nextLong)).substring(0,9) + "B"
  def randomIP14ProtectionReference=("IP14" + Math.abs(rand.nextLong)).substring(0,9) + "A"
  def randomOlderProtectionReference=("A" +  Math.abs(rand.nextLong)).substring(0,5) + "A"
}

object ProtectionTestData {

  import Generator._

  val currDate = LocalDateTime.now.format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE)
  val currTime = LocalDateTime.now.format(java.time.format.DateTimeFormatter.ISO_LOCAL_TIME)

  val openFP2016=Protection(
    nino=randomNino,
    id=randomProtectionID,
    `type`=Protection.extractedType(Protection.Type.FP2016),
    status=Protection.extractedStatus(Protection.Status.Open),
    notificationID=Some(22),
    notificationMsg=None,
    protectionReference=Some(randomFP16ProtectionReference),
    version = 1,
    certificateDate = Some(currDate),
    certificateTime = Some(currTime))

  val openFP2016WithPensionDebits=Protection(
    nino=randomNino,
    id=randomProtectionID,
    `type`=Protection.extractedType(Protection.Type.FP2016),
    status=Protection.extractedStatus(Protection.Status.Open),
    notificationID=Some(22),
    notificationMsg=None,
    protectionReference=Some(randomFP16ProtectionReference),
    version = 1,
    pensionDebits=Some(List(PensionDebit(100000.0,"29-8-2016"), PensionDebit(250000.0,"12-03-2017"))),
    certificateDate = Some(currDate),
    certificateTime = Some(currTime))

  val openIP2016=Protection(
    nino=randomNino,
    id=randomProtectionID,
    `type`=Protection.extractedType(Protection.Type.IP2016),
    status=Protection.extractedStatus(Protection.Status.Open),
    notificationID=Some(12),
    notificationMsg=None,
    protectionReference=Some(randomFP16ProtectionReference),
    version = 1,
    certificateDate = Some(currDate),
    certificateTime = Some(currTime))

  val openFP2014=Protection(
    nino=randomNino,
    id=randomProtectionID,
    `type`=Protection.extractedType(Protection.Type.FP2014),
    status=Protection.extractedStatus(Protection.Status.Open),
    notificationID=None,
    notificationMsg=None,
    protectionReference=Some(randomFP16ProtectionReference),
    version = 1,
    certificateDate = Some(currDate),
    certificateTime = Some(currTime))

  val openIP2014=Protection(
    nino=randomNino,
    id=randomProtectionID,
    `type`=Protection.extractedType(Protection.Type.IP2014),
    status=Protection.extractedStatus(Protection.Status.Open),
    notificationID=None,
    notificationMsg=None,
    protectionReference=Some(randomFP16ProtectionReference),
    version = 1,
    certificateDate = Some(currDate),
    certificateTime = Some(currTime))

  val openPrimary=Protection(
    nino=randomNino,
    id=randomProtectionID,
    `type`=Protection.extractedType(Protection.Type.Primary),
    status=Protection.extractedStatus(Protection.Status.Open),
    notificationID=None,
    notificationMsg=None,
    protectionReference=Some(randomOlderProtectionReference),
    version = 1,
    certificateDate = Some(currDate),
    certificateTime = Some(currTime))

  val openFixed=Protection(
    nino=randomNino,
    id=randomProtectionID,
    `type`=Protection.extractedType(Protection.Type.Fixed),
    status=Protection.extractedStatus(Protection.Status.Open),
    notificationID=None,
    notificationMsg=None,
    protectionReference=Some(randomOlderProtectionReference),
    version = 1,
    certificateDate = Some(currDate),
    certificateTime = Some(currTime))

  val openEnhanced=Protection(
    nino=randomNino,
    id=randomProtectionID,
    `type`=Protection.extractedType(Protection.Type.Enhanced),
    status=Protection.extractedStatus(Protection.Status.Open),
    notificationID=None,
    notificationMsg=None,
    protectionReference=Some(randomOlderProtectionReference),
    version = 1,
    certificateDate = Some(currDate),
    certificateTime = Some(currTime))

  val dormantPrimary=Protection(
    nino=randomNino,
    id=randomProtectionID,
    `type`=Protection.extractedType(Protection.Type.Primary),
    status=Protection.extractedStatus(Protection.Status.Dormant),
    notificationID=None,
    notificationMsg=None,
    protectionReference=Some(randomOlderProtectionReference),
    version = 1,
    certificateDate = Some(currDate),
    certificateTime = Some(currTime))

  val dormantEnhanced=Protection(
    nino=randomNino,
    id=randomProtectionID,
    `type`=Protection.extractedType(Protection.Type.Enhanced),
    status=Protection.extractedStatus(Protection.Status.Dormant),
    notificationID=None,
    notificationMsg=None,
    protectionReference=Some(randomOlderProtectionReference),
    version = 1,
    certificateDate = Some(currDate),
    certificateTime = Some(currTime))

  val withdrawnPrimary=Protection(
    nino=randomNino,
    id=randomProtectionID,
    `type`=Protection.extractedType(Protection.Type.Enhanced),
    status=Protection.extractedStatus(Protection.Status.Withdrawn),
    notificationID=None,
    notificationMsg=None,
    protectionReference=Some(randomOlderProtectionReference),
    version=1,
    certificateDate = Some(currDate),
    certificateTime = Some(currTime))

  val rejected = Protection(
    nino=randomNino,
    id=randomProtectionID,
    `type`=Protection.extractedType(Protection.Type.IP2016),
    status=Protection.extractedStatus(Protection.Status.Rejected),
    notificationID=Some(21),
    notificationMsg=None,
    protectionReference=None,
    version = 1)
}

class ProtectionsFormatSpec extends UnitSpec {

  import ProtectionTestData._

  "FP2016 json read and write functions" should {
    "be an isomorphic pair" in {
      val json = Json.toJson(openFP2016)
      val parsedProtection = Json.fromJson[Protection](json)
      parsedProtection.get shouldEqual openFP2016
    }
  }

  "FP2016 with pension debits json read and write functions" should {
    "be an isomorphic pair" in {
      val json = Json.toJson(openFP2016WithPensionDebits)
      val parsedProtection = Json.fromJson[Protection](json)
      parsedProtection.get shouldEqual openFP2016WithPensionDebits
    }
  }


  "IP2016 json read and write functions" should {
    "be an isomorphic pair" in {
      val json = Json.toJson(openIP2016)
      val parsedProtection = Json.fromJson[Protection](json)
      parsedProtection.get shouldEqual openIP2016
    }
  }

  "IP2014 json read and write functions" should {
    "be an isomorphic pair" in {
      val json = Json.toJson(openIP2014)
      val parsedProtection = Json.fromJson[Protection](json)
      parsedProtection.get shouldEqual openIP2014
    }
  }

  "FP2014 json read and write functions" should {
    "be an isomorphic pair" in {
      val json = Json.toJson(openFP2014)
      val parsedProtection = Json.fromJson[Protection](json)
      parsedProtection.get shouldEqual openFP2014
    }
  }

  "Primary protection json read and write functions" should {
    "be an isomorphic pair" in {
      val json = Json.toJson(openPrimary)
      val parsedProtection = Json.fromJson[Protection](json)
      parsedProtection.get shouldEqual openPrimary
    }
  }

  "Dormant enhanced protection json read and write functions" should {
    "be an isomorphic pair" in {
      val json = Json.toJson(dormantEnhanced)
      val parsedProtection = Json.fromJson[Protection](json)
      parsedProtection.get shouldEqual dormantEnhanced
    }
  }
}

object ProtectionApplicationTestData {

  import Generator._

  import CreateLTAProtectionRequest.ProtectionDetails
  val Fp2016 = ProtectionDetails(
    `type`=Protection.extractedType(Protection.Type.FP2016),
    relevantAmount=Some(1250000),
    preADayPensionInPayment=Some(0),
    postADayBCE=Some(0),
    uncrystallisedRights=Some(0),
    pensionDebits=Some(List()),
    nonUKRights= Some(0))

  val Ip2014 = ProtectionDetails(
    `type`=Protection.extractedType(Protection.Type.IP2014),
    relevantAmount=Some(1250000),
    preADayPensionInPayment=Some(0),
    postADayBCE=Some(0),
    uncrystallisedRights=Some(0),
    pensionDebits=None,
    nonUKRights= Some(0))


  val Ip2016 = ProtectionDetails(
    `type`=Protection.extractedType(Protection.Type.IP2016),
    relevantAmount=Some(1250000),
    preADayPensionInPayment=Some(0),
    postADayBCE=Some(0),
    uncrystallisedRights=Some(0),
    pensionDebits=None,
    nonUKRights= Some(0))

  val Fp2014 = ProtectionDetails(
    `type`=Protection.extractedType(Protection.Type.FP2014),
    relevantAmount=Some(1250000),
    preADayPensionInPayment=Some(0),
    postADayBCE=Some(0),
    uncrystallisedRights=Some(0),
    pensionDebits=None,
    nonUKRights= Some(0))
}

class ProtectionApplicationMethodsSpec extends UnitSpec {

  import ProtectionApplicationTestData._
  import Protection.Type._

  "Calling the requestedType method on Fp2016" should {
    "return FP2016" in {
      Fp2016.requestedType shouldBe Some(FP2016)
    }
  }

  "Calling the requestedType method on Ip2014" should {
    "return IP2014" in {
      Ip2014.requestedType shouldBe Some(IP2014)
    }
  }

  "Calling the requestedType method on Ip2016" should {
    "return IP2016" in {
      Ip2016.requestedType shouldBe Some(IP2016)
    }
  }

  "Calling the requestedType method on Fp2014" should {
    "return None" in {
      Fp2014.requestedType shouldBe None
    }
  }

}
