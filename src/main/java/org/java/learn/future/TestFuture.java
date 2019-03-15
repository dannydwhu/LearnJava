package org.java.learn.future;

import java.util.concurrent.FutureTask;

public class TestFuture {

    public static void testFuture(){
        FutureTask futureTask ;

        throw new RuntimeException("as");
    }

    public static void main(String[] args){
        testFuture();


        System.out.println("after exception");
    }
}
