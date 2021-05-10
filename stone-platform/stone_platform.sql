/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.180.129_dev001
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : 192.168.180.135:3306
 Source Schema         : stone_business

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 24/04/2021 15:16:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `code` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父级id',
  `url` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `layer` tinyint(4) NULL DEFAULT NULL COMMENT '层级',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '1 启用， 0 禁用，默认1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES (2, '系统首页', 'dashboard', NULL, NULL, 'el-icon-lx-home', NULL, '系统首页', '1');
INSERT INTO `tb_menu` VALUES (3, '权限管理', 'dashboard1', NULL, NULL, 'el-icon-s-cooperation', NULL, NULL, '1');
INSERT INTO `tb_menu` VALUES (4, '用户管理', 'user', 3, NULL, 'el-icon-user', NULL, NULL, '1');
INSERT INTO `tb_menu` VALUES (5, '员工管理', 'person', 3, NULL, 'el-icon-picture-outline-round', NULL, NULL, '1');
INSERT INTO `tb_menu` VALUES (6, '菜单管理', 'menu', 3, NULL, 'el-icon-office-building', NULL, NULL, '1');
INSERT INTO `tb_menu` VALUES (7, '自定义菜单栏', 'dashboard2', NULL, NULL, 'el-icon-lx-home', NULL, NULL, '1');
INSERT INTO `tb_menu` VALUES (8, '基础表格', 'table', 7, NULL, 'el-icon-lx-cascades', NULL, NULL, '1');
INSERT INTO `tb_menu` VALUES (9, 'tab选项卡', 'tabs', 7, NULL, 'el-icon-lx-copy', NULL, NULL, '1');
INSERT INTO `tb_menu` VALUES (10, '表单相关', '3', 7, NULL, 'el-icon-lx-calendar', NULL, NULL, '1');
INSERT INTO `tb_menu` VALUES (11, '基本表单', 'form', 10, NULL, 'el-icon-lx-calendar', NULL, NULL, '1');
INSERT INTO `tb_menu` VALUES (12, '三级菜单', '3-2', 10, NULL, 'el-icon-lx-calendar', NULL, NULL, '0');
INSERT INTO `tb_menu` VALUES (13, '富文本编辑器', 'editor', 10, NULL, 'el-icon-lx-calendar', NULL, NULL, '1');
INSERT INTO `tb_menu` VALUES (14, 'markdown编辑器', 'markdown', 10, NULL, 'el-icon-lx-calendar', NULL, NULL, '1');
INSERT INTO `tb_menu` VALUES (15, '文件上传', 'upload', 11, NULL, 'el-icon-lx-calendar', NULL, NULL, '1');
INSERT INTO `tb_menu` VALUES (16, '自定义图标', 'icon', 7, NULL, 'el-icon-lx-emoji', NULL, NULL, '1');
INSERT INTO `tb_menu` VALUES (17, 'schart图表', 'charts', 7, NULL, 'el-icon-pie-chart', NULL, NULL, '1');
INSERT INTO `tb_menu` VALUES (18, '拖拽组件', '6', 7, NULL, 'el-icon-rank', NULL, NULL, '1');
INSERT INTO `tb_menu` VALUES (19, '拖拽列表', 'drag', 18, NULL, 'el-icon-lx-calendar', NULL, NULL, '1');
INSERT INTO `tb_menu` VALUES (20, '拖拽弹框', 'dialog', 18, NULL, 'el-icon-lx-calendar', NULL, NULL, '1');
INSERT INTO `tb_menu` VALUES (21, '国际化功能', 'i18n', 7, NULL, 'el-icon-lx-global', NULL, NULL, '1');
INSERT INTO `tb_menu` VALUES (22, '错误处理', '7', 7, NULL, 'el-icon-lx-warn', NULL, NULL, '1');
INSERT INTO `tb_menu` VALUES (23, '权限测试', 'permission', 22, NULL, 'el-icon-lx-calendar', NULL, NULL, '1');
INSERT INTO `tb_menu` VALUES (24, '404页面', '404', 22, NULL, 'el-icon-lx-calendar', NULL, NULL, '1');
INSERT INTO `tb_menu` VALUES (25, '支持作者', '/donate', 7, NULL, 'el-icon-lx-redpacket_fill', NULL, NULL, '0');

-- ----------------------------
-- Table structure for tb_person
-- ----------------------------
DROP TABLE IF EXISTS `tb_person`;
CREATE TABLE `tb_person`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `corp_id` bigint(20) NULL DEFAULT NULL COMMENT '企业id',
  `person_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工姓名',
  `mobile` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `gender` int(11) NULL DEFAULT 0 COMMENT '性别 0 未知,1 男,2 女, 9 未说明的性',
  `card_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工卡号',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nation_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族代码',
  `native_place` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '籍贯',
  `entry_date` datetime(0) NULL DEFAULT NULL COMMENT '入职日期',
  `age` int(11) NULL DEFAULT NULL COMMENT '员工年龄',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '员工信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_person
-- ----------------------------
INSERT INTO `tb_person` VALUES (5, NULL, '张三', '18565601630', 2, '18565601630', '18565601630@163.com', '新增员工1', '01', '湖南长沙', '2020-08-18 00:00:00', 23);
INSERT INTO `tb_person` VALUES (6, NULL, '李四', '18565601631', 1, '1002', '', NULL, '02', NULL, '2020-08-11 00:00:00', 1);
INSERT INTO `tb_person` VALUES (18, NULL, '赵六', '18565601633', 1, '1002', NULL, NULL, NULL, NULL, '2020-01-01 00:00:00', 22);

-- ----------------------------
-- Table structure for tb_user_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_auth`;
CREATE TABLE `tb_user_auth`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `account_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `account_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user_auth
-- ----------------------------
INSERT INTO `tb_user_auth` VALUES (1, 1001, '18565601630', 'a123456', '张三');
INSERT INTO `tb_user_auth` VALUES (2, 1002, '18565601631', 'a123456', '李四');
INSERT INTO `tb_user_auth` VALUES (5, 1004, '18565601634', 'a123456', '王五');
INSERT INTO `tb_user_auth` VALUES (6, 1004, '18565601635', '18565601635', '18565601635');
INSERT INTO `tb_user_auth` VALUES (7, 1002, '18565601633', 'a123456', '赵柳');

SET FOREIGN_KEY_CHECKS = 1;
