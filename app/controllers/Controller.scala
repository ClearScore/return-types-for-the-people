package controllers

import javax.inject._
import akka.actor.ActorSystem
import model.Model.Registration
import play.api.libs.json.{JsSuccess, Json}
import play.api.mvc._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class Controller @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc) {

  def v1 =
    Action.async(parse.json) {
      implicit req => Json.fromJson[Registration](req.body) match {
          case JsSuccess(registration, _) => service.v1.Register(registration)
            .map(r => Ok(r.toString))
          case _ => Future.successful(BadRequest("bad json"))
        }
    }

  def v2 =
    Action.async(parse.json) {
      implicit req => Json.fromJson[Registration](req.body) match {
        case JsSuccess(registration, _) => service.v2.Register(registration)
          .map(_.fold(BadRequest("None"))(r => Ok(r.toString)))
        case _ => Future.successful(BadRequest("bad json"))
      }
    }

  def v3 =
    Action.async(parse.json) {
      implicit req => Json.fromJson[Registration](req.body) match {
        case JsSuccess(registration, _) => service.v3.Register(registration)
          .map(_.fold(e => BadRequest(e.error), r => Ok(r.toString)))
        case _ => Future.successful(BadRequest("bad json"))
      }
    }

  def v4 =
    Action.async(parse.json) {
      implicit req => Json.fromJson[Registration](req.body) match {
        case JsSuccess(registration, _) => service.v4.Register(registration)
            .map(_.fold(e => BadRequest(e.toNonEmptyList.toList.map(_.error).mkString(", ")), r => Ok(r.toString)))
        case _ => Future.successful(BadRequest("bad json"))
      }
    }
}
