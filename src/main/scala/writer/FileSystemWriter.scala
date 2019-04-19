package writer

import java.util.Properties

import org.apache.spark.sql.{DataFrame, SaveMode}
import utils.Constants

class FileSystemWriter extends TWriter {

  override def write(dataFrame: DataFrame, properties: Properties): Unit = {
    val outPath = properties.getProperty(Constants.WRITE_FILE_PATH)
    dataFrame.coalesce(1).write.mode(SaveMode.Overwrite).parquet(outPath)
  }
}
