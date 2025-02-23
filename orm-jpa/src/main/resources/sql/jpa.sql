SET NAMES utf8mb4;
SET
    FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for orm_department
-- ----------------------------
DROP TABLE IF EXISTS `orm_department`;
CREATE TABLE `orm_department`
(
    `id`               int(11)     NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`             varchar(32) NOT NULL COMMENT '部门名称',
    `superior`         int(11)              DEFAULT NULL COMMENT '上级id',
    `levels`           int(11)     NOT NULL COMMENT '层级',
    `order_no`         int(11)     NOT NULL DEFAULT '0' COMMENT '排序',
    `create_time`      datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `last_update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 41
  DEFAULT CHARSET = utf8
    COMMENT
        ='Spring Boot Demo Orm 系列示例表';

-- ----------------------------
-- Table structure for orm_user
-- ----------------------------
DROP TABLE IF EXISTS `orm_user`;
CREATE TABLE `orm_user`
(
    `id`               int(11)     NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`             varchar(32) NOT NULL COMMENT '用户名',
    `password`         varchar(32) NOT NULL COMMENT '加密后的密码',
    `salt`             varchar(32) NOT NULL COMMENT '加密使用的盐',
    `email`            varchar(32) NOT NULL COMMENT '邮箱',
    `phone_number`     varchar(15) NOT NULL COMMENT '手机号码',
    `status`           int(2)      NOT NULL DEFAULT '1' COMMENT '状态，-1：逻辑删除，0：禁用，1：启用',
    `create_time`      datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `last_login_time`  datetime             DEFAULT NULL COMMENT '上次登录时间',
    `last_update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `name` (`name`),
    UNIQUE KEY `email` (`email`),
    UNIQUE KEY `phone_number` (`phone_number`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 27
  DEFAULT CHARSET = utf8
    COMMENT
        ='Spring Boot Demo Orm 系列示例表';

-- ----------------------------
-- Table structure for orm_user_dept
-- ----------------------------
DROP TABLE IF EXISTS `orm_user_dept`;
CREATE TABLE `orm_user_dept`
(
    `id`               int(11)  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`          int(11)  NOT NULL COMMENT '用户id',
    `dept_id`          int(11)  NOT NULL COMMENT '部门id',
    `create_time`      datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8
    COMMENT
        ='Spring Boot Demo Orm 系列示例表';

SET
    FOREIGN_KEY_CHECKS = 1;
