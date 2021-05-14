CREATE DATABASE `order`;

USE `order`;
/*
 Navicat MySQL Data Transfer

 Source Server         : 120.77.255.85
 Source Server Type    : MySQL
 Source Server Version : 50640
 Source Host           : 120.77.255.85:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50640
 File Encoding         : 65001

 Date: 25/04/2021 18:13:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `name` varchar(20) CHARACTER SET latin1 DEFAULT NULL COMMENT '用户姓名',
  `tel` varchar(20) CHARACTER SET latin1 DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户';

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `name` varchar(20) CHARACTER SET latin1 DEFAULT NULL COMMENT '商品名称',
  `price` decimal(10,2) DEFAULT NULL COMMENT '商品价格',
  `quantity_in_stock` int(11) DEFAULT NULL COMMENT '库存数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品';

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单主表';

-- ----------------------------
-- Table structure for oder_detail
-- ----------------------------
DROP TABLE IF EXISTS `oder_detail`;
CREATE TABLE `oder_detail` (
  `id` int(11) NOT NULL COMMENT '订单明细编号',
  `order_id` int(11) DEFAULT NULL COMMENT '订单编号',
  `product_id` int(11) DEFAULT NULL COMMENT '商品编号',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表';


