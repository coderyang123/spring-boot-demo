package com.demo.easyexcel.service;

import java.io.InputStream;

/**
 * TODO
 *
 * @author yueyang
 * @since 2022-07-20 16:04:00
 */
public interface FileService {
  void upload(InputStream inputStream, String filename);
}
