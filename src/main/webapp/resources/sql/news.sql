/*
Navicat MySQL Data Transfer

Source Server         : localhost_bruce
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : aggregator

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2017-05-16 17:01:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` text COMMENT '标题',
  `author` varchar(20) DEFAULT NULL COMMENT '作者',
  `type` varchar(20) DEFAULT NULL COMMENT '类型',
  `content` text COMMENT '正文',
  `time` text COMMENT '创建时间',
  `image_url` text COMMENT '图片地址',
  `link_flag` int(11) DEFAULT NULL COMMENT '是否含有相关链接（没有0，有1）',
  `url` text COMMENT 'url地址',
  `keyword` text COMMENT '关键字',
  `source` text COMMENT '来源',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=705 DEFAULT CHARSET=utf8;