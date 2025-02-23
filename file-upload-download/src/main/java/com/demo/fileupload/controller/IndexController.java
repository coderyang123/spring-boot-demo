package com.demo.fileupload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页
 *
 * @author yueyang
 * @since 2022-07-08 14:53:00
 */
@Controller
public class IndexController {

  @GetMapping("/")
  public String index() {
    return "index";
  }
}
