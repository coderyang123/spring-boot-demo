SET NAMES utf8mb4;
SET
    FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept`
(
    `did`       int(11) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
    `dept_name` varchar(20) DEFAULT NULL COMMENT '部门名',
    PRIMARY KEY (`did`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
BEGIN;
INSERT INTO `t_dept` (`did`, `dept_name`)
VALUES (1, 'A');
COMMIT;

-- ----------------------------
-- Table structure for t_emp
-- ----------------------------
DROP TABLE IF EXISTS `t_emp`;
CREATE TABLE `t_emp`
(
    `eid`      int(11) NOT NULL AUTO_INCREMENT COMMENT '职员ID',
    `emp_name` varchar(20) DEFAULT NULL COMMENT '职员姓名',
    `age`      int(11)     DEFAULT NULL COMMENT '年龄',
    `email`    varchar(50) DEFAULT NULL COMMENT '邮箱',
    `sex`      char(1)     DEFAULT NULL COMMENT '性别',
    `did`      int(11)     DEFAULT NULL COMMENT '部门ID',
    PRIMARY KEY (`eid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Records of t_emp
-- ----------------------------
BEGIN;
INSERT INTO `t_emp` (`eid`, `emp_name`, `age`, `email`, `sex`, `did`)
VALUES (1, 'test2', 20, '34@qq.com', '女', 1);
INSERT INTO `t_emp` (`eid`, `emp_name`, `age`, `email`, `sex`, `did`)
VALUES (2, 'foo', 23, '34@qq.com', '男', 1);
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `id`       int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username` varchar(20) DEFAULT NULL COMMENT '姓名',
    `password` varchar(20) DEFAULT NULL COMMENT '密码',
    `age`      int(11)     DEFAULT NULL COMMENT '年龄',
    `sex`      char(1)     DEFAULT NULL COMMENT '性别',
    `email`    varchar(50) DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 15
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` (`id`, `username`, `password`, `age`, `sex`, `email`)
VALUES (1, 'admin', '123456', 18, NULL, '12345@qq.com');
INSERT INTO `t_user` (`id`, `username`, `password`, `age`, `sex`, `email`)
VALUES (2, 'foo', '123456', 19, '男', '12346@qq.com');
INSERT INTO `t_user` (`id`, `username`, `password`, `age`, `sex`, `email`)
VALUES (3, 'foo', '123456', 20, '男', '12347@qq.com');
INSERT INTO `t_user` (`id`, `username`, `password`, `age`, `sex`, `email`)
VALUES (4, 'bar', '123456', 21, '男', '12348@qq.com');
INSERT INTO `t_user` (`id`, `username`, `password`, `age`, `sex`, `email`)
VALUES (5, '张三', '123456', 22, '男', '12349@qq.com');
INSERT INTO `t_user` (`id`, `username`, `password`, `age`, `sex`, `email`)
VALUES (6, '李四', '123456', 23, '男', '12341@qq.com');
INSERT INTO `t_user` (`id`, `username`, `password`, `age`, `sex`, `email`)
VALUES (7, '李五', '123456', 24, '男', '12342@qq.com');
COMMIT;

SET
    FOREIGN_KEY_CHECKS = 1;
