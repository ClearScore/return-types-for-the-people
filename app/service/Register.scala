package service

import model.Model.{Error, MemberAccount, Registration}
import service.v4.Register.ValidationResult
import scala.concurrent.Future

trait Register {
  def registerV1(registration: Registration): Future[MemberAccount]
  def registerV2(registration: Registration): Future[Option[MemberAccount]]
  def registerV3(registration: Registration): Future[Either[Error, MemberAccount]]
  def registerV4(registration: Registration): Future[ValidationResult[MemberAccount]]
}
