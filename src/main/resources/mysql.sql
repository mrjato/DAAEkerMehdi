CREATE DATABASE `share`;

/*CREATE BOOKS TABLE*/
CREATE TABLE `share`.`books` (
	`id` int NOT NULL AUTO_INCREMENT,
	`Title` varchar(50) DEFAULT NULL,
	`Author` varchar(50) DEFAULT NULL,
	`ISBN` varchar(50) DEFAULT NULL,
	`Kind` varchar(100) DEFAULT NULL,
	`EditionLanguage` varchar(50) DEFAULT NULL,
	`ReleaseDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
	`Description` varchar(500) DEFAULT NULL,
	PRIMARY KEY (`id`)
);

/*CREATE MUSICS TABLE*/
CREATE TABLE `share`.`musics` (
	`id` int NOT NULL AUTO_INCREMENT,
	`AlbumName` varchar(50) DEFAULT NULL,
	`Artist` varchar(50) DEFAULT NULL,
	`Songs` varchar(100) DEFAULT NULL,
	`Genres` varchar(50) DEFAULT NULL,
	`ReleaseDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
	`Description` varchar(500) DEFAULT NULL,
	PRIMARY KEY (`id`)
);

/*CREATE MOVIES TABLE*/
CREATE TABLE `share`.`movies` (
	`id` int NOT NULL AUTO_INCREMENT,
	`Title` varchar(50) DEFAULT NULL,
	`Director` varchar(100) DEFAULT NULL,
	`Writer` varchar(100) DEFAULT NULL,
	`Genres` varchar(100) DEFAULT NULL,
	`Country` varchar(50) DEFAULT NULL,
	`Language` varchar(50) DEFAULT NULL,
	`ReleaseDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
	`Cast` varchar(150) DEFAULT NULL,
	`RunTime` varchar(50) DEFAULT NULL,	
	`Description` varchar(500) DEFAULT NULL,
	PRIMARY KEY (`id`)
);

/*CREATE USERS TABLE*/
CREATE TABLE `share`.`users` (
	`login` varchar(100) NOT NULL,
	`password` varbinary(64) DEFAULT NULL,
	PRIMARY KEY (`login`)
);

GRANT ALL ON `share`.* TO 'daa'@'localhost' IDENTIFIED BY 'daa';