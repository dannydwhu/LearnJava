package org.java.learn.nio;

import java.util.concurrent.locks.LockSupport;

public class TestNettyServer {

    public void start(){
        TcpEndPoint server = new TcpEndPoint();
        server.setPort(81);
        server.start();

    }

    public static void main(String[] args){
        TestNettyServer server = new TestNettyServer();
        server.start();
    }
}
