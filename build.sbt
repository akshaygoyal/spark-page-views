
lazy val root = (project in file(".")).
  settings(
    name := "page-views",
    version := "0.1",
    scalaVersion := "2.11.8",
    assemblySettings,
    libraryDependencies ++= Seq(
                            dependencies.sparkCore,
                            dependencies.sparkSql,
                            dependencies.json4s,
                            dependencies.sparkRedis,
                            dependencies.sparkStreaming
                            )
  )



lazy val dependencies =
  new {
    val sparkV =  "2.3.1"
    val json4sV = "3.2.11"
    val sparkRedisV = "2.3.0"

    val sparkCore = "org.apache.spark" %% "spark-core" % sparkV % "provided"
    val sparkSql = "org.apache.spark" %% "spark-sql" % sparkV % "provided"
    val sparkStreaming = "org.apache.spark" %% "spark-streaming" % sparkV % "provided"
    val json4s = "org.json4s" %% "json4s-jackson" % json4sV % "provided"
    val sparkRedis = "com.redislabs" % "spark-redis" % sparkRedisV
  }


lazy val assemblySettings = Seq(
  assemblyJarName in assembly := name.value + ".jar",
  assemblyMergeStrategy in assembly := {
    case m if m.toLowerCase.endsWith("manifest.mf") => MergeStrategy.discard
    case m if m.toLowerCase.matches("meta-inf.*\\.sf$") => MergeStrategy.discard
    case "log4j.properties" => MergeStrategy.discard
    case m if m.toLowerCase.startsWith("meta-inf/services/") =>
      MergeStrategy.filterDistinctLines
    case "reference.conf" => MergeStrategy.concat
    case _ => MergeStrategy.first
  }
)