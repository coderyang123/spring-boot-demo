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
@XStreamAlias("root")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Massage {

  @XStreamAlias("userid")
  private String userId;

  @XStreamAlias("password")
  private String password;

  @XStreamAlias("permission")
  private String permission;

  @XStreamAlias("body")
  private Content content;
}
