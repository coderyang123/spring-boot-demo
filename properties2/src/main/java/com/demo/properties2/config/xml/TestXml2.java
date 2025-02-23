package com.demo.properties2.config.xml;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Properties配置类
 *
 * @author yueyang
 * @since 2022-01-07 14:40:00
 */
@Configuration
// 读取配置文件（位于项目resources目录下）
@PropertySource("file:/Users/yueyang/Desktop/config2.xml")
public class TestXml2 {}
