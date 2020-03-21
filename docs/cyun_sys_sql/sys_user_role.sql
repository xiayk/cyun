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

 Date: 21/03/2020 21:55:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('21cee20f195e441a9195aa93ebfd7cae', '4e4208f076b149058d9aa0046b58efa8', '02cd0d18ad8042a1b153da621401b09f');
INSERT INTO `sys_user_role` VALUES ('231ea917eba64dbe9cbf910b267a3a96', '0ab731356cab400091c0945aaf995770', '6d94ea96a7834d41851717cdd8dc5b6f');

SET FOREIGN_KEY_CHECKS = 1;
