package transformer
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.collect_list
import utils.Constants

class PageSetTransform extends TTransform {

  override def apply(dataFrame: DataFrame): DataFrame = {
    val pageSetDF = dataFrame.groupBy(Constants.PROFILE_ID, Constants.PAGE_IS_FIRST_ENTRY)
                              .agg(collect_list(Constants.PAGE_URL).alias(Constants.PAGE_SET))
    pageSetDF
  }
}
