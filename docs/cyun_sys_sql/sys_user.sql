/*
 Navicat Premium Data Transfer

 Source Server         : xiayk.com
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : xiayk.com:3306
 Source Schema         : db_cyun

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 21/03/2020 21:55:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '昵称',
  `create_date` datetime(0) DEFAULT NULL COMMENT '创建时间\r\n',
  `update_date` timestamp(0) DEFAULT NULL COMMENT '修改时间',
  `last_date` datetime(0) DEFAULT NULL COMMENT '上次登录时间',
  `status` int(2) DEFAULT NULL COMMENT '状态(0正常，1禁用，2删除)',
  `parent_id` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父id',
  `create_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `update_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('0ab731356cab400091c0945aaf995770', 'admin', '$2a$10$CvhbsRCm5pupXi3Tzmzo9.0EA8zTrVz90QxBktYfJ5T0maHvAmK86', '管理员', '13766666666', '2019-12-04 17:20:57', '2019-12-05 14:18:55', '2020-03-21 21:51:47', 0, 'd0a5c9ee0a4c4f3b9b7a7432eb6ce418', 'd0a5c9ee0a4c4f3b9b7a7432eb6ce418', 'd0a5c9ee0a4c4f3b9b7a7432eb6ce418');
INSERT INTO `sys_user` VALUES ('4e4208f076b149058d9aa0046b58efa8', 'xiayk', '$2a$10$Hi/PLnlZ3Gj7mMGpxA9Nke4gD7s1PNA2ZA9nLxi5YXqC2qtwmR1Lq', '夏', '13856956857', '2020-03-21 21:52:11', NULL, '2020-03-21 21:52:28', 0, '0ab731356cab400091c0945aaf995770', '0ab731356cab400091c0945aaf995770', NULL);

SET FOREIGN_KEY_CHECKS = 1;
