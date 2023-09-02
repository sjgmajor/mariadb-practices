-- 혼합 SQL문제입니다.

-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?
select count(*)
  from salaries a
 where a.to_date = '9999-01-01'
   and a.salary > (select avg(salary)
					 from salaries a
				    where a.to_date = '9999-01-01');

-- 문제2.(X) 
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 연봉을 조회하세요. 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다. 
select a.emp_no, a.first_name, b.salary, c.dept_no
from employees a, salaries b, dept_emp c
where a.emp_no = b.emp_no
and b.emp_no = c.emp_no
and b.to_date = '9999-01-01'
and c.to_date = '9999-01-01';

-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요 
select a.emp_no, a.first_name, b.salary
  from employees a,
	 salaries b,
	 dept_emp c,
       (select b.dept_no, avg(a.salary) as avg_salary
	     from salaries a, dept_emp b
	    where a.emp_no = b.emp_no
	      and a.to_date = '9999-01-01'
		  and b.to_date = '9999-01-01'
	 group by b.dept_no) d
 where a.emp_no = b.emp_no
   and b.emp_no = c.emp_no
   and b.to_date = '9999-01-01'
   and c.to_date = '9999-01-01'
   and d.dept_no = c.dept_no
   and b.salary > d.avg_salary;

-- 문제4.
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.
select a.emp_no, a.first_name, d.manager_name, c.dept_name
from employees a,
     dept_emp b,
	 departments c,
     (select b.dept_no, a.first_name as manager_name
	  from employees a, dept_manager b
	  where a.emp_no = b.emp_no
        and b.to_date = '9999-01-01') d
where a.emp_no = b.emp_no
and b.dept_no = c.dept_no
and c.dept_no = d.dept_no
and b.to_date = '9999-01-01';

-- 문제5.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.
select a.emp_no, a.first_name, d.title, b.salary
  from employees a,
	 salaries b,
	 dept_emp c,
     titles d
 where a.emp_no = b.emp_no
   and b.emp_no = c.emp_no
   and c.emp_no = d.emp_no
   and b.to_date = '9999-01-01'
   and c.to_date = '9999-01-01'
   and d.to_date = '9999-01-01'
   and c.dept_no = (    select a.dept_no
						  from dept_emp a, departments b, salaries c
					     where a.emp_no = c.emp_no
						   and a.dept_no = b.dept_no
						   and a.to_date = '9999-01-01'
						   and c.to_date = '9999-01-01'
					  group by a.dept_no
					having avg(c.salary) = (select max(a.avg_salary)
											  from (  select b.dept_no, avg(a.salary) as avg_salary
													    from salaries a, dept_emp b
													   where a.emp_no = b.emp_no
														 and a.to_date = '9999-01-01'
														 and b.to_date = '9999-01-01'
													group by b.dept_no) a))
order by salary desc;
      
-- 문제6.
-- 평균 연봉이 가장 높은 부서는? 
-- 총무 20000
	select b.dept_name, avg(c.salary)
	  from dept_emp a, departments b, salaries c
	 where a.emp_no = c.emp_no
	   and a.dept_no = b.dept_no
	   and a.to_date = '9999-01-01'
       and c.to_date = '9999-01-01'
  group by a.dept_no
having avg(c.salary) = (select max(a.avg_salary)
						  from (  select b.dept_no, avg(a.salary) as avg_salary
									from salaries a, dept_emp b
								   where a.emp_no = b.emp_no
									 and a.to_date = '9999-01-01'
									 and b.to_date = '9999-01-01'
								group by b.dept_no) a);

-- 문제7.
-- 평균 연봉이 가장 높은 직책?
-- Engineer 40000
    select b.title, avg(c.salary)
      from employees a, titles b, salaries c
     where a.emp_no = b.emp_no
       and b.emp_no = c.emp_no
       and b.to_date = '9999-01-01'
       and c.to_date = '9999-01-01'
  group by b.title
having avg(c.salary) = (select max(a.avg_salary)
						  from (  select b.title, avg(a.salary) as avg_salary
									from salaries a, titles b
								   where a.emp_no = b.emp_no
									 and a.to_date = '9999-01-01'
									 and b.to_date = '9999-01-01'
								group by b.title) a);


-- 문제8.(순수 join 문제)
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.
select e.dept_name, a.first_name, c.salary, f.first_name, g.salary
  from employees a,
       dept_emp b, 
       salaries c,
       dept_manager d,
       departments e,
       employees f,
       salaries g
 where a.emp_no = b.emp_no
   and b.to_date = '9999-01-01'
   and b.dept_no = d.dept_no
   and c.to_date = '9999-01-01'
   and b.emp_no = c.emp_no
   and d.to_date = '9999-01-01'
   and d.dept_no = e.dept_no
   and d.emp_no = f.emp_no
   and f.emp_no = g.emp_no
   and g.to_date = '9999-01-01'
   and c.salary > g.salary;