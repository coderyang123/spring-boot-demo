SET NAMES utf8mb4;
SET
    FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          bigint UNSIGNED                                              NOT NULL COMMENT '主键',
    `username`    varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
    `password`    varchar(70) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
    `roles`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户角色，格式：ROLE_XXX，多个用逗号隔开',
    `authorities` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户权限，多个用逗号隔开',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER
      SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
    COMMENT
        = '用户表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES (1368956898827378689, 'admin', '$2a$10$nNQI9Ij1rU5NG9JFLQphweTOteCX6O211Nysrg2V5rRSGDRmRWtm.',
        'ROLE_ADMIN,ROLE_USER', 'create');
INSERT INTO `user`
VALUES (1380926753612836866, 'tom', '$2a$10$nNQI9Ij1rU5NG9JFLQphweTOteCX6O211Nysrg2V5rRSGDRmRWtm.',
        'ROLE_ADMIN,ROLE_USER', 'retrieve');
INSERT INTO `user`
VALUES (1380927054004666370, 'jerry', '$2a$10$nNQI9Ij1rU5NG9JFLQphweTOteCX6O211Nysrg2V5rRSGDRmRWtm.',
        'ROLE_ADMIN,ROLE_USER', 'update');
INSERT INTO `user`
VALUES (1387061057352474626, 'yueyang', '$2a$10$SUHjfqqdaWl91fzvTRurrOKftzxQY.rABYjWvpD6K1XrjvAdRF2wS', 'ROLE_ADMIN',
        'delete');

SET
    FOREIGN_KEY_CHECKS = 1;
