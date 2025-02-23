SET NAMES utf8mb4;
SET
    FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for orm_user
-- ----------------------------
DROP TABLE IF EXISTS `orm_user`;
CREATE TABLE `orm_user`
(
    `id`               int(11)                                                NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`             varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
    `password`         varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '加密后的密码',
    `salt`             varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '加密使用的盐',
    `email`            varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
    `phone_number`     varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
    `status`           int(2)                                                 NOT NULL DEFAULT 1 COMMENT '状态，-1：逻辑删除，0：禁用，1：启用',
    `create_time`      datetime                                               NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `last_login_time`  datetime                                               NULL     DEFAULT NULL COMMENT '上次登录时间',
    `last_update_time` datetime                                               NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `name` (`name`) USING BTREE,
    UNIQUE INDEX `email` (`email`) USING BTREE,
    UNIQUE INDEX `phone_number` (`phone_number`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 14
  CHARACTER
      SET = utf8
  COLLATE = utf8_general_ci
    COMMENT
        = 'Spring Boot Demo Orm 系列示例表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orm_user
-- ----------------------------
INSERT INTO `orm_user`
VALUES (1, '通用Mapper名字更新', 'ff342e862e7c3285cdc07e56d6b8973b', '412365a109674b2dbb1981ed561a4c70',
        'user1@xkcoding.com',
        '17300000001', 1, '2022-02-27 09:29:51', NULL, '2022-02-27 09:29:51');
INSERT INTO `orm_user`
VALUES (2, 'user_2', '6c6bf02c8d5d3d128f34b1700cb1e32c', 'fcbdd0e8a9404a5585ea4e01d0e4d7a0', 'user2@xkcoding.com',
        '17300000002', 1, '2022-02-27 09:29:51', NULL, '2022-02-27 09:29:51');
INSERT INTO `orm_user`
VALUES (3, 'testSave3', '111111', 'abc', 'testSave3@xkcoding.com', '17300000003', 1, '2022-02-27 17:44:27',
        '2022-02-27 17:44:27', '2022-02-27 17:44:27');
INSERT INTO `orm_user`
VALUES (4, 'testSave4', '111111', 'abc', 'testSave4@xkcoding.com', '17300000004', 1, '2022-02-27 17:47:52',
        '2022-02-27 17:47:52', '2022-02-27 17:47:52');
INSERT INTO `orm_user`
VALUES (5, 'testSave5', '111111', 'abc', 'testSave5@xkcoding.com', '17300000005', 1, '2022-02-27 17:47:52',
        '2022-02-27 17:47:52', '2022-02-27 17:47:52');
INSERT INTO `orm_user`
VALUES (6, 'testSave6', '111111', 'abc', 'testSave6@xkcoding.com', '17300000006', 1, '2022-02-27 17:47:52',
        '2022-02-27 17:47:52', '2022-02-27 17:47:52');
INSERT INTO `orm_user`
VALUES (7, 'testSave7', '111111', 'abc', 'testSave7@xkcoding.com', '17300000007', 1, '2022-02-27 17:47:52',
        '2022-02-27 17:47:52', '2022-02-27 17:47:52');
INSERT INTO `orm_user`
VALUES (8, 'testSave8', '111111', 'abc', 'testSave8@xkcoding.com', '17300000008', 1, '2022-02-27 17:47:52',
        '2022-02-27 17:47:52', '2022-02-27 17:47:52');
INSERT INTO `orm_user`
VALUES (9, 'testSave9', '111111', 'abc', 'testSave9@xkcoding.com', '17300000009', 1, '2022-02-27 17:47:52',
        '2022-02-27 17:47:52', '2022-02-27 17:47:52');
INSERT INTO `orm_user`
VALUES (10, 'testSave10', '111111', 'abc', 'testSave10@xkcoding.com', '173000000010', 1, '2022-02-27 17:47:52',
        '2022-02-27 17:47:52', '2022-02-27 17:47:52');
INSERT INTO `orm_user`
VALUES (11, 'testSave11', '111111', 'abc', 'testSave11@xkcoding.com', '173000000011', 1, '2022-02-27 17:47:52',
        '2022-02-27 17:47:52', '2022-02-27 17:47:52');
INSERT INTO `orm_user`
VALUES (12, 'testSave12', '111111', 'abc', 'testSave12@xkcoding.com', '173000000012', 1, '2022-02-27 17:47:52',
        '2022-02-27 17:47:52', '2022-02-27 17:47:52');

SET
    FOREIGN_KEY_CHECKS = 1;
