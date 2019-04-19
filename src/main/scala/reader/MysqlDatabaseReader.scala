package reader
import java.util.Properties

import org.apache.spark.sql.DataFrame

/*
    Placeholder for mysql db reader.
    This reader expects db connection parameters as part of Properties.
 */
class MysqlDatabaseReader extends TReader {
  override def read(properties: Properties): DataFrame = {
    null
  }
}
