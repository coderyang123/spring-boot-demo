package com.demo.springsecurity.domain.dto;

import java.util.List;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户信息详情
 *
 * @author yueyang
 * @since 2021-04-26 22:02:00
 */
@Data
public class AuthenticationUserDetailsDTO implements UserDetails {

  /** 用户账号 */
  private String username;

  /** 密码 */
  private String password;

  /** 用户权限 */
  private List<GrantedAuthority> authorities;

  /** 账户是否未过期,过期无法验证 */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /**
   * 指定用户是否解锁,锁定的用户无法进行身份验证
   *
   * @return 是否解锁
   */
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  /**
   * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
   *
   * @return 是否已过期
   */
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /**
   * 是否可用 ,禁用的用户不能身份验证
   *
   * @return 是否可用
   */
  @Override
  public boolean isEnabled() {
    return true;
  }
}
