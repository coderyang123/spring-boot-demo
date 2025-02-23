package com.demo.guava.bloomfilter;

import com.google.common.hash.PrimitiveSink;

/**
 * 布隆过滤器演示
 *
 * @author yueyang
 * @since 2021-02-27 23:48:00
 */
public class BloomFilterTest {

  public static void main(String[] args) {
    bloomFilterTest();
  }

  public static void bloomFilterTest() {
    // 将任意数据类型转换为java基础类型，默认转换为byte数组
    com.google.common.hash.BloomFilter<Integer> integerBloomFilter =
        com.google.common.hash.BloomFilter.create(
            (Integer from, PrimitiveSink primitiveSink) -> primitiveSink.putInt(from),
            // 预计插入的元素总数
            10000L,
            // 期望误判率（0.0 ~ 1.0），值越大则认为检测的假阳性的可能性越大
            0.5);

    // 向布隆过滤器中添加初始化元素
    for (int i = 0; i < 10000; i++) {
      integerBloomFilter.put(i);
    }

    // 检测给定元素是否可能在该布隆过滤器中
    boolean bool = integerBloomFilter.mightContain(66666);
    System.out.println(bool);
  }
}
