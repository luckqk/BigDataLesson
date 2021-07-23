package com.qk.RPC;

import org.apache.hadoop.ipc.ProtocolSignature;

import java.io.IOException;

public class MyInterfaceImpl implements MyInterface{
    @Override
    public String findName(String num) {
        if(num.equals("G20210735010384")){
            return "钱坤";
        }else if(num.equals("20210000000000")){
            return "null";
        }else if(num.equals("20210123456789")){
            return "心心";
        }else{
            return null;
        }
    }

    @Override
    public long getProtocolVersion(String s, long l) throws IOException {
        return MyInterface.versionID;
    }

    @Override
    public ProtocolSignature getProtocolSignature(String s, long l, int i) throws IOException {
        return null;
    }
}
