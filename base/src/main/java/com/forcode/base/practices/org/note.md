#### 1.查询所有子孙部门
```roomsql
select * from org_info where left > @left and right < @right order by left
```
#### 2.查询直接子部门, 不含孙子部门
```roomsql
select * from org_info where left > @left AND right < @right AND level = @level+1;
```
#### 3.查询子孙部门总数
```text
count = (@right - @left - 1) / 2
```
#### 4.判断是否为叶子节点
```text
叶子节点: (@right - 1) == @left
```
#### 5.查询祖链路径
```roomsql
select * from org_info where left < @left and right > @right order by left asc;
```

#### 6.新增节点
```roomsql
update org_info set left = left + 2 WHERE left > @left;
update org_info set right = right + 2 WHERE right >= @left;
insert into org_info(left, right) value (@left, @right);
```
#### 7.删除节点
```roomsql
update org_info set left = left - 2 where left > @left;
update org_info set right = right - 2 where right > @left;
delete from org_info where left = @left and right = @right;
```
