import java.util.Properties

import extractor.PageDetailsExtractor
import org.apache.log4j.{Level, LogManager}
import org.apache.spark.sql.DataFrame
import reader.FileSystemReader
import transformer.{PageCountTransform, PageSetTransform}
import utils.Constants
import writer.{FileSystemWriter, RedisWriter}


object Main {

  val logger = LogManager.getRootLogger
  logger.setLevel(Level.INFO)

  def main(args: Array[String]): Unit = {

    // Read
    var properties: Properties = new Properties()
    properties.put(Constants.READ_FILE_PATH, args(0))
    val reader = new FileSystemReader
    val fileDf = reader.read(properties)

    // Extract
    val extractor = new PageDetailsExtractor
    val detailsDF = extractor.extract(fileDf, true)

    // Transform
    val pageSetTransform = new PageSetTransform
    val page_set_result = pageSetTransform.apply(detailsDF)

    val pageCountTransform = new PageCountTransform
    val page_count_result = pageCountTransform.apply(page_set_result)

    // Write to files
    writeToFile(page_set_result, args(1))
    writeToFile(page_count_result, args(2))

    // Write to redis
    writeToRedis(page_count_result)

  }

  def writeToFile(dataframe: DataFrame, file: String) = {
    val properties = new Properties()
    properties.put(Constants.WRITE_FILE_PATH, file)

    val fileSystemWriter = new FileSystemWriter
    fileSystemWriter.write(dataframe, properties)
  }


  def writeToRedis(page_count_result: DataFrame): Unit = {
    val redisWriter = new RedisWriter
    redisWriter.write(page_count_result, new Properties)
  }


}
