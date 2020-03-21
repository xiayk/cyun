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

 Date: 21/03/2020 21:55:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' id',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单名称',
  `menu_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单code',
  `menu_ico` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单图标',
  `menu_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单url',
  `create_date` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  `update_date` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `update_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改者',
  `parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父id',
  `status` int(2) DEFAULT NULL COMMENT '状态(0正常，1禁用，2删除)',
  `type` int(2) DEFAULT NULL COMMENT '能否删除(0不能，2能)',
  `sort` int(2) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('2c48be2373174c57aec7eaad9f1f36f9', '角色管理', 'sys-role', 'android-contact', 'sys-role', '2019-12-04 09:25:57', 'd0a5c9ee0a4c4f3b9b7a7432eb6ce418', '2019-12-07 21:45:16', 'd0a5c9ee0a4c4f3b9b7a7432eb6ce418', '84959945e0ce44f399d56d59a0d35240', 0, 1, 1);
INSERT INTO `sys_menu` VALUES ('50e054bda83c4d46ac8a3e9dc94b170a', '菜单管理', 'sys-menu', 'navicon-round', 'sys-menu', '2019-12-04 09:26:26', 'd0a5c9ee0a4c4f3b9b7a7432eb6ce418', '2019-12-06 23:15:46', 'd0a5c9ee0a4c4f3b9b7a7432eb6ce418', '84959945e0ce44f399d56d59a0d35240', 0, 1, 0);
INSERT INTO `sys_menu` VALUES ('84959945e0ce44f399d56d59a0d35240', '系统管理', 'sys-setting', 'gear-b', 'sys-set', '2019-12-04 09:51:15', 'd0a5c9ee0a4c4f3b9b7a7432eb6ce418', '2019-12-07 16:50:08', 'd0a5c9ee0a4c4f3b9b7a7432eb6ce418', '0', 0, 2, 9999);
INSERT INTO `sys_menu` VALUES ('bb22905f562a4ab2bb34278a4fe104a8', '首页', 'sys-cyun', 'home', 'sys-home', '2019-12-04 19:52:36', 'd0a5c9ee0a4c4f3b9b7a7432eb6ce418', '2019-12-07 13:37:20', 'd0a5c9ee0a4c4f3b9b7a7432eb6ce418', '0', 1, 2, -1);
INSERT INTO `sys_menu` VALUES ('df5bd377984d4e8d90cf5c75e170e7a7', '用户管理', 'sys-user', 'person-stalker', 'sys-user', '2019-12-04 10:09:33', 'd0a5c9ee0a4c4f3b9b7a7432eb6ce418', '2019-12-07 16:49:49', 'd0a5c9ee0a4c4f3b9b7a7432eb6ce418', '84959945e0ce44f399d56d59a0d35240', 0, 2, -1);

SET FOREIGN_KEY_CHECKS = 1;
