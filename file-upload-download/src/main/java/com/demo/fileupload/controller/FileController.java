package com.demo.fileupload.controller;

import com.demo.fileupload.entity.FileChunk;
import com.demo.fileupload.util.DateUtils;
import com.demo.fileupload.util.TftpUtils;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 普通文件上传
 *
 * @author yueyang
 * @since 2021-02-03 23:50:00
 */
@RestController
@RequestMapping("/file")
public class FileController {

  /** 文件上传路径 */
  @Value("${spring.servlet.multipart.location}")
  private String uploadPath;

  /** 文件分片 */
  private final Map<String, FileChunk> FILE_CHUNKS = new HashMap<>();

  /**
   * 单文件上传
   *
   * @param multipartFile 文件
   * @return 文件上传成功
   */
  @PostMapping("/upload")
  public String fileUpload(@RequestPart("multipartFile") MultipartFile multipartFile) {
    if (multipartFile.isEmpty()) {
      return "【文件上传】失败：文件内容为空";
    }
    String originalFilename = multipartFile.getOriginalFilename();
    Long nowSecond = DateUtils.getNowSecond();
    String suffix = StringUtils.getFilenameExtension(originalFilename);
    String newFilename = nowSecond + "." + suffix;

    // 转存文件
    String absolutePath;
    try {
      File localFile = new File(uploadPath, newFilename);
      absolutePath = localFile.getAbsolutePath();
      multipartFile.transferTo(localFile);
    } catch (IOException e) {
      return String.format("【文件上传】失败：%s", e.getMessage());
    }
    return String.format("【文件上传】成功，绝对路径：%s", absolutePath);
  }

  /**
   * 单文件上传2
   *
   * @param multipartFile 文件
   * @return 文件上传成功
   */
  @PostMapping("/upload2")
  public String fileUpload2(@RequestPart("multipartFile") MultipartFile multipartFile) {
    if (multipartFile.isEmpty()) {
      return "【文件上传】失败：文件内容为空";
    }
    String fileName = multipartFile.getOriginalFilename();
    File destFile = new File(uploadPath, fileName);
    OutputStream out = null;
    InputStream in = null;
    try {
      out = new FileOutputStream(destFile);
      in = multipartFile.getInputStream();
      byte[] buffer = new byte[1024 * 1024 * 1024];
      int len;
      while ((len = in.read(buffer)) != -1) {
        System.out.printf("本次读取了%d字节%n", len);
        out.write(buffer, 0, len);
      }
    } catch (Exception e) {
      return String.format("【文件上传】失败：%s", e.getMessage());
    } finally {
      try {
        Objects.requireNonNull(out).close();
        Objects.requireNonNull(in).close();
      } catch (IOException e) {
        System.out.println("流关闭失败：" + e.getMessage());
      }
    }
    return String.format("【文件上传】成功，绝对路径：%s", destFile.getAbsolutePath());
  }

  /**
   * 多文件上传
   *
   * @param multipartFiles 文件
   * @return 文件上传成功
   */
  @PostMapping("/multipartUpload")
  public String multipartFileUpload(@RequestPart("multipartFiles") MultipartFile[] multipartFiles) {
    if (multipartFiles.length == 0) {
      return "【多文件上传】失败：文件内容为空";
    }
    for (MultipartFile multipartFile : multipartFiles) {
      String originalFilename = multipartFile.getOriginalFilename();
      Long nowSecond = DateUtils.getNowSecond();
      String suffix = StringUtils.getFilenameExtension(originalFilename);
      String newFilename = nowSecond + "." + suffix;

      // 转存文件
      try {
        File localFile = new File(uploadPath, newFilename);
        multipartFile.transferTo(localFile);
      } catch (IOException e) {
        return String.format("【多文件上传】失败：%s", e.getMessage());
      }
    }
    return "【多文件上传】成功";
  }

