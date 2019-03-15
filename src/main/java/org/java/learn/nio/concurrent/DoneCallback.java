package org.java.learn.nio.concurrent;

public interface DoneCallback<V> {
    void onDone(V v);
}
