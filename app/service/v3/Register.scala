package service.v3

import java.time.ZonedDateTime

import model.Model._

import scala.concurrent.Future
import scala.util.{Random, Try}
import scala.concurrent.ExecutionContext.Implicits.global

object Register {

  private def validateName(userName: String): Either[Error, String] =
    Either.cond(userName.matches("^[a-zA-Z]+$"), userName, InvalidName)

  private def validatePassword(password: String): Either[Error, String] =
    Either.cond(password.length > 6, password, PasswordDoesNotMeetCriteria)

  private def validateEmail(email: String): Either[Error, String] =
    Either.cond(email.contains("@"), email, EmailDoesNotMeetCriteria)

  private def validateAge(age: Int): Either[Error, Int] =
    Either.cond(age >= 18, age, InvalidAge)

  private def validate(name: String, password: String, email: String, age: Int): Either[Error, MemberAccount] =
    for {
      validatedName <- validateName(name)
      _             <- validateAge(age)
      _             <- validateEmail(email)
      _             <- validatePassword(password)
    } yield MemberAccount(validatedName, Random.nextInt(10000), ZonedDateTime.now.plusYears(1))

  def apply(registration: Registration): Future[Either[Error, MemberAccount]] =
    Future.fromTry(Try(validate(registration.name, registration.password, registration.email, registration.age))))

}
