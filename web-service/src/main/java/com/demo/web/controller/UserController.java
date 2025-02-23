package com.demo.web.controller;

import com.demo.web.domain.UserDTO;
import com.demo.web.service.UserService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2022-06-06 16:52:00
 */
@RestController
@RequestMapping("/test-user")
public class UserController {

  @PostMapping
  public int add(@RequestBody UserDTO userDTO) {
    try {
      // 接口地址
      String address = "http://127.0.0.1:8080/cxf/user?wsdl";

      // 代理工厂
      JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();

      // 设置代理地址
      jaxWsProxyFactoryBean.setAddress(address);

      // 设置接口类型
      jaxWsProxyFactoryBean.setServiceClass(UserService.class);

      // 创建一个代理接口实现
      UserService userService = (UserService) jaxWsProxyFactoryBean.create();

      return userService.addUser(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @PatchMapping
  public int update(@RequestBody UserDTO userDTO) {
    try {
      // 接口地址
      String address = "http://127.0.0.1:8080/cxf/user?wsdl";

      // 代理工厂
      JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();

      // 设置代理地址
      jaxWsProxyFactoryBean.setAddress(address);

      // 设置接口类型
      jaxWsProxyFactoryBean.setServiceClass(UserService.class);

      // 创建一个代理接口实现
      UserService userService = (UserService) jaxWsProxyFactoryBean.create();

      return userService.updateUser(userDTO);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }
}
