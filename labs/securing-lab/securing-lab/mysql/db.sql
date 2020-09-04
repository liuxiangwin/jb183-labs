DROP TABLE IF EXISTS `Item`;
CREATE TABLE `Item` (`id` BIGINT not null auto_increment primary key, `description` VARCHAR(100), `done` BIT);
INSERT INTO `Item` (`id`,`description`,`done`) VALUES (1,'Pick up newspaper', 0);
INSERT INTO `Item` (`id`,`description`,`done`) VALUES (2,'Buy groceries', 1);
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (`id` BIGINT not null auto_increment primary key, `username` VARCHAR(100));
INSERT INTO `User` (`id`, `username`) VALUES (1,'Guest');
