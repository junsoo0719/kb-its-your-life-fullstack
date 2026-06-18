select * from `buytbl`;
select * from `usertbl`;

select *
from buytbl b
	inner join usertbl u
    on b.userID = u.userID;
    
select *
from buytbl b
	inner join usertbl u
    on b.userID = u.userID
where b.userID = 'JYP';

select
	u.userID,
    u.name,
    b.prodName,
    u.addr,
    concat(u.mobile1, u.mobile2) as 연락처
from usertbl u
	left join buytbl b
	on u.userID = b.userID
order by u.userID;

select *
from usertbl
where mobile1 is not null;

select *
from usertbl
where mobile1 is null;
