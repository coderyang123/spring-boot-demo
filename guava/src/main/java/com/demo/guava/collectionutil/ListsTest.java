package com.demo.guava.collectionutil;

import com.google.common.collect.Lists;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Lists工具方法使用
 *
 * @author yueyang
 * @since 2021-04-11 15:42:00
 */
public class ListsTest {

  /** 初始化List集合 */
  private static final List<Integer> LIST = Lists.newArrayList(1, 2, 3, 4, 5, 6);

  public static void main(String[] args) {
    //    initializeListTest();
    //    partitionTest();
    //    reverseTest();
  }

  /** 初始化集合 */
  public static void initializeListTest() {
    List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6);
    List<Object> list2 = Lists.newArrayListWithCapacity(10);
    List<Object> list3 = Lists.newArrayListWithExpectedSize(20);

    LinkedList<Object> list4 = Lists.newLinkedList();

    CopyOnWriteArrayList<Object> list5 = Lists.newCopyOnWriteArrayList();
  }

  /** 将集合按3个3个来分组 */
  public static void partitionTest() {
    List<List<Integer>> partition = Lists.partition(LIST, 3);
    System.out.println(partition);
  }

  /** 将集合元素反转 */
  public static void reverseTest() {
    List<Integer> reverse = Lists.reverse(LIST);
    System.out.println(reverse);
  }
}
