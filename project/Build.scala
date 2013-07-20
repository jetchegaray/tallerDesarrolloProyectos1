import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "miBoda"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
     "com.github.jmkgreen.morphia" % "morphia" % "1.2.3",
     "com.github.jmkgreen.morphia" % "morphia-logging-slf4j" % "1.2.3",
     "org.mongodb" % "mongo-java-driver" % "2.11.2",
   //   "com.github.jmkgreen.morphia" % "validation" % "0.99",
    javaCore,
    javaJdbc,
    javaEbean
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Typesafe snapshots
    resolvers += "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",
    resolvers += "Maven repository" at "http://morphia.googlecode.com/svn/mavenrepo/",
    resolvers += "MongoDb Java Driver Repository" at "http://repo1.maven.org/maven2/org/mongodb/mongo-java-driver/"
  )

}
