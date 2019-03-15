package org.java.learn.nio.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public interface Promise<V> extends Future<V> {

    Promise<V> done(DoneCallback<V> callable);
    Promise<V> fail(Callable callable);

    <O> Promise<O> pipe(Callable <O>callable);
}
