## 用户评分对电影分数的影响, 用户权重20%，电影本身数据80%
delimiter //
drop procedure if exists update_movie_score;
create procedure update_movie_score()
begin
  declare i int;
  declare max int;
  set i = (select min(id_movies) from movies);
  set max = (select max(id_movies) from movies);
  while i < max do
  update movies
  set star = round((select star from movies where id_movies = i) * 0.8 +
                   (select avg(star)
                    from watched_movie
                    where id_movie = i) * 0.2, 2);
  end while;
end //
delimiter ;
call update_movie_score();