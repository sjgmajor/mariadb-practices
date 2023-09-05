# category
insert into category values(1, '소설');
insert into category values(2, '과학');
insert into category values(3, '예술');

# book
insert into book values(null, '모순', '11700', 1);
insert into book values(null, '초공간', '22500', 2);
insert into book values(null, '완전한 연주', '20700', 3);

# member
insert into member values(null, '서정권', '010-1234-5678', 'sjg1@gmail.com', '1234');
insert into member values(null, '권정서', '010-5678-1234', '1sjg@naver.com', '5678');

# cart
insert into cart values(2, 1, 1);
insert into cart values(1, 2, 2);
insert into cart values(1, 3, 2);

# orders
insert into orders(member_no, address) values(1, '서울특별시 서초구 서초대로74길 33');
insert into orders(member_no, address) values(2, '서울특별시 서초구 서초중앙로28길 16 1F');

# order_book
insert into order_book values(1, 1);
insert into order_book values(2, 2);

# category
select a.name '카테고리' from category a;

# book
select a.title '제목', a.price '가격' from book a;

# member
select a.name '이름', a.tel '전화번호', a.email '이메일', a.password '비밀번호' from member a;

# cart
select b.title '도서 제목', a.quantity '수량', b.price '가격' 
from cart a, book b
where a.book_no = b.book_no
and a.member_no = 1;

# orders
select
concat(date_format(curdate(), '%Y%m%d'), '', a.order_no) '주문번호',
b.name '주문자 이름',
b.email '주문자 이메일',
e.quantity * d.price '결제금액',
a.address '배송지'
from orders a, member b, order_book c, book d, cart e
where a.member_no = b.no
and a.order_no = c.orders_no
and c.book_no = d.book_no
and d.book_no = e.book_no;

# order_book
select a.book_no '도서번호', b.title '도서제목', c.quantity '수량'
from order_book a, book b, cart c
where a.book_no = b.book_no
and b.book_no = c.book_no;