package com.demo.mybatisplus.common.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 全局日期时间格式化配置
 *
 * @author yueyang
 * @since 2023-01-13 23:31:00
 */
@Configuration
public class DateTimeFormatConfig {

  private static final String TIME_PATTERN = "HH:mm:ss";
  private static final String DATE_PATTERN = "yyyy-MM-dd";
  private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

  @Bean
  public LocalTimeSerializer localTimeSerializer() {
    return new LocalTimeSerializer(DateTimeFormatter.ofPattern(TIME_PATTERN));
  }

  @Bean
  public LocalTimeDeserializer localTimeDeserializer() {
    return new LocalTimeDeserializer(DateTimeFormatter.ofPattern(TIME_PATTERN));
  }

  @Bean
  public LocalDateSerializer localDateSerializer() {
    return new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_PATTERN));
  }

  @Bean
  public LocalDateDeserializer localDateDeserializer() {
    return new LocalDateDeserializer(DateTimeFormatter.ofPattern(DATE_PATTERN));
  }

  @Bean
  public LocalDateTimeSerializer localDateTimeSerializer() {
    return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
  }

  @Bean
  public LocalDateTimeDeserializer localDateTimeDeserializer() {
    return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
  }

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer localTimeFormatSerializerCustomizer() {
    return builder -> builder.serializerByType(LocalTime.class, localTimeSerializer());
  }

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer localTimeFormatDeserializerCustomizer() {
    return builder -> builder.deserializerByType(LocalTime.class, localTimeDeserializer());
  }

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer localDateFormatSerializerCustomizer() {
    return builder -> builder.serializerByType(LocalDate.class, localDateSerializer());
  }

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer localDateFormatDeserializerCustomizer() {
    return builder -> builder.deserializerByType(LocalDate.class, localDateDeserializer());
  }

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer localDateTimeFormatSerializerCustomizer() {
    return builder -> builder.serializerByType(LocalDateTime.class, localDateTimeSerializer());
  }

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer localDateTimeFormatDeserializerCustomizer() {
    return builder -> builder.deserializerByType(LocalDateTime.class, localDateTimeDeserializer());
  }
}
