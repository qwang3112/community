/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 8.0.16 : Database - blog
*********************************************************************
*/

USE `myblog`;

/*Table structure for table `t_blog` */

DROP TABLE IF EXISTS `t_blog`;

CREATE TABLE `t_blog` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `appreciation` bit(1) NOT NULL,
                          `commentable` bit(1) NOT NULL,
                          `content` longtext CHARACTER SET utf8 COLLATE utf8_bin,
                          `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                          `created_time` datetime(6) DEFAULT NULL,
                          `flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                          `published` bit(1) NOT NULL,
                          `recommend` bit(1) NOT NULL,
                          `share_statement` bit(1) NOT NULL,
                          `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                          `updated_time` datetime(6) DEFAULT NULL,
                          `views` int(11) DEFAULT NULL,
                          `type_id` bigint(20) DEFAULT NULL,
                          `user_id` bigint(20) DEFAULT NULL,
                          `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

/*Data for the table `t_blog` */

insert  into `t_blog`(`id`,`appreciation`,`commentable`,`content`,`cover`,`created_time`,`flag`,`published`,`recommend`,`share_statement`,`title`,`updated_time`,`views`,`type_id`,`user_id`,`description`) values (1,'','','# Hello world','https://picsum.photos/id/1047/800/450','2020-07-13 13:36:00.174000','原创','','','','hello world','2020-07-13 13:36:00.174000',0,1,1,'hello....');

/*Table structure for table `t_blog_tags` */

DROP TABLE IF EXISTS `t_blog_tags`;

CREATE TABLE `t_blog_tags` (
                               `blogs_id` bigint(20) NOT NULL,
                               `tags_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

/*Data for the table `t_blog_tags` */

insert  into `t_blog_tags`(`blogs_id`,`tags_id`) values (1,1);

/*Table structure for table `t_comment` */

DROP TABLE IF EXISTS `t_comment`;

CREATE TABLE `t_comment` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                             `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                             `created_time` datetime(6) DEFAULT NULL,
                             `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                             `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                             `blog_id` bigint(20) DEFAULT NULL,
                             `parent_comment_id` bigint(20) DEFAULT NULL,
                             `admin_comment` bit(1) NOT NULL,
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

/*Data for the table `t_comment` */

/*Table structure for table `t_tag` */

DROP TABLE IF EXISTS `t_tag`;

CREATE TABLE `t_tag` (
                         `id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

/*Data for the table `t_tag` */

insert  into `t_tag`(`id`,`name`) values (1,'hello');

/*Table structure for table `t_type` */

DROP TABLE IF EXISTS `t_type`;

CREATE TABLE `t_type` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

/*Data for the table `t_type` */

insert  into `t_type`(`id`,`name`) values (1,'文章');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                          `created_time` datetime(6) DEFAULT NULL,
                          `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                          `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                          `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                          `type` int(11) DEFAULT NULL,
                          `updated_time` datetime(6) DEFAULT NULL,
                          `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`avatar`,`created_time`,`email`,`nick_name`,`password`,`type`,`updated_time`,`username`) values (1,'/images/avatar.jpg','2020-06-30 18:34:26.000000','example@hotmail.com','example','4a7d1ed414474e4033ac29ccb8653d9b',1,'2020-06-30 18:35:06.000000','example');