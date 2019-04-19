package extractor

import org.apache.spark.sql.DataFrame

trait TExtractor {
  def extract(dataFrame: DataFrame, cacheEnabled: Boolean) : DataFrame
}
