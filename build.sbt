name := """lightning"""

version := "1"

lazy val root = (project in file(".")).enablePlugins(PlayScala, SwaggerPlugin)

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  guice,
  "org.webjars" % "swagger-ui" % "2.2.0",
  "org.typelevel" %% "cats-core" % "2.0.0"
)

swaggerDomainNameSpaces := Seq("models")