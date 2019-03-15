package org.java.learn.nio.framework;

public interface EndPoint {

    void start();
    void stop();
    int getPort();
    void setPort(int port);

}
