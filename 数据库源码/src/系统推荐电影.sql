## 系统推荐电影功能
delimiter //
create procedure sys_recommend(in userid int,
                               in times int, ## 第几次刷新
                               out movie1 int,
                               out movie2 int,
                               out movie3 int,
                               out movie4 int,
                               out movie5 int)
begin
  declare tname varchar(20);
  declare aname varchar(20);
  declare i int;
  declare maxmovieid int;
  declare intersect_type double;
  declare union_type double;
  declare intersect_actor double;
  declare union_actor double;
  declare similarity double;

  ##怎么特殊化表的名字
  set tname = 'type' + userid;
  set aname = 'actor' + userid;

  set i = (select min(id_movies) from movies);
  set maxmovieid = (select max(id_movies) from movies);
  ## 用户标签和近期观看的类型统计
  drop table if exists tname;
  create temporary table tname (select id_types
                                from user u
                                       join loved_movie_type lmt on u.id_user = lmt.id_user
                                       join types t on lmt.movie_type = t.id_types
                                where u.id_user = 1
                                union
                                select distinct id_type
                                from watched_movie wm
                                       join movies m on wm.id_movie = m.id_movies
                                       join movie_types mt on m.id_movies = mt.id_movie
                                where wm.id_user = 1
                                  and m.star > 8 ##这个不能用确定值,还有date也要限定
                                group by id_type
                                having count(*) > 2);##这个不能直接用确定值
  ## 用户近期观看的演员的统计
  drop table if exists aname;
  create temporary table aname (select distinct id_people
                                from watched_movie wm
                                       join movies m on wm.id_movie = m.id_movies
                                       join credits_as ca on m.id_movies = ca.id_movie
                                where wm.id_user = 1
                                  and m.star > 8 ##这个不能用确定值,还有date也要限定
                                group by id_people
                                having count(*) > 1);

  drop table if exists score;
  create temporary table score
  (
    movieid int    not null,
    score   double not null,
    primary key (movieid)
  );

  while i < maxmovieid do ## 还要判断不在看过的电影里面
  select count(*) into intersect_type
  from (select t.id_types
        from tname t
               join
             (select id_type
              from movies
                     join movie_types mt3 on movies.id_movies = mt3.id_movie
              where id_movies = 1) m_type on t.id_types = m_type.id_type) x;
  select count(*) into union_type
  from (select t.id_types
        from tname t
        union
        select id_type
        from (select id_type
              from movies
                     join movie_types mt3 on movies.id_movies = mt3.id_movie
              where id_movies = 1) m_type) x;
  select count(*) into intersect_actor
  from (select a.id_people
        from aname a
               join
             (select id_people
              from movies
                     join credits_as c on movies.id_movies = c.id_movie
              where id_movies = 1) m_actor on a.id_people = m_actor.id_people) x;
  select count(*) into union_actor
  from (select a.id_people
        from aname a
        union
        select id_people
        from (select id_people
              from movies
                     join credits_as ca2 on movies.id_movies = ca2.id_movie
              where id_movies = 1) m_actor) x;
  set similarity = intersect_type / union_type + intersect_actor / union_actor;
  insert into score values (i, similarity);
  end while;
  set times = 10 * times;
  select *
  from score s
  order by s.score
  limit times, 10;
end //
delimiter ;