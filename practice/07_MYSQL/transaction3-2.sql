start transaction;

-- 송준수 계좌에서 10만원 빼기
update `account` set balance = balance - 100000
where user_id = 'sjs';

-- 이효석 계좌에 10만원 넣기
update `account` set balance = balance + 100000
where user_id = 'lhs';

commit;

select * from `account`;