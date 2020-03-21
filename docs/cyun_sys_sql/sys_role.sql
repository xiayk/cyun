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

 Date: 21/03/2020 21:55:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色code',
  `create_date` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `update_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  `status` int(2) DEFAULT NULL COMMENT '状态(0正常，1禁用，2删除)',
  `type` int(2) DEFAULT NULL COMMENT '类型(0管理员，1其他)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('02cd0d18ad8042a1b153da621401b09f', '管理员', 'cyun-admin', '2019-12-04 09:23:29', 'd0a5c9ee0a4c4f3b9b7a7432eb6ce418', '2020-03-21 21:40:15', 'd0a5c9ee0a4c4f3b9b7a7432eb6ce418', 0, 0);
INSERT INTO `sys_role` VALUES ('6d94ea96a7834d41851717cdd8dc5b6f', '超级管理员', 'cyun-super', '2019-12-04 09:24:00', 'd0a5c9ee0a4c4f3b9b7a7432eb6ce418', '2020-03-21 21:27:44', 'd0a5c9ee0a4c4f3b9b7a7432eb6ce418', 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
