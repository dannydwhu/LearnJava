package org.java.learn.decrypt;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {

    public static void main(String[] arg){
        ExecutorService executor = Executors.newCachedThreadPool();
        Future f = executor.submit(new Callable<Object>() {

            @Override
            public Object call() throws Exception {
                return null;
            }
        });
    }
}
