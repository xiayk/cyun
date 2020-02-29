
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table base_province
# ------------------------------------------------------------

DROP TABLE IF EXISTS `base_province`;

CREATE TABLE `base_province` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '省份编号',
  `name` varchar(45) DEFAULT NULL COMMENT '省份名称',
  `alias` varchar(45) DEFAULT NULL COMMENT '省份别名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `base_province` WRITE;
/*!40000 ALTER TABLE `base_province` DISABLE KEYS */;

INSERT INTO `base_province` (`id`, `name`, `alias`)
VALUES
	('110000','北京市','京'),
	('120000','天津市','津'),
	('130000','河北省','冀'),
	('140000','山西省','晋'),
	('150000','内蒙古自治区','蒙'),
	('210000','辽宁省','辽'),
	('220000','吉林省','吉'),
	('230000','黑龙江省','黑'),
	('310000','上海市','沪'),
	('320000','江苏省','苏'),
	('330000','浙江省','浙'),
	('340000','安徽省','皖'),
	('350000','福建省','闽'),
	('360000','江西省','赣'),
	('370000','山东省','鲁'),
	('410000','河南省','豫'),
	('420000','湖北省','鄂'),
	('430000','湖南省','湘'),
	('440000','广东省','粤'),
	('450000','广西壮族自治区','桂'),
	('460000','海南省','琼'),
	('500000','重庆市','渝'),
	('510000','四川省','川'),
	('520000','贵州省','贵'),
	('530000','云南省','云'),
	('540000','西藏自治区','藏'),
	('610000','陕西省','陕'),
	('620000','甘肃省','甘'),
	('630000','青海省','青'),
	('640000','宁夏回族自治区','宁'),
	('650000','新疆维吾尔自治区','新'),
	('710000','台湾省','台'),
	('810000','香港特别行政区','港'),
	('820000','澳门特别行政区','澳');

/*!40000 ALTER TABLE `base_province` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
