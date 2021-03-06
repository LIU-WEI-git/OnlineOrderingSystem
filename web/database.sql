/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 5.6.15 : Database - online_ordering_system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`online_ordering_system` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `online_ordering_system`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `address_id` varchar(20) NOT NULL,
  `customer_account` varchar(20) NOT NULL,
  `contact` varchar(20) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `info` varchar(100) NOT NULL,
  `delete_tag` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`address_id`),
  KEY `FK_own` (`customer_account`),
  CONSTRAINT `FK_own` FOREIGN KEY (`customer_account`) REFERENCES `customer` (`customer_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `address` */

insert  into `address`(`address_id`,`customer_account`,`contact`,`phone`,`info`,`delete_tag`) values 
('252525252525','10086123','吴先生','10086','云A358',0),
('262626262626','10086223','商先生','10000','云B358',0);

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `admin_account` varchar(20) NOT NULL,
  `admin_name` varchar(20) DEFAULT NULL,
  `admin_password` varchar(128) NOT NULL,
  `admin_register_time` datetime NOT NULL,
  `admin_email` varchar(40) DEFAULT NULL,
  `admin_phone` varchar(11) DEFAULT NULL,
  `delete_tag` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`admin_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`admin_account`,`admin_name`,`admin_password`,`admin_register_time`,`admin_email`,`admin_phone`,`delete_tag`) values 
('admin','Administrator','123','2019-10-01 09:03:57','admin@163.com','12345678901',0),
('test','sjy','123','2019-11-18 16:54:11','123@1.com','123',0);

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `category_id` varchar(20) NOT NULL,
  `category_name` varchar(20) NOT NULL,
  `delete_tag` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `category` */

insert  into `category`(`category_id`,`category_name`,`delete_tag`) values 
('201','披萨',0),
('202','汉堡',0),
('203','面食',0),
('204','小吃',0),
('205','饮料',0);

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `customer_account` varchar(20) NOT NULL,
  `customer_name` varchar(20) DEFAULT NULL,
  `customer_password` varchar(128) NOT NULL,
  `customer_register_time` datetime NOT NULL,
  `customer_email` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`customer_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `customer` */

insert  into `customer`(`customer_account`,`customer_name`,`customer_password`,`customer_register_time`,`customer_email`) values 
('10086001','customer','123','2019-09-01 09:02:26','customer@163.com'),
('10086123','吴东杰','4CA5F80F285635167AAA4343DC481E96','2019-11-18 21:10:51','wdj@163.com'),
('10086223','商学伟','sxw123','2019-11-13 21:11:26','sxw@163.com'),
('test','1233','C1523C29FC523CCD695A3C086F447449','2019-11-30 14:23:53','123@11.com');

/*Table structure for table `dish` */

DROP TABLE IF EXISTS `dish`;

CREATE TABLE `dish` (
  `dish_id` varchar(20) NOT NULL,
  `dish_name` varchar(20) NOT NULL,
  `picture_url` varchar(100) DEFAULT NULL,
  `price` float NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `delete_tag` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`dish_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dish` */

insert  into `dish`(`dish_id`,`dish_name`,`picture_url`,`price`,`description`,`delete_tag`) values 
('10001','芝士焗饭','images/p1.jpg',10,'学生最爱',0),
('10002','芝士焗面','images/p2.jpg',12,'上班族最爱',0),
('10003','果汁','images/p3.jpg',15,'好喝',0),
('10004','炸薯条','images/p4.jpg',8,'好吃',0),
('10005','烤肉拌饭','images/p5.jpg',11,'肉多',0),
('10006','炸鸡拌饭','images/p6.jpg',11,'脆爽炸鸡',0),
('10007','孜然炒饭','images/p7.jpg',9,'分量足',0),
('10008','大盘鸡拌面','images/p8.jpg',8,'地道大盘鸡',0),
('10009','一盒好饭','images/p1.jpg',14,'真的是一盒好饭',0);

/*Table structure for table `dish_category` */

DROP TABLE IF EXISTS `dish_category`;

CREATE TABLE `dish_category` (
  `category_id` varchar(20) NOT NULL,
  `dish_id` varchar(20) NOT NULL,
  PRIMARY KEY (`category_id`,`dish_id`),
  KEY `FK_belong2` (`dish_id`),
  CONSTRAINT `FK_belong2` FOREIGN KEY (`dish_id`) REFERENCES `dish` (`dish_id`),
  CONSTRAINT `FK_belong` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dish_category` */

