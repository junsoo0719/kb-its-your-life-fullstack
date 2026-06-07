drop database tabledb;
create database tabledb;

use tabledb;

drop table if exists usertbl;

CREATE TABLE usertbl(
userID CHAR(8) NOT NULL PRIMARY KEY,
name VARCHAR(10) NOT NULL,
birthYear INT NOT NULL,
addr CHAR(2) NOT NULL,
mobile1 CHAR(3) NULL,
mobile2 CHAR(8) NULL,
height SMALLINT NULL,
mDate DATE NULL
);

drop table if exists buytbl;

create table buytbl(
	num int auto_increment not null primary key,
	userid char(8) not null,
    prodName CHAR(6) NOT NULL,
	groupName CHAR(4) NULL,
	price INT NOT NULL,
	amount SMALLINT NOT NULL,
    foreign key(userid) references usertbl(userid)
);

insert into usertbl values
	('LSG', '이승기', 1987, '서울', '011', '1111111', 182, '2008-8-8'),
    ('KBS', '김범수', 1979, '경남', '011', '2222222', 173, '2012-4-4'),
    ('KKH', '김경호', 1971, '전남', '019', '3333333', 177, '2007-7-7');
    
INSERT INTO buytbl VALUES(NULL, 'KBS', '운동화', NULL, 30, 2);
INSERT INTO buytbl VALUES(NULL, 'KBS', '노트북', '전자', 1000, 1);
INSERT INTO buytbl VALUES(NULL, 'JYP', '모니터', '전자', 200, 1);

DROP TABLE IF EXISTS prodtbl;
CREATE TABLE prodTbl (
prodCode CHAR(3) NOT NULL,
prodID CHAR(4) NOT NULL,
prodDate DATETIME NOT NULL,
prodCur CHAR(10) NULL,
CONSTRAINT PK_prodtbl_proCode_prodID
PRIMARY KEY (prodCode, prodID)
);

SHOW INDEX FROM prodTbl;

