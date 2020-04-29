### 用户信息的增删改查
1. 应用原生servlet、filter、jsp（el，jstl）,数据库mysql，连接池druid，Spring的Jdbctemplate数据增删改查，登录界面用到验证码工具类
2. 数据库创建表
	SET NAMES utf8mb4;
	SET FOREIGN_KEY_CHECKS = 0;

	-- ----------------------------
	-- Table structure for user
	-- ----------------------------
	DROP TABLE IF EXISTS `user`;
	CREATE TABLE `user`  (
	  `id` int(11) NOT NULL AUTO_INCREMENT,
	  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
	  `gender` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
	  `age` int(11) NULL DEFAULT NULL,
	  `address` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
	  `qq` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
	  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
	  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
	  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
	  PRIMARY KEY USING BTREE (`id`)
	) ENGINE = InnoDB AUTO_INCREMENT = 1003 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

	SET FOREIGN_KEY_CHECKS = 1;
	可以使用存储过程生成数据：
	CREATE DEFINER=`root`@`%` PROCEDURE `insert_data_p`(IN num INT)
	BEGIN
	DECLARE i int DEFAULT 1;
	WHILE i <= num
	DO
	insert into user(name,gender,age,address,qq,email,username,password) values(CONCAT('user',i), if(floor(rand()*2),'男','女'),floor(10+rand()*90), if(floor(rand()*2),if(floor(rand()*2),'北京','上海'),'广州'),  floor(50000+rand()*100050000),CONCAT('user',i, '@126.com'),CONCAT('user',i),'1234') ;
	SET i = i + 1;
	END WHILE;

	END
