SET NAMES utf8mb4;
SET
    FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict`
(
    `id`        bigint(20)                                                   NOT NULL COMMENT 'id',
    `parent_id` bigint(20)                                                   NULL DEFAULT NULL COMMENT '上级id',
    `name`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
    `value`     int(20)                                                      NULL DEFAULT NULL COMMENT '值',
    `dict_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER
      SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict
-- ----------------------------
INSERT INTO `dict`
VALUES (1, 0, '名称1', 1, '编码1');
INSERT INTO `dict`
VALUES (2, 0, '名称2', 2, '编码2');
INSERT INTO `dict`
VALUES (3, 0, '名称1', 3, '编码3');
INSERT INTO `dict`
VALUES (4, 0, '名称4', 4, '编码4');
INSERT INTO `dict`
VALUES (5, 0, '名称5', 5, '编码5');

SET
    FOREIGN_KEY_CHECKS = 1;
