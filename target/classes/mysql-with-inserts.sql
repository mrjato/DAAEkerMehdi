CREATE DATABASE `share`;

CREATE TABLE `share`.`people` (
	`id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(50) DEFAULT NULL,
	`surname` varchar(100) DEFAULT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `share`.`users` (
	`login` varchar(100) NOT NULL,
	`password` varbinary(64) DEFAULT NULL,
	PRIMARY KEY (`login`)
);

GRANT ALL ON `share`.* TO 'daa'@'localhost' IDENTIFIED BY 'daa';

INSERT INTO `share`.`people` (`id`,`name`,`surname`) VALUES (0,'Antón','Pérez');
INSERT INTO `share`.`people` (`id`,`name`,`surname`) VALUES (0,'Manuel','Martínez');
INSERT INTO `share`.`people` (`id`,`name`,`surname`) VALUES (0,'Laura','Reboredo');
INSERT INTO `share`.`people` (`id`,`name`,`surname`) VALUES (0,'Perico','Palotes');
INSERT INTO `share`.`people` (`id`,`name`,`surname`) VALUES (0,'Ana','María');
INSERT INTO `share`.`people` (`id`,`name`,`surname`) VALUES (0,'María','Nuevo');
INSERT INTO `share`.`people` (`id`,`name`,`surname`) VALUES (0,'Alba','Fernández');
INSERT INTO `share`.`people` (`id`,`name`,`surname`) VALUES (0,'Asunción','Jiménez');

INSERT INTO `share`.`users` (`login`,`password`) VALUES ('ekerakin', '4141195');
