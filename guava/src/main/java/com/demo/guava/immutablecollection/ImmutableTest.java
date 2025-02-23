package com.demo.guava.immutablecollection;

import com.google.common.collect.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 不可变集合案例演示
 *
 * @author yueyang
 * @since 2021-02-28 22:48:00
 */
public class ImmutableTest {

  public static void main(String[] args) {
    immutableListTest();
    immutableSetTest();
    immutableMapTest();
  }

  /** 构造不可变List集合的四种方式 */
  public static void immutableListTest() {
    // 通过已经存在的集合创建
    List<String> list = Lists.newArrayList("a", "b", "c");
    ImmutableList<String> immutableList = ImmutableList.copyOf(list);

    // 通过初始值直接创建
    ImmutableList<String> immutableList1 = ImmutableList.of("a", "b", "c");

    // 用builder方式创建
    ImmutableList<Object> immutableList2 =
        ImmutableList.builder().add("a").add("b").add("c").build();

    // 用builder方式创建
    ImmutableList<Object> immutableList3 = ImmutableList.builder().addAll(list).build();
  }

  /** 构造不可变Set集合的四种方式 */
  public static void immutableSetTest() {
    // 通过已经存在的集合创建
    Set<String> set = Sets.newHashSet("a", "b", "c");
    ImmutableSet<String> immutableSet = ImmutableSet.copyOf(set);

    // 通过初始值直接创建
    ImmutableSet<String> immutableSet1 = ImmutableSet.of("a", "b", "c");

    // 用builder方式创建
    ImmutableSet<Object> immutableSet2 = ImmutableSet.builder().add("a").add("b").add("c").build();

    // 用builder方式创建
    ImmutableSet<Object> immutableList3 = ImmutableSet.builder().addAll(set).build();
  }

  /** 构造不可变Map集合的四种方式 */
  public static void immutableMapTest() {
    // 通过已经存在的集合创建
    Map<String, String> map = Map.of("k1", "v1", "k2", "v2");
    ImmutableMap<String, String> immutableMap = ImmutableMap.copyOf(map);

    // 通过初始值直接创建
    ImmutableMap<String, String> immutableMap1 = ImmutableMap.of("k1", "v1", "k2", "v2");

    // 用builder方式创建
    ImmutableMap<Object, Object> immutableMap2 = ImmutableMap.builder().put("k1", "v1").build();

    // 用builder方式创建
    ImmutableMap<Object, Object> immutableList3 = ImmutableMap.builder().putAll(map).build();
  }
}
