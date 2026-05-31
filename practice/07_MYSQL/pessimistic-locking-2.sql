-- 송준수네

-- LOCK 을 적용한 트랜잭션
start transaction;

select * from `ticket`
where id = 1
for update;