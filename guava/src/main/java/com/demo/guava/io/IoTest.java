package com.demo.guava.io;

import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.CharSource;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;

/**
 * 使用流（Source）与汇（Sink）对文件进行操作的演示
 *
 * @author yueyang
 * @since 2021-03-05 19:48:00
 */
public class IoTest {

  public static void main(String[] args) throws IOException {
    fileCopyTest();
  }

  /** 文件拷贝演示 */
  public static void fileCopyTest() throws IOException {
    // 创建对应的Source和Sink
    CharSource charSource =
        Files.asCharSource(new File("/Users/yueyang/Desktop/source.txt"), Charsets.UTF_8);
    CharSink charSink =
        Files.asCharSink(new File("/Users/yueyang/Desktop/target.txt"), Charsets.UTF_8);

    // 拷贝
    long l = charSource.copyTo(charSink);
  }
}
