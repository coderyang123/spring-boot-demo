package com.demo.guava.collectionutil;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;

/**
 * Sets工具方法使用
 *
 * @author yueyang
 * @since 2021-03-03 22:52:00
 */
public class SetsTest {

  /** 初始化空Set集合 */
  private static final Set<Integer> SET1 = Sets.newHashSet();

  /** 初始化Set集合 */
  private static final Set<Integer> SET2 = Sets.newHashSet(1, 2, 3);

  /** 初始化Set集合 */
  private static final Set<Integer> SET3 = Sets.newHashSet(3, 4, 5);

  /** 初始化空List集合 */
  private static final List<Integer> LIST1 = Lists.newArrayList();

  public static void main(String[] args) {
    unionTest();
    //    intersectionTest();
    //    differenceTest();
    //    symmetricDifferenceTest();
    //    powerSet();
    //    cartesianProductSet();
  }

  /** 取两个Set的并集 */
  public static void unionTest() {
    Sets.SetView<Integer> union = Sets.union(SET2, SET3);
    System.out.println(union);
  }

  /** 取两个Set的交集 */
  public static void intersectionTest() {
    Sets.SetView<Integer> intersection = Sets.intersection(SET2, SET3);
    System.out.println(intersection);
  }

  /** 取两个Set的差集，set2里存在但是set3里不存在 */
  public static void differenceTest() {
    Sets.SetView<Integer> difference = Sets.difference(SET2, SET3);
    System.out.println(difference);
  }

  /** 取两个Set的差集，set2里存在但是set3里不存在或者set2里不存在但是set3里存在(和取交集相反) */
  public static void symmetricDifferenceTest() {
    Sets.SetView<Integer> symmetricDifference = Sets.symmetricDifference(SET2, SET3);
    System.out.println(symmetricDifference);
  }

  /** 取Set集合里任意元素组成的新Set集合 */
  public static void powerSet() {
    Set<Set<Integer>> powerSet = Sets.powerSet(SET2);
    powerSet.forEach(System.out::println);
  }

  /** 取两个Set集合里的笛卡尔积 */
  public static void cartesianProductSet() {
    Set<List<Integer>> cartesianProduct = Sets.cartesianProduct(SET2, SET3);
    cartesianProduct.forEach(System.out::println);
  }
}
