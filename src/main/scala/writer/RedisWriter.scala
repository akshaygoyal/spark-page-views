package writer

import java.util.Properties

import com.redislabs.provider.redis._
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._
import spark.ContextHandler
import utils.Constants

class RedisWriter extends TWriter {
  override def write(dataFrame: DataFrame, properties: Properties): Unit = {
    val spark = ContextHandler.sparkSession

    val df1 = dataFrame.select(concat(col(Constants.PROFILE_ID), lit("_"), col(Constants.PAGE_IS_FIRST_ENTRY)).alias(Constants.REDIS_KEY), col(Constants.SIZE))

    val rdd = df1.rdd.map(row => (row.getString(0), row.getInt(1).toString))

    spark.sparkContext.toRedisKV(rdd)
  }
}
