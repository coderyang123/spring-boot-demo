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
  /**
   * 普通文件上传下载示例页面
   *
   * @return index.html
   */
  @GetMapping("/")
  public String index() {
    return "index";
  }

  /**
   * 分块上传下载示例页面
   *
   * @return upload.html
   */
  @GetMapping("/upload")
  public String upload() {
    return "upload";
  }
}
