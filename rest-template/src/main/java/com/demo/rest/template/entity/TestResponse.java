package com.demo.rest.template.entity;

import java.util.List;
import lombok.Data;

/**
 * 测试
 *
 * @author yueyang
 * @since 2025-08-25 23:03:00
 */
@Data
public class TestResponse {
  private ReturnData returnData;

  @Data
  public static class ReturnData {
    private Boolean success;
    private String message;
    private List<TestData> data;
  }

  @Data
  public static class TestData {
    private String id;
    private String name;
    private String description;
  }
}
