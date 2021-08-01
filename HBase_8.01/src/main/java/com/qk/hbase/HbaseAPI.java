package com.qk.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class HbaseAPI {

    //判断表是否存在
    public static boolean isTableExist(HBaseAdmin admin, String tableName) throws IOException {
        try{
            boolean ans = false;
            if (admin.tableExists(TableName.valueOf(tableName))){
                System.out.println("table " + tableName + " exists");
                ans = true;
            }else{
                System.out.println("table " + tableName + " not exists");
                ans = false;
            }
            return ans;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    //创建表
    public static void createTable(HBaseAdmin admin, String tableName, String... cfs) throws IOException {
        if(isTableExist(admin, tableName)){
            System.out.println(tableName + " exist");
            return;
        }

        TableDescriptorBuilder table = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName));
        for(String cf:cfs) {
            table.setColumnFamily(ColumnFamilyDescriptorBuilder.of(cf));
        }
        TableDescriptor descriptor = table.build();
        admin.createTable(descriptor);
        System.out.println("table " + tableName + " create successfully");
    }

    //删除表
    public static void dropTable(HBaseAdmin admin, String tableName) throws IOException {
        if(!isTableExist(admin, tableName)){
            System.out.println(tableName + " not exist");
            return;
        }
        admin.disableTable(TableName.valueOf(tableName));
        admin.deleteTable(TableName.valueOf(tableName));
    }

    //创建命名空间
    public static void createNameSpace(HBaseAdmin admin, String ns) throws IOException {
        NamespaceDescriptor namespaceDescriptor = NamespaceDescriptor.create(ns).build();
        admin.createNamespace(namespaceDescriptor);
    }

    //存入值
    public static void putData(Connection connection, String tableName, String rowKey, String cf,
                               String cn, String value) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));

        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn), Bytes.toBytes(value));
        table.put(put);
        System.out.println("Add value:" + value + " in cn:" + cn + " in cf:" + cf
                + " in table:" + tableName + " successfully");

        table.close();

    }

    //查询值
    public static void getData(Connection connection, String tableName, String rowKey) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));

        Get get = new Get(Bytes.toBytes(rowKey));

        Result result = table.get(get);

        for(Cell cell: result.rawCells()){
            System.out.println("CF:" + Bytes.toString(CellUtil.cloneFamily(cell)) +
                    ", CN:" + Bytes.toString(CellUtil.cloneQualifier(cell)) +
                    ", Value:" + Bytes.toString(CellUtil.cloneValue(cell)));
        }

        table.close();
    }

    public static void main(String[] args) throws IOException {
        //配置
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "47.101.204.23:2181,47.101.216.12:2181,47.101.206.249:2181");
        Connection connection = ConnectionFactory.createConnection(conf);
        HBaseAdmin admin = (HBaseAdmin) connection.getAdmin();

        String tableName = "qiankun:student";
        createNameSpace(admin, "qiankun");
        createTable(admin, tableName, "info", "score");
        putData(connection, tableName, "qiankun", "info", "student_id", "G20210735010384");
        putData(connection, tableName, "qiankun", "info", "class", "4");
        putData(connection, tableName, "qiankun", "score", "understanding", "100");
        putData(connection, tableName, "qiankun", "score", "programmimg", "100");

        getData(connection, tableName, "qiankun");

        admin.close();
        connection.close();
    }
}
