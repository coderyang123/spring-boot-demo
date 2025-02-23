package com.demo.httpclient.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * HTTP请求工具类
 *
 * @author yueyang
 * @since 2022-06-08 22:18:00
 */
@Slf4j
public class HttpClientUtils {

  /** HTTP客户端 */
  private static final CloseableHttpClient HTTP_CLIENT;

  static {
    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
    // 总连接池数量
    connectionManager.setMaxTotal(150);

    // 可为每个域名设置单独的连接池数量
    connectionManager.setMaxPerRoute(new HttpRoute(new HttpHost("xx.xx.xx.xx")), 80);

    // setConnectTimeout：设置建立连接的超时时间
    // setConnectionRequestTimeout：从连接池中拿连接的等待超时时间
    // setSocketTimeout：发出请求后等待对端应答的超时时间
    RequestConfig requestConfig =
        RequestConfig.custom()
            .setConnectTimeout(1000)
            .setConnectionRequestTimeout(2000)
            .setSocketTimeout(3000)
            .build();

    // 重试处理器
    HttpRequestRetryHandler retryHandler = new StandardHttpRequestRetryHandler();

    HTTP_CLIENT =
        HttpClients.custom()
            .setConnectionManager(connectionManager)
            .setDefaultRequestConfig(requestConfig)
            .setRetryHandler(retryHandler)
            .build();
  }

  /**
   * Get请求
   *
   * @param uri 请求地址
   * @param params 请求参数
   * @return 响应内容
   */
  public static JSONObject doHttpGet(String uri, Map<String, String> params) {
    CloseableHttpResponse response = null;
    try {
      URIBuilder uriBuilder = new URIBuilder(uri);
      if (!Objects.isNull(params) && !params.isEmpty()) {
        List<NameValuePair> list = new ArrayList<>();
        params.forEach((k, v) -> list.add(new BasicNameValuePair(k, v)));
        uriBuilder.setParameters(list);
      }
      HttpGet httpGet = new HttpGet(uriBuilder.build());
      response = HTTP_CLIENT.execute(httpGet);
      int statusCode = response.getStatusLine().getStatusCode();
      if (HttpStatus.SC_OK == statusCode) {
        HttpEntity entity = response.getEntity();
        if (!Objects.isNull(entity)) {
          return JSON.parseObject(EntityUtils.toString(entity, "utf-8"));
        }
      }
    } catch (Exception ex) {
      log.error("CloseableHttpClient-get-请求异常", ex);
    } finally {
      try {
        if (!Objects.isNull(response)) {
          response.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return new JSONObject();
  }

  /**
   * Post请求
   *
   * @param uri 请求地址
   * @param params 请求参数
   * @return 响应内容
   */
  public static JSONObject doHttpPost(String uri, Map<String, String> params) {
    CloseableHttpResponse response = null;
    try {
      HttpPost httpPost = new HttpPost(uri);
      if (!Objects.isNull(params) && !params.isEmpty()) {
        List<NameValuePair> list = new ArrayList<>();
        params.forEach((k, v) -> list.add(new BasicNameValuePair(k, v)));
        HttpEntity httpEntity = new UrlEncodedFormEntity(list, "utf-8");
        httpPost.setEntity(httpEntity);
      }
      response = HTTP_CLIENT.execute(httpPost);

      int statusCode = response.getStatusLine().getStatusCode();
      if (HttpStatus.SC_OK == statusCode) {
        HttpEntity entity = response.getEntity();
        if (!Objects.isNull(entity)) {
          return JSON.parseObject(EntityUtils.toString(entity, "utf-8"));
        }
      }
    } catch (Exception ex) {
      log.error("CloseableHttpClient-post-请求异常", ex);
    } finally {
      try {
        if (!Objects.isNull(response)) {
          response.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return new JSONObject();
  }
}
