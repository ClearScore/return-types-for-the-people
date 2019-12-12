package service.v1

import java.time.ZonedDateTime
import model.Model._
import scala.concurrent.Future
import scala.util.Random
import scala.concurrent.ExecutionContext.Implicits.global

object Register {

  private def validate(name: String, password: String, email: String, age: Int): MemberAccount =
    if (name.matches("^[a-zA-Z]+$") && email.contains("@") && password.length > 6 && age >= 18) {
      MemberAccount(name, Random.nextInt(10000), ZonedDateTime.now.plusYears(1))
    } else {
      MemberAccount("", 0, null)
    }

  def apply(registration: Registration): Future[MemberAccount] =
    Future(validate(registration.name, registration.password, registration.email, registration.age))

}
