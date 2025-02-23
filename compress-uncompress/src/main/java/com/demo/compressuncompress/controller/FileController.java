package com.demo.compressuncompress.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.util.ZipUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件压缩/解压缩示例
 *
 * @author yueyang
 * @since 2021-12-28 09:34:00
 */
@Slf4j
@RestController
@RequestMapping("file")
public class FileController {

  /** 图片压缩后的输出路径 */
  @Value("${file.output.path}")
  private String outputPath;

  /**
   * 使用系统默认编码压缩文件到资源到当前目录
   *
   * @param resourcePath 待压缩的资源路径
   */
  @GetMapping("zip")
  public void zip(@RequestParam("resourcePath") String resourcePath) {
    if (StringUtils.isBlank(resourcePath)) {
      log.error("资源路径：【{}】无效", resourcePath);
    }
    try {
      ZipUtil.zip(resourcePath);
    } catch (UtilException e) {
      log.error("资源压缩失败，资源路径：【{}】", resourcePath);
    }
  }

  /**
   * 使用系统默认编码压缩文件到资源到指定目录（压缩包不包含顶层目录路径）
   *
   * @param resourcePath 待压缩的资源路径
   */
  @GetMapping("zip2")
  public void zip2(@RequestParam("resourcePath") String resourcePath) {
    if (StringUtils.isBlank(resourcePath)) {
      log.error("资源路径：【{}】无效", resourcePath);
    }
    try {
      String targetName = DateUtil.currentSeconds() + ".zip";
      ZipUtil.zip(resourcePath, outputPath + targetName);
    } catch (UtilException e) {
      log.error("资源压缩失败，资源路径：【{}】", resourcePath);
    }
  }

  /**
   * 使用系统默认编码压缩文件到资源到指定目录（压缩包包含顶层目录路径）
   *
   * @param resourcePath 待压缩的资源路径
   */
  @GetMapping("zip3")
  public void zip3(@RequestParam("resourcePath") String resourcePath) {
    if (StringUtils.isBlank(resourcePath)) {
      log.error("资源路径：【{}】无效", resourcePath);
    }
    try {
      String targetName = DateUtil.currentSeconds() + ".zip";
      ZipUtil.zip(resourcePath, outputPath + targetName, true);
    } catch (UtilException e) {
      log.error("资源压缩失败，资源路径：【{}】", resourcePath);
    }
  }

  /**
   * 使用系统默认编码解压缩文件到文件名同名的目录中
   *
   * @param resourcePath 待解压缩的压缩包全路径，例：/Users/yueyang/Documents/压缩包/1640657735.zip
   */
  @GetMapping("unzip")
  public void unzip(@RequestParam("resourcePath") String resourcePath) {
    if (StringUtils.isBlank(resourcePath)) {
      log.error("资源路径：【{}】无效", resourcePath);
    }
    try {
      ZipUtil.unzip(resourcePath);
    } catch (UtilException e) {
      log.error("资源压缩失败，资源路径：【{}】", resourcePath);
    }
  }

  /**
   * 使用系统默认编码解压缩文件到指定目录中
   *
   * @param resourcePath 待解压缩的压缩包全路径，例：/Users/yueyang/Documents/压缩包/1640657735.zip
   */
  @GetMapping("unzip2")
  public void unzip2(@RequestParam("resourcePath") String resourcePath) {
    if (StringUtils.isBlank(resourcePath)) {
      log.error("资源路径：【{}】无效", resourcePath);
    }
    try {
      ZipUtil.unzip(resourcePath, outputPath);
    } catch (UtilException e) {
      log.error("资源压缩失败，资源路径：【{}】", resourcePath);
    }
  }
}
