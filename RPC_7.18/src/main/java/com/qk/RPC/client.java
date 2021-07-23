package com.qk.RPC;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

public class client {
    public static void main(String[] args) {
        try{
            MyInterface proxy = RPC.getProxy(MyInterface.class, 1L,
                    new InetSocketAddress("localhost",12345), new Configuration());
            String res1 = proxy.findName("G20210735010384");
            System.out.println(res1);
            String res2 = proxy.findName("20210000000000");
            System.out.println(res2);
            String res3 = proxy.findName("20210123456789");
            System.out.println(res3);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
