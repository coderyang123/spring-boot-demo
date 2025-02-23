package com.demo.elasticsearchhighlevelclient;

import java.io.IOException;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestHighLevelClientForIndexTests {
  @Autowired RestHighLevelClient restHighLevelClient;

  /**
   * 创建索引
   *
   * @throws IOException IO异常
   */
  @Test
  void testCreateMapping() throws IOException {
    CreateIndexRequest createIndexRequest = new CreateIndexRequest("products2");
    createIndexRequest.mapping(
        "{\n"
            + "    \"properties\": {\n"
            + "      \"title\": {\n"
            + "        \"type\": \"keyword\"\n"
            + "      },\n"
            + "      \"price\": {\n"
            + "        \"type\": \"double\"\n"
            + "      },\n"
            + "      \"created_at\": {\n"
            + "        \"type\": \"date\"\n"
            + "      },\n"
            + "      \"description\": {\n"
            + "        \"type\": \"text\"\n"
            + "      }\n"
            + "    }\n"
            + "  }",
        XContentType.JSON);
    CreateIndexResponse createIndexResponse =
        restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
    System.out.println("createIndexResponse:" + createIndexResponse.isAcknowledged());
    restHighLevelClient.close();
  }

  /**
   * 删除索引
   *
   * @throws IOException IO异常
   */
  @Test
  void testDeleteMapping() throws IOException {
    DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("products2");
    AcknowledgedResponse response =
        restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
    System.out.println("response:" + response.isAcknowledged());
    restHighLevelClient.close();
  }
}
