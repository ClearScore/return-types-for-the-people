package controllers

import akka.actor.ActorSystem
import javax.inject.{Inject, Singleton}
import play.api.mvc._
import scala.concurrent.ExecutionContext

@Singleton
class SwaggerController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc) {
  def index = Action {
    Redirect(url = "/docs/swagger-ui/index.html?url=/assets/swagger.json")
  }
}
