package org.java.learn.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTest {

    private static AtomicInteger count = new AtomicInteger();
    public static void main(String[] args){
        Executors.newCachedThreadPool();
        ExecutorService  pool = Executors.newFixedThreadPool(10);
        
        List<Future> f = new ArrayList<>();
        
        for(int i=0; i<1000; i++){
            Runnable r =  new Runnable(){

                @Override
                public void run() {
                    try {
                        Thread.sleep(10l);
                    } catch (InterruptedException e) {
                    }
                    
                    count.incrementAndGet();
                    System.out.println(count);
                }
               
            };
            pool.submit(r);
        }
        
        pool.shutdown();
        
        System.out.println("count: " + count);
    }
}
