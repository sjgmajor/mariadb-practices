-- 
-- ddl
-- 

drop table member;
create table member (
    no int not null auto_increment,
    email varchar(200) not null,
    password varchar(64) not null,
    name varchar(100) not null,
    department varchar(100),
    primary key(no)
);
desc member;

alter table member add column juminbunho char(13) not null;
desc member;

alter table member drop column juminbunho;
desc member;

alter table member add column juminbunho char(13) not null after email;
desc member;

alter table member change column department dept varchar(200);
desc member;

alter table member add self_intro text;
desc member;

alter table member drop column juminbunho;
desc member;

--
-- dml
--

-- insert
insert
  into member
values (null, 'sjg3234@gmail.com', password('1234'), '서정권', '개발팀', null);
select * from member;

insert
  into member(email, name, dept, password)
values ('sjg32344@gmail.com', '서정권4', '개발팀4', password('1234'));
select * from member;

-- update
update member
   set email='kixxcar@gmail.com', name='킥스카'
 where no = 4;
 select * from member;
 
-- delete
delete
  from member
 where no = 4;
select * from member;

-- transactio begin
set autocommit = 0;
select @@autocommit from dual;

insert
  into member(email, name, dept, password)
values ('sjg32344@gmail.com', '서정권4', '개발팀4', password('1234'));

select no, email, dept from member;

commit;

select no, email, dept from member;

select * from member;