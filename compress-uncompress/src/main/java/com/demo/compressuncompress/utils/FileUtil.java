package com.demo.compressuncompress.utils;

import java.io.File;
import org.springframework.util.StringUtils;

/**
 * 文件工具类
 *
 * @author yueyang
 * @since 2021-12-27 16:50:00
 */
public class FileUtil {

  /**
   * 获取文件扩展名
   *
   * @param file 文件资源
   * @return 文件扩展名
   */
  public static String getFileExtension(File file) {
    if (file != null && file.exists() && file.isFile()) {
      return StringUtils.getFilenameExtension(file.getName());
    }

    return null;
  }
}
