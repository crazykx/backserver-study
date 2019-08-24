##### [图解SQL的JOIN](https://coolshell.cn/articles/3463.html)

TableA TableB 其各有四条记录，其中有两条记录是相同的，如下所示：

```
id name       id  name
-- ----       --  ----
1  Pirate     1   Rutabaga
2  Monkey     2   Pirate
3  Ninja      3   Darth Vader
4  Spaghetti  4   Ninja
```

下面让我们来看看不同的Join会产生什么样的结果。

inner join 

```
select * from TableA 
INNER JOIN TableB
on TableA.name = TableB.name;
```

![inner join](/Users/kx/Desktop/backserver-study/专栏笔记/MySQL进阶/mysqlJoin/img/Inner_Join.png)

left join/ right join

```
select * from tableA
LEFT JOIN tableB
on tableA.name = tableB.name
where tableB.id is not null
```

![left join](/Users/kx/Desktop/backserver-study/专栏笔记/MySQL进阶/mysqlJoin/img/Left_Join.png)

full join( mysql 不支持 Full join) 通过联合实现

```
select * from tablea left join tableb on tablea.name = tableb.name
union
select * from tablea right join tableb on tablea.name = tableb.name
```

![full join](/Users/kx/Desktop/backserver-study/专栏笔记/MySQL进阶/mysqlJoin/img/Full_Join.png)