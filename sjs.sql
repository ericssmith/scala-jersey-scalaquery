

DROP TABLE IF EXISTS `todo`;
CREATE TABLE `todo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `description` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


INSERT INTO `todo` (title,description) VALUES ('First Todo', 'Do the first thing');
INSERT INTO `todo` (title,description) VALUES ('Second Todo', 'Do the second thing');

