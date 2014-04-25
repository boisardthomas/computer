create schema if not exists `computer-database-db`;
use `computer-database-db`;

drop table if exists computer;
drop table if exists company;
drop table if exists log;

create table company (
  id                        bigint not null auto_increment,
  name                      varchar(255),
  constraint pk_company primary key (id))
;

create table computer (
  id                        bigint not null auto_increment,
  name                      varchar(255),
  introduced                dateTime,
  discontinued              dateTime,
  company_id                bigint,
  constraint pk_computer primary key (id))
;

CREATE TABLE `log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `operation` varchar(255) DEFAULT NULL,
  `eventDate` datetime DEFAULT NULL,
  `eventType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE  TABLE users (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(45) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username));

CREATE TABLE user_roles (
  user_role_id INT(11) NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL,
  ROLE VARCHAR(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (ROLE,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));
  
alter table computer add constraint fk_computer_company_1 foreign key (company_id) references company (id) on delete restrict on update restrict;
create index ix_computer_company_1 on computer (company_id);


#-----------------------------------
#USER RIGHTS MANAGEMENT
#-----------------------------------
CREATE USER 'jee-cdb'@'localhost' IDENTIFIED BY 'password';

GRANT ALL PRIVILEGES ON `computer-database-db`.* TO 'jee-cdb'@'localhost' WITH GRANT OPTION;

FLUSH PRIVILEGES;
