create table people(
                       id real,
                       name text primary key ,
                       age integer,
                       drivers_license boolean,
                       car_id text references car(id)

);

create table car(
                    id text primary key ,
                    brand text,
                    model text,
                    cash integer
);

select name, age, drivers_license, brand, model, cash
from people
         inner join car c on c.id = people.car_id;


select student.name, student.age, f.name
from student
         inner join faculty f on f.id = student."faculty id";

select name
from avatar
         inner join student s on s.id = avatar.student_id