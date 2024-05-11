/*
 Navicat Premium Data Transfer

 Source Server         : mysql8
 Source Server Type    : MySQL
 Source Server Version : 80026 (8.0.26)
 Source Host           : localhost:3306
 Source Schema         : bs

 Target Server Type    : MySQL
 Target Server Version : 80026 (8.0.26)
 File Encoding         : 65001

 Date: 11/05/2024 23:40:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for shops
-- ----------------------------
DROP TABLE IF EXISTS `shops`;
CREATE TABLE `shops`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '店铺名',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地址',
  `off_day` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '休息日',
  `phone_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话号码',
  `logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'Logo地址',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '封面地址',
  `owner_id` bigint NOT NULL COMMENT '拥有人ID',
  `status` int NOT NULL COMMENT '1为在运行，0为申请中，2为已下架',
  `fixed_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name_address_idx`(`name` ASC, `address` ASC) USING BTREE,
  INDEX `owner_idx`(`owner_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27126761863169 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商店表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shops
-- ----------------------------
INSERT INTO `shops` VALUES (25662670247936, '啦啦宝都上海金桥店', '上海市浦东新区新金桥路738号 125号店', '全年无休', '021-5820-02030', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/b34ecab8-bb54-4938-9890-5db78313cf53.png', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/b6862ccb-40b1-42f7-ab35-a39f2827d095.jpg', 25662670247936, 1, 20.00);
INSERT INTO `shops` VALUES (25662670247937, '啦啦宝都上海金桥店', '上海市浦东新区新金桥路738号 125号店', '全年无休', '021-5820-02030', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/39c280e9-0d82-4133-9574-bcb59474a8ed.jpeg', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/39c280e9-0d82-4133-9574-bcb59474a8ed.jpeg', 25662670247937, 1, 20.00);
INSERT INTO `shops` VALUES (25662670247938, '啦啦宝都上海金桥店', '上海市浦东新区新金桥路738号 125号店', '全年无休', '021-5820-02030', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/28f85edf-db09-4d5a-8b50-ae4b33b9e7ec.jpeg', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/28f85edf-db09-4d5a-8b50-ae4b33b9e7ec.jpeg', 25662670247938, 1, 20.00);
INSERT INTO `shops` VALUES (25662670247957, '啦啦宝都上海金桥店', '上海市浦东新区新金桥路738号 125号店', '全年无休', '021-5820-02030', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/cb62f8f8-ac7e-4b1c-8cb6-025b2f3a4d8d.jpeg', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/cb62f8f8-ac7e-4b1c-8cb6-025b2f3a4d8d.jpeg', 25662670247957, 1, 20.00);
INSERT INTO `shops` VALUES (25662670247958, '啦啦宝都上海金桥店', '上海市浦东新区新金桥路738号 125号店', '全年无休', '021-5820-02030', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/acd0a34c-8595-4376-a733-8718bddeaa8f.png', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/acd0a34c-8595-4376-a733-8718bddeaa8f.png', 25662670247958, 1, 20.00);
INSERT INTO `shops` VALUES (25662670247959, '啦啦宝都上海金桥店', '上海市浦东新区新金桥路738号 125号店', '全年无休', '021-5820-02030', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/d9f3b4e4-6425-476f-9314-8d4ce24858c1.png', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/d9f3b4e4-6425-476f-9314-8d4ce24858c1.png', 25662670247959, 1, 20.00);
INSERT INTO `shops` VALUES (25662670247960, '啦啦宝都上海金桥店', '上海市浦东新区新金桥路738号 125号店', '全年无休', '021-5820-02030', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/8ac56375-fc5f-469f-992f-0af4f83780da.png', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/8ac56375-fc5f-469f-992f-0af4f83780da.png', 25662670247960, 1, 20.00);
INSERT INTO `shops` VALUES (25662670247961, '啦啦宝都上海金桥店', '上海市浦东新区新金桥路738号 125号店', '全年无休', '021-5820-02030', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/605a64e6-b21c-48d8-878b-59b93d2c93b1.jpg', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/605a64e6-b21c-48d8-878b-59b93d2c93b1.jpg', 25662670247961, 1, 20.00);
INSERT INTO `shops` VALUES (25662670247962, '啦啦宝都上海金桥店', '上海市浦东新区新金桥路738号 125号店', '全年无休', '021-5820-02030', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/d798155c-2710-4b2b-95ee-2abad1c9f9ba.jpg', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/d798155c-2710-4b2b-95ee-2abad1c9f9ba.jpg', 25662670247962, 1, 20.00);
INSERT INTO `shops` VALUES (25662670247965, '啦啦宝都上海金桥店', '上海市浦东新区新金桥路738号 125号店', '全年无休', '021-5820-02030', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/8f2051b0-4e47-4fcd-ac74-2765782ea98d.jpg', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/8f2051b0-4e47-4fcd-ac74-2765782ea98d.jpg', 25662670247965, 1, 20.00);
INSERT INTO `shops` VALUES (25662670247966, '啦啦宝都上海金桥店', '上海市浦东新区新金桥路738号 125号店', '全年无休', '021-5820-02030', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/b6862ccb-40b1-42f7-ab35-a39f2827d095.jpg', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/b6862ccb-40b1-42f7-ab35-a39f2827d095.jpg', 25662670247966, 1, 20.00);
INSERT INTO `shops` VALUES (25662670247967, '啦啦宝都上海金桥店', '上海市浦东新区新金桥路738号 125号店', '全年无休', '021-5820-02030', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/b6862ccb-40b1-42f7-ab35-a39f2827d095.jpg', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/b6862ccb-40b1-42f7-ab35-a39f2827d095.jpg', 25662670247967, 1, 20.00);
INSERT INTO `shops` VALUES (25662670247968, '啦啦宝都上海金桥店', '上海市浦东新区新金桥路738号 125号店', '全年无休', '021-5820-02030', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/b6862ccb-40b1-42f7-ab35-a39f2827d095.jpg', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/b6862ccb-40b1-42f7-ab35-a39f2827d095.jpg', 25662670247968, 1, 20.00);
INSERT INTO `shops` VALUES (25662670247969, '啦啦宝都上海金桥店', '上海市浦东新区新金桥路738号 125号店', '全年无休', '021-5820-02030', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/b34ecab8-bb54-4938-9890-5db78313cf53.png', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/b6862ccb-40b1-42f7-ab35-a39f2827d095.jpg', 25662670247969, 1, 20.00);
INSERT INTO `shops` VALUES (25662670247970, '啦啦宝都上海金桥店', '上海市浦东新区新金桥路738号 125号店', '全年无休', '021-5820-02030', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/b34ecab8-bb54-4938-9890-5db78313cf53.png', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/b6862ccb-40b1-42f7-ab35-a39f2827d095.jpg', 25662670247970, 1, 20.00);
INSERT INTO `shops` VALUES (25662670247971, '啦啦宝都上海金桥店', '上海市浦东新区新金桥路738号 125号店', '全年无休', '021-5820-02030', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/b34ecab8-bb54-4938-9890-5db78313cf53.png', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/b6862ccb-40b1-42f7-ab35-a39f2827d095.jpg', 25662670247971, 1, 20.00);
INSERT INTO `shops` VALUES (25662670247972, '啦啦宝都上海金桥店', '上海市浦东新区新金桥路738号 125号店', '全年无休', '021-5820-02030', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/b34ecab8-bb54-4938-9890-5db78313cf53.png', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/b6862ccb-40b1-42f7-ab35-a39f2827d095.jpg', 25662670247972, 1, 20.00);
INSERT INTO `shops` VALUES (25662670247973, '啦啦宝都上海金桥店', '上海市浦东新区新金桥路738号 125号店', '全年无休', '021-5820-02030', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/b34ecab8-bb54-4938-9890-5db78313cf53.png', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/b6862ccb-40b1-42f7-ab35-a39f2827d095.jpg', 25662670247973, 1, 20.00);
INSERT INTO `shops` VALUES (25662670247974, '啦啦宝都上海金桥店', '上海市浦东新区新金桥路738号 125号店', '全年无休', '021-5820-02030', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/b34ecab8-bb54-4938-9890-5db78313cf53.png', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/b6862ccb-40b1-42f7-ab35-a39f2827d095.jpg', 25662670247974, 1, 20.00);
INSERT INTO `shops` VALUES (25662670247975, '啦啦宝都上海金桥店', '上海市浦东新区新金桥路738号 125号店', '全年无休', '021-5820-02030', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/b34ecab8-bb54-4938-9890-5db78313cf53.png', 'https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/b6862ccb-40b1-42f7-ab35-a39f2827d095.jpg', 25662670247975, 1, 20.00);

SET FOREIGN_KEY_CHECKS = 1;
