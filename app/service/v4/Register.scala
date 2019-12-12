package service.v4

import cats.data._
import cats.implicits._
import java.time.ZonedDateTime
import model.Model._
import scala.concurrent.Future
import scala.util.Random
import scala.concurrent.ExecutionContext.Implicits.global

object Register {

  type ValidationResult[A] = ValidatedNec[Error, A]

  private def validateName(userName: String): ValidationResult[String] =
    if(userName.matches("^[a-zA-Z]+$")) userName.validNec else InvalidName.invalidNec

  private def validatePassword(password: String): ValidationResult[String] =
    if(password.length > 6) password.validNec else PasswordDoesNotMeetCriteria.invalidNec

  private def validateEmail(email: String): ValidationResult[String] =
    if(email.contains("@")) email.validNec else EmailDoesNotMeetCriteria.invalidNec

  private def validateAge(age: Int): ValidationResult[Int] =
    if (age >= 18) age.validNec else InvalidAge.invalidNec

  private def validate(name: String, password: String, email: String, age: Int): ValidationResult[MemberAccount] =
    (validateName(name), validateAge(age), validateEmail(email), validatePassword(password))
      .mapN((name, _, _, _) => MemberAccount(name, Random.nextInt(10000), ZonedDateTime.now.plusYears(1)))

  def apply(registration: Registration): Future[ValidationResult[MemberAccount]] =
    Future(validate(registration.name, registration.password, registration.email, registration.age))

}
