package com.demo.guava.collectionutil;

import com.google.common.collect.Maps;
import java.util.Map;

/**
 * Maps工具方法使用
 *
 * @author yueyang
 * @since 2021-04-11 16:09:00
 */
public class MapsTest {

  public static void main(String[] args) {}

  /** 初始化集合 */
  public static void initializeMapTest() {
    Map<String, Object> map = Maps.newHashMap();
    Map<String, Object> map1 = Maps.newHashMapWithExpectedSize(10);

    Map<Object, Object> map2 = Maps.newLinkedHashMap();
    Map<Object, Object> map3 = Maps.newLinkedHashMapWithExpectedSize(10);

    Map<Comparable<String>, Object> map4 = Maps.newTreeMap();

    Map<Object, Object> map5 = Maps.newConcurrentMap();
  }
}
