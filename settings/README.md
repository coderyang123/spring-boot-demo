# Intellij IDEA 设置

# 🔌 1.插件

| 插件                               | 作用            |
|----------------------------------|---------------|
| `Alibaba Java Coding Guidelines` | 阿里巴巴代码规范提示    |
| `CamelCase`                      | 变量命名风格转换      |
| `GenerateSerialVersionUID`       | 生成实体类序列号      |
| `google-java-format`             | 谷歌代码格式化       |
| `Grep Console`                   | 输出日志美化        |
| `Maven Helper`                   | 依赖冲突分析        |
| `MybatisX`                       | Mybatis辅助     |
| `Rainbow Brackets`               | 括号美化          |
| `RestfulTool`                    | API接口查找       |
| `Translation`                    | 翻译            |
| `CodeGlance pro`                 | 右侧小地图         |
| `SequenceDiagram`                | 显示方法调用时序图     |
| `GitHub Copilot`                 | 代码提示          |
| `Json Helper`                    | JSON字符串格式化/压缩 |

# 📄 2.全局配置 File -> New Projects Setup -> Settings For New Projects

## 2.1 代码模板

```
#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")
/**
  * TODO
  *
  * @author yueyang
  * @since ${YEAR}-${MONTH}-${DAY} ${HOUR}:${MINUTE}:00
  */
public class ${NAME} {
}
```

![](picture/template.jpg)

## 2.2 编码格式

![](picture/encoding.jpg)

## 2.3 行内提示

![](picture/inlay.jpg)

## 2.4 大小写切换

![](picture/camel_case.jpg)

## 2.5 设置编译堆区大小

![](picture/compiler.jpg)

## 2.6 应用注解

![](picture/annotation.jpg)

## 2.7 依赖下载地址

`https://maven.aliyun.com/nexus/content/groups/public`  
![](picture/jar_repository.jpg)

## 2.8 自动导包

![](picture/auto_import.jpg)

## 2.9 Markdown默认预览

![](picture/preview.jpg)

## 2.10 控制台字体颜色

`21D86E` `CE5153`
![](picture/color.png)

# 🛠️ 3.项目配置 File -> Settings

## 3.1 字体

![](picture/字体.png)

## 3.2 代码提示

![](picture/代码提示.png)

## 3.3 代码缩进风格

![](picture/代码缩进风格.png)

## 3.4 屏蔽无用文件

![](picture/屏蔽无用文件.png)

## 3.5 `maven`地址

![](picture/maven地址.png)

## 3.6 选中复制粘贴整行

![](picture/选中复制粘贴整行.png)

## 3.7 删除行

![](picture/delete.png)

## 3.8 代码补全

![](picture/code_completion.png)

## 3.9 代码注释

![](picture/code_comment.png)

## 3.10 `Git`配置

![](picture/Git地址.png)

## 3.11 `SVN`配置

![](picture/svn配置.png)

## 3.12 设置编译JDK版本

![](picture/JDK版本.png)

# ✳️ 4.DEBUG技巧

## 4.1 断点跳转(Run to Cursor): 鼠标点击代码行数

## 4.2 删除并重置调用栈，效果类似 DEBUG 的“撤销”: DEBUG 中 terminal 点击红色x(Drop Frame)

## 4.3 固定表达式跟踪 : Variables窗口点击”+号” New Watch，输入条件

## 4.4 多线程debug : 右键点击断点，将Suspend由All修改为Thread