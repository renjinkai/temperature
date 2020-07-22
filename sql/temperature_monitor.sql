/*
 Navicat Premium Data Transfer

 Source Server         : 81.70.27.165
 Source Server Type    : MySQL
 Source Server Version : 50649
 Source Host           : 81.70.27.165:3306
 Source Schema         : temperature_monitor

 Target Server Type    : MySQL
 Target Server Version : 50649
 File Encoding         : 65001

 Date: 22/07/2020 10:58:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for alipay_config
-- ----------------------------
DROP TABLE IF EXISTS `alipay_config`;
CREATE TABLE `alipay_config`  (
  `id` bigint(20) NOT NULL,
  `app_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `charset` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `format` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gateway_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `notify_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `private_key` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `public_key` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `return_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sign_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sys_service_provider_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of alipay_config
-- ----------------------------

-- ----------------------------
-- Table structure for app_group
-- ----------------------------
DROP TABLE IF EXISTS `app_group`;
CREATE TABLE `app_group`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '群组名称',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '群组编码',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人联系方式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of app_group
-- ----------------------------

-- ----------------------------
-- Table structure for app_group_person_relation
-- ----------------------------
DROP TABLE IF EXISTS `app_group_person_relation`;
CREATE TABLE `app_group_person_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` bigint(20) NULL DEFAULT NULL COMMENT '群组ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '成员ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of app_group_person_relation
-- ----------------------------

-- ----------------------------
-- Table structure for app_person_device_relation
-- ----------------------------
DROP TABLE IF EXISTS `app_person_device_relation`;
CREATE TABLE `app_person_device_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '监控人ID',
  `device_id` bigint(20) NULL DEFAULT NULL COMMENT '设备ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of app_person_device_relation
-- ----------------------------

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '上级部门',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `enabled` bit(1) NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '校验码',
  `frequency` int(11) NULL DEFAULT NULL COMMENT '采集频率',
  `over_frequency` int(11) NULL DEFAULT NULL COMMENT '超标后采集频率',
  `over_time` int(11) NULL DEFAULT NULL COMMENT '超标次数告警',
  `level` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否有权限，默认false',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES (1, '部门管理系统', 0, '2020-07-22 10:08:16', b'1', 'primary_school', NULL, 60, 15, 3, NULL);
INSERT INTO `dept` VALUES (2, 'APP用户部门', 0, '2020-07-22 10:52:43', b'1', 'app', NULL, 60, 15, 3, NULL);

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `device_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备ID',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '入库时间',
  `bind_time` datetime(0) NULL DEFAULT NULL COMMENT '绑定时间',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `id_card` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK1cj4m2oycbv6kvm1m4172wx6v`(`dept_id`) USING BTREE,
  CONSTRAINT `FK1cj4m2oycbv6kvm1m4172wx6v` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of device
-- ----------------------------

-- ----------------------------
-- Table structure for device_message
-- ----------------------------
DROP TABLE IF EXISTS `device_message`;
CREATE TABLE `device_message`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `message_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息类型',
  `product_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品ID',
  `device_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备ID',
  `tenant_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户ID',
  `pay_load` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备内容',
  `message` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息体',
  `device_time` datetime(0) NULL DEFAULT NULL COMMENT '设备时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of device_message
-- ----------------------------

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dict
-- ----------------------------
INSERT INTO `dict` VALUES (1, 'user_status', '用户状态');
INSERT INTO `dict` VALUES (4, 'dept_status', '部门状态');
INSERT INTO `dict` VALUES (5, 'job_status', '岗位状态');
INSERT INTO `dict` VALUES (28, 'device_status', '设备状态');
INSERT INTO `dict` VALUES (29, 'dept_type', '机构类型');
INSERT INTO `dict` VALUES (30, 'alarm_method', '通知方式');
INSERT INTO `dict` VALUES (31, 'dept_level', '部门节点是否可用');

-- ----------------------------
-- Table structure for dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `dict_detail`;
CREATE TABLE `dict_detail`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典标签',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典值',
  `sort` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '排序',
  `dict_id` bigint(11) NULL DEFAULT NULL COMMENT '字典id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK5tpkputc6d9nboxojdbgnpmyb`(`dict_id`) USING BTREE,
  CONSTRAINT `FK5tpkputc6d9nboxojdbgnpmyb` FOREIGN KEY (`dict_id`) REFERENCES `dict` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 119 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dict_detail
-- ----------------------------
INSERT INTO `dict_detail` VALUES (1, '激活', 'true', '1', 1);
INSERT INTO `dict_detail` VALUES (2, '锁定', 'false', '2', 1);
INSERT INTO `dict_detail` VALUES (11, '正常', 'true', '1', 4);
INSERT INTO `dict_detail` VALUES (12, '停用', 'false', '2', 4);
INSERT INTO `dict_detail` VALUES (13, '正常', 'true', '1', 5);
INSERT INTO `dict_detail` VALUES (14, '停用', 'false', '2', 5);
INSERT INTO `dict_detail` VALUES (106, '已绑定', 'true', '1', 28);
INSERT INTO `dict_detail` VALUES (107, '未绑定', 'false', '2', 28);
INSERT INTO `dict_detail` VALUES (108, '政府', 'government', '1', 29);
INSERT INTO `dict_detail` VALUES (109, '企业', 'enterprise', '2', 29);
INSERT INTO `dict_detail` VALUES (110, '医院', 'hospital', '3', 29);
INSERT INTO `dict_detail` VALUES (111, '工厂', 'factory', '4', 29);
INSERT INTO `dict_detail` VALUES (112, '小学', 'primary_school', '5', 29);
INSERT INTO `dict_detail` VALUES (113, '中学', 'middle_school', '6', 29);
INSERT INTO `dict_detail` VALUES (114, '大学', 'university', '7', 29);
INSERT INTO `dict_detail` VALUES (115, '短信', 'message', '1', 30);
INSERT INTO `dict_detail` VALUES (116, '微信', 'wechat', '2', 30);
INSERT INTO `dict_detail` VALUES (117, '禁止', 'false', '1', 31);
INSERT INTO `dict_detail` VALUES (118, '允许', 'true', '2', 31);

-- ----------------------------
-- Table structure for email_config
-- ----------------------------
DROP TABLE IF EXISTS `email_config`;
CREATE TABLE `email_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `from_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人',
  `host` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件服务器SMTP地址',
  `pass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `port` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '端口',
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发件者用户名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of email_config
-- ----------------------------

-- ----------------------------
-- Table structure for gen_config
-- ----------------------------
DROP TABLE IF EXISTS `gen_config`;
CREATE TABLE `gen_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `cover` bit(1) NULL DEFAULT NULL COMMENT '是否覆盖',
  `module_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块名称',
  `pack` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '至于哪个包下',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前端代码生成的路径',
  `api_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `prefix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of gen_config
-- ----------------------------
INSERT INTO `gen_config` VALUES (1, 'renjk', b'1', 'accounts-system', 'com.skyform.modules.system', 'D:\\generator\\', 'D:\\generator\\api', NULL);

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `enabled` bit(1) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `sort` bigint(20) NOT NULL,
  `dept_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKmvhj0rogastlctflsxf1d6k3i`(`dept_id`) USING BTREE,
  CONSTRAINT `FKmvhj0rogastlctflsxf1d6k3i` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of job
-- ----------------------------
INSERT INTO `job` VALUES (1, '超级管理员', b'1', '2020-07-22 10:10:15', 1, 1);

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `exception_detail` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `log_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `params` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `request_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` bigint(20) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of log
-- ----------------------------

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `i_frame` bit(1) NULL DEFAULT NULL COMMENT '是否外链',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件',
  `pid` bigint(20) NOT NULL COMMENT '上级菜单ID',
  `sort` bigint(20) NOT NULL COMMENT '排序',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '2018-12-18 15:11:29', b'0', '系统管理', NULL, 0, 1, 'system', 'system');
INSERT INTO `menu` VALUES (2, '2018-12-18 15:14:44', b'0', '用户管理', 'system/user/index', 1, 2, 'peoples', 'user');
INSERT INTO `menu` VALUES (3, '2018-12-18 15:16:07', b'0', '角色管理', 'system/role/index', 1, 3, 'role', 'role');
INSERT INTO `menu` VALUES (4, '2018-12-18 15:16:45', b'0', '权限管理', 'system/permission/index', 1, 4, 'permission', 'permission');
INSERT INTO `menu` VALUES (5, '2018-12-18 15:17:28', b'0', '菜单管理', 'system/menu/index', 1, 5, 'menu', 'menu');
INSERT INTO `menu` VALUES (6, '2018-12-18 15:17:48', b'0', '系统监控', NULL, 0, 9, 'monitor', 'monitor');
INSERT INTO `menu` VALUES (7, '2018-12-18 15:18:26', b'0', '操作日志', 'monitor/log/index', 6, 11, 'log', 'logs');
INSERT INTO `menu` VALUES (8, '2018-12-18 15:19:01', b'0', '系统缓存', 'monitor/redis/index', 6, 13, 'redis', 'redis');
INSERT INTO `menu` VALUES (9, '2018-12-18 15:19:34', b'0', 'SQL监控', 'monitor/sql/index', 6, 14, 'sqlMonitor', 'druid');
INSERT INTO `menu` VALUES (10, '2018-12-19 13:38:16', b'0', '组件管理', NULL, 0, 13, 'zujian', 'components');
INSERT INTO `menu` VALUES (11, '2018-12-19 13:38:49', b'0', '图标库', 'components/IconSelect', 10, 51, 'icon', 'icon');
INSERT INTO `menu` VALUES (14, '2018-12-27 10:13:09', b'0', '邮件工具', 'tools/email/index', 36, 24, 'email', 'email');
INSERT INTO `menu` VALUES (15, '2018-12-27 11:58:25', b'0', '富文本', 'components/Editor', 10, 52, 'fwb', 'tinymce');
INSERT INTO `menu` VALUES (16, '2018-12-28 09:36:53', b'0', '图床管理', 'tools/picture/index', 36, 25, 'image', 'pictures');
INSERT INTO `menu` VALUES (19, '2018-12-31 14:52:38', b'0', '支付宝工具', 'tools/aliPay/index', 36, 27, 'alipay', 'aliPay');
INSERT INTO `menu` VALUES (24, '2019-01-04 16:24:48', b'0', '三级菜单1', 'nested/menu1/menu1-1', 22, 999, 'menu', 'menu1-1');
INSERT INTO `menu` VALUES (27, '2019-01-07 17:27:32', b'0', '三级菜单2', 'nested/menu1/menu1-2', 22, 999, 'menu', 'menu1-2');
INSERT INTO `menu` VALUES (28, '2019-01-07 20:34:40', b'0', '定时任务', 'system/timing/index', 36, 21, 'timing', 'timing');
INSERT INTO `menu` VALUES (30, '2019-01-11 15:45:55', b'0', '代码生成', 'generator/index', 36, 22, 'dev', 'generator');
INSERT INTO `menu` VALUES (32, '2019-01-13 13:49:03', b'0', '异常日志', 'monitor/log/errorLog', 6, 12, 'error', 'errorLog');
INSERT INTO `menu` VALUES (33, '2019-03-08 13:46:44', b'0', 'Markdown', 'components/MarkDown', 10, 53, 'markdown', 'markdown');
INSERT INTO `menu` VALUES (34, '2019-03-08 15:49:40', b'0', 'Yaml编辑器', 'components/YamlEdit', 10, 54, 'dev', 'yaml');
INSERT INTO `menu` VALUES (35, '2019-03-25 09:46:00', b'0', '部门管理', 'system/dept/index', 1, 6, 'dept', 'dept');
INSERT INTO `menu` VALUES (36, '2019-03-29 10:57:35', b'0', '系统工具', '', 0, 14, 'sys-tools', 'sys-tools');
INSERT INTO `menu` VALUES (37, '2019-03-29 13:51:18', b'0', '岗位管理', 'system/job/index', 1, 7, 'Steve-Jobs', 'job');
INSERT INTO `menu` VALUES (38, '2019-03-29 19:57:53', b'0', '接口文档', 'tools/swagger/index', 36, 23, 'swagger', 'swagger2');
INSERT INTO `menu` VALUES (39, '2019-04-10 11:49:04', b'0', '字典管理', 'system/dict/index', 1, 8, 'dictionary', 'dict');
INSERT INTO `menu` VALUES (55, '2020-05-29 10:44:27', b'0', '人员管理', 'student/monitor/index', 0, 51, 'peoples', 'student');
INSERT INTO `menu` VALUES (58, '2020-06-05 15:03:35', b'0', '综合监控', '', 0, 50, 'monitor', 'complex');
INSERT INTO `menu` VALUES (59, '2020-06-05 15:06:35', b'0', '监控详情', 'complex/center', 58, 2, 'redis', ':id');
INSERT INTO `menu` VALUES (60, '2020-06-05 15:07:55', b'0', '设备管理', 'device/index', 0, 52, 'running', 'device');
INSERT INTO `menu` VALUES (61, '2020-06-05 15:24:00', b'0', '监控信息', 'complex/index', 58, 1, 'develop', 'list');
INSERT INTO `menu` VALUES (62, '2020-06-28 09:19:27', b'0', '群组管理', 'weChatGroup/index', 0, 60, 'dept', 'group');
INSERT INTO `menu` VALUES (63, '2020-06-28 09:20:24', b'0', '系统设置', 'setting/index', 0, 53, 'role', 'setting');
INSERT INTO `menu` VALUES (64, '2020-06-28 11:35:06', b'0', '短信日志', 'messageLog/index', 6, 26, 'log', 'messageLog');
INSERT INTO `menu` VALUES (65, '2020-07-16 16:17:12', b'0', '设备信息', 'device_message/index', 6, 999, 'markdown', 'deviceMessage');

-- ----------------------------
-- Table structure for message_log
-- ----------------------------
DROP TABLE IF EXISTS `message_log`;
CREATE TABLE `message_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `send_time` datetime(0) NULL DEFAULT NULL COMMENT '短信发送时间',
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '验证码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of message_log
-- ----------------------------

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '别名',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `pid` int(11) NOT NULL COMMENT '上级权限',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 95 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '超级管理员', '2018-12-03 12:27:48', 'ADMIN', 0);
INSERT INTO `permission` VALUES (2, '用户管理', '2018-12-03 12:28:19', 'USER_ALL', 0);
INSERT INTO `permission` VALUES (3, '用户查询', '2018-12-03 12:31:35', 'USER_SELECT', 2);
INSERT INTO `permission` VALUES (4, '用户创建', '2018-12-03 12:31:35', 'USER_CREATE', 2);
INSERT INTO `permission` VALUES (5, '用户编辑', '2018-12-03 12:31:35', 'USER_EDIT', 2);
INSERT INTO `permission` VALUES (6, '用户删除', '2018-12-03 12:31:35', 'USER_DELETE', 2);
INSERT INTO `permission` VALUES (7, '角色管理', '2018-12-03 12:28:19', 'ROLES_ALL', 0);
INSERT INTO `permission` VALUES (8, '角色查询', '2018-12-03 12:31:35', 'ROLES_SELECT', 7);
INSERT INTO `permission` VALUES (10, '角色创建', '2018-12-09 20:10:16', 'ROLES_CREATE', 7);
INSERT INTO `permission` VALUES (11, '角色编辑', '2018-12-09 20:10:42', 'ROLES_EDIT', 7);
INSERT INTO `permission` VALUES (12, '角色删除', '2018-12-09 20:11:07', 'ROLES_DELETE', 7);
INSERT INTO `permission` VALUES (13, '权限管理', '2018-12-09 20:11:37', 'PERMISSION_ALL', 0);
INSERT INTO `permission` VALUES (14, '权限查询', '2018-12-09 20:11:55', 'PERMISSION_SELECT', 13);
INSERT INTO `permission` VALUES (15, '权限创建', '2018-12-09 20:14:10', 'PERMISSION_CREATE', 13);
INSERT INTO `permission` VALUES (16, '权限编辑', '2018-12-09 20:15:44', 'PERMISSION_EDIT', 13);
INSERT INTO `permission` VALUES (17, '权限删除', '2018-12-09 20:15:59', 'PERMISSION_DELETE', 13);
INSERT INTO `permission` VALUES (18, '缓存管理', '2018-12-17 13:53:25', 'REDIS_ALL', 0);
INSERT INTO `permission` VALUES (20, '缓存查询', '2018-12-17 13:54:07', 'REDIS_SELECT', 18);
INSERT INTO `permission` VALUES (22, '缓存删除', '2018-12-17 13:55:04', 'REDIS_DELETE', 18);
INSERT INTO `permission` VALUES (23, '图床管理', '2018-12-27 20:31:49', 'PICTURE_ALL', 0);
INSERT INTO `permission` VALUES (24, '查询图片', '2018-12-27 20:32:04', 'PICTURE_SELECT', 23);
INSERT INTO `permission` VALUES (25, '上传图片', '2018-12-27 20:32:24', 'PICTURE_UPLOAD', 23);
INSERT INTO `permission` VALUES (26, '删除图片', '2018-12-27 20:32:45', 'PICTURE_DELETE', 23);
INSERT INTO `permission` VALUES (29, '菜单管理', '2018-12-28 17:34:31', 'MENU_ALL', 0);
INSERT INTO `permission` VALUES (30, '菜单查询', '2018-12-28 17:34:41', 'MENU_SELECT', 29);
INSERT INTO `permission` VALUES (31, '菜单创建', '2018-12-28 17:34:52', 'MENU_CREATE', 29);
INSERT INTO `permission` VALUES (32, '菜单编辑', '2018-12-28 17:35:20', 'MENU_EDIT', 29);
INSERT INTO `permission` VALUES (33, '菜单删除', '2018-12-28 17:35:29', 'MENU_DELETE', 29);
INSERT INTO `permission` VALUES (35, '定时任务管理', '2019-01-08 14:59:57', 'JOB_ALL', 0);
INSERT INTO `permission` VALUES (36, '任务查询', '2019-01-08 15:00:09', 'JOB_SELECT', 35);
INSERT INTO `permission` VALUES (37, '任务创建', '2019-01-08 15:00:20', 'JOB_CREATE', 35);
INSERT INTO `permission` VALUES (38, '任务编辑', '2019-01-08 15:00:33', 'JOB_EDIT', 35);
INSERT INTO `permission` VALUES (39, '任务删除', '2019-01-08 15:01:13', 'JOB_DELETE', 35);
INSERT INTO `permission` VALUES (40, '部门管理', '2019-03-29 17:06:55', 'DEPT_ALL', 0);
INSERT INTO `permission` VALUES (41, '部门查询', '2019-03-29 17:07:09', 'DEPT_SELECT', 40);
INSERT INTO `permission` VALUES (42, '部门创建', '2019-03-29 17:07:29', 'DEPT_CREATE', 40);
INSERT INTO `permission` VALUES (43, '部门编辑', '2019-03-29 17:07:52', 'DEPT_EDIT', 40);
INSERT INTO `permission` VALUES (44, '部门删除', '2019-03-29 17:08:14', 'DEPT_DELETE', 40);
INSERT INTO `permission` VALUES (45, '岗位管理', '2019-03-29 17:08:52', 'USERJOB_ALL', 0);
INSERT INTO `permission` VALUES (46, '岗位查询', '2019-03-29 17:10:27', 'USERJOB_SELECT', 45);
INSERT INTO `permission` VALUES (47, '岗位创建', '2019-03-29 17:10:55', 'USERJOB_CREATE', 45);
INSERT INTO `permission` VALUES (48, '岗位编辑', '2019-03-29 17:11:08', 'USERJOB_EDIT', 45);
INSERT INTO `permission` VALUES (49, '岗位删除', '2019-03-29 17:11:19', 'USERJOB_DELETE', 45);
INSERT INTO `permission` VALUES (50, '字典管理', '2019-04-10 16:24:51', 'DICT_ALL', 0);
INSERT INTO `permission` VALUES (51, '字典查询', '2019-04-10 16:25:16', 'DICT_SELECT', 50);
INSERT INTO `permission` VALUES (52, '字典创建', '2019-04-10 16:25:29', 'DICT_CREATE', 50);
INSERT INTO `permission` VALUES (53, '字典编辑', '2019-04-10 16:27:19', 'DICT_EDIT', 50);
INSERT INTO `permission` VALUES (54, '字典删除', '2019-04-10 16:27:30', 'DICT_DELETE', 50);
INSERT INTO `permission` VALUES (70, '学生管理', '2020-05-29 11:21:52', 'STUDENT_ALL', 0);
INSERT INTO `permission` VALUES (71, '学生查询', '2020-05-29 11:25:59', 'STUDENT_SELECT', 70);
INSERT INTO `permission` VALUES (72, '学生创建', '2020-05-29 11:27:40', 'STUDENT_CREATE', 70);
INSERT INTO `permission` VALUES (73, '学生编辑', '2020-05-29 11:31:50', 'STUDENT_EDIT', 70);
INSERT INTO `permission` VALUES (74, '学生删除', '2020-05-29 11:32:11', 'STUDENT_DELETE', 70);
INSERT INTO `permission` VALUES (75, '设备管理', '2020-06-01 13:36:59', 'DEVICE_ALL', 0);
INSERT INTO `permission` VALUES (76, '设备查询', '2020-06-01 13:37:44', 'DEVICE_SELECT', 75);
INSERT INTO `permission` VALUES (77, '设备创建', '2020-06-01 13:38:49', 'DEVICE_CREATE', 75);
INSERT INTO `permission` VALUES (78, '设备编辑', '2020-06-01 13:39:07', 'DEVICE_EDIT', 75);
INSERT INTO `permission` VALUES (79, '设备删除', '2020-06-01 13:39:19', 'DEVICE_DELETE', 75);
INSERT INTO `permission` VALUES (80, '设备绑定', '2020-06-02 09:44:51', 'DEVICE_BIND', 75);
INSERT INTO `permission` VALUES (81, '设备解绑', '2020-06-02 09:45:18', 'DEVICE_UNBIND', 75);
INSERT INTO `permission` VALUES (82, '学生导出', '2020-06-02 11:42:07', 'STUDENT_EXPORT', 70);
INSERT INTO `permission` VALUES (83, '下载学生导入模板', '2020-06-02 11:42:30', 'STUDENT_DOWNLOAD', 70);
INSERT INTO `permission` VALUES (84, '学生导入', '2020-06-02 11:42:45', 'STUDENT_IMPORT', 70);
INSERT INTO `permission` VALUES (85, '部门验证码绑定', '2020-06-03 09:58:12', 'DEPT_BIND', 40);
INSERT INTO `permission` VALUES (86, '群组管理', '2020-06-19 13:11:57', 'APPGROUP_ALL', 0);
INSERT INTO `permission` VALUES (87, '群组查询', '2020-06-19 13:13:09', 'APPGROUP_SELECT', 86);
INSERT INTO `permission` VALUES (88, '群组创建', '2020-06-19 13:13:27', 'APPGROUP_CREATE', 86);
INSERT INTO `permission` VALUES (89, '群组编辑', '2020-06-19 13:13:40', 'APPGROUP_EDIT', 86);
INSERT INTO `permission` VALUES (90, '群组删除', '2020-06-19 13:13:52', 'APPGROUP_DELETE', 86);
INSERT INTO `permission` VALUES (91, '设备数据管理', '2020-07-16 17:04:26', 'DEVICEMESSAGE_ALL', 0);
INSERT INTO `permission` VALUES (92, '设备数据查询', '2020-07-16 17:04:50', 'DEVICEMESSAGE_SELECT', 91);
INSERT INTO `permission` VALUES (93, '设备数据编辑', '2020-07-16 17:10:30', 'DEVICEMESSAGE_EDIT', 91);
INSERT INTO `permission` VALUES (94, '设备数据删除', '2020-07-16 17:11:07', 'DEVICEMESSAGE_DELETE', 91);

-- ----------------------------
-- Table structure for picture
-- ----------------------------
DROP TABLE IF EXISTS `picture`;
CREATE TABLE `picture`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '上传日期',
  `delete_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除的URL',
  `filename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片名称',
  `height` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片高度',
  `size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片大小',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `width` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片宽度',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of picture
-- ----------------------------

-- ----------------------------
-- Table structure for quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `quartz_job`;
CREATE TABLE `quartz_job`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `bean_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Spring Bean名称',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron 表达式',
  `is_pause` bit(1) NULL DEFAULT NULL COMMENT '状态：1暂停、0启用',
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名称',
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '创建或更新日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of quartz_job
-- ----------------------------

-- ----------------------------
-- Table structure for quartz_log
-- ----------------------------
DROP TABLE IF EXISTS `quartz_log`;
CREATE TABLE `quartz_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `baen_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `exception_detail` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `is_success` bit(1) NULL DEFAULT NULL,
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of quartz_log
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `data_scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `level` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, NULL, '超级管理员', '系统所有权', '全部', 1);

-- ----------------------------
-- Table structure for roles_depts
-- ----------------------------
DROP TABLE IF EXISTS `roles_depts`;
CREATE TABLE `roles_depts`  (
  `role_id` bigint(20) NOT NULL,
  `dept_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE,
  INDEX `FK7qg6itn5ajdoa9h9o78v9ksur`(`dept_id`) USING BTREE,
  CONSTRAINT `FK7qg6itn5ajdoa9h9o78v9ksur` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKrg1ci4cxxfbja0sb0pddju7k` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of roles_depts
-- ----------------------------

-- ----------------------------
-- Table structure for roles_menus
-- ----------------------------
DROP TABLE IF EXISTS `roles_menus`;
CREATE TABLE `roles_menus`  (
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`menu_id`, `role_id`) USING BTREE,
  INDEX `FKcngg2qadojhi3a651a5adkvbq`(`role_id`) USING BTREE,
  CONSTRAINT `FKcngg2qadojhi3a651a5adkvbq` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKq1knxf8ykt26we8k331naabjx` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of roles_menus
-- ----------------------------
INSERT INTO `roles_menus` VALUES (1, 1);
INSERT INTO `roles_menus` VALUES (2, 1);
INSERT INTO `roles_menus` VALUES (3, 1);
INSERT INTO `roles_menus` VALUES (4, 1);
INSERT INTO `roles_menus` VALUES (5, 1);
INSERT INTO `roles_menus` VALUES (6, 1);
INSERT INTO `roles_menus` VALUES (7, 1);
INSERT INTO `roles_menus` VALUES (8, 1);
INSERT INTO `roles_menus` VALUES (9, 1);
INSERT INTO `roles_menus` VALUES (10, 1);
INSERT INTO `roles_menus` VALUES (11, 1);
INSERT INTO `roles_menus` VALUES (14, 1);
INSERT INTO `roles_menus` VALUES (15, 1);
INSERT INTO `roles_menus` VALUES (16, 1);
INSERT INTO `roles_menus` VALUES (19, 1);
INSERT INTO `roles_menus` VALUES (28, 1);
INSERT INTO `roles_menus` VALUES (30, 1);
INSERT INTO `roles_menus` VALUES (32, 1);
INSERT INTO `roles_menus` VALUES (33, 1);
INSERT INTO `roles_menus` VALUES (34, 1);
INSERT INTO `roles_menus` VALUES (35, 1);
INSERT INTO `roles_menus` VALUES (36, 1);
INSERT INTO `roles_menus` VALUES (37, 1);
INSERT INTO `roles_menus` VALUES (38, 1);
INSERT INTO `roles_menus` VALUES (39, 1);
INSERT INTO `roles_menus` VALUES (55, 1);
INSERT INTO `roles_menus` VALUES (58, 1);
INSERT INTO `roles_menus` VALUES (59, 1);
INSERT INTO `roles_menus` VALUES (60, 1);
INSERT INTO `roles_menus` VALUES (61, 1);
INSERT INTO `roles_menus` VALUES (62, 1);
INSERT INTO `roles_menus` VALUES (63, 1);
INSERT INTO `roles_menus` VALUES (64, 1);
INSERT INTO `roles_menus` VALUES (65, 1);

-- ----------------------------
-- Table structure for roles_permissions
-- ----------------------------
DROP TABLE IF EXISTS `roles_permissions`;
CREATE TABLE `roles_permissions`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE,
  INDEX `FKboeuhl31go7wer3bpy6so7exi`(`permission_id`) USING BTREE,
  CONSTRAINT `FK4hrolwj4ned5i7qe8kyiaak6m` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKboeuhl31go7wer3bpy6so7exi` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of roles_permissions
-- ----------------------------
INSERT INTO `roles_permissions` VALUES (1, 1);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `id_card` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `device_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备ID',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '班级ID',
  `dept_school` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学校名称',
  `dept_school_id` bigint(20) NULL DEFAULT NULL COMMENT '学校ID',
  `parent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家长姓名',
  `parent_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家长联系方式',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '住校地址',
  `room` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房间号',
  `alarm_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '责任人',
  `alarm_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '责任人手机号',
  `alarm_wechat` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '责任人微信号',
  `alarm_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通知方式',
  `recent_temperature` double(11, 1) NULL DEFAULT NULL COMMENT '最近一次检测体温',
  `recent_time` datetime(0) NULL DEFAULT NULL COMMENT '最近一次检测时间',
  `loca` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '轨迹',
  `bind_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '绑定状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK51vi5k0vbdvve6mrxlme8x0vo`(`dept_id`) USING BTREE,
  CONSTRAINT `FK51vi5k0vbdvve6mrxlme8x0vo` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student
-- ----------------------------

-- ----------------------------
-- Table structure for temperature
-- ----------------------------
DROP TABLE IF EXISTS `temperature`;
CREATE TABLE `temperature`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `id_card` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `device_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备ID',
  `temperature` double(11, 1) NULL DEFAULT NULL COMMENT '温度',
  `record_time` datetime(0) NULL DEFAULT NULL COMMENT '记录时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKtqhu3ppkw9cmpjjeca4wp56dm`(`dept_id`) USING BTREE,
  CONSTRAINT `FKtqhu3ppkw9cmpjjeca4wp56dm` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of temperature
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `enabled` bigint(20) NULL DEFAULT NULL COMMENT '状态：1启用、0禁用',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `last_password_reset_time` datetime(0) NULL DEFAULT NULL COMMENT '最后修改密码的日期',
  `dept_id` bigint(20) NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job_id` bigint(20) NULL DEFAULT NULL,
  `cname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_kpubos9gc2cvtkb0thktkbkes`(`email`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  INDEX `FK5rwmryny6jthaaxkogownknqp`(`dept_id`) USING BTREE,
  INDEX `FKfftoc2abhot8f2wu6cl9a5iky`(`job_id`) USING BTREE,
  CONSTRAINT `FK5rwmryny6jthaaxkogownknqp` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKfftoc2abhot8f2wu6cl9a5iky` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80', '2020-07-22 10:05:22', '188038155@qq.com', 1, 'a44fcd442f961f418397a52c2507af69', 'admin', NULL, 1, '17600639411', 1, NULL);

-- ----------------------------
-- Table structure for users_roles
-- ----------------------------
DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `FKq4eq273l04bpu4efj0jd0jb98`(`role_id`) USING BTREE,
  CONSTRAINT `users_roles_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `users_roles_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users_roles
-- ----------------------------
INSERT INTO `users_roles` VALUES (1, 1);

-- ----------------------------
-- Table structure for verification_code
-- ----------------------------
DROP TABLE IF EXISTS `verification_code`;
CREATE TABLE `verification_code`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '验证码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `status` bit(1) NULL DEFAULT NULL COMMENT '状态：1有效、0过期',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '验证码类型：email或者短信',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收邮箱或者手机号码',
  `scenes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务名称：如重置邮箱、重置密码等',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of verification_code
-- ----------------------------

-- ----------------------------
-- Table structure for visits
-- ----------------------------
DROP TABLE IF EXISTS `visits`;
CREATE TABLE `visits`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip_counts` bigint(20) NULL DEFAULT NULL,
  `pv_counts` bigint(20) NULL DEFAULT NULL,
  `week_day` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_11aksgq87euk9bcyeesfs4vtp`(`date`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of visits
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
