package com.demo.guava.multiset;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.Iterator;
import java.util.Set;

/**
 * 新型集合
 *
 * @author yueyang
 * @since 2021-02-28 23:09:00
 */
public class MultisetTest {

  public static void main(String[] args) {
    multiset();
  }

  /** 新型集合的常用方法 */
  public static void multiset() {
    // 类似没有顺序限制的ArrayList，或者没有元素限制的HashSet
    HashMultiset<String> multiset = HashMultiset.create();

    // 添加单个元素
    multiset.add("a");
    multiset.add("a");

    // 返回一个包含所有元素的迭代器，包含重复元素
    Iterator<String> iterator = multiset.iterator();

    // 返回所有元素个数，包含重复元素
    int size = multiset.size();

    // 统计给定元素出现的次数
    int count = multiset.count("a");

    // 和Map的entrySet类似，每个元素是个Map，key是元素，value是元素出现的个数
    Set<Multiset.Entry<String>> entrySet = multiset.entrySet();

    // 返回所有不重复元素的Set
    Set<String> elementSet = multiset.elementSet();
  }
}
