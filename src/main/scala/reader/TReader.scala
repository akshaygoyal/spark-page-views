package reader

import java.util.Properties

import org.apache.spark.sql.DataFrame

trait TReader {
  def read(properties: Properties): DataFrame
}
