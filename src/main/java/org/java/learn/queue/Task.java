package org.java.learn.queue;

import java.io.Serializable;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class Task<T> implements Delayed, Serializable {

    private final long timeout;
    private final T task;
    private final long n;
    private static final AtomicLong atomic = new AtomicLong(0);

    public Task(long timeout, T t){
        this.task = t;
        this.timeout = timeout;
        this.n = atomic.getAndIncrement();
    }


    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.timeout - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if(this == o)
            return 0;

        if(o instanceof Task){
            Task x = (Task) o;
            long diff = this.timeout - x.timeout;
            return diff < 0 ? -1 : ( diff > 0 ? 1 : 1);
        }
        long d = (getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
    }

}
