package com.demo.regex;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RegexApplicationTests {

  /** 目标字符串和正则表达式是否匹配 */
  @Test
  void testMatches() {
    boolean bool = Pattern.matches("lo*k", "look");
    assertTrue(bool);

    boolean bool2 = Pattern.matches("lo*k", "lk");
    assertTrue(bool2);
  }

  /** 目标字符串和正则表达式是否匹配 */
  @Test
  void testSplit() {
    Pattern pattern = Pattern.compile("a*b");

    String[] splits = pattern.split("-ab-aab-aab-");
    assertArrayEquals(new String[] {"-", "-", "-", "-"}, splits);

    String[] splits2 = pattern.split("ab-aab-aab");
    assertArrayEquals(new String[] {"", "-", "-"}, splits2);
  }

  /** 返回整个目标字符串与 Pattern 是否完全匹配 */
  @Test
  void testMatches2() {
    Pattern pattern = Pattern.compile("a*b");

    Matcher matcher = pattern.matcher("ab");
    boolean bool = matcher.matches();
    assertTrue(bool);

    Matcher matcher2 = pattern.matcher("abc");
    boolean bool2 = matcher2.matches();
    assertFalse(bool2);

    Matcher matcher3 = pattern.matcher("bab");
    boolean bool3 = matcher3.matches();
    assertFalse(bool3);
  }

  /** 返回整个目标字符串与 Pattern 是否部分匹配 */
  @Test
  void testLookingAt() {
    Pattern pattern = Pattern.compile("a*b");

    Matcher matcher = pattern.matcher("ab");
    boolean bool = matcher.lookingAt();
    assertTrue(bool);

    Matcher matcher2 = pattern.matcher("abc");
    boolean bool2 = matcher2.lookingAt();
    assertTrue(bool2);

    Matcher matcher3 = pattern.matcher("babc");
    boolean bool3 = matcher3.lookingAt();
    assertTrue(bool3);
  }

  /** 将现有的 Matcher 对象应用于一个新的字符序列 */
  @Test
  void testReset() {
    String[] mails = {
      "kongyeeku@163.com", "kongyeeku@gmail.com", "ligang@crazyit.org", "wawa@abc.xx"
    };
    Pattern pattern = Pattern.compile("\\w{3,20}@\\w+\\.(com|org|cn|net|gov)");
    Matcher matcher = null;
    for (String mail : mails) {
      if (Objects.isNull(matcher)) {
        matcher = pattern.matcher(mail);
      } else {
        matcher.reset(mail);
      }
      String isMatch = mail + (matcher.matches() ? "是" : "不是") + "一个有效的邮件地址！";
      System.out.println(isMatch);
    }
  }

  /** 返回目标字符串中是否包含与 Pattern 匹配的子串，包含则使用group()获取字串 */
  @Test
  void testFind() {
    Pattern pattern = Pattern.compile("a*b");

    // 返回整个目标字符串与 Pattern 是否匹配
    Matcher matcher = pattern.matcher("abcabcabc");
    while (matcher.find()) {
      String str = matcher.group();
      System.out.println("匹配到的目标字符串：" + str);
      assertEquals("ab", str);
    }
  }

  /** 从指定索引开始，返回目标字符串中是否包含与 Pattern 匹配的子串，包含则使用group()获取字串， */
  @Test
  void testFind2() {
    Pattern pattern = Pattern.compile("a*b");

    // 返回整个目标字符串与 Pattern 是否匹配
    Matcher matcher = pattern.matcher("abcabcabc");
    int startFind = 2;
    while (matcher.find(startFind)) {
      String str = matcher.group();
      int start = matcher.start();
      int end = matcher.end();
      startFind = end;
      System.out.println("匹配到的目标字符串：" + str + "，子串的起始位置：" + start + "，其结束位置：" + end);
      assertEquals("ab", str);
    }
  }

  /** 使用指定字符串替换全部匹配的子串 */
  @Test
  void testReplaceAll() {
    Pattern pattern = Pattern.compile("re\\w*");

    Matcher matcher = pattern.matcher("Java has regular expressions in 1.4");
    String str = matcher.replaceAll("-");
    System.out.println(str);
  }

  /** 使用每个匹配的子串的起始下标值替换匹配的子串 */
  @Test
  void testReplaceAll2() {
    Pattern pattern = Pattern.compile("re\\w*");

    Matcher matcher = pattern.matcher("Java has regular expressions in 1.4");
    String str = matcher.replaceAll(replacer -> String.valueOf(replacer.start()));
    System.out.println(str);
  }

  /** 使用给定字符串替换首个匹配的子串 */
  @Test
  void testReplaceFirst() {
    Pattern pattern = Pattern.compile("re\\w*");

    Matcher matcher = pattern.matcher("Java has regular expressions in 1.4");
    String str = matcher.replaceFirst("-");
    System.out.println(str);
  }

  /** 使用首个匹配的子串的起始下标值替换匹配的子串 */
  @Test
  void testReplaceFirst2() {
    Pattern pattern = Pattern.compile("re\\w*");

    Matcher matcher = pattern.matcher("Java has regular expressions in 1.4");
    String str = matcher.replaceFirst(replacer -> String.valueOf(replacer.start()));
    System.out.println(str);
  }

  /** 验证手机号码 */
  @Test
  void testVerifyPhoneNumber() {
    String regex = "(0\\d{2,3}-?\\d{7,8})|(0\\d{2,3}\\s?\\d{7,8})|(13\\d{9})|(15[1089]\\d{8})";
    Pattern pattern = Pattern.compile(regex);

    Matcher matcher = pattern.matcher("0730-1234567");
    boolean bool = matcher.matches();
    assertTrue(bool);

    Matcher matcher2 = pattern.matcher("0730 1234567");
    boolean bool2 = matcher2.matches();
    assertTrue(bool2);

    Matcher matcher3 = pattern.matcher("07301234567");
    boolean bool3 = matcher3.matches();
    assertTrue(bool3);

    Matcher matcher4 = pattern.matcher("13874098821");
    boolean bool4 = matcher4.matches();
    assertTrue(bool4);

    Matcher matcher5 = pattern.matcher("15114098821");
    boolean bool5 = matcher5.matches();
    assertTrue(bool5);

    Matcher matcher6 = pattern.matcher("15254098821");
    boolean bool6 = matcher6.matches();
    assertFalse(bool6);
  }

  /** 去除短信签名 */
  @Test
  void testVerifySignature() {
    String regex = "(【(\\d|\\w|\\s|[\\u4E00-\\u9FA5])*】)|(\\[(\\d|\\w|\\s|[\\u4E00-\\u9FA5])*\\])";
    Pattern pattern = Pattern.compile(regex);

    Matcher matcher = pattern.matcher("【te s t123】【测试】短信内容[test]");
    String result = matcher.replaceAll("");
    assertEquals("短信内容", result);
  }
}
