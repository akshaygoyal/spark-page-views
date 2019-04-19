package spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object ContextHandler {

  val sparkSession = SparkSession
                          .builder
                          .master("local")
                          .config(new SparkConf)
                          .config("spark.redis.host", "127.0.0.1")
                          .config("spark.redis.port", "6379")
                          .getOrCreate
}