package reader

import java.util.Properties

import org.apache.spark.sql.DataFrame
import spark.ContextHandler
import utils.Constants

/*
    Input reader from any file system. This expects file path with appropriate filesystem prefix (s3:// or hdfs:// etc.)
 */
class FileSystemReader extends TReader {

  override def read(properties: Properties): DataFrame = {

    val path = properties.getProperty(Constants.READ_FILE_PATH)
    val spark = ContextHandler.sparkSession
    val fileDf = spark.read.parquet(path).filter(_ != null)
    fileDf

  }
}
