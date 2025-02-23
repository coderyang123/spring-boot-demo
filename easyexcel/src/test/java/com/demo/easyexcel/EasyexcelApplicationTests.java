package com.demo.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.demo.easyexcel.common.listener.ExcelListener;
import com.demo.easyexcel.domain.dto.StudentExportDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class EasyexcelApplicationTests {

  @Test
  void contextLoads() {}

  /** 测试简单写入Excel文件（.xlsx） */
  @Test
  void simpleWriteXlsx() {
    // 初始化数据
    List<StudentExportDTO> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      StudentExportDTO data = new StudentExportDTO("Helen" + i, LocalDate.now(), 123456.1234);
      list.add(data);
    }

    // 写入Excel，路径必须存在
    String fileName = "D:/simpleWrite.xlsx";
    // 这里需要指定写用哪个class去写，然后写到第一个sheet，名字为模板1，然后文件流会自动关闭
    EasyExcel.write(fileName, StudentExportDTO.class).sheet("模板1").doWrite(list);
  }

  /** 测试简单写入Excel文件（.xls） */
  @Test
  void simpleWriteXls() {
    // 初始化数据
    List<StudentExportDTO> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      StudentExportDTO data = new StudentExportDTO("Helen" + i, LocalDate.now(), 123456.1234);
      list.add(data);
    }

    // 写入Excel，路径必须存在
    String fileName = "D:/simpleWrite.xls";
    // 这里需要指定写用哪个class去写，然后写到第一个sheet，名字为模板1，然后文件流会自动关闭
    EasyExcel.write(fileName, StudentExportDTO.class)
        .excelType(ExcelTypeEnum.XLS)
        .sheet("模板1")
        .doWrite(list);
  }

  /** 测试简单读取Excel文件（.xlsx） */
  @Test
  void simpleReadXlsx() {
    String fileName = "D:/simpleWrite.xlsx";
    // 这里默认读取第一个sheet
    EasyExcel.read(fileName, StudentExportDTO.class, new ExcelListener()).sheet().doRead();
  }

  /** 测试简单读取Excel文件（.xlsx） */
  @Test
  void simpleReadXlsx2() {
    String fileName = "D:/simpleWrite.xlsx";
    ExcelReader excelReader =
        EasyExcel.read(fileName, StudentExportDTO.class, new ExcelListener()).build();

    // 构建一个sheet，这里可以指定要读取的名字或者no
    ReadSheet readSheet = EasyExcel.readSheet(0).build();

    // 读取一个sheet
    excelReader.read(readSheet);
  }

  /** 测试简单读取Excel文件（.xls） */
  @Test
  void simpleReadXls() {
    String fileName = "C:\\Users\\Admin\\Desktop\\simpleWrite.xlsx";
    // 这里默认读取第一个sheet
    EasyExcel.read(fileName, StudentExportDTO.class, new ExcelListener())
        .excelType(ExcelTypeEnum.XLS)
        .sheet()
        .doRead();
  }

  /** 不使用监听器，同步的读取返回，不推荐使用，如果数据量大会把数据放到内存里面 */
  @Test
  public void synchronousRead() {
    String fileName = "C:\\Users\\Admin\\Desktop\\simpleWrite.xlsx";

    // 这里 需要指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish
    List<StudentExportDTO> list =
        EasyExcel.read(fileName).head(StudentExportDTO.class).sheet().doReadSync();
    for (StudentExportDTO data : list) {
      log.info("读取到数据:{}>>>", data);
    }
  }

  /** 不使用监听器，同步的读取返回，不推荐使用，如果数据量大会把数据放到内存里面 */
  @Test
  public void synchronousRead2() {
    String fileName = "C:\\Users\\Admin\\Desktop\\simpleWrite.xlsx";

    // 这里 也可以不指定class，返回一个list，然后读取第一个sheet，同步读取会自动finish
    List<Map<Integer, String>> listMap = EasyExcel.read(fileName).sheet().doReadSync();
    for (Map<Integer, String> data : listMap) {
      log.info("读取到数据:{}", data);
    }
  }

  @Test
  void test() {
    String s = String.format("第%s页", 1);
    System.out.println(s);
  }
}
