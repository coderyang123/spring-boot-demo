package com.demo.easyexcel.service.impl;

import com.demo.easyexcel.service.FileService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author yueyang
 * @since 2022-07-20 16:04:00
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {
  /** 文件上传路径 */
  @Value("${spring.servlet.multipart.location}")
  private String uploadPath;

  public void upload(InputStream inputStream, String filename) {
    try (InputStream innerInputStream = inputStream;
        FileOutputStream outputStream = new FileOutputStream(new File(uploadPath, filename))) {
      byte[] buffer = new byte[10240];
      int length;
      while ((length = innerInputStream.read(buffer)) > 0) {
        outputStream.write(buffer, 0, length);
      }
      outputStream.flush();
    } catch (Exception e) {
      log.error("文件上传失败", e);
    }
  }
}
