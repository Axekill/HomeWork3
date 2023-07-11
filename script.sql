select*
from student;

select *
from student
where age between 10 and 20;

select name
from student;

select *
from student
where name like '%a%';

select *
from student
where age < student.id;

select name,age
from student
order by age;
