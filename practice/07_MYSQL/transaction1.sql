create database `transaction`;
use `transaction`;

create table `account` (
	user_id varchar(10) primary key,
    name 	varchar(10),
    balance int
);

insert into `account` values ('lhs', '이효석', 500000);
insert into `account` values ('sjs', '송준수', 1000000);

select * from `account`;

-- 송금 시작
-- 이효석 계좌에서 10만원 빼기
update `account` set balance = balance - 100000
where user_id = 'lhs';

-- 송준수 계좌로 10만원 넣기
update `account` set balance = balance + 100000
where user_id = 'sjs';

select * from `account`;

-- 송준수 계좌에서 10만원 빼기
update `account` set balance = balance - 100000
where user_id = 'sjs';

select * from `account`;

-- 송준수 계좌에 10만원 넣기
update `account` set balance = balance + 100000
where user_id = 'sjs';

select * from `account`;