create database if not exists application_service;
use application_service;

drop table if exists applicationWorkFlow;

create table if not exists applicationWorkFlow
(
    applicationWorkFlow_id 				int auto_increment primary key,
    employeeId  	int,
    createDate   	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    lastModificationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `status`		varchar(42),
    `comment`		varchar(255)	
);

truncate table applicationWorkFlow;

insert into applicationWorkFlow (employeeId, `status`, `comment`) values
	(1, "approved", "looks good");
insert into applicationWorkFlow (employeeId, `status`, `comment`) values
    (2, "pending", "");
    
UPDATE applicationWorkFlow
SET `comment` = ""
WHERE applicationWorkFlow_id = 2;











drop table if exists digitalDocument;

create table if not exists digitalDocument
(
    digitalDocument_id 		int auto_increment primary key,
    `type`  	varchar(42),
    isRequired   	varchar(42),
    `path`		varchar(255),
    `description`	varchar(255),
    title       varchar(42)
);

truncate table digitalDocument;

insert into digitalDocument (`type`, isRequired, `path`, `description`, title) values
	("test type", "yes", "test path", "test description", "test title");