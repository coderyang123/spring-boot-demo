package com.demo.elasticsearchhighlevelclient.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.lang.NonNull;

/**
 * 配置ES客户端
 *
 * @author yueyang
 * @since 2022-01-24 14:25:00
 */
@Configuration
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {

  /************************** 配置单节点（application.yml） **************************/
  @Value("${elasticsearch.host}")
  private String host;

  @NonNull
  @Bean(destroyMethod = "close")
  @Override
  public RestHighLevelClient elasticsearchClient() {
    final ClientConfiguration clientConfiguration =
        ClientConfiguration.builder().connectedTo(host).build();
    return RestClients.create(clientConfiguration).rest();
  }

  /************************** 配置多节点（application-multi.yml） **************************/
  /*@Value("${elasticsearch.hosts}")
  private String[] hosts;

  @NonNull
  @Bean(destroyMethod = "close")
  @Override
  public RestHighLevelClient elasticsearchClient() {
    ClientConfiguration clientConfiguration =
        ClientConfiguration.builder().connectedTo(hosts).build();
    return RestClients.create(clientConfiguration).rest();
  }*/
}
