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

 Date: 21/03/2020 21:55:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色id',
  `menu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('17eaee1d4e8740af92fe103aebf85736', '6d94ea96a7834d41851717cdd8dc5b6f', '2c48be2373174c57aec7eaad9f1f36f9');
INSERT INTO `sys_role_menu` VALUES ('3a319ef122554ca39eb6df4ab3fe52d3', '6d94ea96a7834d41851717cdd8dc5b6f', '50e054bda83c4d46ac8a3e9dc94b170a');
INSERT INTO `sys_role_menu` VALUES ('4ae3c581cb1a49ad8da6b10fd6cdb3b2', '6d94ea96a7834d41851717cdd8dc5b6f', '84959945e0ce44f399d56d59a0d35240');
INSERT INTO `sys_role_menu` VALUES ('64f36e037a5b4ce6bcbf3945daf486b4', '6d94ea96a7834d41851717cdd8dc5b6f', 'bb22905f562a4ab2bb34278a4fe104a8');
INSERT INTO `sys_role_menu` VALUES ('81f03ac1425146e89668f93d9a97fdb4', '6d94ea96a7834d41851717cdd8dc5b6f', 'df5bd377984d4e8d90cf5c75e170e7a7');
INSERT INTO `sys_role_menu` VALUES ('9167f1fbb3bb44bfbd0c498b878fcc5e', '02cd0d18ad8042a1b153da621401b09f', '84959945e0ce44f399d56d59a0d35240');
INSERT INTO `sys_role_menu` VALUES ('9fbfaa9a80d5461881eefdc63fe556ad', '02cd0d18ad8042a1b153da621401b09f', 'df5bd377984d4e8d90cf5c75e170e7a7');
INSERT INTO `sys_role_menu` VALUES ('a2019d8c09654269868f81332c19152c', '02cd0d18ad8042a1b153da621401b09f', 'c999ee0e6e1f460ba14462d770009b4d');

SET FOREIGN_KEY_CHECKS = 1;
