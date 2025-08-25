package com.demo.rest.template.controller;

import com.demo.rest.template.entity.TestRequestParam;
import com.demo.rest.template.entity.TestResponse;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 测试
 *
 * @author yueyang
 * @since 2025-08-25 22:52:00
 */
@Slf4j
@RestController
@RequestMapping("/api/rest")
public class TestController {

  @PostMapping("/test")
  public TestResponse test(@RequestBody TestRequestParam testRequestParam) {
    log.info("test request param:{}", testRequestParam);
    TestResponse testResponse = new TestResponse();
    TestResponse.ReturnData returnData = new TestResponse.ReturnData();
    returnData.setSuccess(true);
    returnData.setMessage("Request successful");
    TestResponse.TestData testData = new TestResponse.TestData();
    testData.setId("1");
    testData.setName("Sample Data");
    testData.setDescription("This is a sample description.");
    returnData.setData(List.of(testData));
    testResponse.setReturnData(returnData);
    return testResponse;
  }
}
