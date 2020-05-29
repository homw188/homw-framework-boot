CREATE DATABASE /*!32312 IF NOT EXISTS*/ `seata_order` CHARACTER SET utf8 COLLATE utf8_general_ci;

USE `seata_order`;

/*Table structure for table `t_order` */
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`(  
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` BIGINT(20) COMMENT '用户id',
  `product_id` BIGINT(20) COMMENT '商品id',
  `count` INT(11) COMMENT '数量',
  `money` DECIMAL(19,4) COMMENT '金额',
  `status` INT(11) DEFAULT 0 COMMENT '状态(0:新建 1:完成)',
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `seata_storage`CHARACTER SET utf8 COLLATE utf8_general_ci; 

USE `seata_storage`; 

/*Table structure for table `t_storage` */
DROP TABLE IF EXISTS `t_storage`;
CREATE TABLE `t_storage`(  
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `product_id` BIGINT(20) COMMENT '产品id',
  `total` INT(11) COMMENT '总数',
  `used` INT(11) COMMENT '已使用',
  `residue` INT(11) COMMENT '余数',
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `t_storage` */
INSERT INTO `t_storage` (`product_id`, `total`, `used`, `residue`) VALUES ('1', '100', '0', '100');


CREATE DATABASE /*!32312 IF NOT EXISTS*/ `seata_account`CHARACTER SET utf8 COLLATE utf8_general_ci; 

USE `seata_account`; 

/*Table structure for table `t_account` */
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account`(  
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` BIGINT(20) COMMENT '用户id',
  `total` DECIMAL(19,4) COMMENT '总额',
  `used` DECIMAL(19,4) COMMENT '已使用',
  `residue` DECIMAL(19,4) COMMENT '余额',
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `t_account` */
INSERT INTO `t_account` (`user_id`, `total`, `used`, `residue`) VALUES ('1', '1000', '0', '1000'); 