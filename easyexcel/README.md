# Spring Boot 集成 alibaba easyexcel 实现导入导出 Excel 文件

> 本项目主要基于`Spring Boot`集成`easyexcel`演示导入导出`Excel`文件

## 1 基础框架搭建

### 1.1 主要依赖

```xml

<dependencys>
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>easyexcel</artifactId>
        <version>2.2.8</version>
    </dependency>
</dependencys>  
```

### 1.2 建立对应的实体类

1.2.1 [`ExcelStudentDTO.java`](./src/main/java/com/demo/easyexcel/domain/dto/ExcelStudentDTO.java) 学生实体类

### 1.3 编写日期格式转换器

1.3.1 [`LocalDateConverter.java`](src/main/java/com/demo/easyexcel/common/converter/LocalDateConverter.java) 日期格式转换器

### 1.4 在实体类日期字段注明使用转换器

1.4.1 `@ExcelProperty(converter = LocalDateConverter.class)`

### 1.5 在实体类日期字段注明表头名称

1.5.1 `@ExcelProperty("姓名")`

## 2 测试简单写入 Excel 文件

### 2.1 编写测试方法 [`EasyexcelApplicationTests.java`](./src/test/java/com/demo/easyexcel/EasyexcelApplicationTests.java)

2.1.1 `simpleWriteXlsx()` 写入.xlsx文件   
2.1.2 `simpleWriteXls()` 写入.xls文件

## 3 测试简单读取 Excel 文件

### 3.1 编写读取监听器 [`ExcelListener.java`](src/main/java/com/demo/easyexcel/common/listener/ExcelListener.java)

### 3.2 编写测试方法 [`EasyexcelApplicationTests.java`](./src/test/java/com/demo/easyexcel/EasyexcelApplicationTests.java)

3.2.1 `simpleWriteXlsx()` 读取.xlsx文件   
3.2.2 `simpleWriteXlsx2()` 读取.xlsx文件  
3.2.3 `simpleReadXls()` 读取.xls文件  
3.2.4 `synchronousRead()` 不使用监听器，读取.xlsx文件，同步的返回  
3.2.5 `synchronousRead2()` 不使用监听器，读取.xlsx文件，同步的返回

## 4 导入 Excel 数据到数据库

### 4.1 添加依赖

```xml

<dependencys>
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.4.1</version>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencys>
```

### 4.2 新建数据库表

[`sql`](src/main/resources/sql/easyexcel.sql)

### 4.3 添加数据库相应配置

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://IP:3306/easyexcel?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT%2B8
    username: root
    password: root
```

### 4.4 建立 Excel 文件对应的实体类

4.4.1 [`DictDO.class`](./src/main/java/com/demo/easyexcel/domain/entity/DictDO.java)

### 4.5 建立对应的`Mapper`

4.5.1 [`DictDoMapper.class`](./src/main/java/com/demo/easyexcel/mapper/DictDoMapper.java)

### 4.6 建立对应的 XML

4.6.1 [`DictDoMapper.class`](./src/main/resources/mapper/DictDoMapper.xml)

### 4.7 建立对应的字典 EXCEL 文件读取监听器

4.7.1 [`ExcelDictDoListener.class`](src/main/java/com/demo/easyexcel/common/listener/ExcelDictDoListener.java)

### 4.8 建立对应的`Service`

4.8.1 [`DictDoService.class`](./src/main/java/com/demo/easyexcel/service/DictDoService.java)

### 4.9 建立对应的`ServiceImpl`

4.9.1 [`DictDoService.class`](./src/main/java/com/demo/easyexcel/service/impl/DictDoServiceImpl.java)

### 4.10 建立对应的`Controller`

4.10.1 [`DictDoController.class`](./src/main/java/com/demo/easyexcel/controller/DictDoController.java)

## 5 导出 Excel 文件到本地客户端

### 5.1 新增查询所有字典的方法 [`DictDoService.class`](./src/main/java/com/demo/easyexcel/service/impl/DictDoServiceImpl.java)

5.1.1 `listDictDo()`

### 5.2 新增查询所有字典的接口 [`DictDoController.class`](./src/main/java/com/demo/easyexcel/controller/DictDoController.java)

5.2.1 `exportExcel()`

## 6 异步导出 Excel 文件到服务器

6.1.1 `asyncExportExcel()`
