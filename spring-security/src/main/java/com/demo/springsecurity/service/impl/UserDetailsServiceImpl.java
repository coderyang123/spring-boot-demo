package com.demo.springsecurity.service.impl;

import com.demo.springsecurity.domain.dto.AuthenticationUserDetailsDTO;
import com.demo.springsecurity.domain.entity.UserDO;
import com.demo.springsecurity.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * SpringSecurity用户信息校验
 *
 * @author yueyang
 * @since 2021-04-23 12:10:00
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserService userService;

  public UserDetailsServiceImpl(UserService userService) {
    this.userService = userService;
  }

  /**
   * 用户信息校验
   *
   * @param username 用户名
   * @return 用户信息
   * @throws UsernameNotFoundException UsernameNotFoundException
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDO user = userService.selectUserByUserName(username);
    if (Objects.isNull(user)) {
      throw new UsernameNotFoundException("登录用户：" + username + "不存在");
    }

    // 将数据库的roles解析为UserDetails的权限集
    AuthenticationUserDetailsDTO authenticationUserDetailsDTO = new AuthenticationUserDetailsDTO();
    BeanUtils.copyProperties(user, authenticationUserDetailsDTO);

    // 用户角色
    List<GrantedAuthority> roleList =
        AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles());

    // 权限列表
    List<GrantedAuthority> authorityList =
        AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities());

    // 赋值用户角色和权限列表
    List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
    grantedAuthorityList.addAll(roleList);
    grantedAuthorityList.addAll(authorityList);
    authenticationUserDetailsDTO.setAuthorities(grantedAuthorityList);

    return authenticationUserDetailsDTO;
  }
}
