package com.demo.fileupload.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.net.tftp.TFTP;
import org.apache.commons.net.tftp.TFTPClient;

/**
 * TFTP工具类
 *
 * @author yueyang
 * @since 2021-07-20 22:22:00
 */
public class TftpUtils {

  /** TFTP客户端 */
  private static final TFTPClient T_FTP = new TFTPClient();

  /** 打开SOCKET的超时时间（50秒） */
  private static final int DEFAULT_TIMEOUT_50_SECONDS = 500000;

  /**
   * 文件上传
   *
   * @param hostname TFTP服务器主机名
   * @param remoteFilename 远程文件名
   * @param fileInput 文件流
   * @param port TFTP服务器端口
   */
  public static void uploadFile(
      String hostname, String remoteFilename, FileInputStream fileInput, int port)
      throws IOException {
    T_FTP.setDefaultTimeout(DEFAULT_TIMEOUT_50_SECONDS);

    // 打开本地socket
    T_FTP.open();

    // 文件上传
    T_FTP.sendFile(remoteFilename, TFTP.BINARY_MODE, fileInput, hostname, port);

    // 关闭TFTP客户端
    T_FTP.close();
  }

  /**
   * 文件下载
   *
   * @param hostname TFTP服务器主机名
   * @param remoteFilename 远程文件名
   * @param port TFTP服务器端口
   * @param outputStream outputStream
   * @throws IOException IOException
   */
  public static void downloadFile(
      String hostname, String remoteFilename, int port, OutputStream outputStream)
      throws IOException {
    T_FTP.setDefaultTimeout(DEFAULT_TIMEOUT_50_SECONDS);

    // 打开本地socket
    T_FTP.open();

    // 文件下载
    T_FTP.receiveFile(remoteFilename, TFTP.BINARY_MODE, outputStream, hostname, port);

    // 关闭TFTP客户端
    T_FTP.close();
  }
}
