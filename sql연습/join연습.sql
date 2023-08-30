-- inner join

-- 예제1: 현재, 근무하고 있는 직원의 사번, 이름, 직책을 모두 출력
select a.emp_no, first_name, title
  from employees as a, titles as b
 where a.emp_no = b.emp_no              -- join 조건(n-1)
   and b.to_date = '9999-01-01';        -- row 선택 조건