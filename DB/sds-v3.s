/*
SQLyog - Free MySQL GUI v5.19
Host - 5.0.15-nt : Database - sds-v3
*********************************************************************
Server version : 5.0.15-nt
*/


SET NAMES utf8;

SET SQL_MODE='';

create database if not exists `sds-v3`;

USE `sds-v3`;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

/*Table structure for table `file` */

DROP TABLE IF EXISTS `file`;

CREATE TABLE `file` (
  `fileid` int(11) NOT NULL auto_increment,
  `uploadedby` int(11) NOT NULL,
  `filename` varchar(200) NOT NULL,
  `filecontent` mediumblob,
  `uploadeddate` varchar(50) NOT NULL,
  `encryptedformate` varchar(100) NOT NULL,
  `typeofuserid` int(11) NOT NULL,
  `keywords` varchar(200) NOT NULL,
  `rank` int(11) NOT NULL DEFAULT 0,
  `dateInMillis` BIGINT(20)  NOT NULL,
  `cluster` varchar(10) NOT NULL,
  PRIMARY KEY  (`fileid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `file` */


/*Table structure for table `filerequest` */

DROP TABLE IF EXISTS `filerequest`;

CREATE TABLE `filerequest` (
  `filerequestid` int(11) NOT NULL auto_increment,
  `filename` varchar(200) NOT NULL,
  `secretekey` varchar(200) NOT NULL,
  PRIMARY KEY  (`filerequestid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `filerequest` */


/*Table structure for table `typeofuser` */

DROP TABLE IF EXISTS `typeofuser`;

CREATE TABLE `typeofuser` (
  `typeofuserid` int(11) NOT NULL auto_increment,
  `typeofuser` varchar(200) NOT NULL,
  PRIMARY KEY  (`typeofuserid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `typeofuser` */

insert into `typeofuser` (`typeofuserid`,`typeofuser`) values (1,'Data Owner');
insert into `typeofuser` (`typeofuserid`,`typeofuser`) values (2,'Data Consumer');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (  
 `userid` int(11) NOT NULL auto_increment,   
 `username` varchar(100) NOT NULL, 
 `password` varchar(100) NOT NULL,
 `phone` varchar(100) NOT NULL,
 `email` varchar(100) NOT NULL,
 `secretkey` varchar(200) NOT NULL,   
 PRIMARY KEY  (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user` */


/*Table structure for table `userrolemapping` */

DROP TABLE IF EXISTS `userrolemapping`;

CREATE TABLE `userrolemapping` (
  `userrolemappingid` int(11) NOT NULL auto_increment,
  `userid` int(11) NOT NULL,
  `typeofuserid` int(11) NOT NULL,
  PRIMARY KEY  (`userrolemappingid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `userrolemapping` */


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
