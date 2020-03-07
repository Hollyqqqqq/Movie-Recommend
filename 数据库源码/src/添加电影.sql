## 添加一部电影(start transaction)，，一直执行到最后一步才是确认添加（commit）,中间如果按取消则rollback
delimiter //
create trigger insert_movie
  before insert
  on movies
  for each row
begin
  if (select count(*) from movies where titles = new.titles) != 0 then
    signal sqlstate 'HY000' set message_text = 'The movie exists.'; ## 'HY000'是随便写的不存在的东西，用来抛出异常
  end if;
end //
delimiter ;
insert into movies (titles, year_released, country, star, intro, runtime)
values (?, ?, ?, ?, ?, ?);
## 点击下一步,就是执行上面的insert
## 开始插入type
## java里有个循环，string[] 包含各种type，对于string[i]循环以下操作(跳过create)
delimiter //
create procedure find_type(in type varchar(20), out typeid int)
begin
  if (select count(*) from types where type_name = type) = 0 then
    insert into types (type_name) values (type);
  end if;
  set typeid = (select id_types from types where type_name = type);
end//
delimiter ;
call find_type(/*string[i]*/, @typeid);
set @new_movie_id = (select max(id_movies)
                     from movies);
insert into movie_types
values (@new_movie_id, @typeid);
## 点击下一步代表执行上面的循环
## 开始添加导演、演员
## java里两个循环，string1[] 包含各个director，string2[] 包含各个actor, 分别对于string1[i], string2[i]循环以下操作(跳过create)
delimiter // ##people的性别设置可以为空，完善信息的时候再添加性别
create procedure find_people(in people_name varchar(20), out peopleid int)
begin
  if (select count(*) from people p where p.name = people_name) = 0 then
    insert into people (name) values (people_name);
  end if;
  set peopleid = (select id_people from people where name = people_name);
end//
delimiter ;
call find_people(/*string1[i]或string2[i]*/, @peopleid);
set @new_movie_id = (select max(id_movies)
                     from movies);
insert into credits_as
values (@new_movie_id, @peopleid, /*A或者D，在循环的时候就指定*/);
##开始完善演员信息,(把演员的出生和死亡什么的归到一个新建的简介info)
delimiter //
create procedure complete_people(in peopleid int, in peoplegender varchar(1), in information varchar(200))
begin
  update people set people.gender = peoplegender, people.info = information where people.id_people = peopleid;
end //
delimiter ;
select count(*)
from people
where gender is null; ##循环次数，用java控制
call complete_people((select min(id_people)
                      from people
                      where gender is null),/*需要输入性别*/, /*需要输入information*/);
