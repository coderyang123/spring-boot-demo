package com.demo.elasticsearchhighlevelclient.config;

/**
 * 配置ES客户端
 *
 * @author yueyang
 * @since 2023-04-05 01:50:00
 */
/*@Setter
@ConfigurationProperties(prefix = "elasticsearch")
@Configuration
public class RestClientConfig2 extends AbstractElasticsearchConfiguration {*/

  /*************************** 配置单节点（application.yml） ***************************/
  /*private String host;

  @NonNull
  @Bean(destroyMethod = "close")
  @Override
  public RestHighLevelClient elasticsearchClient() {
    final ClientConfiguration clientConfiguration =
        ClientConfiguration.builder().connectedTo(host).build();
    return RestClients.create(clientConfiguration).rest();
  }*/

/*************************** 配置多节点（application-multi.yml） ***************************/
  /*private String[] hosts;

    @NonNull
    @Bean(destroyMethod = "close")
    @Override
    public RestHighLevelClient elasticsearchClient() {
      ClientConfiguration clientConfiguration =
          ClientConfiguration.builder().connectedTo(hosts).build();
      return RestClients.create(clientConfiguration).rest();
    }
  }*/
