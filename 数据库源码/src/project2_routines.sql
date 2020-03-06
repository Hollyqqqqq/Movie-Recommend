-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: project2
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping events for database 'project2'
--

--
-- Dumping routines for database 'project2'
--
/*!50003 DROP PROCEDURE IF EXISTS `add_friend` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_friend`(in userid1 int, in userid2 int, out result varchar(20))
begin
  if (userid1 != userid2) and ((select count(*) from user where user.id_user = userid2) != 0) then
    if (select count(*) from friends where friends.user1 = userid1 and friends.user2 = userid2) = 0 then
      insert into friends (user1, user2) values (userid1, userid2);
      insert into friends (user1, user2) values (userid2, userid1);
      set result = 'true';
    else
      set result = 'false';
    end if;
  else
    set result = 'false';
  end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `add_friend_request` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_friend_request`(in userid1 int, in userid2 int, out result varchar(20))
begin
  if (userid1 != userid2) and ((select count(*) from user where user.id_user = userid2) != 0) then
    if (select count(*) from friends where friends.user1 = userid1 and friends.user2 = userid2) = 0
      and (select count(*) from friend_request where userid = userid1 and friendid = userid2) = 0 then
      insert into friend_request (userid, friendid, date) values (userid1, userid2, current_date);
      set result = 'true';
    else
      set result = 'false';
    end if;
  else
    set result = 'false';
  end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `agree_add_friend` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `agree_add_friend`(in idrequest int)
begin
  declare u1id int;
  declare u2id int;
  set u1id = (select userid from friend_request where requestid = idrequest);
  set u2id = (select friendid from friend_request where requestid = idrequest);
  start transaction ;
  insert into friends (user1, user2) values (u1id, u2id);
  insert into friends (user1, user2) values (u2id, u1id);
  delete from friend_request where (userid = u1id and friendid = u2id) or (userid = u2id and friendid = u1id);
  commit;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `check_answer` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `check_answer`(in userid int, in ans1 varchar(100), in ans2 varchar(100), in ans3 varchar(100), out result varchar(100))
begin
  set @real_ans1 = (select answer from question_answer where user_id = userid and question_id = 1);
  set @real_ans2 = (select answer from question_answer where user_id = userid and question_id = 2);
  set @real_ans3 = (select answer from question_answer where user_id = userid and question_id = 3);
  if (@real_ans1 != ans1 or @real_ans2 != ans2 or @real_ans3 != ans3) then 
    set result = 'Not correct answer!';
  else 
    set result = (select password from user where id_user = userid);
  end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `classify_movie` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `classify_movie`(IN typeid int, IN country_ varchar(20), IN year int)
begin
  if (typeid != 0) then
    drop table if exists accord_type;
    create temporary table accord_type (
      select movies.*
      from movies
             join movie_types on movies.id_movie = movie_types.id_movie
      where movie_types.id_type = typeid);
  else
    drop table if exists accord_type;
    create temporary table accord_type (
      select movies.*
      from movies);
  end if;

  if (country_ != '全部地区' and country_ != '其他地区') then
    drop table if exists accord_country;
    create temporary table accord_country (
      select movies.*
      from movies
      where movies.country = country_);
  else
    if (country_ != '其他地区') then
      drop table if exists accord_country;
      create temporary table accord_country (
        select movies.*
        from movies);
    else
      drop table if exists accord_country;
      create temporary table accord_country (
        select movies.*
        from movies where movies.country not in ('中国','英国','法国','美国','日本','西班牙','俄罗斯'));
    end if;
  end if;

  if (year != 0 and year != -1) then
    drop table if exists accord_year;
    create temporary table accord_year (
      select movies.*
      from movies
      where year_released between year and (year + 9));
  else
    if (year != -1) then
      drop table if exists accord_year;
      create temporary table accord_year (
        select movies.*
        from movies);
    else
      drop table if exists accord_year;
      create temporary table accord_year (
        select movies.*
        from movies
        where year_released < 2000);
    end if;
  end if;

  select at.*
  from accord_type at
         join accord_country ac
              on at.id_movie = ac.id_movie
         join accord_year ay
              on at.id_movie = ay.id_movie;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_all` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_all`()
begin
  declare i int;
  declare max int;
  set i = (select min(id_movie) from movies);
  set max = (select max(id_movie) from movies);
  while i <= max do
  call delete_movie(i, @x);
  set i = i + 1;
  end while;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_friend` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_friend`(in userid int, in friendid int, out success int)
begin
  set success = 0;
  start transaction ;
  delete
  from recommend
  where cp_id in (select id_friends
                  from friends
                  where (user1 = userid and user2 = friendid)
                     or (user2 = userid and user1 = friendid));
  delete from friends where (user1 = userid and user2 = friendid) or (user2 = userid and user1 = friendid);
  commit ;
  set success = 1;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_movie` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_movie`(in movieid int, out success int)
begin
  set success = 0;
  start transaction ;
  delete from movie_types where id_movie = movieid;
  delete from credits_as where id_movie = movieid;
  delete from movie_music where id_movie = movieid;
  delete from watched_movie where id_movie = movieid;
  delete from recommend where movie_id = movieid;
  delete from movies where id_movie = movieid;
  commit;
  set success = 1;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `export_movies` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `export_movies`()
begin
  declare i int;
  declare max int;
  set i = (select min(id_movie) from movies);
  set max = (select max(id_movie) from movies);
  set group_concat_max_len = 1000;
  drop table if exists export;
  create temporary table export
  (
    title         varchar(100),
    star          double(3, 1),
    country       varchar(20),
    types         varchar(1000),
    director      varchar(1000),
    actor         varchar(1000),
    runtime       int,
    year_released int,
    intro         varchar(1000)
  );
  while i <= max do
  if (select count(*) from movies where id_movie = i) != 0 then
    insert into export select movies.title,
           movies.star,
           movies.country,
           w.types,
           w.director,
           w.actor,
           movies.runtime,
           movies.year_released,
           movies.intro
    from (select x.id_movie, x.types, y.director, z.actor
          from (select movies.id_movie, group_concat(type_name) types
                from movies
                       left join movie_types on movies.id_movie = movie_types.id_movie
                       left join types on movie_types.id_type = types.id_types
                where movies.id_movie = i) x
                 left join (select movies.id_movie, group_concat(people.name) director
                       from movies
                              left join credits_as on movies.id_movie = credits_as.id_movie
                              left join people on credits_as.id_people = people.id_people
                       where movies.id_movie = i
                         and credits_as.role = 'D') y
                      on x.id_movie = y.id_movie
                 left join (select movies.id_movie, group_concat(people.name) actor
                       from movies
                              left join credits_as on movies.id_movie = credits_as.id_movie
                              left join people on credits_as.id_people = people.id_people
                       where movies.id_movie = i
                         and credits_as.role = 'A') z
                      on x.id_movie = z.id_movie) w
           join movies on movies.id_movie = w.id_movie;
  end if ;
  set i = i + 1;
  end while;
  select title as '电影名', star as '评分', country as '国家/地区', types as '类型', director as '导演', actor as '演员', runtime as '时长', year_released as '发行年份', intro as '简介' from export;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `find_people` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `find_people`(in people_name varchar(20), in p_gender varchar(1), in p_born varchar(20), in p_info varchar(1000), out peopleid int)
begin
  if (select count(*) from people p where p.name = people_name and p.born = p_born) = 0 then
    insert into people (name, gender, born, info) values (people_name, p_gender, p_born, p_info);
  end if;
  set peopleid = (select id_people from people where name = people_name and p_born=born);
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `find_type` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `find_type`(in type varchar(20), out typeid int)
begin
  if (select count(*) from types where type_name = type) = 0 then
    insert into types (type_name) values (type);
  end if;
  set typeid = (select id_types from types where type_name = type);
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `fri_recommend` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `fri_recommend`(in userid int)
begin
  select user.user_name,recommend.movie_id, movies.title, movies.star, recommend.reason, recommend.date
  from friends
         join recommend on friends.id_friends = recommend.cp_id
         join movies on recommend.movie_id = movies.id_movie
         join user on friends.user1 = user.id_user
  where friends.user2 = userid
    and datediff(recommend.date, current_date) > -7;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_movie` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_movie`(in title_ varchar(45), in year_ int, in country_ varchar(20), in star_ double,
                              in intro_ varchar(1000), in runtime_ int, out insert_or_update int, out movieid int)
begin
  if (select count(*) from movies where title = title_) != 0 then
    update movies m
    set m.year_released = year_,
        m.country       = country_,
        m.star          = star_,
        m.original_score= star_,
        m.intro         = intro_,
        m.runtime       = runtime_
    where m.title = title_;
    set insert_or_update = 0;
  else
    insert into movies (title, year_released, country, star, original_score, intro, runtime)
    values (title_, year_, country_, star_, star_, intro_, runtime_);
    set insert_or_update = 1;
  end if;
  set movieid = (select id_movie from movies where movies.title = title_);
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `recommend_movie` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `recommend_movie`(in userid int, in friendid int, in movieid int, in rea varchar(500))
begin
  set @cpid = (
    select id_friends
    from friends
    where user1 = userid
      and user2 = friendid);
  replace into recommend (cp_id, movie_id, reason, date) VALUES (@cpid, movieid, rea, curdate());
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `replace_watched_movie` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `replace_watched_movie`(in iduser int, in idmovie int, in star_ int, in comm varchar(1000))
begin
  replace into watched_movie (id_user, id_movie, star, date, commends)
  values (iduser, idmovie, star_, curdate(), comm);
UPDATE movies 
SET 
    star = ROUND((SELECT 
                    x.original_score
                FROM
                    (SELECT 
                        original_score
                    FROM
                        movies
                    WHERE
                        id_movie = idmovie) x) * 0.9 + (SELECT 
                    y.a
                FROM
                    (SELECT 
                        AVG(star) a
                    FROM
                        watched_movie
                    WHERE
                        id_movie = idmovie) y) * 0.1,
            2)
WHERE
    id_movie = idmovie;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `search_movie` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `search_movie`(in subtitle varchar(100))
begin
  select * from movies
    where movies.title like concat(subtitle, '%')
    or movies.title like concat('%', subtitle)
    or movies.title like concat('%', subtitle, '%')
     or movies.intro like concat('%',subtitle)
    or movies.intro like concat(subtitle,'%')
    or movies.intro like concat('%',subtitle,'%')
    ;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `show_friends` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `show_friends`(in userid int)
begin
  select x.id_user, x.user_name, group_concat(type_name) preference, x.decribe
  from (select user.id_user, user.user_name, user.decribe
        from friends
               join user on friends.user2 = user.id_user
        where friends.user1 = userid) x
         left join preference on x.id_user = preference.id_user
  left  join types on preference.movie_type = types.id_types
    group by x.id_user;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `show_friend_request` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `show_friend_request`(in iduser int)
begin
  select x.id_user, x.user_name, group_concat(type_name) preference, x.decribe, x.date, x.requestid
  from (select friend_request.requestid, user.id_user, user.user_name, user.decribe, friend_request.date
        from friend_request
               join user on friend_request.userid = user.id_user
        where friend_request.friendid = iduser) x
         left join preference on x.id_user = preference.id_user
         left join types on preference.movie_type = types.id_types
  group by x.id_user;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `show_movie_by_type` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `show_movie_by_type`(in typename varchar(45))
begin
  call find_type(typename, @typeid);
  select movies.* from movies
    left join movie_types on movies.id_movie = movie_types.id_movie
  where movie_types.id_type = @typeid;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `show_movie_comments` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `show_movie_comments`(in movieid int)
begin
  select user.user_name, watched_movie.star, watched_movie.commends, watched_movie.date
  from watched_movie
         join user on watched_movie.id_user = user.id_user
  where watched_movie.id_movie = movieid;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `show_movie_detail` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `show_movie_detail`(in movieid int)
begin
  set group_concat_max_len = 200;
  select movies.title,
         movies.star,
         movies.country,
         w.types,
         movies.runtime,
         movies.year_released,
         movies.intro
  from (select x.id_movie, x.types
        from (select movies.id_movie, group_concat(type_name) types
              from movies
                     left join movie_types on movies.id_movie = movie_types.id_movie
                     left join types on movie_types.id_type = types.id_types
              where movies.id_movie = movieid) x) w
         left join movies on movies.id_movie = w.id_movie;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `show_movie_people` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `show_movie_people`(in movieid int)
begin
  select people.*, credits_as.role from movies
    join credits_as on credits_as.id_movie = movies.id_movie
    join people on people.id_people = credits_as.id_people
  where movies.id_movie = movieid;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `show_recently_watched` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `show_recently_watched`(in userid int)
begin
  select movies.title
  from user
         join watched_movie on user.id_user = watched_movie.id_user
         join movies on watched_movie.id_movie = movies.id_movie
  where user.id_user = userid
    and datediff(watched_movie.date, current_date) > -7;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `show_user_info` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `show_user_info`(in userid int)
begin
  select user.user_name, group_concat(types.type_name) preference, user.decribe from user
   left join preference on user.id_user = preference.id_user
 left join types on preference.movie_type = types.id_types
    where user.id_user = userid;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `show_watched_movies` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `show_watched_movies`(in userid int)
begin
  select movies.id_movie, movies.title, watched_movie.star, watched_movie.commends
  from user
         join watched_movie on user.id_user = watched_movie.id_user
         join movies on watched_movie.id_movie = movies.id_movie
  where user.id_user = userid;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sys_recommend` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sys_recommend`(IN userid int, IN times int)
begin
  declare i int;
  declare maxmovieid int;
  declare intersect_type int;
  declare union_type int;
  declare intersect_actor int;
  declare union_actor int;
  declare similarity double;
  declare avg_score double;
  declare avg_type_count double;
  declare avg_actor_count double;

  set avg_score = (select avg(watched_movie.star)
                   from watched_movie
                   where watched_movie.id_user = userid);
  set avg_type_count = (select avg(cnt)
                        from (select count(*) cnt
                              from watched_movie
                                     join movie_types on watched_movie.id_movie = movie_types.id_movie
                              where watched_movie.id_user = userid
                              group by id_type) x);
  set avg_actor_count = (select avg(cnt)
                         from (select count(id_people) cnt
                               from watched_movie
                                      join credits_as on credits_as.id_movie = watched_movie.id_movie
                               where id_user = userid
                               group by id_people) x);

  set i = (select min(id_movie) from movies);
  set maxmovieid = (select max(id_movie) from movies);
  ## 用户标签和近期观看的类型统计
  drop table if exists tname;
  create temporary table tname (select id_types
                                from user u
                                       join preference lmt on u.id_user = lmt.id_user
                                       join types t on lmt.movie_type = t.id_types
                                where u.id_user = userid
                                union
                                select distinct id_type
                                from watched_movie wm
                                       join movies m on wm.id_movie = m.id_movie
                                       join movie_types mt on m.id_movie = mt.id_movie
                                where wm.id_user = userid
                                  and m.star > avg_score
                                  #                                   and m.star > 8
                                  and DATEDIFF(wm.date, current_date) > -7
                                group by id_type
                                having count(*) > avg_type_count);
  #                                 having count(*) > 2);
  ## 用户近期观看的演员的统计
  drop table if exists aname;
  create temporary table aname (select distinct id_people
                                from watched_movie wm
                                       join movies m on wm.id_movie = m.id_movie
                                       join credits_as ca on m.id_movie = ca.id_movie
                                where wm.id_user = userid
                                  and m.star > avg_score # 看过电影的平均值
                                  #                                   and m.star > 8
                                  and datediff(wm.date, current_date) > -7
                                group by id_people
                                having count(*) > avg_actor_count);
  #                                 having count(*) > 1);

  drop table if exists score;
  create temporary table score
  (
    movieid int not null,
    score   double,
    primary key (movieid)
  );

  while i < maxmovieid do ## 还要判断不在看过的电影里面,,如果电影不存在，？？
  if (select count(*) from movies where id_movie = i) != 0 then
    select count(*) into intersect_type
    from (select t.id_types
          from tname t
                 join
               (select id_type
                from movies
                       join movie_types mt3 on movies.id_movie = mt3.id_movie
                where movies.id_movie = i) m_type on t.id_types = m_type.id_type) x;

    select count(*) into union_type
    from (select t.id_types
          from tname t
          union
          select id_type
          from (select id_type
                from movies
                       join movie_types mt3 on movies.id_movie = mt3.id_movie
                where movies.id_movie = i) m_type) x;

    select count(*) into intersect_actor
    from (select a.id_people
          from aname a
                 join
               (select id_people
                from movies
                       join credits_as c on movies.id_movie = c.id_movie
                where movies.id_movie = i) m_actor on a.id_people = m_actor.id_people) x;

    select count(*) into union_actor
    from (select a.id_people
          from aname a
          union
          select id_people
          from (select id_people
                from movies
                       join credits_as ca2 on movies.id_movie = ca2.id_movie
                where movies.id_movie = i) m_actor) x;

    set similarity = intersect_type * 1.0 / union_type + intersect_actor * 1.0 / union_actor;
    insert into score values (i, similarity);
  end if ;
  set i = i + 1;
  end while;
  set times = 10 * times;
  select movies.id_movie, movies.title, movies.star, movies.country, movies.year_released
  from score s
         join movies on s.movieid = movies.id_movie
  order by s.score desc
  limit times, 10;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-06 16:41:29
