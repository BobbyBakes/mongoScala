name := "untitled"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies  ++= Seq(
    // Last stable release
    "org.scalanlp" %% "breeze" % "0.13",
    "org.scalanlp" %% "breeze-natives" % "0.13",
    "org.scalanlp" %% "breeze-viz" % "0.13"
)

resolvers += "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

// https://mvnrepository.com/artifact/org.mongodb.scala/mongo-scala-driver_2.11
libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "1.2.1"

retrieveManaged := true