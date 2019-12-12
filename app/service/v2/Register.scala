package service.v2

import java.time.ZonedDateTime
import model.Model._
import scala.concurrent.Future
import scala.util.Random
import scala.concurrent.ExecutionContext.Implicits.global

object Register {

  private def validate(name: String, password: String, email: String, age: Int): Option[MemberAccount] =
    if (name.matches("^[a-zA-Z]+$") && email.contains("@") && password.length > 6 && age >= 18) {
      Option(MemberAccount(name, Random.nextInt(10000), ZonedDateTime.now.plusYears(1)))
    } else {
      None
    }

  def apply(registration: Registration): Future[Option[MemberAccount]] =
    Future(validate(registration.name, registration.password, registration.email, registration.age))

}
