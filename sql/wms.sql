/*
 Navicat MySQL Data Transfer

 Source Server         : xlf
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : wms

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 10/01/2023 21:30:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '货名',
  `storage` int NOT NULL COMMENT '仓库',
  `goodsType` int NOT NULL COMMENT '分类',
  `count` int NULL DEFAULT NULL COMMENT '数量',
  `remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '篮球', 1, 2, 48, '篮球');
INSERT INTO `goods` VALUES (2, '足球', 1, 2, 22, '足球');
INSERT INTO `goods` VALUES (3, '达利园面包', 3, 1, 272, '达利园零售面包');
INSERT INTO `goods` VALUES (4, '卫龙辣条', 3, 1, 110, '');
INSERT INTO `goods` VALUES (5, '练习本', 5, 2, 900, '');

-- ----------------------------
-- Table structure for goodstype
-- ----------------------------
DROP TABLE IF EXISTS `goodstype`;
CREATE TABLE `goodstype`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名',
  `remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goodstype
-- ----------------------------
INSERT INTO `goodstype` VALUES (1, '食品类', '食品');
INSERT INTO `goodstype` VALUES (2, '文体类', '文体用品');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int NOT NULL,
  `menuCode` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单编码',
  `menuName` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名字',
  `menuLevel` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单级别',
  `menuParentCode` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单的父code',
  `menuClick` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '点击触发的函数',
  `menuRight` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限 0超级管理员，1表示管理员，2表示普通用户，可以用逗号组合使用',
  `menuComponent` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menuIcon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '001', '管理员管理', '1', NULL, 'Admin', '0', 'admin/AdminManage.vue', 'el-icon-s-custom');
INSERT INTO `menu` VALUES (2, '002', '用户管理', '1', NULL, 'User', '0,1', 'user/UserManage.vue', 'el-icon-user-solid');
INSERT INTO `menu` VALUES (3, '003', '仓库管理', '1', NULL, 'Storage', '0,1', 'storage/StorageManage.vue', 'el-icon-office-building');
INSERT INTO `menu` VALUES (4, '004', '物品分类管理', '1', NULL, 'Goodstype', '0,1', 'goodstype/GoodstypeManage', 'el-icon-menu');
INSERT INTO `menu` VALUES (5, '005', '物品管理 ', '1', NULL, 'Goods', '0,1,2', 'goods/GoodsManage', 'el-icon-s-management');
INSERT INTO `menu` VALUES (6, '006', '记录管理', '1', NULL, 'Record', '0,1,2', 'record/RecordManage', 'el-icon-s-order');

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods` int NOT NULL COMMENT '货品id',
  `userId` int NULL DEFAULT NULL COMMENT '取货人/补货人',
  `admin_id` int NULL DEFAULT NULL COMMENT '操作人id',
  `count` int NULL DEFAULT NULL COMMENT '数量',
  `createtime` datetime NULL DEFAULT NULL COMMENT '操作时间',
  `remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES (1, 3, 7, 2, 10, '2022-12-01 13:51:33', NULL);
INSERT INTO `record` VALUES (2, 1, 7, 2, 1, '2022-12-01 15:47:57', NULL);
INSERT INTO `record` VALUES (3, 1, 7, 1, 1, '2022-12-01 16:42:43', '');
INSERT INTO `record` VALUES (4, 2, 7, 1, 2, '2022-12-01 19:38:37', '');
INSERT INTO `record` VALUES (5, 3, 7, 1, 20, '2022-12-01 19:38:40', '');
INSERT INTO `record` VALUES (6, 3, 7, 1, 10, '2022-12-01 19:38:15', '补货');
INSERT INTO `record` VALUES (7, 1, 11, 1, 15, '2022-12-01 19:38:19', '');
INSERT INTO `record` VALUES (8, 3, 7, 1, -30, '2022-12-01 19:38:23', '购买');
INSERT INTO `record` VALUES (9, 4, NULL, 1, 100, '2022-12-01 19:38:42', NULL);
INSERT INTO `record` VALUES (10, 2, 11, 2, 2, '2022-11-29 19:38:44', '');
INSERT INTO `record` VALUES (11, 2, 11, 2, 1, '2022-11-28 19:38:51', '');
INSERT INTO `record` VALUES (12, 2, 11, 2, 1, '2022-11-29 19:38:54', '');
INSERT INTO `record` VALUES (13, 2, 7, 2, 2, '2022-11-29 19:38:58', '');
INSERT INTO `record` VALUES (14, 2, 7, 2, 3, '2022-11-28 19:39:05', '');
INSERT INTO `record` VALUES (15, 3, 7, 1, 10, '2022-11-28 19:39:10', '');
INSERT INTO `record` VALUES (16, 3, 11, 1, -20, '2022-12-01 19:36:53', '');
INSERT INTO `record` VALUES (17, 4, 14, 1, 3, '2022-12-01 21:02:45', '');
INSERT INTO `record` VALUES (18, 4, 1, 1, 7, '2022-12-01 21:20:23', '');
INSERT INTO `record` VALUES (19, 2, 1, 1, 3, '2022-12-01 21:21:54', '');
INSERT INTO `record` VALUES (20, 2, 14, 1, -5, '2022-12-01 21:22:07', '');
INSERT INTO `record` VALUES (21, 5, 1, 1, 1000, '2022-12-03 17:19:12', NULL);
INSERT INTO `record` VALUES (22, 1, 1, 1, 12, '2022-12-03 17:39:11', '');
INSERT INTO `record` VALUES (23, 1, 1, 1, 11, '2022-12-03 17:53:15', '');
INSERT INTO `record` VALUES (24, 5, 13, 1, -100, '2022-12-03 21:14:45', '');
INSERT INTO `record` VALUES (25, 3, 7, 1, -30, '2022-12-11 16:20:19', '');
INSERT INTO `record` VALUES (26, 3, 1, 1, 12, '2023-01-09 17:01:07', '');

-- ----------------------------
-- Table structure for storage
-- ----------------------------
DROP TABLE IF EXISTS `storage`;
CREATE TABLE `storage`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '仓库名',
  `remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of storage
-- ----------------------------
INSERT INTO `storage` VALUES (1, '仓库2', '这是一个大仓库');
INSERT INTO `storage` VALUES (3, '仓库1', '这是一个小仓库');
INSERT INTO `storage` VALUES (5, '仓库3', '这是一个新仓库');
INSERT INTO `storage` VALUES (7, '仓库5', '新仓库');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名字',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `age` int NULL DEFAULT NULL,
  `sex` int NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `role_id` int NULL DEFAULT NULL COMMENT '⻆⾊ 0超级管理员，1管理员，2普通账号',
  `isValid` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'Y' COMMENT '是否有效，Y有效，其他⽆效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'sa', '超级管理员', '010617', 18, 1, '11111', 0, 'Y');
INSERT INTO `user` VALUES (2, 'admin', 'xlf', '123', 18, 1, '15719099816', 1, 'Y');
INSERT INTO `user` VALUES (5, 'aaa', '张三', '333', 24, 1, NULL, 1, 'Y');
INSERT INTO `user` VALUES (6, 'bbb', '李华', '12345', 18, 0, '13570817712', 1, 'Y');
INSERT INTO `user` VALUES (7, '111', 'Marry', '13232', 18, 0, '15718099143', 2, 'Y');
INSERT INTO `user` VALUES (8, '111', 'lucy', '132332', 11, 0, '15777321719', 1, 'Y');
INSERT INTO `user` VALUES (10, '12345', 'Mike', '11111', 15, 1, '15076577825', 1, 'Y');
INSERT INTO `user` VALUES (11, '222', '李明', '123123', 19, 1, '16716088623', 2, 'Y');
INSERT INTO `user` VALUES (12, '23456', '李四', '123456', 24, 1, '19987267791', 2, 'Y');
INSERT INTO `user` VALUES (13, '34567', '流星', '123456', 18, 0, '17232626234', 2, 'Y');
INSERT INTO `user` VALUES (14, '132332', '李辉', '123456', 19, 1, '17519624155', 2, 'Y');
INSERT INTO `user` VALUES (15, 'leader', '陈', '123456', 31, 1, '18296148164', 1, 'Y');

SET FOREIGN_KEY_CHECKS = 1;
