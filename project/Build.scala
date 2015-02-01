import sbt._
import Keys._

object FastParsersBuild extends Build {
	import PublishSettings._

   def commonSettings = Seq(
    	version := "0.1-SNAPSHOT",
	   	scalaVersion := "2.11.5" ,
       	//scalacOptions := Seq("-optimize"),
	   	libraryDependencies ++=  Seq(
		"org.scala-lang" % "scala-compiler"  % scalaVersion.value % "provided",
		"org.scala-lang" % "scala-reflect" % scalaVersion.value 
	)
   ) ++ publishSettings


   lazy val Examples = Project(
	id = "Examples",
	base = file("Examples"),	
	dependencies = Seq(FastParsers),
	settings = commonSettings ++ Seq (
	   // include the macro classes and resources in the main jar
	   mappings in (Compile, packageBin) ++= mappings.in(FastParsers, Compile, packageBin).value,
	   // include the macro sources in the main source jar
	   mappings in (Compile, packageSrc) ++= mappings.in(FastParsers, Compile, packageSrc).value
	)
   )
	
   lazy val FastParsers = Project(
	id = "FastParsers",
	base = file("FastParsers"),	
	settings = commonSettings ++ publishableSettings ++ Seq (
		resolvers ++= Seq(
		  "Sonatype Releases" at "http://oss.sonatype.org/content/repositories/releases"
		),
		//addSbtPlugin("org.scoverage" %% "sbt-scoverage" % "0.98.0"),
			
		libraryDependencies += "org.scalatest"  % "scalatest_2.11"   % "2.2.1" % "test",
		
		libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.11.5" % "test",
		
		libraryDependencies += "com.storm-enroute" % "scalameter_2.11" % "0.6" % "test",

		testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework"),
  
    	logBuffered := false
	) 
   )
}