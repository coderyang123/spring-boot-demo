package com.demo.properties2.factory;

import java.io.IOException;
import java.util.List;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

/**
 * YML文件解析工厂
 *
 * @author yueyang
 * @since 2022-01-07 15:35:00
 */
public class YmlPropertySourceFactory extends DefaultPropertySourceFactory {

  @Override
  public PropertySource<?> createPropertySource(String name, EncodedResource resource)
      throws IOException {
    List<PropertySource<?>> sources =
        new YamlPropertySourceLoader()
            .load(resource.getResource().getFilename(), resource.getResource());
    return sources.get(0);
  }
}
