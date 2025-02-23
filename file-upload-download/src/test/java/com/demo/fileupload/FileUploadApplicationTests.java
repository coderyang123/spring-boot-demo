package com.demo.fileupload;

import com.demo.fileupload.util.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FileUploadApplicationTests {

  @Test
  void createNewFileTest() {
    FileUtils.createNewFile();
  }

  @Test
  void renameFileTest() {
    FileUtils.renameFile();
  }

  @Test
  void deleteFileTest() {
    FileUtils.deleteFile();
  }

  @Test
  void createNewDirectoryTest() {
    FileUtils.createNewDirectory();
  }

  @Test
  void createMultistageDirectoryTest() {
    FileUtils.createMultistageDirectory();
  }

  @Test
  void renameDirectoryTest() {
    FileUtils.renameDirectory();
  }

  @Test
  void deleteDirectoryTest() {
    FileUtils.deleteDirectory();
  }

  @Test
  void recursionDeleteDirectoryTest() {
    FileUtils.recursionDeleteDirectory();
  }
}
