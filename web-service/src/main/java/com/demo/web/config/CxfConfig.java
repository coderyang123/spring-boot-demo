package com.demo.web.config;

import com.demo.web.service.UserService;
import com.demo.web.service.impl.UserServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CXF配置类
 *
 * @author yueyang
 * @since 2022-06-06 15:48:00
 */
@Configuration
public class CxfConfig {

  @Bean(name = Bus.DEFAULT_BUS_ID)
  public SpringBus springBus() {
    return new SpringBus();
  }

  @Bean
  public UserService userService() {
    return new UserServiceImpl();
  }

  @Bean
  public EndpointImpl userEndpoint() {
    EndpointImpl endpoint = new EndpointImpl(springBus(), userService());
    endpoint.publish("/user");
    return endpoint;
  }
}
