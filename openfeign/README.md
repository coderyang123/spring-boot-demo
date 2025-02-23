# Spring Boot 演示远程调用案例

> 本项目主要演示如何远程调用第三方接口

## 1 [`pom.xml`](./pom.xml)

主要添加的项目依赖

```xml
<!--feign远程接口调用-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
    <version>3.0.1</version>
</dependency>
```

## 2 [`application.yml`](./src/main/resources/application.yml)

```yaml
server:
  port: 8081

# 远程服务地址
file-server:
  ip: 127.0.0.1
  port: 8080

spring:
  servlet:
    multipart:
      enabled: true # 开启文件上传
      max-request-size: 200MB # 上传的总文件大小
      max-file-size: 50MB # 上传的单个文件大小
```

## 3 [`OpenFeignClient.java`](./src/main/java/com/demo/openfeign/feign/OpenFeignClient.java)

```java

@FeignClient(name = "feignclient", url = "http://${file-server.ip}:${file-server.port}/")
public interface OpenFeignClient {
    /**
     * 单文件上传
     *
     * @param multipartFile 表单名
     * @return 文件名及路径
     */
    @PostMapping(value = "/fileUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String fileUpload(@RequestPart("multipartFile") MultipartFile multipartFile);

    /**
     * 多文件上传
     *
     * @param multipartFiles 表单名
     * @return 文件名及路径
     */
    @PostMapping(value = "/multipartFileUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String multipartFileUpload(@RequestPart("multipartFiles") MultipartFile[] multipartFiles);

    /**
     * 测试Get方法
     *
     * @param id ID
     * @return String
     */
    @GetMapping(value = "/testGet/{id}")
    String testGet(@PathVariable String id);

    /**
     * 测试GET请求
     *
     * @param id ID
     * @return ID
     */
    @GetMapping("/testGet2")
    String testGet2(@RequestParam("id") String id);

    /**
     * 测试POST请求
     *
     * @param id ID
     * @return ID
     */
    @PostMapping("/testPost")
    String testPost(@RequestParam("id") String id);

    /**
     * 测试POST请求
     *
     * @param student student
     * @return ID
     */
    @PostMapping("/testPost2")
    String testPost2(@RequestBody Student student);
}
```

## 4 [`FileController.java`](./src/main/java/com/demo/openfeign/controller/FileController.java)

```java

@RestController
public class FileController {
    private final OpenFeignClient feignclient;

    public FileController(OpenFeignClient feignclient) {
        this.feignclient = feignclient;
    }

    /**
     * 单文件上传
     *
     * @param multipartFile 文件
     * @return 文件上传成功
     */
    @PostMapping("/fileUpload")
    public String fileUpload(@RequestPart("multipartFile") MultipartFile multipartFile) {
        return feignclient.fileUpload(multipartFile);
    }

    /**
     * 多文件上传
     *
     * @param multipartFiles 文件
     * @return 文件上传成功
     */
    @PostMapping("/multipartFileUpload")
    public String multipartFileUpload(@RequestPart("multipartFiles") MultipartFile[] multipartFiles) {
        return feignclient.multipartFileUpload(multipartFiles);
    }

    /**
     * 测试Get方法
     *
     * @param id ID
     * @return String
     */
    @GetMapping(value = "/testGet/{id}")
    String testGet(@PathVariable String id) {
        return feignclient.testGet(id);
    }

    /**
     * 测试GET请求
     *
     * @param id ID
     * @return ID
     */
    @GetMapping("/testGet2")
    public String testGet2(@RequestParam("id") String id) {
        return feignclient.testGet2(id);
    }

    /**
     * 测试POST请求
     *
     * @param id ID
     * @return ID
     */
    @PostMapping("/testPost")
    public String testPost(@RequestParam("id") String id) {
        return feignclient.testPost(id);
    }

    /**
     * 测试POST请求
     *
     * @param student student
     * @return ID
     */
    @PostMapping("/testPost2")
    String testPost2(@RequestBody Student student) {
        return feignclient.testPost2(student);
    }
}
```