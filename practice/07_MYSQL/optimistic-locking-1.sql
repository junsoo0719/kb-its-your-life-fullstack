-- 황지원네
update `ticket`
set stock = 1, version = 0
where id = 1;

start transaction;

select * from `ticket`;

update `ticket`
set stock = stock - 1,
	version = version + 1
where id = 1 and version = 0;

commit;

select * from `ticket`;