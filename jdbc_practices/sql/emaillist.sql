-- emaillist

-- insert

insert into emaillist values(null, '서', '정권', 'sjg3234@gmail.com');

-- findAll
select no, first_name, last_name, email
from emaillist
order by no asc;

-- delete
delete from emaillist
where email = 'sjg3234@gmail.com';

-- findAll
select no, first_name, last_name, email
from emaillist
order by no asc;