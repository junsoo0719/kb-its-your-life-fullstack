-- 트랜잭션 시작
start transaction;

-- 송준수 계좌에서 10만원 빼기
update `account` set balance = balance - 100000
where user_id = 'sjs';

-- 문제가 생긴 경우
rollback;

-- 이효석 계좌에 10만원 넣기
update `account` set balance = balance + 100000
where user_id = 'lhs';

commit;

select * from `account`;