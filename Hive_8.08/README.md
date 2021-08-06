<big>**Hive作业步骤**</big>

1. **展示电影ID为2116这部电影各年龄段的平均影评分**

```sql
SELECT u.age AS age, avg(r.rate) AS avgrate
FROM hive_sql_test1.t_user AS u JOIN hive_sql_test1.t_rating AS r ON u.userid=r.userid
WHERE r.movieid=2116
GROUP BY u.age
```
**结果**<br>

![Image text](https://github.com/luckqk/BigDataLesson/blob/main/Hive_8.08/Q1.png)

2. **找出男性评分最高且评分次数超过50次的10部电影，展示电影名，平均影评分和评分次数!**

```sql
select  t1.sex sex, m.Moviename name, t1.avgrate avgrate, t1.total total from 
(
    select r.MovieID , collect_set(u.sex)[0] sex, avg(r.rate) avgrate ,count(r.rate) total 
    from hive_sql_test1.t_rating r join hive_sql_test1.t_user u on r.UserID = u.UserID
    where u.sex='M' 
    group by r.movieid having count(r.MovieID) > 50 
    order by avgrate desc limit 10
) t1 left join 
hive_sql_test1.t_movie m where t1.MovieID = m.MovieID;
```
注意点: hql与sql不同点,https://www.jianshu.com/p/508f33d44324 <br>
**结果**<br>

![Image text](https://github.com/luckqk/BigDataLesson/blob/main/Hive_8.08/Q2.png)

3. **找出影评次数最多的女士所给出最高分的10部电影的平均影评分，展示电影名和平均影评分（可使用多行SQL）**

step1:查找次数最多的女士的id
```sql
select r.userid, count(r.userid) total
from hive_sql_test1.t_rating r join hive_sql_test1.t_user u on r.userid=u.userid
where u.sex="F"
group by r.userid
order by total desc
limit 1;
```

![Image text](https://github.com/luckqk/BigDataLesson/blob/main/Hive_8.08/Q3(1).png)
step2:查找这位女士最高分的10部电影id
```sql
create table mid_film_id as
select r.movieid movieid, r.rate rate
from hive_sql_test1.t_rating r
where r.userid=1150
order by rate desc
limit 10;
```
step3:查找这些电影的电影名以及平均评分
```sql
create table ans as
select m.moviename moviename, avg(r.rate) avgrate
from mid_film_id mid
join hive_sql_test1.t_rating r on mid.movieid=r.movieid
join hive_sql_test1.t_movie m on mid.movieid=m.movieid
group by m.moviename;
```
step4:展示结果
```sql
select o.moviename moviename, o.avgrate avgrate 
from ans o
order by avgrate desc;
```
**结果**<br>

![Image text](https://github.com/luckqk/BigDataLesson/blob/main/Hive_8.08/Q3(2).png)

![Image text](https://github.com/luckqk/BigDataLesson/blob/main/Hive_8.08/Q3(3).png)
