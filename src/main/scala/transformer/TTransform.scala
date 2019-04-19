package transformer

import org.apache.spark.sql.DataFrame

trait TTransform {
  def apply(dataFrame: DataFrame): DataFrame
}
