package org.java.learn.nio.concurrent;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.FutureTask;

public class DefaultPromise<V> extends FutureTask<V> implements Promise<V> {

    private List<DoneCallback<V>> doneCallableList = null;
    private V value;

    public DefaultPromise(){
        super(new Callable() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });
    }

    public void setValue(V v){
        this.value = v;

        try{
            if(null != this.doneCallableList){
                for(DoneCallback<V> c : doneCallableList){
                    c.onDone(this.value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.set(v);
        }
    }

    @Override
    public Promise done(DoneCallback callable) {
        if(super.isDone()){
            try {
                callable.onDone(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if(doneCallableList == null){
                doneCallableList = new CopyOnWriteArrayList<>();
            }
            doneCallableList.add(callable);
        }
        return this;
    }

    @Override
    public Promise fail(Callable callable) {
        return null;
    }

    @Override
    public Promise pipe(Callable callable) {
        return null;
    }
}
