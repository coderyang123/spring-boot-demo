DROP TABLE
    IF
        EXISTS `multi_user`;
CREATE TABLE `multi_user`
(
    `id`   BIGINT(64) NOT NULL,
    `name` VARCHAR(50) DEFAULT NULL,
    `age`  INT(30)     DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  CHARACTER
      SET = utf8
  COLLATE = utf8_general_ci;