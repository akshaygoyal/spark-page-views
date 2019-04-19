package transformer
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{col, size}
import utils.Constants

class PageCountTransform extends TTransform {

  override def apply(dataFrame: DataFrame): DataFrame = {
    dataFrame.withColumn(Constants.SIZE, size(col(Constants.PAGE_SET))).drop(Constants.PAGE_SET)
  }
}
