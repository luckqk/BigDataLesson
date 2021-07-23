package com.qk.RPC;

import org.apache.hadoop.ipc.VersionedProtocol;

public interface MyInterface extends VersionedProtocol {
    long versionID = 1L;
    String findName(String num);
}
