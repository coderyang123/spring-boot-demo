package com.demo.xstream.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息实体类
 *
 * @author yueyang
 * @since 2022-06-08 09:39:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Detail {

  @XStreamAlias("phoneNo")
  private String phoneNumber;

  @XStreamAlias("sendId")
  private String sendId;

  @XStreamAlias("smsContent")
  private String smsContent;

  @XStreamAlias("serviceId")
  private String serviceId;

  @XStreamAlias("needReply")
  private String needReply;

  @XStreamAlias("company")
  private String company;

  @XStreamAlias("sendTime")
  private String sendTime;

  @XStreamAlias("comId")
  private String comId;

  @XStreamAlias("comCode")
  private String comCode;

  @XStreamAlias("operatorCode")
  private String operatorCode;
}
