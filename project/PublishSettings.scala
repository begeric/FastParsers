import sbt._
import Keys._

object PublishSettings {
	def publishSettings = Seq(
		organization := "com.github.begeric",
		publishMavenStyle := true,
	    publishOnlyWhenOnMaster := publishOnlyWhenOnMasterImpl.value,
	    publishTo <<= version { v: String =>
	      val nexus = "https://oss.sonatype.org/"
	      if (v.trim.endsWith("SNAPSHOT"))
	        Some("snapshots" at nexus + "content/repositories/snapshots")
	      else
	        Some("releases" at nexus + "service/local/staging/deploy/maven2")
	    },
	    pomIncludeRepository := { x => false },
	    publishArtifact in Compile := false,
	    publishArtifact in Test := false,
	    pomExtra := (
	      <url>https://github.com/begeric/FastParsers</url>
	      <inceptionYear>2014</inceptionYear>
	      <licenses>
	        <license>
	          <name>MIT</name>
	          <url>https://github.com/begeric/FastParsers/blob/experiment/LICENSE.txt</url>
	          <distribution>repo</distribution>
	        </license>
	      </licenses>
	      <scm>
	        <url>git:github.com/begeric/FastParsers.git</url>
	        <connection>scm:git:git://github.com/begueric/FastParsers.git</connection>
	      </scm>
	      <issueManagement>
	        <system>GitHub</system>
	        <url>https://github.com/begueric/FastParsers/issues</url>
	      </issueManagement>
	    ),
	    publishArtifact in (Compile, packageDoc) := false
	)

 lazy val publishOnlyWhenOnMaster = taskKey[Unit]("publish task for Travis (don't publish when building pull requests, only publish when the build is triggered by merge into master)")
  def publishOnlyWhenOnMasterImpl = Def.taskDyn {
    import scala.util.Try
    val travis   = Try(sys.env("TRAVIS")).getOrElse("false") == "true"
    val pr       = Try(sys.env("TRAVIS_PULL_REQUEST")).getOrElse("false") != "false"
    val branch   = Try(sys.env("TRAVIS_BRANCH")).getOrElse("??")
    val snapshot = version.value.trim.endsWith("SNAPSHOT")
    (travis, pr, branch, snapshot) match {
      case (true, false, "master", true) => publish
      case _                             => Def.task ()
    }
  }

  lazy val publishableSettings = Seq(
    publishArtifact in Compile := true,
    publishArtifact in Test := false,
    credentials ++= {
      val mavenSettingsFile = System.getenv("MAVEN_SETTINGS_FILE")
      if (mavenSettingsFile != null) {
        println("Loading Sonatype credentials from " + mavenSettingsFile)
        try {
          import scala.xml._
          val settings = XML.loadFile(mavenSettingsFile)
          def readServerConfig(key: String) = (settings \\ "settings" \\ "servers" \\ "server" \\ key).head.text
          Some(Credentials(
            "Sonatype Nexus Repository Manager",
            "oss.sonatype.org",
            readServerConfig("username"),
            readServerConfig("password")
          ))
        } catch {
          case ex: Exception =>
            println("Failed to load Maven settings from " + mavenSettingsFile + ": " + ex)
            None
        }
      } else {
        println("Do not load Credentials")
        None
      }
    }.toList
  )
}