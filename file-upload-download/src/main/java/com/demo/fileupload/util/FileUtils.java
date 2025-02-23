package com.demo.fileupload.util;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * 文件工具类
 *
 * @author yueyang
 * @since 2021-03-26 23:51:00
 */
public class FileUtils {

  /**
   * 获取文件后缀名
   *
   * @param originalFilename 源文件名
   * @return 文件后缀名
   */
  public static String getFileSuffix(String originalFilename) {
    return originalFilename.substring(originalFilename.indexOf("."));
  }

  /** 创建文件 */
  public static void createNewFile() {
    // 创建File对象
    File file = new File("C:\\Users\\10231\\Documents\\Temp\\test.txt");

    // 当文件不存在时创建
    if (!file.exists()) {
      try {
        boolean result = file.createNewFile();
        System.out.println("文件创建结果：" + result);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /** 重命名文件 */
  public static void renameFile() {
    // 创建File对象
    File file = new File("C:\\Users\\10231\\Documents\\Temp\\test.txt");

    // 当文件存在时重命名
    if (file.exists()) {
      boolean result = file.renameTo(new File("C:\\Users\\10231\\Documents\\Temp\\test2.txt"));
      System.out.println("文件重命名结果：" + result);
    }
  }

  /** 删除文件 */
  public static void deleteFile() {
    // 创建File对象
    File file = new File("C:\\Users\\10231\\Documents\\Temp\\test2.txt");

    // 当文件存在时删除
    if (file.exists()) {
      boolean result = file.delete();
      System.out.println("文件删除结果：" + result);
    }
  }

  /** 创建目录 */
  public static void createNewDirectory() {
    // 创建File对象
    File file = new File("C:\\Users\\10231\\Documents\\Temp\\test");

    // 当目录不存在时创建
    if (!file.exists()) {
      boolean result = file.mkdir();
      System.out.println("目录创建结果：" + result);
    }
  }

  /** 创建多级目录 */
  public static void createMultistageDirectory() {
    // 创建File对象
    File file = new File("C:\\Users\\10231\\Documents\\Temp\\test\\test2\\test3");

    // 当目录不存在时创建
    if (!file.exists()) {
      boolean result = file.mkdirs();
      System.out.println("目录创建结果：" + result);
    }
  }

  /** 重命名目录 */
  public static void renameDirectory() {
    // 创建File对象
    File file = new File("C:\\Users\\10231\\Documents\\Temp\\test");

    // 当文件存在时重命名
    if (file.exists()) {
      boolean result = file.renameTo(new File("C:\\Users\\10231\\Documents\\Temp\\test2"));
      System.out.println("目录重命名结果：" + result);
    }
  }

  /** 删除空目录 */
  public static void deleteDirectory() {
    // 创建File对象
    File file = new File("C:\\Users\\10231\\Documents\\Temp\\test2");

    // 当文件存在时删除
    if (file.exists()) {
      boolean result = file.delete();
      System.out.println("目录删除结果：" + result);
    }
  }

  /** 删除非空目录 */
  public static void recursionDeleteDirectory() {
    // 创建File对象
    File file = new File("C:\\Users\\10231\\Documents\\Temp\\test2");

    boolean result = recursionDelete(file);
    System.out.println("递归删除结果：" + result);
  }

  private static boolean recursionDelete(File file) {
    if (file.exists() && file.isDirectory()) {
      // 列出目录下所有文件和目录
      File[] files = file.listFiles();
      if (!Objects.isNull(files)) {
        for (File f : files) {
          // 递归删除文件或目录
          recursionDelete(f);
        }
      }
    }
    return file.delete();
  }
}
