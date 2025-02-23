package com.demo.openfeign.controller;

import com.demo.openfeign.entity.Student;
import com.demo.openfeign.feign.OpenFeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 普通文件上传
 *
 * @author yueyang
 * @since 2021-02-03 23:50:00
 */
@RestController
public class FileController {

  private final OpenFeignClient feignclient;

  public FileController(OpenFeignClient feignclient) {
    this.feignclient = feignclient;
  }

  /**
   * 单文件上传
   *
   * @param multipartFile 文件
   * @return 文件上传成功
   */
  @PostMapping("/fileUpload")
  public String fileUpload(@RequestPart("multipartFile") MultipartFile multipartFile) {
    return feignclient.fileUpload(multipartFile);
  }

  /**
   * 多文件上传
   *
   * @param multipartFiles 文件
   * @return 文件上传成功
   */
  @PostMapping("/multipartFileUpload")
  public String multipartFileUpload(@RequestPart("multipartFiles") MultipartFile[] multipartFiles) {
    return feignclient.multipartFileUpload(multipartFiles);
  }

  /**
   * 测试Get方法
   *
   * @param id ID
   * @return String
   */
  @GetMapping(value = "/testGet/{id}")
  String testGet(@PathVariable String id) {
    return feignclient.testGet(id);
  }

  /**
   * 测试GET请求
   *
   * @param id ID
   * @return ID
   */
  @GetMapping("/testGet2")
  public String testGet2(@RequestParam("id") String id) {
    return feignclient.testGet2(id);
  }

  /**
   * 测试POST请求
   *
   * @param id ID
   * @return ID
   */
  @PostMapping("/testPost")
  public String testPost(@RequestParam("id") String id) {
    return feignclient.testPost(id);
  }

  /**
   * 测试POST请求
   *
   * @param student student
   * @return ID
   */
  @PostMapping("/testPost2")
  String testPost2(@RequestBody Student student) {
    return feignclient.testPost2(student);
  }
}
