#虚谷数据库使用C3p0连接池的demo程序

1.  c3p0-config.xml 为C3P0和虚谷数据库的连接配置文件
2.  ConnectionPool类实现了和虚谷数据库建立连接。
3.  C3p0Test类实现了通过C3P0和虚谷数据库建立连接后，使用虚谷JDBC执行DDL、DML语句，批量插入等操作。
4.  target/lib为虚谷JDBC驱动
