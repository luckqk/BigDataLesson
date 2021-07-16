操作步骤
===
~~~~
1. 生成jar包
2. hadoop fs -mkdir /user/student/qk/
3. hadoop fs -put  文件路径 hadoop文件夹路径
4. 清除已有的output文件夹， hadoop fs -rm -r -skipTrash /user/student/qk/output/
5. 提交hadoop任务,yarn jar jar包 类名 -Dmapreduce.job.reduces=1 input output
6. 查看生成文件 hadoop fs -ls hadoop文件夹路径