select * from `account`;

-- 격리성 확인
start transaction;

-- 이효석 계좌에서 50만원 빼기
update `account` set balance = balance - 500000
where user_id = 'lhs';

-- 송준수 계좌로 50만원 넣기
update `account` set balance = balance + 500000
where user_id = 'sjs';

commit;