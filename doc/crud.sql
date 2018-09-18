/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : ssm_crud

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2018-09-18 15:33:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_dept
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dept`;
CREATE TABLE `tbl_dept` (
  `dept_Id` int(11) NOT NULL,
  `dept_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_dept
-- ----------------------------
INSERT INTO `tbl_dept` VALUES ('1', '计算机');
INSERT INTO `tbl_dept` VALUES ('3', '网络部');

-- ----------------------------
-- Table structure for tbl_emp
-- ----------------------------
DROP TABLE IF EXISTS `tbl_emp`;
CREATE TABLE `tbl_emp` (
  `emp_Id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`emp_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_emp
-- ----------------------------
INSERT INTO `tbl_emp` VALUES ('1', '张三', 'M', '33333322@qq.com', '3');
INSERT INTO `tbl_emp` VALUES ('2', '张三王', 'F', 'qwer@qq.com', '3');
INSERT INTO `tbl_emp` VALUES ('3', 'bzchao', 'M', 'aaa@qq.com', '1');
INSERT INTO `tbl_emp` VALUES ('4', '陈光超', 'F', 'chao@qq.com', '1');
INSERT INTO `tbl_emp` VALUES ('5', 'baidu', 'F', 'baidi@qq.com', '3');
INSERT INTO `tbl_emp` VALUES ('6', '王伟', 'F', 'wangweii@qq.com', '3');
