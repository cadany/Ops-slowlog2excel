
#功能说明

实现将MYSQL慢SQL日志转换为EXCEL文件，slow log格式固定

```
# Time: 2022-02-23T02:19:24.309555Z
# User@Host: xxx[xxxx] @  [x.x.x.x]  Id: xxx
# Query_time: xxxx  Lock_time: xxx Rows_sent: xxx  Rows_examined: xxxx
SQL statement

```

当`SQL statement`为多行时合并为一个单元格式内容。
