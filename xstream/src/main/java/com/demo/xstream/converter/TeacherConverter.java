package com.demo.xstream.converter;

import com.demo.xstream.domain.Name;
import com.demo.xstream.domain.Teacher;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * 学生类转换器
 *
 * @author yueyang
 * @since 2022-06-09 09:43:00
 */
public class TeacherConverter implements Converter {

  /**
   * 检查支持的对象类型的序列化
   *
   * @param object 要转换的对象类型的类
   * @return 是否能转换
   */
  @Override
  public boolean canConvert(Class object) {
    return object.equals(Teacher.class);
  }

  /**
   * 序列化对象到XML
   *
   * @param value 要封送的对象
   * @param writer 要写入的流
   * @param context 允许XStream处理嵌套对象的上下文
   */
  @Override
  public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
    Teacher teacher = (Teacher) value;

    // 自定义第一个属性转换逻辑
    writer.startNode("name");
    writer.setValue(teacher.getName().getFirstName() + "-" + teacher.getName().getLastName());
    writer.endNode();

    // 自定义第二个属性转换逻辑
    writer.startNode("nick");
    writer.setValue(teacher.getName().getNickName() + "&" + teacher.getName().getCode());
    writer.endNode();
  }

  /**
   * 从XML对象反序列化
   *
   * @param reader 要从中读取文本的流
   * @param context 允许XStream处理嵌套对象的上下文
   * @return 反序列化的对象
   */
  @Override
  public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
    // 解析第一个属性值
    reader.moveDown();
    String[] nameArray = reader.getValue().split("-");
    reader.moveUp();

    // 解析第二个属性值
    reader.moveDown();
    String[] nickArray = reader.getValue().split("&");
    reader.moveUp();

    return new Teacher(new Name(nameArray[0], nameArray[1], nickArray[0], nickArray[1]));
  }
}