  /**
   * 文件下载
   *
   * @param openStyle 文件打开方式，下载(attachment)/在线预览(inline)
   * @param response HttpServletResponse
   */
  @RequestMapping("/download")
  public void fileDownload(
      @RequestParam(value = "openStyle", required = false) String openStyle,
      HttpServletResponse response) {
    ClassPathResource classPathResource = new ClassPathResource("/images/1.jpg");
    try (InputStream inputStream = classPathResource.getInputStream();
        OutputStream outputStream = response.getOutputStream()) {
      // 浏览器用UTF-8来解析返回的数据
      response.setContentType("application/x-download");

      // servlet用UTF-8转码
      response.setCharacterEncoding(StandardCharsets.UTF_8.name());

      // 防止文件名中文乱码
      String fileName =
          URLEncoder.encode("picture.jpg", StandardCharsets.UTF_8).replaceAll("\\+", "%20");

      // 判断文件打开方式，下载或在线打开
      openStyle = Objects.isNull(openStyle) ? "attachment" : openStyle;
      response.setHeader("Content-Disposition", openStyle + ";filename*=utf-8''" + fileName);

      // 文件拷贝
      FileCopyUtils.copy(inputStream, outputStream);
      outputStream.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 文件下载
   *
   * @param fileId 文件ID(用于获取源文件路径)
   * @param openStyle 文件打开方式，下载(attachment)/在线打开(inline)
   * @param response HttpServletResponse
   */
  @GetMapping("/download2/{id}")
  public void fileDownload2(
      @PathVariable("id") String fileId,
      @RequestParam(value = "openStyle", required = false) String openStyle,
      HttpServletResponse response) {
    // TODO 根据fileId获取文件路径，这里直接用文件名代替
    try (InputStream inputStream = new FileInputStream(new File(uploadPath, fileId));
        OutputStream outputStream = response.getOutputStream()) {
      // 浏览器用UTF-8来解析返回的数据
      response.setContentType("application/x-download");

      // servlet用UTF-8转码
      response.setCharacterEncoding(StandardCharsets.UTF_8.name());

      // 防止文件名中文乱码
      String fileName = URLEncoder.encode(fileId, StandardCharsets.UTF_8).replaceAll("\\+", "%20");

      // 判断文件打开方式，下载或在线打开
      openStyle = Objects.isNull(openStyle) ? "attachment" : openStyle;
      response.setHeader("Content-Disposition", openStyle + ";filename*=utf-8''" + fileName);

      // 文件拷贝
      FileCopyUtils.copy(inputStream, outputStream);
      outputStream.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 删除文件
   *
   * @param fileId 文件ID(用于获取源文件路径)
   * @return 删除文件
   */
  @DeleteMapping("/{id}")
  public String deleteFile(@PathVariable("id") String fileId) {
    // TODO 根据fileId获取文件路径，这里直接用文件名代替
    File file = new File(uploadPath, fileId);

    // 立即删除文件
    if (file.exists()) {
      boolean delete = file.delete();
      if (delete) {
        // TODO 文件删除成功后删除数据库记录，这里省略
      }
      return "【文件删除】成功";
    } else {
      return "【文件删除】失败，文件不存在";
    }
  }

  /**
   * FTP单文件上传
   *
   * @param multipartFile 文件
   * @return 文件上传成功
   */
  @PostMapping("/tftp/upload")
  public String tftpUpload(@RequestPart("multipartFile") MultipartFile multipartFile) {
    if (multipartFile.isEmpty()) {
      return "【文件上传】失败：文件内容为空";
    }
    String originalFilename = multipartFile.getOriginalFilename();
    String suffix = StringUtils.getFilenameExtension(originalFilename);
    String newFilename =
        Objects.isNull(suffix)
            ? System.currentTimeMillis() + ""
            : System.currentTimeMillis() + "." + suffix;

    // 上传文件
    try (FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream()) {
      TftpUtils.uploadFile("192.168.218.220", newFilename, fileInputStream, 69);
    } catch (Exception e) {
      return String.format("【文件上传】失败：%s", e.getMessage());
    }

    return "【文件上传】成功";
  }

  /**
   * TFTP文件下载
   *
   * @param fileId 文件ID(用于获取源文件路径)
   * @param openStyle 文件打开方式，下载(attachment)/在线打开(inline)
   * @param response HttpServletResponse
   * @return 文件下载成功
   */
  @GetMapping("/tftp/download/{id}")
  public String tftpDownload(
      @PathVariable("id") String fileId,
      @RequestParam(value = "openStyle", required = false) String openStyle,
      HttpServletResponse response) {
    // TODO 根据fileId获取文件路径，这里直接用文件名代替
    response.setContentType("application/x-download;charset=UTF-8");

    // servlet用UTF-8转码
    response.setCharacterEncoding(StandardCharsets.UTF_8.name());

    // 下载后的文件名
    String extension = StringUtils.getFilenameExtension(fileId);
    String fileName = System.currentTimeMillis() + "." + extension;

    // 判断文件打开方式，下载或在线打开
    openStyle = Objects.isNull(openStyle) ? "attachment" : openStyle;
    response.addHeader("Content-Disposition", openStyle + ";filename*=utf-8''" + fileName);

    try (ServletOutputStream outputStream = response.getOutputStream()) {
      TftpUtils.downloadFile("192.168.218.220", fileId, 69, outputStream);
      outputStream.flush();
    } catch (IOException e) {
      return String.format("【文件下载】失败：%s", e.getMessage());
    }

    return "【文件下载】成功";
  }

  /**
   * 文件分片上传
   *
   * @param file 待上传文件
   * @param chunkNumber 当前分片序号
   * @param totalChunks 总分片数
   * @param identifier 文件唯一标识符
   */
  @PostMapping("/chunk")
  public ResponseEntity<String> uploadChunk(
      @RequestParam("file") MultipartFile file,
      @RequestParam("chunkNumber") int chunkNumber,
      @RequestParam("totalChunks") int totalChunks,
      @RequestParam("identifier") String identifier) {
    try {
      FileChunk fileChunk =
          new FileChunk(file.getOriginalFilename(), chunkNumber, totalChunks, identifier);
      FILE_CHUNKS.put(identifier + "_" + chunkNumber, fileChunk);

      Path filePath = Paths.get(uploadPath + identifier + "_" + chunkNumber);
      Files.createDirectories(filePath.getParent());
      try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
        fos.write(file.getBytes());
      }
      if (chunkNumber == totalChunks) {
        mergeChunks(identifier);
        return ResponseEntity.ok("File upload completed.");
      } else {
        return ResponseEntity.ok("Chunk " + chunkNumber + " uploaded successfully.");
      }
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Failed to upload chunk: " + e.getMessage());
    }
  }

  public void mergeChunks(String identifier) throws IOException {
    FileChunk firstChunk =
        FILE_CHUNKS.values().stream()
            .filter(chunk -> chunk.getIdentifier().equals(identifier))
            .findFirst()
            .orElseThrow(() -> new IOException("No chunks found for identifier: " + identifier));

    String originalFilename = firstChunk.getOriginalFilename();
    Path finalFilePath = Paths.get(uploadPath + originalFilename);
    Files.createDirectories(finalFilePath.getParent());

    try (FileOutputStream fos = new FileOutputStream(finalFilePath.toFile())) {
      for (int i = 1; i <= firstChunk.getTotalChunks(); i++) {
        Path chunkPath = Paths.get(uploadPath + identifier + "_" + i);
        fos.write(Files.readAllBytes(chunkPath));
        Files.delete(chunkPath);
      }
    }

    FILE_CHUNKS.values().removeIf(chunk -> chunk.getIdentifier().equals(identifier));
  }
}
