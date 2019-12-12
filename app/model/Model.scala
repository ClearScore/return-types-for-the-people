package model

import java.time.ZonedDateTime
import play.api.libs.json.{Format, Json}

object Model {
  case class Registration(name: String, age: Int, email: String, password: String)

  object Registration{
    implicit val fmt: Format[Registration] = Json.format[Registration]
  }

  case class MemberAccount(name: String, accountNo: Int, expiryDate: ZonedDateTime)

  object MemberAccount{
    implicit val fmt: Format[MemberAccount] = Json.format[MemberAccount]
  }

  sealed trait Error {
    def error: String
  }

  case object InvalidName extends Error {
    def error: String = "Name must not have special characters"
  }

  case object PasswordDoesNotMeetCriteria extends Error {
    def error: String = "Password must be at least 6 characters long"
  }

  case object EmailDoesNotMeetCriteria extends Error {
    def error: String = "Email must be in correct format"
  }

  case object InvalidAge extends Error {
    def error: String = "Must be over 18"
  }
}
