SET NAMES utf8mb4;
SET
    FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`
(
    `id`          bigint(20) NOT NULL COMMENT '主键ID',
    `name`        varchar(30) DEFAULT NULL COMMENT '商品名',
    `user_id`     bigint(20) NOT NULL COMMENT '用户ID',
    `create_time` datetime    DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Records of goods
-- ----------------------------
BEGIN;
INSERT INTO `goods` (`id`, `name`, `user_id`, `create_time`)
VALUES (1, 'Iphone 13', 1, '2022-07-22 15:43:03');
INSERT INTO `goods` (`id`, `name`, `user_id`, `create_time`)
VALUES (2, 'Iphone 13 pro', 6, '2022-07-22 15:43:03');
COMMIT;

-- ----------------------------
-- Table structure for time
-- ----------------------------
DROP TABLE IF EXISTS `time`;
CREATE TABLE `time`
(
    `id`              bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `local_date_time` datetime DEFAULT NULL COMMENT '日期时间',
    `local_date`      date     DEFAULT NULL COMMENT '日期',
    `local_time`      time     DEFAULT NULL COMMENT '时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1613939736479531010
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Records of time
-- ----------------------------
BEGIN;
INSERT INTO `time` (`id`, `local_date_time`, `local_date`, `local_time`)
VALUES (1613938817218146306, '2012-01-14 00:10:10', '2012-01-14', '00:10:10');
INSERT INTO `time` (`id`, `local_date_time`, `local_date`, `local_time`)
VALUES (1613939094642028546, '2023-01-14 00:10:10', '2023-01-14', '00:23:10');
INSERT INTO `time` (`id`, `local_date_time`, `local_date`, `local_time`)
VALUES (1613939736479531009, '2023-01-14 00:10:10', '2023-01-14', '00:23:10');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          bigint(20) NOT NULL COMMENT '主键ID',
    `name`        varchar(30) DEFAULT NULL COMMENT '姓名',
    `age`         int(11)     DEFAULT NULL COMMENT '年龄',
    `email`       varchar(50) DEFAULT NULL COMMENT '邮箱',
    `create_time` datetime    DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `name`, `age`, `email`, `create_time`)
VALUES (1, 'Jone', 18, 'test1@baomidou.com', '2022-07-19 15:43:03');
INSERT INTO `user` (`id`, `name`, `age`, `email`, `create_time`)
VALUES (2, 'Jack', 20, 'test2@baomidou.com', '2022-07-19 15:43:03');
INSERT INTO `user` (`id`, `name`, `age`, `email`, `create_time`)
VALUES (3, 'Tom', 28, 'test3@baomidou.com', '2022-07-19 15:43:03');
INSERT INTO `user` (`id`, `name`, `age`, `email`, `create_time`)
VALUES (4, 'Sandy', 21, 'test4@baomidou.com', '2022-07-19 15:43:03');
INSERT INTO `user` (`id`, `name`, `age`, `email`, `create_time`)
VALUES (5, 'Billie', 24, 'test5@baomidou.com', '2022-07-19 15:43:03');
INSERT INTO `user` (`id`, `name`, `age`, `email`, `create_time`)
VALUES (6, '雨化田', 18, 'test6@baomidou.com', '2022-07-19 15:43:03');
INSERT INTO `user` (`id`, `name`, `age`, `email`, `create_time`)
VALUES (7, '王小波', 18, 'test7@baomidou.com', '2022-07-19 15:43:03');
INSERT INTO `user` (`id`, `name`, `age`, `email`, `create_time`)
VALUES (8, '雨化田', 18, 'test8@baomidou.com', '2022-07-22 15:43:03');
INSERT INTO `user` (`id`, `name`, `age`, `email`, `create_time`)
VALUES (1613928710027624449, 'evan', 18, '102@qq.com', '2023-01-13 23:19:20');
COMMIT;

-- ----------------------------
-- Table structure for user_1
-- ----------------------------
DROP TABLE IF EXISTS `user_1`;
CREATE TABLE `user_1`
(
    `id`          bigint(20) NOT NULL COMMENT '主键ID',
    `name`        varchar(30) DEFAULT NULL COMMENT '姓名',
    `age`         int(11)     DEFAULT NULL COMMENT '年龄',
    `email`       varchar(50) DEFAULT NULL COMMENT '邮箱',
    `create_time` datetime    DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Records of user_1
-- ----------------------------
BEGIN;
INSERT INTO `user_1` (`id`, `name`, `age`, `email`, `create_time`)
VALUES (1, 'Jone', 18, 'test1@baomidou.com', '2022-07-19 15:43:03');
INSERT INTO `user_1` (`id`, `name`, `age`, `email`, `create_time`)
VALUES (2, 'Jack', 20, 'test2@baomidou.com', '2022-07-19 15:43:03');
INSERT INTO `user_1` (`id`, `name`, `age`, `email`, `create_time`)
VALUES (3, 'Tom', 28, 'test3@baomidou.com', '2022-07-19 15:43:03');
INSERT INTO `user_1` (`id`, `name`, `age`, `email`, `create_time`)
VALUES (4, 'Sandy', 21, 'test4@baomidou.com', '2022-07-19 15:43:03');
INSERT INTO `user_1` (`id`, `name`, `age`, `email`, `create_time`)
VALUES (5, 'Billie', 24, 'test5@baomidou.com', '2022-07-19 15:43:03');
INSERT INTO `user_1` (`id`, `name`, `age`, `email`, `create_time`)
VALUES (6, '雨化田', 18, 'test6@baomidou.com', '2022-07-19 15:43:03');
INSERT INTO `user_1` (`id`, `name`, `age`, `email`, `create_time`)
VALUES (7, '王小波', 18, 'test7@baomidou.com', '2022-07-19 15:43:03');
COMMIT;

SET
    FOREIGN_KEY_CHECKS = 1;
