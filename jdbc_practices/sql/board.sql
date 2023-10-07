-- board
select * from user;
select * from guestbook;
select * from board;
 desc user;
 insert into user values (null, '관리자', 'admin@mysite.com', password('1234'), 'male', now(), 'ADMIN');
 
insert into board values(null, '점심?', '배고파', 1 , now(), 1, 1, 1, 1);
insert into board values(null, '안녕', '안녕하세요', 1 , now(), 2, 1, 1, 1);
insert into board values(null, '짬뽕', '먹자ㅛㅊ료ㅕ효', 1 , now(), 1, 5, 3, 2);
insert into board values(null, '돈가스', '먹자', 1 , now(), 1, 5, 3, 2);
insert into board values(null, '굿!', '좋아', 1 , now(), 1, 3, 3, 1);
insert into board values(null, '가자', '돈가스 좋아', 1 , now(), 1, 4, 4, 1);
insert into board values(null, '안녕!', '배고파', 1 , now(), 2, 2, 2, 2);
insert into board values(null, '배고파', '그래?', 0, now(), (select MAX(g_no) as max_gno FROM board) + 1, 1, 1, user_no);

select ifnull((select MAX(o_no) from board), 1) from board  where g_no = ?

insert into board select null, '?', '?', 0, now(), ifnull((select MAX(g_no) from board), 0) + 1, 1, 1, 1 from dual;

select b.no, b.title, a.name, b.hit, b.reg_date, b.g_no, b.o_no, b.depth, b.user_no
from user a, board b
where a.no = b.user_no
order by b.g_no desc, b.o_no asc
limit ((1 - 1) * 5), 5;

delete from board where no = 5;

select b.user_no, b.title, a.name, b.hit, b.reg_date, b.g_no, b.o_no, b.depth, b.no, b.hit
					from user a, board b
						 where a.no = b.user_no
						 and b.no = 1;
                         
                         
                         
                         
select b.user_no, b.title, a.name, b.hit, b.reg_date, b.g_no, b.o_no, b.depth, b.no, b.contents
						  from user a, board b
						 where a.no = b.user_no
						  and b.no = 1;
                          
select b.user_no, b.title, a.name, b.hit, b.reg_date, b.g_no, b.o_no, b.depth, b.no
						 from user a, board b
					      where a.no = b.user_no;
                          
                          
                          
insert into board
select (null, 'a', 'b', 0, now(), ifnull(MAX(g_no), 0) + 1, 1, 1, 1)
from board;

update board
set title = '1'
, contents = '2'
where no = 1;

-- site--
select title, welcome, profile, description 
		        from site;
desc site;



insert into site values (null, 'MySite', '안녕하세요. 서정권의 mysite에 오신 것을 환영합니다.', '/assets/images/loopy.jpg', '이 사이트는 웹 프로그램밍 실습과제 예제 사이트입니다.
메뉴는 사이트 소개, 방명록, 게시판이 있구요. Java 수업 + 데이터베이스 수업 + 웹프로그래밍 수업 배운 거 있는거 없는 거 다 합쳐서 만들어 놓은 사이트 입니다.');

select * from site;

update site
set description = '이 사이트는 웹 프로그램밍 실습과제 예제 사이트입니다./r/n
메뉴는 사이트 소개, 방명록, 게시판이 있구요. Java 수업 + 데이터베이스 수업 + 웹프로그래밍 수업 배운 거 있는거 없는 거 다 합쳐서 만들어 놓은 사이트 입니다.'
where no = 1;

update site
			   set description = 'a',
				   welcome = 'a',
				   profile = 'a',
				   description = 'a'
			 where no = 1;


alter table user add column role enum('USER', 'ADMIN') NOT NULL DEFAULT 'USER';
