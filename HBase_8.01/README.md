<big>**HBase作业步骤**</big>

1. 本地环境运行，需要在 C:\Windows\System32\drivers\etc 中的host中配置一下域名解析,避免运行时遇到域名解析异常
2. 建立命名空间
3. 建立表(需要命名空间名、表名、列族名)
4. 存入值(需要表名、行键、列族名、列限定符、值)
5. 获取值(用以验证整个操作是否成功)
6. 删除表(dropTable)
<br>
结果<br>

![Image text](https://github.com/luckqk/BigDataLesson/blob/main/HBase_8.01/%E7%BB%93%E6%9E%9C.png)
