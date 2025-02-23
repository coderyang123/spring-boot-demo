package com.demo.openfeign.feign;

import com.demo.openfeign.entity.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * openfeign调用
 *
 * @author yueyang
 * @since 2021-02-06 14:05:00
 */
@FeignClient(name = "feignclient", url = "http://${file-server.ip}:${file-server.port}/")
public interface OpenFeignClient {

  /**
   * 单文件上传
   *
   * @param multipartFile 表单名
   * @return 文件名及路径
   */
  @PostMapping(value = "/fileUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  String fileUpload(@RequestPart("multipartFile") MultipartFile multipartFile);

  /**
   * 多文件上传
   *
   * @param multipartFiles 表单名
   * @return 文件名及路径
   */
  @PostMapping(value = "/multipartFileUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  String multipartFileUpload(@RequestPart("multipartFiles") MultipartFile[] multipartFiles);

  /**
   * 测试Get方法
   *
   * @param id ID
   * @return String
   */
  @GetMapping(value = "/testGet/{id}")
  String testGet(@PathVariable String id);

  /**
   * 测试GET请求
   *
   * @param id ID
   * @return ID
   */
  @GetMapping("/testGet2")
  String testGet2(@RequestParam("id") String id);

  /**
   * 测试POST请求
   *
   * @param id ID
   * @return ID
   */
  @PostMapping("/testPost")
  String testPost(@RequestParam("id") String id);

  /**
   * 测试POST请求
   *
   * @param student student
   * @return ID
   */
  @PostMapping("/testPost2")
  String testPost2(@RequestBody Student student);
}
