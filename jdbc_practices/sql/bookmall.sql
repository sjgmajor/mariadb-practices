update orders 
set total_price = 5
where order_no = 1;

select * from orders;


select * from order_book;

select a.book_no, c.title, a.quantity
from order_book a join orders b on b.order_no = a.orders_no
join book c on a.book_no = c.book_no
where substring_index(a.orders_no,'-',1)=3;

select substring_index(a.order_no, '-',-1)
from orders a;
select a.order_no
from orders a;


select * from order_book;


select a.cart_no
from cart a
where a.member_no=1;

select * from book;
select * from category;
select * from member;

select a.no
from member a
where a.name = '서정권';
                    
select a.order_no, a.member_no, a.address, a.total_price
from orders a, member b, order_book c, book d
where a.order_no = c.order_no
and c.book_no = d.book_no;


# category
desc category;
insert into category values(null, '소설');
insert into category values(null, '과학');
insert into category values(null, '예술');

# category
select * from category a;


# book
insert into book values(null, '모순', 11700, 2);
insert into book values(null, '초공간', 22500, 3);
insert into book values(null, '완전한 연주', 20700, 1);

# book
select a.title '제목', a.price '가격' from book a;

# member
insert into member values(null, '서정권', '010-1234-5678', 'sjg1@gmail.com', '1234');
insert into member values(null, '권정서', '010-5678-1234', '1sjg@naver.com', '5678');

# member
select a.name '이름', a.tel '전화번호', a.email '이메일', a.password '비밀번호' from member a;

# cart
desc cart;
insert into cart values(2, 2, 1);
insert into cart values(1, 3, 2);
insert into cart values(1, 1, 2);

# cart
desc cart;
select b.title '도서 제목', a.quantity '수량', b.price '가격' 
from cart a, book b
where a.book_no = b.book_no
and a.member_no = 1;

# orders
desc orders;
insert into orders values(null, 1, '서울특별시 서초구 서초대로74길 33', null);
insert into orders values(null, 2, '서울특별시 서초구 서초중앙로28길 16 1F', null);

# order_book
desc order_book;
insert into order_book values(2, 1, null, null);
insert into order_book values(3, 2, null, null);

update order_book a set a.price = 1000
 where a.book_no = b.book_no;
 
select * from order_book;

# orders
select concat(date_format(curdate(), '%Y%m%d'), '-', a.order_no) '주문번호', b.name, b.email, a.address, a.total_price from orders a join member b on a.member_no = b.no
where a.member_no = 3;

select a.book_no 
from book a
where a.title= '모순';