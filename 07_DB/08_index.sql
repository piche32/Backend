
use ssafydb;

-- ------------------------------------------------------------------------------------------
-- index 확인
-- ------------------------------------------------------------------------------------------
select count(*) from users;
select count(*) from students;

show index from users;
show index from students;
-- ------------------------------------------------------------------------------------------
-- full table scan
-- ------------------------------------------------------------------------------------------
explain analyze
select  *
from	students;

explain analyze
select  *
from	users;

-- ------------------------------------------------------------------------------------------
-- Index full scan
-- ------------------------------------------------------------------------------------------
explain analyze
select  count(*)
from	students;
explain analyze
select  count(*)
from	users;

-- ------------------------------------------------------------------------------------------
-- Index unique scan
-- ------------------------------------------------------------------------------------------
explain analyze
select  name, campus, class_no, ord_no
from	students
where	student_id = 'A004648';

explain analyze
select  no,name,age
from	users
WHERE	no = 7777; 

-- ------------------------------------------------------------------------------------------
-- Index range scan
-- ------------------------------------------------------------------------------------------
explain analyze
select  student_id, name, campus, class_no, ord_no
from	students 
where	student_id  BETWEEN 'A004800' and 'Y004999'; 
 
 explain analyze
select  no,name,age
from	users 
WHERE	no between 1 and 90000;


-- ------------------------------------------------------------------------------------------
-- secendary index
-- ------------------------------------------------------------------------------------------
-- explain analyze
select  name, campus, class_no
from	students
where   name = '김태희';

-- explain analyze
select  no,name,age
from	users
WHERE	name = 'user007777'; 

-- 이름에 인덱스 생성
create index idx_name on users(name);

show index from users;


-- ------------------------------------------------------------------------------------------
-- functional based index
-- ------------------------------------------------------------------------------------------

    select  name, campus, class_no
    from	students
    where	substr(name,2) = '수지';
    
    explain analyze
	select  no,name,age
	from	users
	WHERE	substr(name,2) = 'us'; 

   -- 함수 기반 인덱스 생성



-- ------------------------------------------------------------------------------------------
-- multi-columned index
-- ------------------------------------------------------------------------------------------

select ord_no, campus, class_no, name
FROM   students 
WHERE  ord_no = 13
AND    campus = '서울';

explain analyze
select  no, age,gender
from	users
where	age between 20 and 29
and		gender = 'f';
    

   -- multi columned 인덱스 생성
   create index idx_gender_age on users(gender, age);
   
explain analyze
select  name, age,gender
from	users
where gender = 'f';
    
------------------------------------------------------------------------------------------
-- covering index
-- ------------------------------------------------------------------------------------------


select  count( distinct campus )
from	students
where	ord_no = 13;


select	student_id
from	students
where	ord_no = 13;

