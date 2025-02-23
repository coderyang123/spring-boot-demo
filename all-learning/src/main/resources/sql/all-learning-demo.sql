SET NAMES utf8mb4;
SET
    FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          bigint(20)                                                   NOT NULL COMMENT '主键ID',
    `username`    varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
    `password`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
    `age`         int(11)                                                      NULL DEFAULT NULL COMMENT '年龄',
    `email`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
    `phone`       varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
    `create_time` datetime                                                     NULL DEFAULT NULL COMMENT '创建时间',
    `modify_time` datetime                                                     NULL DEFAULT NULL COMMENT '修改时间',
    `creator`     varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
    `operator`    varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作人',
    `deleted`     tinyint(2)                                                   NULL DEFAULT NULL COMMENT '是否已删除 0：未删除 1：已删除',
    `version`     bigint(20)                                                   NULL DEFAULT NULL COMMENT '版本号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER
      SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES (1, '张三', '123456', 18, 'test1@baomidou.com', '18207309711', '2022-07-19 15:43:03', '2022-07-19 15:43:20',
        'admin', 'admin', 0, 1);
INSERT INTO `user`
VALUES (2, '李四', '123456', 20, 'test2@baomidou.com', '18207309712', '2022-07-19 15:43:10', '2022-07-19 15:43:23',
        'admin', 'admin', 0, 1);
INSERT INTO `user`
VALUES (3, '王五', '123456', 28, 'test3@baomidou.com', '18207309713', '2022-07-19 15:43:12', '2022-07-19 15:43:26',
        'admin', 'admin', 0, 1);
INSERT INTO `user`
VALUES (4, '赵六', '123456', 21, 'test4@baomidou.com', '18207309714', '2022-07-19 15:43:15', '2022-07-19 15:43:28',
        'admin', 'admin', 0, 1);
INSERT INTO `user`
VALUES (5, '赵七七', '123456', 24, 'test5@baomidou.com', '18207309715', '2022-07-19 15:43:17', '2022-07-19 15:43:30',
        'admin', 'admin', 0, 1);

SET
    FOREIGN_KEY_CHECKS = 1;
