package utils

import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import com.fasterxml.jackson.databind.node.ObjectNode
import data.PageDetails
import org.apache.log4j.{Level, LogManager}
import org.codehaus.jackson.JsonProcessingException

object JsonUtils {

  val logger = LogManager.getRootLogger

  def getJsonValue(objectNode: ObjectNode, key: String) = {
    if(objectNode.get(key) != null) {
      objectNode.get(key).asText()
    } else {
      ""
    }
  }

  def getPageDetails(value: String): PageDetails = {
    var node: JsonNode = null
    try {
      node = new ObjectMapper().reader().readTree(value)
      val objectNode = node.asInstanceOf[ObjectNode]
      val pageUrl = getJsonValue(objectNode, Constants.PAGE_URL)
      val isFirstEntry = getJsonValue(objectNode, Constants.PAGE_IS_FIRST_ENTRY)
      return new PageDetails(pageUrl, isFirstEntry)
    } catch {
      case e: JsonProcessingException =>
        logger.error(e.getMessage, e)
        throw new RuntimeException("Error while reading json : " + value, e)
    }
    null
  }

}
