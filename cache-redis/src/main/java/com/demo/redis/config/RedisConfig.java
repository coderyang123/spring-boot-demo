package com.demo.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.Duration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis配置类
 *
 * @author yueyang
 * @since 2021-04-08 23:17:00
 */
@Configuration
@EnableCaching
public class RedisConfig {

  /**
   * 设置RedisTemplate规则
   *
   * @param redisConnectionFactory redisConnectionFactory
   * @return RedisTemplate
   */
  @Bean
  public RedisTemplate<String, Object> redisTemplate(
      LettuceConnectionFactory redisConnectionFactory) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

    // 设置连接池工厂
    redisTemplate.setConnectionFactory(redisConnectionFactory);

    // 配置key的序列化方式
    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    redisTemplate.setKeySerializer(stringRedisSerializer);

    // 配置value的序列化方式
    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer =
        new Jackson2JsonRedisSerializer<>(Object.class);

    // 序列化时将类的数据类型存入json，以便反序列化的时候转换成正确的类型
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.activateDefaultTyping(
        LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);

    // 解决jackson2无法反序列化LocalDateTime的问题
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    objectMapper.registerModule(new JavaTimeModule());

    // 指定要序列化的域，field、get和set，以及修饰符范围，ANY是都有包括private和public
    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

    jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

    redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
    return redisTemplate;
  }

  /**
   * 自定义key规则
   *
   * @return KeyGenerator
   */
  @Bean
  public KeyGenerator keyGenerator() {
    return (target, method, params) -> {
      StringBuilder sb = new StringBuilder();
      sb.append(target.getClass().getName());
      sb.append(method.getName());
      for (Object obj : params) {
        sb.append(obj.toString());
      }
      return sb.toString();
    };
  }

  /**
   * 设置CacheManager缓存规则
   *
   * @param factory factory
   * @return CacheManager
   */
  @Bean
  public CacheManager cacheManager(RedisConnectionFactory factory) {
    RedisSerializer<String> redisSerializer = new StringRedisSerializer();

    // 配置key的序列化方式
    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer =
        new Jackson2JsonRedisSerializer<>(Object.class);

    // 解决查询缓存转换异常的问题
    ObjectMapper objectMapper = new ObjectMapper();

    // 指定要序列化的域，field、get和set，以及修饰符范围，ANY是都有包括private和public
    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

    // 序列化时将类的数据类型存入json，以便反序列化的时候转换成正确的类型
    objectMapper.activateDefaultTyping(
        LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
    jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

    // 配置序列化（解决乱码的问题），过期时间200秒
    RedisCacheConfiguration config =
        RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofSeconds(200))
            .serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    jackson2JsonRedisSerializer))
            .disableCachingNullValues();

    return RedisCacheManager.builder(factory).cacheDefaults(config).build();
  }
}
