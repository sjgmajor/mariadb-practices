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

update order_book a set a.price = b.price, a.quantity = c.quantity
   from book b
 join cart c
 on a.book_no = b.book_no
 on b.book_no = c.book_no
 on a.price = b.price
 on a.quantity = c.quantity
 where a.book_no = a.;
 
select * from order_book;

# orders
select
concat(date_format(curdate(), '%Y%m%d'), '', a.order_no) '주문번호',
b.name '주문자 이름',
b.email '주문자 이메일',
c.quantity * c.price '결제금액',
a.address '배송지'
from orders a, member b, order_book c, cart d
where a.member_no = b.no
and a.order_no = c.orders_no;

select a.book_no 
from book a
where a.title= '모순';