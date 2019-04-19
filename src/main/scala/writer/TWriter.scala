package writer

import java.util.Properties

import org.apache.spark.sql.DataFrame

trait TWriter {
  def write(dataFrame: DataFrame, properties: Properties): Unit
}
