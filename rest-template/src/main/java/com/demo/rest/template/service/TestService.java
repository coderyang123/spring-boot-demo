package com.demo.rest.template.service;

import com.demo.rest.template.entity.TestRequestParam;
import com.demo.rest.template.entity.TestResponse;
import javax.annotation.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 测试
 *
 * @author yueyang
 * @since 2025-08-25 22:51:00
 */
@Service
public class TestService {
  @Resource private RestTemplate restTemplate;

  public TestResponse postDemo() {
    String url = "http://localhost:8080/api/rest/test";
    TestRequestParam requestParam = new TestRequestParam("param1", 123);
    return restTemplate.postForObject(url, requestParam, TestResponse.class);
  }

  public TestResponse postWithHeadersDemo() {
    String url = "http://localhost:8080/api/rest/test";

    // 设置请求头
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", "Bearer " + "your_token_here");

    // 创建请求实体
    TestRequestParam requestParam = new TestRequestParam("param1", 123);
    HttpEntity<TestRequestParam> requestEntity = new HttpEntity<>(requestParam, headers);

    ResponseEntity<TestResponse> response =
        restTemplate.exchange(url, HttpMethod.POST, requestEntity, TestResponse.class);

    return response.getBody();
  }

  public String submitFormDemo(String name, String email) {
    String url = "http://localhost:8080/api/rest/test";

    // 设置表单数据
    MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
    formData.add("name", name);
    formData.add("email", email);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);

    return restTemplate.postForObject(url, requestEntity, String.class);
  }
}
