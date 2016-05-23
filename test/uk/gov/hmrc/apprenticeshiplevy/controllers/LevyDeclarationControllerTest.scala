package uk.gov.hmrc.apprenticeshiplevy.controllers

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{FlatSpec, Matchers}
import play.api.test.FakeRequest

class LevyDeclarationControllerTest extends FlatSpec with Matchers with ScalaFutures {

  "declarations" should "return 200" in {
    LevyDeclarationController.declarations("123/AB12345", None)(FakeRequest()).futureValue.header.status shouldBe 200
  }

}
