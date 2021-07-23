Hadoop RPC作业步骤
===
~~~~
1. 定义协议，描述提供的接口或者功能(interface)
2. Server端实现协议，返回版本号(版本号变量名一定要用versionID)
3. 实现server类
server端运行结果
![image](https://github.com/luckqk/BigDataLesson/blob/main/RPC_7.18/server.png)
4. 实现client类，调用设定的服务
client端运行结果
![image](https://github.com/luckqk/BigDataLesson/blob/main/RPC_7.18/client.png)
