package com.demo.xstream;

import com.demo.xstream.converter.TeacherConverter;
import com.demo.xstream.domain.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.xml.sax.InputSource;

@SpringBootTest
class XstreamApplicationTests {

  /**
   * 格式化XML字符串
   *
   * @param xml 待格式化XML字符串
   * @return 格式化后XML字符串
   */
  private static String formatXml(String xml) {
    try {
      Transformer serializer = SAXTransformerFactory.newInstance().newTransformer();
      serializer.setOutputProperty(OutputKeys.INDENT, "yes");
      serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
      Source xmlSource = new SAXSource(new InputSource(new ByteArrayInputStream(xml.getBytes())));
      StreamResult res = new StreamResult(new ByteArrayOutputStream());
      serializer.transform(xmlSource, res);

      return res.getOutputStream().toString();
    } catch (Exception e) {
      return xml;
    }
  }

  /** 基础转换 */
  @Test
  void annotationTest() {
    // 构造数据
    Student student = new Student();
    student.setStudentName("tom");
    student.setType(0);
    student.setNotes(
        List.of(new Note("title1", "description1"), new Note("title2", "description2")));

    // 配置XStream
    XStream xstream = new XStream(new StaxDriver());
    xstream.autodetectAnnotations(true);
    xstream.addPermission(AnyTypePermission.ANY);

    // 对象转换成XML字符串
    String xml = xstream.toXML(student);

    // 打印内容
    System.out.println(xml);

    // 格式化XML字符串
    String formattedXML = formatXml(xml);

    // 打印内容
    System.out.println(formattedXML);

    // XML字符串转换成对象
    Student student1 = (Student) xstream.fromXML(xml);

    // 打印内容
    System.out.println(student1);
  }

  /** 复杂转换 */
  @Test
  void annotationTest2() {
    // 构造数据
    Detail detail = new Detail();
    detail.setPhoneNumber("12");
    detail.setSendId("12");
    detail.setSmsContent("12");
    detail.setServiceId("12");
    detail.setNeedReply("12");
    detail.setCompany("12");
    detail.setSendTime("12");
    detail.setComId("12");
    detail.setComCode("12");
    detail.setOperatorCode("12");

    Detail detail2 = new Detail();
    detail2.setPhoneNumber("2");
    detail2.setSendId("2");
    detail2.setSmsContent("2");
    detail2.setServiceId("2");
    detail2.setNeedReply("2");
    detail2.setCompany("2");
    detail2.setSendTime("2");
    detail2.setComId("2");
    detail2.setComCode("2");
    detail2.setOperatorCode("2");

    Massage massage =
        new Massage(
            "test-userid",
            "test-password",
            "test-permission",
            new Content(List.of(detail, detail2)));

    // 配置XStream
    XStream xstream = new XStream(new StaxDriver());
    xstream.autodetectAnnotations(true);
    xstream.addPermission(AnyTypePermission.ANY);

    // 对象转换成XML字符串
    String xml = xstream.toXML(massage);

    // 格式化后打印内容
    System.out.println(formatXml(xml));

    // XML字符串转换成对象
    Massage massage1 = (Massage) xstream.fromXML(xml);

    // 格式化后打印内容
    System.out.println(massage1);
  }

  /** 自定义转换器转换 */
  @Test
  void annotationTest3() {
    // 构造数据
    Teacher teacher = new Teacher(new Name("paul", "lucky", "lucy", "1"));

    // 配置XStream
    XStream xstream = new XStream(new StaxDriver());
    xstream.addPermission(AnyTypePermission.ANY);
    xstream.autodetectAnnotations(true);

    // 注册转换器
    xstream.registerConverter(new TeacherConverter());

    // 对象转换成XML字符串
    String xml = xstream.toXML(teacher);

    // 格式化后打印内容
    System.out.println(formatXml(xml));

    // XML字符串转换成对象
    Teacher teacher1 = (Teacher) xstream.fromXML(xml);

    // 格式化后打印内容
    System.out.println(teacher1);
  }
}
