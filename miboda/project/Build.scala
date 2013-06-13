import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "miboda"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    "leodagdag"                         % "play2-morphia-plugin_2.9.1"  % "0.0.6",
  //    "com.github.jmkgreen.morphia" % "morphia" % "1.2.2",
     "org.mongodb" % "mongo-java-driver" % "2.7.3",
    "com.google.code.morphia" % "morphia" % "0.99", 
    "com.google.code.morphia" % "morphia-logging-slf4j" % "0.99", 
     "com.github.jmkgreen.morphia" % "morphia-logging-slf4j" % "1.2.2",
       ("com.github.jmkgreen.morphia" % "morphia-logging-slf4j" % "1.2.2" % "compile" notTransitive()),
       ("com.github.jmkgreen.morphia" % "morphia-validation" % "1.2.2" % "compile" notTransitive()),
     javaCore,
    javaJdbc,
    javaEbean
  )

    
  val main = play.Project(appName, appVersion, appDependencies).settings(
		  resolvers ++= Seq(DefaultMavenRepository, Resolvers.githubRepository, Resolvers.morphiaRepository, Resolvers.typesafeRepository)
   )
  
  	object Resolvers { 
      val githubRepository = "LeoDagDag repository" at "http://leodagdag.github.com/repository/"
      val dropboxRepository = "Dropbox repository" at "http://dl.dropbox.com/u/18533645/repository/"
      val morphiaRepository = "Morphia repository" at "http://morphia.googlecode.com/svn/mavenrepo/"
      val typesafeRepository = "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"	
  }

}
