package com.demo.easyexcel.controller;

import com.alibaba.excel.EasyExcel;
import com.demo.easyexcel.domain.entity.DictDO;
import com.demo.easyexcel.service.DictDoService;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 字典管理
 *
 * @author yueyang
 * @since 2021-04-04 13:12:00
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class DictDoController {
  private final DictDoService dictDoService;

  /**
   * 导入Excel
   *
   * @param file 文件
   * @return 导入数据成功
   * @throws IOException IOException
   */
  @PostMapping("/importExcel")
  public String importExcel(@RequestPart("file") MultipartFile file) throws IOException {
    InputStream inputStream = file.getInputStream();
    dictDoService.importExcel(inputStream);
    return "导入数据成功！";
  }

  /**
   * 导出Excel-导出到客户端
   *
   * @param response response
   * @throws IOException IOException
   */
  @GetMapping("/exportExcel")
  public void exportExcel(HttpServletResponse response) throws IOException {
    response.setContentType("application/vnd.ms-excel");
    response.setCharacterEncoding("utf-8");

    // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
    String fileName =
        URLEncoder.encode("temp_dict", StandardCharsets.UTF_8).replaceAll("\\+", "%20");
    response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

    // 导出Excel
    EasyExcel.write(response.getOutputStream(), DictDO.class)
        .sheet("数据字典")
        .doWrite(dictDoService.listDictDo());
  }

  /** 异步导出Excel-导出到服务器 */
  @GetMapping("/asyncExportExcel")
  public String asyncExportExcel() {
    dictDoService.asyncExportExcel();
    return "导出成功！";
  }
}
