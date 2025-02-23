package com.demo.email.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Component;

/**
 * 加密工具类
 *
 * @author yueyang
 * @since 2022-06-24 16:05:00
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JasyptUtils {

  private final StringEncryptor encryptor;

  /** 生成加密密码 */
  public void generatePassword() {
    // 你的邮箱授权码
    String password = "guvvwxzpbzlkbdgj1";

    // 加密后的密码(注意：配置上去的时候需要加 ENC(加密密码))
    String encryptPassword = encryptor.encrypt(password);
    String decryptPassword = encryptor.decrypt(encryptPassword);

    log.info("password = " + password);
    log.info("encryptPassword = " + encryptPassword);
    log.info("decryptPassword = " + decryptPassword);
  }
}
