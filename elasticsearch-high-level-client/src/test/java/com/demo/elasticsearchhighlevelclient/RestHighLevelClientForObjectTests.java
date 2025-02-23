package com.demo.elasticsearchhighlevelclient;

import com.demo.elasticsearchhighlevelclient.domain.entity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.*;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class RestHighLevelClientForObjectTests {
  @Autowired RestHighLevelClient restHighLevelClient;

  /**
   * 创建文档
   *
   * @throws IOException IO异常
   */
  @Test
  void testCreateDocument() throws IOException {
    Product product = new Product();
    product.setId(2);
    product.setTitle("test2");
    product.setPrice(0.2D);
    product.setDescription("test2");

    // 录入ES
    IndexRequest indexRequest = new IndexRequest("products");
    indexRequest
        .id(product.getId().toString())
        .source(new ObjectMapper().writeValueAsString(product), XContentType.JSON);

    IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    System.out.println(indexResponse.status());
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
    Product product = new ObjectMapper().readValue(getResponse.getSourceAsString(), Product.class);

    System.out.println(product);
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
      Product product = new ObjectMapper().readValue(hit.getSourceAsString(), Product.class);
      System.out.println(product);
    }
    restHighLevelClient.close();
  }

  /**
   * 综合查询文档
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

    List<Product> productList = new ArrayList<>();
    SearchHit[] hits = searchResponse.getHits().getHits();
    for (SearchHit hit : hits) {
      Product product = new ObjectMapper().readValue(hit.getSourceAsString(), Product.class);

      // 处理高亮
      Map<String, HighlightField> highlightFields = hit.getHighlightFields();
      if (highlightFields.containsKey("title")) {
        product.setTitle(highlightFields.get("title").fragments()[0].toString());
      }
      productList.add(product);
    }
    productList.forEach(System.out::println);

    restHighLevelClient.close();
  }

  /**
   * 查询多个指定ID的文档是否存在
   *
   * @throws IOException IO异常
   */
  @Test
  void testMultiGetRequest() throws IOException {
    String indexName = "my-index";
    MultiGetRequest request = new MultiGetRequest();
    request.add(new MultiGetRequest.Item(indexName, "1"));
    request.add(new MultiGetRequest.Item(indexName, "2"));
    request.add(new MultiGetRequest.Item(indexName, "3"));
    request.add(new MultiGetRequest.Item(indexName, "33"));

    MultiGetResponse multiGetResponse = restHighLevelClient.mget(request, RequestOptions.DEFAULT);
    List<String> list =
        Arrays.stream(multiGetResponse.getResponses())
            .filter(response -> response.getResponse().isExists())
            .map(MultiGetItemResponse::getId)
            .collect(Collectors.toList());
    list.forEach(id -> log.info("存在的文档ID：{}", id));
  }

  /**
   * 组合查询示例
   *
   * @throws IOException IO异常
   */
  @Test
  void testBoolRequest() throws Exception {
    String indexName = "my-index";
    // 构建bool查询
    BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
    boolQuery.must(QueryBuilders.termQuery("field1", "value1"));
    boolQuery.mustNot(QueryBuilders.termQuery("field2", "value2"));
    boolQuery.should(QueryBuilders.termQuery("field3", "value3"));
    boolQuery.filter(QueryBuilders.rangeQuery("field4").gte("value4"));

    // 构建查询请求
    SearchRequest searchRequest = new SearchRequest(indexName);
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(boolQuery);
    searchRequest.source(searchSourceBuilder);

    // 执行查询请求
    SearchResponse searchResponse =
        restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    List<Product> list =
        Arrays.stream(searchResponse.getHits().getHits())
            .map(
                hit -> {
                  try {
                    return new ObjectMapper().readValue(hit.getSourceAsString(), Product.class);
                  } catch (JsonProcessingException e) {
                    log.error("结果解析错误，data：{}，error：{}", hit.getSourceAsString(), e);
                    return null;
                  }
                })
            .collect(Collectors.toList());
    list.forEach(product -> log.info("存在的文档：{}", product));
  }

  /**
   * 分页查询示例
   *
   * @throws IOException IO异常
   */
  @Test
  void testBoolRequestByPage() throws Exception {
    String indexName = "my-index";
    int from = 0;
    int size = 10;
    SearchRequest searchRequest = new SearchRequest(indexName);
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.matchAllQuery());
    searchSourceBuilder.from(from);
    searchSourceBuilder.size(size);
    searchRequest.source(searchSourceBuilder);

    // 查询
    SearchResponse searchResponse =
        restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    SearchHits hits = searchResponse.getHits();

    // 总记录数
    long totalHits = hits.getTotalHits().value;

    // 总页数
    int pageCount = (int) Math.ceil((double) totalHits / size);

    // 循环查询每一页
    for (int i = 0; i < pageCount; i++) {
      searchSourceBuilder.from(i * size);
      searchRequest.source(searchSourceBuilder);
      searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
      hits = searchResponse.getHits();
      for (SearchHit hit : hits) {
        String sourceAsString = hit.getSourceAsString();
        log.info("第{}页，数据：{}", i + 1, sourceAsString);
      }
    }
  }
}
