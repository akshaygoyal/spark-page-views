package extractor

import org.apache.spark.sql.DataFrame
import org.apache.spark.storage.StorageLevel
import spark.ContextHandler.sparkSession.sqlContext.implicits._
import utils.{Constants, JsonUtils}

class PageDetailsExtractor extends TExtractor {
  override def extract(dataFrame: DataFrame, cacheEnabled: Boolean): DataFrame = {

    val detailsDF = dataFrame.select(Constants.PROFILE_ID, Constants.PROPERTIES)
                              .map(row => {
                                val pageDetails = JsonUtils.getPageDetails(row.getString(1))
                                (row.getString(0), pageDetails.pageUrl, pageDetails.pageIsFirstEntry)
                              })
                              .select($"_1".alias(Constants.PROFILE_ID), $"_2".alias(Constants.PAGE_URL), $"_3".alias(Constants.PAGE_IS_FIRST_ENTRY))
                              .orderBy(Constants.PROFILE_ID, Constants.PAGE_URL)

    if (cacheEnabled) {
      detailsDF.persist(StorageLevel.MEMORY_AND_DISK)
    }

    detailsDF
  }
}
