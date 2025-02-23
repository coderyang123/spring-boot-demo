package com.demo.httpclient.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import org.junit.jupiter.api.Test;

class HttpClientUtilsTest {

  @Test
  void doHttpGet() {
    JSONObject jsonObject =
        HttpClientUtils.doHttpGet("https://httpbin.org/get", Map.of("k1", "v1", "k2", "v2"));
    System.out.println(JSON.toJSONString(jsonObject, true));
  }

  @Test
  void doHttpPost() {
    JSONObject jsonObject =
        HttpClientUtils.doHttpPost("https://httpbin.org/post", Map.of("k1", "v1", "k2", "v2"));
    System.out.println(JSON.toJSONString(jsonObject, true));
  }
}
