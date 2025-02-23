package com.demo.compressuncompress.controller;

import com.demo.compressuncompress.utils.ImageUtil;
import java.io.File;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 图片压缩示例
 *
 * @author yueyang
 * @since 2021-12-27 11:15:00
 */
@Slf4j
@RestController
@RequestMapping("/picture")
public class PictureController {

  /** 图片压缩后的输出路径 */
  @Value("${image.output.path}")
  private String outputPath;

  @GetMapping("/compress")
  public void compress(@RequestParam("filePath") String filePath) {
    if (StringUtils.isBlank(filePath)) {
      log.error("文件资源路径：【{}】无效", filePath);
    }
    compressPicture(filePath);
  }

  @GetMapping("/compress2")
  public void compress2(@RequestParam("filePath") String filePath) {
    if (StringUtils.isBlank(filePath)) {
      log.error("文件资源路径：【{}】无效", filePath);
    }
    compressPicture2(filePath);
  }

  @GetMapping("/compress3")
  public void compress3(@RequestParam("filePath") String filePath) {
    if (StringUtils.isBlank(filePath)) {
      log.error("文件资源路径：【{}】无效", filePath);
    }
    compressPicture3(filePath);
  }

  private void compressPicture(String filePath) {
    File file = new File(filePath);

    // 资源是文件夹
    if (file.exists() && file.isDirectory()) {
      File[] pictures = file.listFiles();
      if (Objects.isNull(pictures)) {
        log.info("文件资源：【{}】为空", filePath);
        return;
      }
      for (File picture : pictures) {
        if (picture.isDirectory()) {
          compressPicture(picture.getAbsolutePath());
        }

        // 资源是文件
        if (picture.isFile()) {
          ImageUtil.compressPictureContent(picture, new File(outputPath + file.getName()));
        }
      }

      // 资源是文件
    } else if (file.exists() && file.isFile()) {
      ImageUtil.compressPictureContent(file, new File(outputPath + file.getName()));
    }
  }

  private void compressPicture2(String filePath) {
    File file = new File(filePath);

    // 资源是文件夹
    if (file.exists() && file.isDirectory()) {
      File[] pictures = file.listFiles();
      if (Objects.isNull(pictures)) {
        log.info("文件资源：【{}】为空", filePath);
        return;
      }
      for (File picture : pictures) {
        if (picture.isDirectory()) {
          compressPicture2(picture.getAbsolutePath());
        }

        // 资源是文件
        if (picture.isFile()) {
          ImageUtil.compressPictureContent2(picture, new File(outputPath + file.getName()));
        }
      }

      // 资源是文件
    } else if (file.exists() && file.isFile()) {
      ImageUtil.compressPictureContent2(file, new File(outputPath + file.getName()));
    }
  }

  private void compressPicture3(String filePath) {
    File file = new File(filePath);

    // 资源是文件夹
    if (file.exists() && file.isDirectory()) {
      File[] pictures = file.listFiles();
      if (Objects.isNull(pictures)) {
        log.info("文件资源：【{}】为空", filePath);
        return;
      }
      for (File picture : pictures) {
        if (picture.isDirectory()) {
          compressPicture3(picture.getAbsolutePath());
        }

        // 资源是文件
        if (picture.isFile()) {
          ImageUtil.compressPictureContent3(picture.getAbsolutePath(), outputPath + file.getName());
        }
      }

      // 资源是文件
    } else if (file.exists() && file.isFile()) {
      ImageUtil.compressPictureContent3(file.getAbsolutePath(), outputPath + file.getName());
    }
  }
}
