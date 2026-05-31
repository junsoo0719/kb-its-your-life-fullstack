-- 송준수네
start transaction;

select * from `ticket`;

update `ticket`
set stock = stock - 1,
	version = version + 1
where id = 1 and version = 0;

commit;

select * from `ticket`;