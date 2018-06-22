-- DROP DATABASE IF EXISTS `mytest`;

CREATE DATABASE mytest;

/*
Navicat MySQL Data Transfer

Source Server         : localhost5.7
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : mytest

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-06-22 10:18:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sms_send
-- ----------------------------
DROP TABLE IF EXISTS `t_sms_send`;
CREATE TABLE `t_sms_send` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `user_name` varchar(255) NOT NULL COMMENT '用户id',
  `server_name` varchar(255) DEFAULT NULL COMMENT '服务名称',
  `phone` varchar(255) NOT NULL COMMENT '手机号',
  `content` varchar(1000) NOT NULL COMMENT '内容',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1007 DEFAULT CHARSET=utf8 COMMENT='短信发送记录表';

-- ----------------------------
-- Records of t_sms_send
-- ----------------------------
INSERT INTO `t_sms_send` VALUES ('1000', '123', '李四', 'lesson-svr', '18515481207', '您的短信验证码为：1111，欢迎注册OKAY，请在1分钟内输入。', '2018-06-21 13:43:30');
INSERT INTO `t_sms_send` VALUES ('1001', '123', '李四', 'lesson-svr', '18515481207', '您的短信验证码为：1111，欢迎注册OKAY，请在1分钟内输入。', '2018-06-21 14:01:06');
INSERT INTO `t_sms_send` VALUES ('1002', '123', '李四', 'lesson-svr', '18515481207', '您的短信验证码为：1111，欢迎注册OKAY，请在1分钟内输入。', '2018-06-21 14:01:08');
INSERT INTO `t_sms_send` VALUES ('1003', '123', '李四', 'lesson-svr', '18515481207', '您的短信验证码为：1111，欢迎注册OKAY，请在1分钟内输入。', '2018-06-21 14:01:09');
INSERT INTO `t_sms_send` VALUES ('1004', '123', '李四', 'lesson-svr', '18515481207', '您的短信验证码为：1111，欢迎注册OKAY，请在1分钟内输入。', '2018-06-21 14:01:09');
INSERT INTO `t_sms_send` VALUES ('1005', '123', '李四', 'lesson-svr', '18515481207', '您的短信验证码为：1111，欢迎注册OKAY，请在1分钟内输入。', '2018-06-21 14:01:10');
INSERT INTO `t_sms_send` VALUES ('1006', '123', '李四', 'lesson-svr', '18515481207', '您的短信验证码为：1111，欢迎注册OKAY，请在1分钟内输入。', '2018-06-21 14:01:10');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `phone` varchar(255) NOT NULL COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1000', '1111', '张三', 'aaaweqrqwer', '18511639464');
