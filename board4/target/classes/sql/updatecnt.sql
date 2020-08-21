select * from board;
update board set cnt=0 where cnt is null;

create table board(
seq number primary key,
title varchar2(30),
writer varchar2(30),
content varchar2(2000),
regDate date default sysdate,
cnt number(3),
uploadFile varchar2(100)
);
select * from board;

/* 칼럼 추가*/
alter table board add (uploadFile varchar2(100));


/* 권한 테이블*/
create table roles(
role varchar2(20) primary key,
roleName varchar2(30 ),
description varchar2(50)
);

select * from roles;
insert into roles values ('admin','admin','관리자권한');
insert into roles values ('user','user','일반권한');
insert into roles values ('operation','operation','사용자권한');

select * from users;


