package com.demo.fastjson;

import static org.junit.jupiter.api.Assertions.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.demo.fastjson.entity.*;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FastjsonApplicationTests {

  /** 集合转JSON字符串 */
  @Test
  void testToJSONString() {
    UserDO userDO = new UserDO("tom", "111", 18, LocalDate.now(), "18223321133");
    UserDO userDO2 = new UserDO("jerry", "222", 18, LocalDate.now(), "18323321133");
    UserDO userDO3 = new UserDO("lily", "333", 18, LocalDate.now(), "18523321133");
    List<UserDO> list = List.of(userDO, userDO2, userDO3);
    String string = JSON.toJSONString(list);

    assertEquals(
        "[{\"age\":18,\"birthday\":\"2022-06-16\",\"password\":\"111\",\"phone\":\"18223321133\",\"username\":\"tom\"},{\"age\":18,\"birthday\":\"2022-06-16\",\"password\":\"222\",\"phone\":\"18323321133\",\"username\":\"jerry\"},{\"age\":18,\"birthday\":\"2022-06-16\",\"password\":\"333\",\"phone\":\"18523321133\",\"username\":\"lily\"}]",
        string);
  }

  /** 集合转JSON字符串，排除某些字段，指定日期格式和序列化顺序 */
  @Test
  void testToJSONString2() {
    User2DO userDO = new User2DO("tom", "111", 18, LocalDate.now(), "18223321133");
    List<User2DO> list = List.of(userDO);
    String string = JSON.toJSONString(list);

    assertEquals(
        "[{\"phone\":\"18223321133\",\"birth_day\":\"2022/06/16\",\"age\":18,\"user_name\":\"tom\"}]",
        string);
  }

  /**
   * 集合转JSON字符串，只序列化值
   * 示例：[["18223321133","29/03/2021",18,"tom"],["18323321133","29/03/2021",18,"jerry"],["18523321133","29/03/2021",18,"lily"]]
   */
  @Test
  void testToJSONString3() {
    User2DO userDO = new User2DO("tom", "111", 18, LocalDate.now(), "18223321133");
    User2DO userDO2 = new User2DO("jerry", "222", 18, LocalDate.now(), "18323321133");
    List<User2DO> list = List.of(userDO, userDO2);
    String string = JSON.toJSONString(list, SerializerFeature.BeanToArray);

    assertEquals(
        "[[\"18223321133\",\"2022/06/16\",18,\"tom\"],[\"18323321133\",\"2022/06/16\",18,\"jerry\"]]",
        string);
  }

  /** 复杂对象转JSON字符串 */
  @Test
  void testToJSONString4() {
    HumanDO humanDO = new HumanDO(1, new UserDO("tom", "123", 18, LocalDate.now(), "18207309718"));
    String string = JSONObject.toJSONString(humanDO);

    assertEquals(
        "{\"id\":1,\"userDO\":{\"age\":18,\"birthday\":\"2022-06-16\",\"password\":\"123\",\"phone\":\"18207309718\",\"username\":\"tom\"}}",
        string);
  }

  /** 复杂对象转JSON字符串 */
  @Test
  void testToJSONString5() {
    Class1DO class1DO =
        new Class1DO(
            1,
            List.of(
                new UserDO("tom", "123", 18, LocalDate.now(), "18207309718"),
                new UserDO("tony", "123", 18, LocalDate.now(), "18207309718")));
    String string = JSONObject.toJSONString(class1DO);

    assertEquals(
        "{\"id\":1,\"users\":[{\"age\":18,\"birthday\":\"2022-06-16\",\"password\":\"123\",\"phone\":\"18207309718\",\"username\":\"tom\"},{\"age\":18,\"birthday\":\"2022-06-16\",\"password\":\"123\",\"phone\":\"18207309718\",\"username\":\"tony\"}]}",
        string);
  }

  /** 复杂对象转JSON字符串 */
  @Test
  void testToJSONString6() {
    Class2DO class2DO =
        new Class2DO(
            List.of(
                new UserDO("tom", "123", 18, LocalDate.now(), "18207309718"),
                new UserDO("tony", "123", 18, LocalDate.now(), "18207309718")));

    String string = JSONObject.toJSONString(class2DO);

    assertEquals(
        "{\"users\":[{\"age\":18,\"birthday\":\"2022-06-16\",\"password\":\"123\",\"phone\":\"18207309718\",\"username\":\"tom\"},{\"age\":18,\"birthday\":\"2022-06-16\",\"password\":\"123\",\"phone\":\"18207309718\",\"username\":\"tony\"}]}",
        string);
  }

  /** JSON字符串转对象（如注解配置了字段的name或者format，则须保持一致，否则转换异常） */
  @Test
  void testParseObject() {
    String userString =
        "{'age':18,'birth_day':'2021/03/29','pass_word':'111','phone':'18223321133','user_name':'tom'}";
    User2DO user2DO = JSON.parseObject(userString, User2DO.class);

    assertEquals(new User2DO("tom", "111", 18, LocalDate.of(2021, 3, 29), "18223321133"), user2DO);
  }

  /** JSON字符串转对象，部分值不被反序列化 */
  @Test
  void testParseObject2() {
    String userString =
        "{'age':18,'birth_day':'2021/03/29','pass_word':'111','phone':'18223321133','user_name':'tom'}";
    User3DO userDO = JSON.parseObject(userString, User3DO.class);

    assertEquals(new User3DO("tom", null, 18, LocalDate.of(2021, 3, 29), "18223321133"), userDO);
  }

  /** JSON字符串转复杂对象 */
  @Test
  void testParseObject3() {
    String userString =
        "{'id':1,'userDO':{'age':18,'birthday':'2022-06-16','password':'123','phone':'18207309718','username':'tom'}}";
    HumanDO humanDO = JSON.parseObject(userString, HumanDO.class);

    assertEquals(
        new HumanDO(1, new UserDO("tom", "123", 18, LocalDate.now(), "18207309718")), humanDO);
  }

  /** JSON字符串转复杂对象 */
  @Test
  void testParseObject4() {
    String classString =
        "{'id':1,'users':[{'age':18,'birthday':'2022-06-16','password':'123','phone':'18207309718','username':'tom'}]}";
    Class1DO class1DO = JSON.parseObject(classString, Class1DO.class);

    assertEquals(
        new Class1DO(1, List.of(new UserDO("tom", "123", 18, LocalDate.now(), "18207309718"))),
        class1DO);
  }

  /** JSON字符串转复杂对象 */
  @Test
  void testParseObject5() {
    String classString =
        "{'id':1,'users':[{'age':18,'birthday':'2022-06-16','password':'123','phone':'18207309718','username':'tom'}]}";
    Class1DO class1DO = JSON.parseObject(classString, Class1DO.class);

    assertEquals(
        new Class1DO(1, List.of(new UserDO("tom", "123", 18, LocalDate.now(), "18207309718"))),
        class1DO);
  }

  /** JSON字符串转复杂对象 */
  @Test
  void testParseObject7() {
    String classString = "{'id':1,'users': ''}";
    Class1DO class1DO = JSON.parseObject(classString, Class1DO.class);

    assertEquals(1, class1DO.getId());
    assertNull(class1DO.getUserDOList());
  }

  /** JSON字符串转JSON对象 */
  @Test
  void test6() {
    String userString =
        "{'age':18,'birthday':'2021/03/29','password':'111','phone':'18223321133','username':'tom'}";
    JSONObject jsonObject = JSONObject.parseObject(userString);

    Integer age = jsonObject.getInteger("age");
    assertEquals(18, age);

    String username = jsonObject.getString("username");
    assertEquals("tom", username);
  }

  /** JSON字符串转JSON数组 */
  @Test
  void test7() {
    String userString =
        "[{'age':18,'birthday':'2021/03/29','password':'111'}, {'age':19,'birthday':'2021/03/30','password':'222'}]";
    JSONArray jsonArray = JSONObject.parseArray(userString);
    String string = jsonArray.toString();

    assertEquals(
        "[{\"birthday\":\"2021/03/29\",\"password\":\"111\",\"age\":18},{\"birthday\":\"2021/03/30\",\"password\":\"222\",\"age\":19}]",
        string);
  }

  /** 复杂JSON字符串解析 */
  @Test
  void test8() {
    String userString =
        "{'username':'tom','info':{'age':18,'user_info':[{'birthday':'2021/03/29','password':'111'}, {'birthday':'2021/03/30','password':'222'}]}}";
    JSONObject jsonObject = JSONObject.parseObject(userString);

    String username = jsonObject.getString("username");
    assertEquals("tom", username);

    JSONObject info = jsonObject.getJSONObject("info");
    Integer age = info.getInteger("age");
    assertEquals(18, age);

    JSONArray userInfo = info.getJSONArray("user_info");
    assertEquals(
        "[{\"birthday\":\"2021/03/29\",\"password\":\"111\"},{\"birthday\":\"2021/03/30\",\"password\":\"222\"}]",
        userInfo.toJSONString());
  }

  /** 对象转JSON字符串，空集合属性转成空数组 */
  @Test
  void test9() {
    Class1DO class1DO = new Class1DO(1, null);

    String jsonString = JSON.toJSONString(class1DO, SerializerFeature.WriteNullListAsEmpty);
    assertEquals("{\"id\":1,\"users\":[]}", jsonString);
  }

  /** JSON字符串转字符串数组 */
  @Test
  void test10() {
    String userString = "['age' ,'password', 'age', 'birthday', 'password']";

    String[] array = JSONObject.parseArray(userString, String.class).toArray(new String[0]);
    assertArrayEquals(new String[] {"age", "password", "age", "birthday", "password"}, array);
  }
}