insert  into `dish_category`(`category_id`,`dish_id`) values 
('201','10003'),
('203','10003'),
('204','10004'),
('204','10005'),
('204','10006'),
('204','10007'),
('201','10008');

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `order_id` varchar(20) NOT NULL,
  `customer_account` varchar(20) NOT NULL,
  `address_id` varchar(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `order_state` int(11) NOT NULL,
  `delivery_state` int(11) NOT NULL,
  `discount` float DEFAULT NULL,
  `order_price` float DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FK_has` (`address_id`),
  KEY `FK_place` (`customer_account`),
  CONSTRAINT `FK_has` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`),
  CONSTRAINT `FK_place` FOREIGN KEY (`customer_account`) REFERENCES `customer` (`customer_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `order` */

insert  into `order`(`order_id`,`customer_account`,`address_id`,`create_time`,`remark`,`order_state`,`delivery_state`,`discount`,`order_price`) values 
('121212121212','10086123','252525252525','2019-11-13 21:25:56','麻烦配送快一点',0,0,-10,40),
('121212121213','10086223','262626262626','2019-11-19 21:26:39','',2,2,0,72),
('1f61c4fd61d7d357','10086123','252525252525','2019-11-25 19:11:27','',1,1,10,43),
('63f3e094a826c72f','10086123','252525252525','2019-11-25 19:52:26','',2,2,0,8),
('af0aec2aafa8ec71','10086223','262626262626','2019-11-25 20:14:14','1111',0,0,0,18);

/*Table structure for table `order_item` */

DROP TABLE IF EXISTS `order_item`;

CREATE TABLE `order_item` (
  `order_id` varchar(20) NOT NULL,
  `dish_id` varchar(20) NOT NULL,
  `amount` int(11) NOT NULL,
  PRIMARY KEY (`order_id`,`dish_id`),
  KEY `FK_contain2` (`dish_id`),
  CONSTRAINT `FK_contain` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`),
  CONSTRAINT `FK_contain2` FOREIGN KEY (`dish_id`) REFERENCES `dish` (`dish_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `order_item` */

insert  into `order_item`(`order_id`,`dish_id`,`amount`) values 
('121212121212','10001',5),
('121212121213','10002',6),
('1f61c4fd61d7d357','10001',1),
('1f61c4fd61d7d357','10002',1),
('1f61c4fd61d7d357','10003',1),
('1f61c4fd61d7d357','10004',2),
('63f3e094a826c72f','10004',1),
('af0aec2aafa8ec71','10001',1),
('af0aec2aafa8ec71','10004',1);

/*Table structure for table `order_address_info` */

DROP TABLE IF EXISTS `order_address_info`;

/*!50001 DROP VIEW IF EXISTS `order_address_info` */;
/*!50001 DROP TABLE IF EXISTS `order_address_info` */;

/*!50001 CREATE TABLE  `order_address_info`(
 `order_id` varchar(20) ,
 `customer_account` varchar(20) ,
 `customer_name` varchar(20) ,
 `contact` varchar(20) ,
 `phone` varchar(11) ,
 `info` varchar(100) 
)*/;

/*Table structure for table `order_item_info` */

DROP TABLE IF EXISTS `order_item_info`;

/*!50001 DROP VIEW IF EXISTS `order_item_info` */;
/*!50001 DROP TABLE IF EXISTS `order_item_info` */;

/*!50001 CREATE TABLE  `order_item_info`(
 `order_id` varchar(20) ,
 `dish_name` varchar(20) ,
 `picture_url` varchar(100) ,
 `price` float ,
 `amount` int(11) ,
 `total_price` double 
)*/;

/*View structure for view order_address_info */

/*!50001 DROP TABLE IF EXISTS `order_address_info` */;
/*!50001 DROP VIEW IF EXISTS `order_address_info` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `order_address_info` AS (select `o`.`order_id` AS `order_id`,`c`.`customer_account` AS `customer_account`,`c`.`customer_name` AS `customer_name`,`a`.`contact` AS `contact`,`a`.`phone` AS `phone`,`a`.`info` AS `info` from ((`customer` `c` join `address` `a`) join `order` `o`) where ((`c`.`customer_account` = `o`.`customer_account`) and (`o`.`address_id` = `a`.`address_id`))) */;

/*View structure for view order_item_info */

/*!50001 DROP TABLE IF EXISTS `order_item_info` */;
/*!50001 DROP VIEW IF EXISTS `order_item_info` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `order_item_info` AS (select `o`.`order_id` AS `order_id`,`d`.`dish_name` AS `dish_name`,`d`.`picture_url` AS `picture_url`,`d`.`price` AS `price`,`oi`.`amount` AS `amount`,(`d`.`price` * `oi`.`amount`) AS `total_price` from ((`order` `o` join `dish` `d`) join `order_item` `oi`) where ((`oi`.`order_id` = `o`.`order_id`) and (`oi`.`dish_id` = `d`.`dish_id`))) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
