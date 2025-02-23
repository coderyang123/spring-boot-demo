package com.demo.elasticsearchhighlevelclient;

import java.io.IOException;
import java.util.List;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedDoubleTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.ParsedSum;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestHighLevelClientForAggregationTests {
  @Autowired RestHighLevelClient restHighLevelClient;

  /**
   * 聚合查询（针对非TEXT类型字段）-各价格水果的数量
   *
   * @throws IOException IO异常
   */
  @Test
  void testAggregationQueryDocument() throws IOException {
    SearchRequest searchRequest = new SearchRequest("fruit");
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

    // 查询条件
    sourceBuilder.query(QueryBuilders.matchAllQuery());

    // 聚合条件
    sourceBuilder.aggregation(AggregationBuilders.terms("price_group").field("price"));

    // 不查询详情
    sourceBuilder.size(0);

    // 查询
    searchRequest.source(sourceBuilder);
    SearchResponse searchResponse =
        restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

    // 处理聚合结果
    Aggregations aggregations = searchResponse.getAggregations();
    ParsedDoubleTerms terms = aggregations.get("price_group");
    List<? extends Terms.Bucket> buckets = terms.getBuckets();
    for (Terms.Bucket bucket : buckets) {
      System.out.println(bucket.getKey() + ", " + bucket.getDocCount());
    }

    restHighLevelClient.close();
  }

  /**
   * 聚合查询（针对非TEXT类型字段）-各水果的数量
   *
   * @throws IOException IO异常
   */
  @Test
  void testAggregationQueryDocument2() throws IOException {
    SearchRequest searchRequest = new SearchRequest("fruit");
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    sourceBuilder
        // 查询条件
        .query(QueryBuilders.matchAllQuery())
        // 聚合条件
        .aggregation(AggregationBuilders.terms("title_group").field("title"))
        // 不查询详情
        .size(0);

    // 查询
    searchRequest.source(sourceBuilder);
    SearchResponse searchResponse =
        restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

    // 处理聚合结果
    Aggregations aggregations = searchResponse.getAggregations();
    ParsedStringTerms terms = aggregations.get("title_group");
    List<? extends Terms.Bucket> buckets = terms.getBuckets();
    for (Terms.Bucket bucket : buckets) {
      System.out.println(bucket.getKey() + ", " + bucket.getDocCount());
    }

    restHighLevelClient.close();
  }

  /**
   * 聚合查询（针对非TEXT类型字段）-价格总和
   *
   * @throws IOException IO异常
   */
  @Test
  void testAggregationQueryDocument3() throws IOException {
    SearchRequest searchRequest = new SearchRequest("fruit");
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

    // 设置查询和聚合条件
    sourceBuilder
        .query(QueryBuilders.matchAllQuery())
        .aggregation(AggregationBuilders.sum("price_sum").field("price"))
        .size(0);

    // 查询
    searchRequest.source(sourceBuilder);
    SearchResponse searchResponse =
        restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

    // 处理聚合结果
    ParsedSum parsedSum = searchResponse.getAggregations().get("price_sum");
    System.out.println(parsedSum.getValue());

    restHighLevelClient.close();
  }
}
