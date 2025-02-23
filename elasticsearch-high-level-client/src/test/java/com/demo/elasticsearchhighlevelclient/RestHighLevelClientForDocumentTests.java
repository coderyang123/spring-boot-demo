package com.demo.elasticsearchhighlevelclient;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Map;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestHighLevelClientForDocumentTests {

  @Autowired RestHighLevelClient restHighLevelClient;

  /**
   * 创建文档
   *
   * @throws IOException IO异常
   */
  @Test
  void testCreateDocument() throws IOException {
    IndexRequest indexRequest = new IndexRequest("products");
    indexRequest
        .id("2")
        .source(
            "{\n"
                + "  \"title\": \"iphone13\",\n"
                + "  \"price\": 5999.99,\n"
                + "  \"created_at\": \"2021-09-16\",\n"
                + "  \"description\": \"iPhone 13屏幕采用6.1英寸OLED屏幕。\"\n"
                + "}",
            XContentType.JSON);
    IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    System.out.println(indexResponse.status());
    restHighLevelClient.close();
  }

  /**
   * 更新文档
   *
   * @throws IOException IO异常
   */
  @Test
  void testUpdateDocument() throws IOException {
    UpdateRequest updateRequest = new UpdateRequest("products", "1");
    updateRequest.doc("{\"title\":\"好月亮\"}", XContentType.JSON);
    UpdateResponse updateResponse =
        restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
    System.out.println(updateResponse.status());
    restHighLevelClient.close();
  }

  /**
   * 删除文档
   *
   * @throws IOException IO异常
   */
  @Test
  void testDeleteDocument() throws IOException {
    DeleteRequest deleteRequest = new DeleteRequest("products", "1");
    DeleteResponse deleteResponse =
        restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
    System.out.println(deleteResponse.status());
    restHighLevelClient.close();
  }

  /**
   * 根据ID查询文档
   *
   * @throws IOException IO异常
   */
  @Test
  void testQueryDocument() throws IOException {
    GetRequest getRequest = new GetRequest("products", "1");
    GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
    System.out.println(getResponse.getSourceAsString());
    restHighLevelClient.close();
  }

  /**
   * 查询所有文档
   *
   * @throws IOException IO异常
   */
  @Test
  void testQueryDocument2() throws IOException {
    SearchRequest searchRequest = new SearchRequest("products");
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    sourceBuilder.query(QueryBuilders.matchAllQuery());
    searchRequest.source(sourceBuilder);
    SearchResponse searchResponse =
        restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    System.out.println(searchResponse.getHits().getTotalHits().value);

    SearchHit[] hits = searchResponse.getHits().getHits();
    for (SearchHit hit : hits) {
      System.out.println(hit.getSourceAsString());
    }
    restHighLevelClient.close();
  }

  /**
   * 综合查询文档 1.分页查询 2.排序查询 3.过滤查询结果字段 4.高亮查询 5.模糊查询
   *
   * @throws IOException IO异常
   */
  @Test
  void testQueryDocument3() throws IOException {
    SearchRequest searchRequest = new SearchRequest("products");
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    sourceBuilder
        // 分页设置
        .from(0)
        .size(2)
        // 排序设置
        .sort("price", SortOrder.DESC)
        // 过滤查询结果字段
        .fetchSource(new String[] {"price"}, new String[] {})
        // 高亮设置（这里的高亮查询不太严谨，高亮查询得应用在可分词的字段上更合适，新建索引时得指定中文分词）
        .highlighter(
            new HighlightBuilder()
                .field("title")
                .requireFieldMatch(false)
                .preTags("<span style='color:red;'>")
                .postTags("</span>"))
        // 查询条件设置
        .query(QueryBuilders.fuzzyQuery("title", "test"));
    searchRequest.source(sourceBuilder);
    SearchResponse searchResponse =
        restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    System.out.println("总条数: " + searchResponse.getHits().getTotalHits().value);

    SearchHit[] hits = searchResponse.getHits().getHits();
    for (SearchHit hit : hits) {
      System.out.println("结果：" + hit.getSourceAsString());
      Map<String, HighlightField> highlightFields = hit.getHighlightFields();
      highlightFields.forEach(
          (k, v) -> System.out.println("key: " + k + " value: " + v.fragments()[0]));
    }
    restHighLevelClient.close();
  }

  /**
   * 过滤查询
   *
   * @throws IOException IO异常
   */
  @Test
  void testQueryDocument4() throws IOException {
    SearchRequest searchRequest = new SearchRequest("products");
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    sourceBuilder
        .query(QueryBuilders.matchAllQuery())
        .postFilter(QueryBuilders.termQuery("price", "8999.99"));

    searchRequest.source(sourceBuilder);
    SearchResponse searchResponse =
        restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    System.out.println("总条数: " + searchResponse.getHits().getTotalHits().value);
    System.out.println("得分: " + searchResponse.getHits().getMaxScore());

    SearchHit[] hits = searchResponse.getHits().getHits();
    for (SearchHit hit : hits) {
      System.out.println(hit.getSourceAsString());
    }

    restHighLevelClient.close();
  }

  /**
   * 时间范围查询（设定固定时区）
   *
   * @throws IOException IO异常
   */
  @Test
  void testQueryDocument5() throws IOException {
    SearchRequest searchRequest = new SearchRequest("products");
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    sourceBuilder.query(
        QueryBuilders.rangeQuery("created_at")
            .from("2021-09-01")
            .to("2021-09-30")
            .timeZone(ZoneId.SHORT_IDS.get("CTT")));

    searchRequest.source(sourceBuilder);
    SearchResponse searchResponse =
        restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    System.out.println("总条数: " + searchResponse.getHits().getTotalHits().value);
    System.out.println("得分: " + searchResponse.getHits().getMaxScore());

    SearchHit[] hits = searchResponse.getHits().getHits();
    for (SearchHit hit : hits) {
      System.out.println(hit.getSourceAsString());
    }

    restHighLevelClient.close();
  }
}
