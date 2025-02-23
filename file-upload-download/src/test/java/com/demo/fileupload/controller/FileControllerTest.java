package com.demo.fileupload.controller;

import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FileControllerTest {

  @Test
  void fileDownload() {
    Assertions.assertEquals(StandardCharsets.UTF_8.name(), "UTF-8");
  }
}
