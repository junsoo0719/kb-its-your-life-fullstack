select * from `member`;

alter table `member`
add index idx_member_name (name);

alter table `member`
drop index idx_member_name;

explain analyze
select * from `member`
where name = '순자';

explain
select * from `member`
where id = 25;

explain
select * from `member`
where email = 'soonja.tae@example.com';