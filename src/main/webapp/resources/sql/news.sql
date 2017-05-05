/*
Navicat MySQL Data Transfer

Source Server         : localhost_bruce
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : aggregator

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2017-05-05 18:10:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL,
  `title` text COMMENT '标题',
  `author` varchar(20) DEFAULT NULL COMMENT '作者',
  `type` varchar(20) DEFAULT NULL COMMENT '类型',
  `content` text COMMENT '正文',
  `create` datetime DEFAULT NULL COMMENT '创建时间',
  `image_flag` int(11) DEFAULT NULL COMMENT '是否含有图片（没有0，有1）',
  `link_flag` int(11) DEFAULT NULL COMMENT '是否含有相关链接（没有0，有1）',
  `url` text COMMENT 'url地址',
  `extro_1` text COMMENT '备用字段1',
  `extro_2` text COMMENT '备用字段2',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------
