# Spring Boot 集成 Mybatis ORM 框架案例

# 1 基础框架搭建

## 1.1 主要依赖

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <!-- Mybatis核心 -->
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>2.2.2</version>
    </dependency>
</dependencies>
```

## 1.2 新建数据库表

[`mybatis.sql`](./src/main/resources/sql/mybatis.sql)

## 1.3 建立对应的实体类

[`UserDO.java`](./src/main/java/com/demo/mybatis/entity/UserDO.java) 用户实体类

## 1.4 建立对应的映射类

[`UserMapper.java`](./src/main/java/com/demo/mybatis/mapper/UserMapper.java) 用户管理映射类

## 1.5 建立对应的映射文件

[`UserMapper.xml`](./src/main/resources/mappers/UserMapper.xml) 用户管理映射文件

## 1.6 配置包扫描路径

[`OrmMybatisApplication.java`](./src/main/java/com/demo/mybatis/OrmMybatisApplication.java)

## 1.7 配置数据库连接信息

[`application.yml`](./src/main/resources/application.yml)

# 2 基本CRUD示例

[`UserMapperTests.java`](./src/test/java/com/demo/mybatis/UserMapperTest.java)

# 3 Mybatis 映射文件里获取参数值的情况

## 3.1 单个字面量类型的参数

有两种方式：`${}`和`#{}`

- `${}`的本质就是字符串拼接，若为字符串类型或日期类型的字段进行赋值时，需要手动加单引号
- `#{}`的本质就是占位符赋值，若为字符串类型或日期类型的字段进行赋值时，可以自动添加单引号

### 3.1.1 ${}（有拼接成错误SQL的风险）

```
# 接口方法为：
UserDO getUserByName(@Param("username") String username);

# 映射文件为：
<select id="getUserByName" resultType="com.demo.mybatis.entity.UserDO">
    select * from t_user where username = ${username}
</select>

# 生成的SQL语句：
select * from t_user where username = username;
```

### 3.1.2 #{}

```
# 接口方法为：
UserDO getUserByName(@Param("username") String username);

# 映射文件为：
<select id="getUserByName" resultType="com.demo.mybatis.entity.UserDO">
    select * from t_user where username = #{username}
</select>

# 生成的SQL语句：
select * from t_user where username = 'username';
```

## 3.2 多个字面量类型的参数

### 3.2.1 ${}（需要在入参处手动加上单引号）

```
# 接口方法为：
UserDO getUserByNameAndAge(@Param("username") String username, @Param("age") int age);

# 映射文件为：
<select id="getUserByNameAndAge2" resultType="com.demo.mybatis.entity.UserDO">
  select *
  from t_user
  where username = '${username}'
    and age = ${age}
</select>
```

### 3.2.2 #{}

```
# 接口方法为：
UserDO getUserByNameAndAge(@Param("username") String username, @Param("age") int age);

# 映射文件为：
<select id="getUserByNameAndAge" resultType="com.demo.mybatis.entity.UserDO">
  select *
  from t_user
  where username = #{username}
    and age = #{age}
</select>
```

## 3.3 map集合类型的参数

如果用`${}`接受参数同样须手动添加单引号

```
# 接口方法为：
UserDO getUserByMap(Map<String, Object> paramMap);

# 映射文件为：
<select id="getUserByMap" resultType="com.demo.mybatis.entity.UserDO">
  select *
  from t_user
  where username = #{username}
    and age = #{age}
</select>

# 测试方法：
void getUserByMapTest() {
  Map<String, Object> paramMap = new HashMap<>();
  paramMap.put("username", "foo");
  paramMap.put("age", 23);
  
  UserDO user = userMapper2.getUserByMap(paramMap);
  log.info("user:{}", user);
}
```

## 3.4 实体类类型的参数

如果用`${}`接受参数同样须手动添加单引号

```
# 接口方法为：
UserDO getUserByObject(UserDO userDO);

# 映射文件为：
<select id="getUserByObject" resultType="com.demo.mybatis.entity.UserDO">
  select *
  from t_user
  where username = #{username}
    and age = #{age}
</select>

# 测试方法：
void getUserByObjectTest() {
  UserDO userDO = new UserDO();
  userDO.setAge(23);
  userDO.setUsername("foo");

  UserDO user = userMapper2.getUserByObject(userDO);
  log.info("user:{}", user);
}
```

# 4 查询示例

## 4.1 查询一个实体类对象

[`UserMapper3Tests.java`](./src/test/java/com/demo/mybatis/UserMapper3Test.java)

## 4.2 查询一个List集合

[`UserMapper3Tests.java`](./src/test/java/com/demo/mybatis/UserMapper3Test.java)

## 4.3 查询单个数据

[`UserMapper3Tests.java`](./src/test/java/com/demo/mybatis/UserMapper3Test.java)

## 4.4 查询一条数据为map集合

[`UserMapper3Tests.java`](./src/test/java/com/demo/mybatis/UserMapper3Test.java)

## 4.5 查询多条数据为map集合（方式一）

[`UserMapper3Tests.java`](./src/test/java/com/demo/mybatis/UserMapper3Test.java)

## 4.6 查询多条数据为map集合（方式二）

[`UserMapper3Tests.java`](./src/test/java/com/demo/mybatis/UserMapper3Test.java)

## 4.7 模糊查询一个List集合（方式一）

[`UserMapper3Tests.java`](./src/test/java/com/demo/mybatis/UserMapper3Test.java)

## 4.8 模糊查询一个List集合（方式二，推荐）

[`UserMapper3Tests.java`](./src/test/java/com/demo/mybatis/UserMapper3Test.java)

## 4.9 根据ID查询用户信息（自定义映射关系）

[`UserMapper3Tests.java`](./src/test/java/com/demo/mybatis/UserMapper3Test.java)

## 4.9 根据ID查询员工及其所在部门的信息（多对一的关系，方式一：级联）

[`EmpMapperTests.java`](./src/test/java/com/demo/mybatis/EmpMapperTest.java)

## 4.10 根据ID查询员工及其所在部门的信息（多对一的关系，方式二：`</association>`）

[`EmpMapperTests.java`](./src/test/java/com/demo/mybatis/EmpMapperTest.java)

## 4.11 根据ID查询部门及其所属的员工的信息（一对多的关系，`</collection>`）

[`DeptMapperTests.java`](./src/test/java/com/demo/mybatis/DeptMapperTest.java)

# 5 修改示例

[`UserMapper4Tests.java`](./src/test/java/com/demo/mybatis/UserUpdateMapperTest.java)

# 6 删除示例

[`UserMapper4Tests.java`](./src/test/java/com/demo/mybatis/UserMapper4Test.java)

# 7 新增示例

## 7.1 单个新增示例（自增主键回写）

[`UserMapper5Tests.java`](./src/test/java/com/demo/mybatis/UserMapper5Test.java)

## 7.2 批量新增示例（自增主键回写）

[`UserMapper5Tests.java`](./src/test/java/com/demo/mybatis/UserMapper5Test.java)

# 8 动态SQL示例

[`DynamicMapperTests.java`](./src/test/java/com/demo/mybatis/DynamicMapperTest.java)

## 8.1 `<if>` 判断是否添加条件

## 8.2 `<where>` 过滤条件

## 8.3 `<trim>` 去掉或添加标签中的内容

## 8.4 `<choose>、<when>、<otherwise>` 分支条件

## 8.5 `<foreach>` 循环条件

## 8.6 `<sql>` 代码段 