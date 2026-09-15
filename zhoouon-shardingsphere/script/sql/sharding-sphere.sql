/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 8002
 Source Host           : localhost:3306
 Source Schema         : tcbiz_ins

 Target Server Type    : MySQL
 Target Server Version : 8002
 File Encoding         : 65001

 Date: 15/05/2024 09:30:20
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for router_config_0
-- ----------------------------
DROP TABLE IF EXISTS `router_config_0`;
CREATE TABLE `router_config_0`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_1
-- ----------------------------
DROP TABLE IF EXISTS `router_config_1`;
CREATE TABLE `router_config_1`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_01
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_2
-- ----------------------------
DROP TABLE IF EXISTS `router_config_2`;
CREATE TABLE `router_config_2`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_02
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_3
-- ----------------------------
DROP TABLE IF EXISTS `router_config_3`;
CREATE TABLE `router_config_3`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_03
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_4
-- ----------------------------
DROP TABLE IF EXISTS `router_config_4`;
CREATE TABLE `router_config_4`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_04
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_5
-- ----------------------------
DROP TABLE IF EXISTS `router_config_5`;
CREATE TABLE `router_config_5`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_05
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_6
-- ----------------------------
DROP TABLE IF EXISTS `router_config_6`;
CREATE TABLE `router_config_6`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_6
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_7
-- ----------------------------
DROP TABLE IF EXISTS `router_config_7`;
CREATE TABLE `router_config_7`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_7
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_8
-- ----------------------------
DROP TABLE IF EXISTS `router_config_8`;
CREATE TABLE `router_config_8`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_8
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_9
-- ----------------------------
DROP TABLE IF EXISTS `router_config_9`;
CREATE TABLE `router_config_9`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_9
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_10
-- ----------------------------
DROP TABLE IF EXISTS `router_config_10`;
CREATE TABLE `router_config_10`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_10
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_11
-- ----------------------------
DROP TABLE IF EXISTS `router_config_11`;
CREATE TABLE `router_config_11`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_11
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_12
-- ----------------------------
DROP TABLE IF EXISTS `router_config_12`;
CREATE TABLE `router_config_12`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_12
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_13
-- ----------------------------
DROP TABLE IF EXISTS `router_config_13`;
CREATE TABLE `router_config_13`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_13
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_14
-- ----------------------------
DROP TABLE IF EXISTS `router_config_14`;
CREATE TABLE `router_config_14`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_14
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_15
-- ----------------------------
DROP TABLE IF EXISTS `router_config_15`;
CREATE TABLE `router_config_15`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_15
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_16
-- ----------------------------
DROP TABLE IF EXISTS `router_config_16`;
CREATE TABLE `router_config_16`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_16
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_17
-- ----------------------------
DROP TABLE IF EXISTS `router_config_17`;
CREATE TABLE `router_config_17`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_17
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_18
-- ----------------------------
DROP TABLE IF EXISTS `router_config_18`;
CREATE TABLE `router_config_18`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_18
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_19
-- ----------------------------
DROP TABLE IF EXISTS `router_config_19`;
CREATE TABLE `router_config_19`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_19
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_20
-- ----------------------------
DROP TABLE IF EXISTS `router_config_20`;
CREATE TABLE `router_config_20`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_20
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_21
-- ----------------------------
DROP TABLE IF EXISTS `router_config_21`;
CREATE TABLE `router_config_21`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_21
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_22
-- ----------------------------
DROP TABLE IF EXISTS `router_config_22`;
CREATE TABLE `router_config_22`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for router_config_23
-- ----------------------------
DROP TABLE IF EXISTS `router_config_23`;
CREATE TABLE `router_config_23`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_23
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_24
-- ----------------------------
DROP TABLE IF EXISTS `router_config_24`;
CREATE TABLE `router_config_24`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_24
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_25
-- ----------------------------
DROP TABLE IF EXISTS `router_config_25`;
CREATE TABLE `router_config_25`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_25
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_26
-- ----------------------------
DROP TABLE IF EXISTS `router_config_26`;
CREATE TABLE `router_config_26`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_26
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_27
-- ----------------------------
DROP TABLE IF EXISTS `router_config_27`;
CREATE TABLE `router_config_27`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_27
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_28
-- ----------------------------
DROP TABLE IF EXISTS `router_config_28`;
CREATE TABLE `router_config_28`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_28
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_29
-- ----------------------------
DROP TABLE IF EXISTS `router_config_29`;
CREATE TABLE `router_config_29`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_29
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_30
-- ----------------------------
DROP TABLE IF EXISTS `router_config_30`;
CREATE TABLE `router_config_30`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_30
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_31
-- ----------------------------
DROP TABLE IF EXISTS `router_config_31`;
CREATE TABLE `router_config_31`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_31
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_32
-- ----------------------------
DROP TABLE IF EXISTS `router_config_32`;
CREATE TABLE `router_config_32`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_32
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_33
-- ----------------------------
DROP TABLE IF EXISTS `router_config_33`;
CREATE TABLE `router_config_33`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_33
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_34
-- ----------------------------
DROP TABLE IF EXISTS `router_config_34`;
CREATE TABLE `router_config_34`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_34
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_35
-- ----------------------------
DROP TABLE IF EXISTS `router_config_35`;
CREATE TABLE `router_config_35`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_35
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_36
-- ----------------------------
DROP TABLE IF EXISTS `router_config_36`;
CREATE TABLE `router_config_36`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_36
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_37
-- ----------------------------
DROP TABLE IF EXISTS `router_config_37`;
CREATE TABLE `router_config_37`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_37
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_38
-- ----------------------------
DROP TABLE IF EXISTS `router_config_38`;
CREATE TABLE `router_config_38`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_38
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_39
-- ----------------------------
DROP TABLE IF EXISTS `router_config_39`;
CREATE TABLE `router_config_39`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_39
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_40
-- ----------------------------
DROP TABLE IF EXISTS `router_config_40`;
CREATE TABLE `router_config_40`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_40
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_41
-- ----------------------------
DROP TABLE IF EXISTS `router_config_41`;
CREATE TABLE `router_config_41`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_41
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_42
-- ----------------------------
DROP TABLE IF EXISTS `router_config_42`;
CREATE TABLE `router_config_42`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_42
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_43
-- ----------------------------
DROP TABLE IF EXISTS `router_config_43`;
CREATE TABLE `router_config_43`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_43
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_44
-- ----------------------------
DROP TABLE IF EXISTS `router_config_44`;
CREATE TABLE `router_config_44`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_44
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_45
-- ----------------------------
DROP TABLE IF EXISTS `router_config_45`;
CREATE TABLE `router_config_45`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_45
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_46
-- ----------------------------
DROP TABLE IF EXISTS `router_config_46`;
CREATE TABLE `router_config_46`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_46
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_47
-- ----------------------------
DROP TABLE IF EXISTS `router_config_47`;
CREATE TABLE `router_config_47`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_47
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_48
-- ----------------------------
DROP TABLE IF EXISTS `router_config_48`;
CREATE TABLE `router_config_48`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_48
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for router_config_49
-- ----------------------------
DROP TABLE IF EXISTS `router_config_49`;
CREATE TABLE `router_config_49`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `repay_no` varchar(50) NOT NULL,
    `wifi_name` varchar(255)  DEFAULT NULL,
    `wifi_password` varchar(255)  DEFAULT NULL,
    `wifi_switch` int(2)  DEFAULT NULL,
    `encrypt_type` int(2)  DEFAULT NULL,
    `admin_password` varchar(255)  DEFAULT NULL,
    `hide_switch` int(2)  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of router_config_49
-- ----------------------------
BEGIN;
COMMIT;

SET
FOREIGN_KEY_CHECKS = 1;
