# Sql 基础50道

[高频 SQL 50 题（基础版） - 学习计划 - 力扣（LeetCode）全球极客挚爱的技术成长平台](https://leetcode.cn/studyplan/sql-free-50/)

# 高级字符串函数 

## [1667. 修复表中的名字 - 力扣（LeetCode）](https://leetcode.cn/problems/fix-names-in-a-table/description/?envType=study-plan-v2&envId=sql-free-50)

## [1527. 患某种疾病的患者 - 力扣（LeetCode）](https://leetcode.cn/problems/patients-with-a-condition/description/?envType=study-plan-v2&envId=sql-free-50)

问题：查一个字符串列表，含有指定前缀的字符串。

思路：要是就一个字符串，直接截取字符串前面的内容在比较就行，但是是字符串列表。那么把字符串列表当作一条字符传，如(str = "hnsqls  sqls ls") 模糊查询我们要的字段。



```sql
select *
from Patients
where conditions like '%DIAB1%'
```

发现错误

![image-20250324101401303](images/Sql 基础50.assets/image-20250324101401303.png)

 思路问题：上述思路可以实现，对整个字符串的包含子段的数据。但是忽略了前缀。如数据

conditions='DIAB100 '

conditions='test DIAB100 '

conditions='testDIAB100 '

直接模糊都可以查询，都会查到，怎么解决？只匹配前缀。

可以只前缀模糊匹配查询。 后面的数据怎么模糊匹配前缀？可以使用空格代替 

```sql
# Write your MySQL query statement below

select *
from Patients
where conditions like 'DIAB1%' or  conditions like '% DIAB1%'
```



[196. 删除重复的电子邮箱 - 力扣（LeetCode）](https://leetcode.cn/problems/delete-duplicate-emails/?envType=study-plan-v2&envId=sql-free-50)

问题：删除重复的字符串，只保留最小id的字符串

思路：

* 注意是删除，而不是选择

* 不重复的，不用修改,重复的删除，仅仅保留最小id的元素
* 那直接通过email分组，同时获去最小id。
* 使用not in  删除不是最小id的元素

错误示范 ： **MySQL 不允许在 `DELETE` 或 `UPDATE` 语句的子查询中直接引用目标表**。为了解决这个问题，我们需要将子查询的结果包装在一个临时表中。

```sql
delete from Person
where id not in
(
    select min(id) as id
    from Person
    group by email
)

```

正确：当作临时表，获取数据元素

```sql
delete from Person
where id not in
(
    select t.id
    from 
         (
          select min(id) as id
            from Person
             group by email
         ) as t 
)
```



## [176. 第二高的薪水 - 力扣（LeetCode）](https://leetcode.cn/problems/second-highest-salary/?envType=study-plan-v2&envId=sql-free-50)

题目描述：

查找第二高的薪水，没有第二高就是null

思路

* 根据薪水分组+降序排序
* limit +offset + ifnull

```sql
# Write your MySQL query statement below
select 
ifnull(
    (
      select salary
      from Employee
      group by salary
      order by salary desc
      limit 1 offset 1
    )
, null
) as SecondHighestSalary
```

## [1484. 按日期分组销售产品 - 力扣（LeetCode）](https://leetcode.cn/problems/group-sold-products-by-the-date/description/?envType=study-plan-v2&envId=sql-free-50)

题目描述：

查找出相同日期下，卖货的数量，以及数量名称的集合（按字典序排序），总体按日期排序，没有主键。

思路

* 根据日期分组排序，查出当天的，卖货数量（注意去重）。
* 商品名称集合要拼接，排序拼接,->想到了局部排序-但是最总还是要拼接

最终思路

* 根据日期分组排序，对产品去重，获得每组的数据，然后通过group_concat 对分组后不同行的字段进行拼接



tips: 函数 row_number() over (partition by coumle1 order by coumle2 desc) 局部排序给局部1，2，3排序

学习了更好的解法： 

将多行数据合并为一个字符串。

```sql
GROUP_CONCAT(
    [DISTINCT] 列名 
    [ORDER BY 排序字段 [ASC|DESC]]
    [SEPARATOR '分隔符']
)
```

```sql
select sell_date ,
count(distinct product) as num_sold ,
group_concat(distinct product order by product asc separator ',') as products
from Activities
group by sell_date

```



## [1327. 列出指定时间段内所有的下单产品 - 力扣（LeetCode）](https://leetcode.cn/problems/list-the-products-ordered-in-a-period/description/?envType=study-plan-v2&envId=sql-free-50)

题目表述：

一个商品表，一个订单表，找出指定时间前，卖出商品超过100 的商品名称和商品数量

思路

* 连表，过滤出指定时间前的数据
* 根据商品id 分组计算出售商品数量
* 子查询过滤出商品超100的数量 ---》优化，直接having

```sql
# Write your MySQL query statement below
select product_name,
sum(unit) as unit
from Products p
join Orders o
on p.product_id = o.product_id
where order_date <= '2020-02-29' and order_date >= '2020-02-01'
group by p.product_id
having unit >=100
```

