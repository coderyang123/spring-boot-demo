# Spring Boot 应用打成`War`包

## 1 主要依赖

```xml

<dependencys>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>

  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
  </dependency>
</dependencys>
```

## 2 修改`pom`文件打包方式改为`war`

`<packaging>war</packaging>`

## 3 添加`Tomcat`依赖配置，覆盖`SpringBoot`自带的`Tomcat`依赖

```xml

<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-tomcat</artifactId>
  <scope>provided</scope>
</dependency>
```

## 4 修改启动类 [`PackageWarApplication.java`](./src/main/java/com/demo/packagewar/PackageWarApplication.java)

### 4.1 继承`SpringBootServletInitializer`重写`configure`方法

### 4.2 简单编写个测试接口

## 5 服务器安装`JDK`（11.0.11版本）

### 5.1 将`JDK`压缩包上传至`/usr/local/src`，然后解压`tar -zxvf 包名 -C /usr/local/`

### 5.2 配置`JDK`环境变量

5.2.1 新建`java.sh`文件：`vim /etc/profile.d/java.sh`

5.2.2 内容如下：

```shell script
export JAVA_HOME=/usr/local/jdk-11.0.11
export CLASS_PATH="$JAVA_HOME/lib:$JAVA_HOME/jre/lib"
export PATH=$PATH:$JAVA_HOME/bin
```

5.2.3 刷新配置文件：`source /etc/profile`

5.2.4 检验安装是否成功：`java -version`

## 6 服务器安装`Tomcat`（9.0.45版本）

### 6.1 将`Tomcat`压缩包上传至`/usr/local/src`，然后解压`tar -zxvf 包名 -C /usr/local/`

### 6.2 配置`Tomcat`环境变量

6.2.1 新建`tomcat.sh`文件：`vim /etc/profile.d/tomcat.sh`

6.2.2 内容如下：

```shell script
CATALINA_BASE=/usr/local/tomcat-9.0.45
PATH=$CATALINA_BASE/bin:$PATH
export PATH CATALINA_BASE
```

6.2.3 刷新配置文件：`source /etc/profile`

6.2.4 检验安装是否成功：`catalina.sh version`

## 7 部署项目

### 7.1 项目打包上传至`/usr/local/tomcat-9.0.45/webapps`

### 7.2 解压`War`包：`unzip -d 目录名（和War包名一致） War包`

### 7.3 启动`Tomcat`：`./../bin/startup.sh`

### 7.4 查看启动日志：`tail -f -n 200 ../logs/catalina.out`

### 7.5 访问接口：`IP:8080/package-war-demo/hello`
