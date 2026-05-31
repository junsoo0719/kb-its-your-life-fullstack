-- 황지원네
update `ticket`
set stock = stock + 1
where id = 1;

-- LOCK 을 적용한 트랜잭션
start transaction;

select * from `ticket`
where id = 1
for update;

commit;