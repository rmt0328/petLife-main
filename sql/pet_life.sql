/*
 Navicat Premium Data Transfer

 Source Server         : localhost_5.7
 Source Server Type    : MySQL
 Source Server Version : 50734 (5.7.34-log)
 Source Host           : localhost:3306
 Source Schema         : pet_life

 Target Server Type    : MySQL
 Target Server Version : 50734 (5.7.34-log)
 File Encoding         : 65001

 Date: 24/05/2023 22:50:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for adopt_apply
-- ----------------------------
DROP TABLE IF EXISTS `adopt_apply`;
CREATE TABLE `adopt_apply`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `adopt_id` int(11) NOT NULL,
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名称',
  `adopt_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `sex` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户性别（1-男 2-女）',
  `age` int(11) NOT NULL COMMENT '年龄',
  `pet_num` int(11) NOT NULL DEFAULT 0 COMMENT '养宠数量',
  `house_status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '住房情况（1-租房 2-自有房）',
  `marital_status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '婚姻状况（1-未婚 2-已婚）',
  `earning` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收入（1-八千以内 2-八千到两万 3-两万以上）',
  `job` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职业',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '居住地',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `weixin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信号',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '申请理由',
  `is_passed` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '2' COMMENT '是否通过（0-未通过 1-通过 2-审核中）',
  `is_deleted` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '0-正常 1-删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '申请领养表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of adopt_apply
-- ----------------------------
INSERT INTO `adopt_apply` VALUES (1, 1, 'oh2iA5AVCtjvCfxA9XKPDB0yqhp0', '小明', 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '1', 23, 1, '1', '1', '2', '金融', '杭州', '13100200001', '1232131', '想养狗', '0', '0', '2023-05-18 21:40:38', '2023-05-18 23:20:37');
INSERT INTO `adopt_apply` VALUES (2, 1, 'oh2iA5AVCtjvCfxA9XKPDB0yqhp0', '哈哈[呲牙]', 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '1', 25, 2, '1', '1', '2', '金融', '杭州', '13198567084', 'ghjk', '哈哈[呲牙]', '0', '0', '2023-05-18 21:57:49', '2023-05-18 23:20:37');
INSERT INTO `adopt_apply` VALUES (3, 1, 'oh2iA5AVCtjvCfxA9XKPDB0yqhp01', '小白', 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '1', 25, 2, '1', '1', '2', '金融', '杭州', '13100000085', 'hjja', '哈哈哈', '0', '0', '2023-05-18 22:50:51', '2023-05-18 23:20:37');
INSERT INTO `adopt_apply` VALUES (5, 1, 'oh2iA5AVCtjvCfxA9XKPDB0yqhp0', '小白', 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '1', 25, 0, '1', '1', '2', '互联网', '杭州', '13100000001', 'hjjbvfh', '测试一下', '1', '0', '2023-05-18 23:20:11', '2023-05-18 23:20:37');

-- ----------------------------
-- Table structure for answer_prize
-- ----------------------------
DROP TABLE IF EXISTS `answer_prize`;
CREATE TABLE `answer_prize`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(1275) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '题目',
  `options` varchar(1275) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '选项 (多个选项逗号分隔)',
  `answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '正确答案',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型',
  `is_deleted` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '0-正常 1-删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '答题表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of answer_prize
-- ----------------------------
INSERT INTO `answer_prize` VALUES (1, '猜一猜：北京冬奥会火炬“雪花”，如何保证燃料充足持续燃烧', '上方威亚吊着气路,把气瓶藏在花瓣里', '上方威亚吊着气路', '我们和地球', '0', '2022-02-17 14:58:13', '2022-02-25 16:41:10');
INSERT INTO `answer_prize` VALUES (2, '这是一个题目', '选项一,选项二', '选项一', '测试类别', '0', '2022-04-05 11:30:30', '2022-04-05 11:30:29');

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标签',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'H5链接',
  `bg_color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '背景色',
  `picture` varchar(1275) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '封面图',
  `view` int(11) NOT NULL DEFAULT 0 COMMENT '浏览量',
  `is_deleted` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '0-正常 1-删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, '沙雕集锦', '狗子沙雕集锦', 'https://mp.weixin.qq.com/s/bNjidkegbuBVrtxGl7uOZA', '#FCF5C9', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic2.zhimg.com%2Fv2-6df7121d5e4955182c835d77f826c773_r.jpg%3Fsource%3D1940ef5c&refer=http%3A%2F%2Fpic2.zhimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1646472663&t=1c7f3796198783c41685d8e4262d0046', 0, '0', '2022-02-03 16:09:21', '2023-03-02 21:23:31');
INSERT INTO `article` VALUES (2, '养宠百科', '狗子沙雕集锦', 'https://mp.weixin.qq.com/s/bNjidkegbuBVrtxGl7uOZA', '#EEF9FF', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic2.zhimg.com%2Fv2-6df7121d5e4955182c835d77f826c773_r.jpg%3Fsource%3D1940ef5c&refer=http%3A%2F%2Fpic2.zhimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1646472663&t=1c7f3796198783c41685d8e4262d0046', 0, '0', '2022-02-03 16:09:21', '2022-02-03 17:48:31');
INSERT INTO `article` VALUES (3, '萌宠故事', '狗子沙雕集锦', 'https://mp.weixin.qq.com/s/bNjidkegbuBVrtxGl7uOZA', '#F2FCE8', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic2.zhimg.com%2Fv2-6df7121d5e4955182c835d77f826c773_r.jpg%3Fsource%3D1940ef5c&refer=http%3A%2F%2Fpic2.zhimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1646472663&t=1c7f3796198783c41685d8e4262d0046', 0, '0', '2022-02-03 16:09:21', '2022-02-03 17:48:29');
INSERT INTO `article` VALUES (4, '领表情包', '狗子沙雕集锦', 'https://mp.weixin.qq.com/s/bNjidkegbuBVrtxGl7uOZA', '#FFEEEF', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic2.zhimg.com%2Fv2-6df7121d5e4955182c835d77f826c773_r.jpg%3Fsource%3D1940ef5c&refer=http%3A%2F%2Fpic2.zhimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1646472663&t=1c7f3796198783c41685d8e4262d0046', 0, '0', '2022-02-03 16:09:21', '2022-02-03 17:48:28');
INSERT INTO `article` VALUES (5, '领表情包', '狗子沙雕集锦', 'https://mp.weixin.qq.com/s/bNjidkegbuBVrtxGl7uOZA', '#F2FCE8', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic2.zhimg.com%2Fv2-6df7121d5e4955182c835d77f826c773_r.jpg%3Fsource%3D1940ef5c&refer=http%3A%2F%2Fpic2.zhimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1646472663&t=1c7f3796198783c41685d8e4262d0046', 0, '0', '2022-02-03 16:09:21', '2022-02-03 17:48:27');

-- ----------------------------
-- Table structure for chat
-- ----------------------------
DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发送人id',
  `to_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接收人id',
  `content` varchar(1275) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息内容',
  `is_done` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '0-未读 1-已读',
  `is_deleted` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '0-正常 1-删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '聊天表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chat
-- ----------------------------
INSERT INTO `chat` VALUES (15, 'oh2iA5AVCtjvCfxA9XKPDB0yqhp0', 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '你好', '1', '0', '2022-09-28 16:53:44', '2022-09-28 17:44:03');
INSERT INTO `chat` VALUES (16, 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', 'oh2iA5AVCtjvCfxA9XKPDB0yqhp0', '有事请留言', '1', '0', '2022-09-28 17:44:20', '2022-09-28 17:44:24');
INSERT INTO `chat` VALUES (17, 'oh2iA5AVCtjvCfxA9XKPDB0yqhp0', 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '你好 我想领养你的狗狗', '1', '0', '2023-05-18 21:45:58', '2023-05-18 21:46:08');

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型 （ADOPT FIND CIRCLE）',
  `userId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `from_id` int(11) NOT NULL COMMENT '来源ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collect
-- ----------------------------

-- ----------------------------
-- Table structure for collect_user_answer
-- ----------------------------
DROP TABLE IF EXISTS `collect_user_answer`;
CREATE TABLE `collect_user_answer`  (
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `answer_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '答题ID',
  `type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '答题结果 1-答对 2-答错',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户答题关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collect_user_answer
-- ----------------------------
INSERT INTO `collect_user_answer` VALUES ('oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '1', '2', '2022-02-17 18:14:03');
INSERT INTO `collect_user_answer` VALUES ('oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '1', '1', '2022-02-18 10:21:39');
INSERT INTO `collect_user_answer` VALUES ('oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '2', '1', '2022-04-05 11:30:57');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父级ID 可为null',
  `grand_id` int(11) NULL DEFAULT NULL COMMENT '祖级ID 可为null',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类别 ADOPT FIND CIRCLE',
  `from_id` int(11) NOT NULL COMMENT '所属文章ID',
  `owner_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属者ID',
  `love` int(11) NOT NULL DEFAULT 0 COMMENT '点赞',
  `is_deleted` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '0-正常 1-删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (16, NULL, NULL, '11', 'CIRCLE', 3, 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', 0, '1', '2022-02-03 01:27:41', '2022-02-15 13:54:48');
INSERT INTO `comment` VALUES (17, NULL, NULL, '111', 'CIRCLE', 3, 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', 0, '1', '2022-02-12 19:24:26', '2022-02-15 13:54:50');
INSERT INTO `comment` VALUES (18, NULL, NULL, '1111', 'CIRCLE', 3, 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', 0, '1', '2022-02-15 14:13:10', '2022-02-15 14:13:15');
INSERT INTO `comment` VALUES (19, NULL, NULL, '2121', 'CIRCLE', 3, 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', 0, '1', '2022-02-15 14:13:52', '2022-02-15 14:13:53');
INSERT INTO `comment` VALUES (20, NULL, NULL, '111', 'CIRCLE', 3, 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', 0, '0', '2022-02-15 14:14:03', NULL);
INSERT INTO `comment` VALUES (21, NULL, NULL, '222', 'CIRCLE', 3, 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', 0, '0', '2022-02-15 14:14:05', NULL);
INSERT INTO `comment` VALUES (22, NULL, NULL, '111', 'FIND', 3, 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', 0, '1', '2022-02-15 14:25:26', '2022-02-15 14:25:49');
INSERT INTO `comment` VALUES (23, NULL, NULL, '312232131', 'FIND', 3, 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', 0, '0', '2022-02-15 14:25:31', NULL);
INSERT INTO `comment` VALUES (24, NULL, NULL, '111', 'CIRCLE', 7, 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', 0, '0', '2022-09-27 21:02:09', NULL);

-- ----------------------------
-- Table structure for complaint
-- ----------------------------
DROP TABLE IF EXISTS `complaint`;
CREATE TABLE `complaint`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '举报内容',
  `wechat` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号/微信号',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '类型（1-领养人、2-送养人）',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '性别（1-男、2-女、3-未知）',
  `city` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所在城市',
  `address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '具体地址',
  `images` json NULL COMMENT '图片',
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '状态（1-审核中、2-已通过、3-未通过）',
  `is_deleted` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '0-正常 1-删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '举报信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of complaint
-- ----------------------------
INSERT INTO `complaint` VALUES (2, '測試1', 'asaddsa', '1', '1', '杭州', 'xx小区', '[\"www.baidu.com\", \"www.baidu2.com\"]', 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '1', '0', '2023-05-21 20:05:39', '2023-05-22 21:58:59');
INSERT INTO `complaint` VALUES (3, '测试2', 'wqwe', '2', '2', '武汉', 'xx小区', '[\"https://pet-life.oss-cn-beijing.aliyuncs.com/images/2023/05/21/aFuS7nfPE6xn142a346cf36b7adee35f89674f6ef0a8.jpg\", \"https://pet-life.oss-cn-beijing.aliyuncs.com/images/2023/05/21/5hS8XSTcaWiva335b2b66c2e289ae4c0bfb5d3208304.jpg\"]', 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '2', '0', '2023-05-21 20:23:11', '2023-05-22 21:59:13');
INSERT INTO `complaint` VALUES (4, '测试4', 'wqwe', '2', '1', '武汉', 'xx小区', '[\"https://pet-life.oss-cn-beijing.aliyuncs.com/images/2023/05/21/aFuS7nfPE6xn142a346cf36b7adee35f89674f6ef0a8.jpg\", \"https://pet-life.oss-cn-beijing.aliyuncs.com/images/2023/05/21/5hS8XSTcaWiva335b2b66c2e289ae4c0bfb5d3208304.jpg\"]', 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '1', '0', '2023-05-21 20:23:11', '2023-05-22 21:58:59');
INSERT INTO `complaint` VALUES (5, '测试5', 'wqwe', '2', '1', '武汉', 'xx小区', '[\"https://pet-life.oss-cn-beijing.aliyuncs.com/images/2023/05/21/aFuS7nfPE6xn142a346cf36b7adee35f89674f6ef0a8.jpg\", \"https://pet-life.oss-cn-beijing.aliyuncs.com/images/2023/05/21/5hS8XSTcaWiva335b2b66c2e289ae4c0bfb5d3208304.jpg\"]', 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '1', '0', '2023-05-21 20:23:11', '2023-05-22 21:59:23');
INSERT INTO `complaint` VALUES (6, '测试6', 'wqwe', '2', '1', '武汉', 'xx小区', '[\"https://pet-life.oss-cn-beijing.aliyuncs.com/images/2023/05/21/aFuS7nfPE6xn142a346cf36b7adee35f89674f6ef0a8.jpg\", \"https://pet-life.oss-cn-beijing.aliyuncs.com/images/2023/05/21/5hS8XSTcaWiva335b2b66c2e289ae4c0bfb5d3208304.jpg\"]', 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '1', '0', '2023-05-21 20:23:11', '2023-05-22 21:58:59');
INSERT INTO `complaint` VALUES (7, '测试7', 'wqwe', '2', '1', '武汉', 'xx小区', '[\"https://pet-life.oss-cn-beijing.aliyuncs.com/images/2023/05/21/aFuS7nfPE6xn142a346cf36b7adee35f89674f6ef0a8.jpg\", \"https://pet-life.oss-cn-beijing.aliyuncs.com/images/2023/05/21/5hS8XSTcaWiva335b2b66c2e289ae4c0bfb5d3208304.jpg\"]', 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '3', '0', '2023-05-21 20:23:11', '2023-05-22 21:59:59');
INSERT INTO `complaint` VALUES (8, '测试8', 'wqwe', '2', '1', '武汉', 'xx小区', '[\"https://pet-life.oss-cn-beijing.aliyuncs.com/images/2023/05/21/aFuS7nfPE6xn142a346cf36b7adee35f89674f6ef0a8.jpg\", \"https://pet-life.oss-cn-beijing.aliyuncs.com/images/2023/05/21/5hS8XSTcaWiva335b2b66c2e289ae4c0bfb5d3208304.jpg\"]', 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '1', '0', '2023-05-21 20:23:11', '2023-05-22 21:58:59');
INSERT INTO `complaint` VALUES (9, '测试9', 'wqwe', '2', '1', '武汉', 'xx小区', '[\"https://pet-life.oss-cn-beijing.aliyuncs.com/images/2023/05/21/aFuS7nfPE6xn142a346cf36b7adee35f89674f6ef0a8.jpg\", \"https://pet-life.oss-cn-beijing.aliyuncs.com/images/2023/05/21/5hS8XSTcaWiva335b2b66c2e289ae4c0bfb5d3208304.jpg\"]', 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '1', '0', '2023-05-21 20:23:11', '2023-05-22 21:58:59');
INSERT INTO `complaint` VALUES (10, '测试10', 'wqwe', '2', '1', '武汉', 'xx小区', '[\"https://pet-life.oss-cn-beijing.aliyuncs.com/images/2023/05/21/aFuS7nfPE6xn142a346cf36b7adee35f89674f6ef0a8.jpg\", \"https://pet-life.oss-cn-beijing.aliyuncs.com/images/2023/05/21/5hS8XSTcaWiva335b2b66c2e289ae4c0bfb5d3208304.jpg\"]', 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '1', '0', '2023-05-21 20:23:11', '2023-05-22 21:58:59');
INSERT INTO `complaint` VALUES (11, '测试11', 'wqwe', '2', '1', '武汉', 'xx小区', '[\"https://pet-life.oss-cn-beijing.aliyuncs.com/images/2023/05/21/aFuS7nfPE6xn142a346cf36b7adee35f89674f6ef0a8.jpg\", \"https://pet-life.oss-cn-beijing.aliyuncs.com/images/2023/05/21/5hS8XSTcaWiva335b2b66c2e289ae4c0bfb5d3208304.jpg\"]', 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '1', '0', '2023-05-21 20:23:11', '2023-05-22 21:58:59');
INSERT INTO `complaint` VALUES (12, '测试12', 'wqwe', '2', '1', '武汉', 'xx小区', '[\"https://pet-life.oss-cn-beijing.aliyuncs.com/images/2023/05/21/aFuS7nfPE6xn142a346cf36b7adee35f89674f6ef0a8.jpg\", \"https://pet-life.oss-cn-beijing.aliyuncs.com/images/2023/05/21/5hS8XSTcaWiva335b2b66c2e289ae4c0bfb5d3208304.jpg\"]', 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '1', '0', '2023-05-21 20:23:11', '2023-05-22 21:58:59');
INSERT INTO `complaint` VALUES (13, '举报', 'aaa', '1', '1', '武汉', 'xx 小区', '[\"https://pet-life.oss-cn-beijing.aliyuncs.com/images/2023/05/24/tmp_9ade0a302463eafec4ec9b4da61a586cb1353041f72488fa.jpg\"]', 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '2', '0', '2023-05-24 22:43:56', '2023-05-24 22:47:03');

-- ----------------------------
-- Table structure for credits
-- ----------------------------
DROP TABLE IF EXISTS `credits`;
CREATE TABLE `credits`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `credits_num` int(11) NOT NULL DEFAULT 0 COMMENT '积分',
  `day_num` int(11) NOT NULL DEFAULT 0 COMMENT '签到天数',
  `is_deleted` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '0-正常 1-删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '积分表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of credits
-- ----------------------------
INSERT INTO `credits` VALUES (1, 'oEgDe5clk5QtAgQSrDhq7ajKiVzE', 0, 8, '0', '2022-01-30 17:36:41', '2022-01-31 17:58:43');

-- ----------------------------
-- Table structure for pet_adopt
-- ----------------------------
DROP TABLE IF EXISTS `pet_adopt`;
CREATE TABLE `pet_adopt`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '宠物类型（1 猫咪 2 狗狗）',
  `pic_prefix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片前缀',
  `pictures` varchar(1275) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别（1 男, 2 女）',
  `physical_condition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '健康状况（数组）',
  `age` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '年龄',
  `conditions` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '领养条件（数组）',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '城市',
  `content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `open_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_finish` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '是否送养成功（0-已送养 1-正在送养）',
  `view` int(11) NULL DEFAULT 0 COMMENT '浏览量',
  `is_deleted` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '0-正常 1-删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '宠物领养' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pet_adopt
-- ----------------------------
INSERT INTO `pet_adopt` VALUES (1, '我的狗狗', '小黄', '13100000000', '2', 'https://pet-life.oss-cn-beijing.aliyuncs.com/', '\"images/2023/05/18/PdKQbPPdiXT3142a346cf36b7adee35f89674f6ef0a8.jpg\"', '1', '已免疫, 已驱虫, 已绝育', '4-6月', '仅限同城, 工作稳定, 接收回访, 签订领养协议, 不得遗弃、转让、贩卖、虐待', '武汉', '测试', 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '0', 81, '0', '2023-05-18 21:34:53', '2023-05-18 23:20:52');
INSERT INTO `pet_adopt` VALUES (6, '测试2', '1', '1', '1', 'https://pet-life.oss-cn-beijing.aliyuncs.com/', '\"images/2023/05/21/dSvBKj3bEsZAa335b2b66c2e289ae4c0bfb5d3208304.jpg\"', '1', '已免疫', '0-3月', '仅限同城', '武汉', '1', 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '1', 0, '0', '2023-05-16 19:32:30', '2023-05-24 16:47:21');

-- ----------------------------
-- Table structure for pet_circle
-- ----------------------------
DROP TABLE IF EXISTS `pet_circle`;
CREATE TABLE `pet_circle`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户openId',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `pictures` varchar(1275) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片',
  `is_deleted` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '0-正常 1-删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '动态' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pet_circle
-- ----------------------------
INSERT INTO `pet_circle` VALUES (7, 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '哈哈哈哈哈', '\"images/2022/09/27/M0tNZRtDBQyic40895490ce7d72b8e9995b8d11638e3.png\"', '0', '2022-09-27 20:58:28', '2022-09-27 20:58:28');

-- ----------------------------
-- Table structure for pet_cyclopedia
-- ----------------------------
DROP TABLE IF EXISTS `pet_cyclopedia`;
CREATE TABLE `pet_cyclopedia`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `content` varchar(1275) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容(富文本)',
  `view` int(11) NOT NULL DEFAULT 0 COMMENT '浏览量',
  `type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型(1-标签 2-列表)',
  `background_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签背景颜色',
  `is_deleted` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '0-正常 1-删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '宠物科普' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pet_cyclopedia
-- ----------------------------
INSERT INTO `pet_cyclopedia` VALUES (1, '新人养宠', NULL, '这是新人养宠', 1, '1', '#FCF5C9', '0', '2022-01-30 13:59:08', '2023-03-02 21:18:59');
INSERT INTO `pet_cyclopedia` VALUES (2, '抚养护理', NULL, '这是抚养护理', 4, '1', '#EEF9FF', '0', '2022-01-30 13:59:38', '2023-03-02 21:18:53');
INSERT INTO `pet_cyclopedia` VALUES (3, '基础病例', NULL, '这是基础病例', 3, '1', '#FFEEEF', '0', '2022-01-30 14:00:01', '2022-02-12 18:37:23');
INSERT INTO `pet_cyclopedia` VALUES (4, '猫咪呕吐没食欲', 'https://img1.baidu.com/it/u=931545919,4030748642&fm=253&fmt=auto&app=138&f=JPEG?w=306&h=459', '猫咪呕吐没食欲猫咪呕吐没食欲猫咪呕吐没食欲猫咪呕吐没食欲', 3, '2', NULL, '0', '2022-01-30 14:03:58', '2022-01-30 21:16:09');
INSERT INTO `pet_cyclopedia` VALUES (5, '猫咪一直舔毛的原因', 'https://img0.baidu.com/it/u=358489060,1008822084&fm=253&fmt=auto&app=138&f=JPEG?w=654&h=500', '<div style=\"color: red;\">猫咪一直舔毛的原因</div>猫咪一直舔毛的原因猫咪一直舔毛的原因猫咪一直舔毛的原因', 211, '2', NULL, '0', '2022-01-30 14:04:36', '2023-03-02 21:18:56');

-- ----------------------------
-- Table structure for pet_find
-- ----------------------------
DROP TABLE IF EXISTS `pet_find`;
CREATE TABLE `pet_find`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `bread` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品种',
  `type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '宠物类型（1-猫咪 2-狗狗）',
  `pic_prefix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片前缀',
  `pictures` varchar(1275) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '性别（0-未知 1-男 2-女）',
  `age` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年龄',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '城市',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '具体位置',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  `open_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_finish` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '是否完成（0-已完成 1-进行中）',
  `view` int(11) NOT NULL DEFAULT 0 COMMENT '浏览量',
  `is_deleted` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '0-正常 1-删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '寻宠' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pet_find
-- ----------------------------
INSERT INTO `pet_find` VALUES (1, '测试', '小明', '13100000001', '小白', '2', 'https://pet-life.oss-cn-beijing.aliyuncs.com/', '\"images/2022/09/27/wVNwa0hqZXF4c40895490ce7d72b8e9995b8d11638e3.png\"', '0', '7-12月', '杭州', '12121', '测试测试测试', 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '1', 0, '0', '2023-05-24 16:24:11', '2023-05-24 16:24:15');
INSERT INTO `pet_find` VALUES (2, '测试1', '小明', '13100000001', '小白', '2', 'https://pet-life.oss-cn-beijing.aliyuncs.com/', '\"images/2022/09/27/wVNwa0hqZXF4c40895490ce7d72b8e9995b8d11638e3.png\"', '0', '7-12月', '杭州', '12121', '测试测试测试', 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '1', 0, '0', '2023-05-24 16:24:11', '2023-05-24 16:24:15');
INSERT INTO `pet_find` VALUES (5, '狗狗丢失', '小王', '13222222222', '金毛', '2', 'https://pet-life.oss-cn-beijing.aliyuncs.com/', '\"images/2022/09/27/wVNwa0hqZXF4c40895490ce7d72b8e9995b8d11638e3.png\"', '1', '7-12月', '杭州', '2222', '121222', 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', '0', 5, '0', '2022-09-27 21:03:15', '2023-05-24 22:34:14');

-- ----------------------------
-- Table structure for user_follow
-- ----------------------------
DROP TABLE IF EXISTS `user_follow`;
CREATE TABLE `user_follow`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `follow_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '关注的用户ID',
  `is_deleted` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '0-正常 1-删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户关注表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_follow
-- ----------------------------

-- ----------------------------
-- Table structure for wx_user
-- ----------------------------
DROP TABLE IF EXISTS `wx_user`;
CREATE TABLE `wx_user`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `session_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `union_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `country` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国家',
  `province` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `gender` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `user_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户类型 (wx、qq、app)',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态 (0-正常 1-禁用 2-黑名单)',
  `is_deleted` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '0-正常 1-删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_user
-- ----------------------------
INSERT INTO `wx_user` VALUES (3, 'oh2iA5D5r4ICUzQOwuxz2Kqvba3Y', 'FrZi6+j0hq2AC+YBCWtKcg==', 'null', '时来运转 11', 'https://pet-life.oss-cn-beijing.aliyuncs.com/images/2023/05/24/tmp_e8fe234890d28966ff47b5ab3d1775b5de2b113acb16f137.jpg', '', '', '', '1', '1310000002', NULL, 'WECHAT_MP', '0', '0', '2022-01-31 21:23:23', '2023-05-24 22:43:18');
INSERT INTO `wx_user` VALUES (4, 'oh2iA5AVCtjvCfxA9XKPDB0yqhp0', 'jgynVPkm8Gfp0LyJI7jxtA==', 'null', '微信用户', 'https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132', '', '', '', '0', NULL, NULL, 'WECHAT_MP', '0', '0', '2023-05-18 21:39:43', '2023-05-18 23:17:59');

SET FOREIGN_KEY_CHECKS = 1;
