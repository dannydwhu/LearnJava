package org.java.learn.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestReetrantLock {
    public static void test(){
        Lock l = new ReentrantLock();

        CyclicBarrier cb = new CyclicBarrier(0);
        try {
            cb.await();
            System.out.println("await 1");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        try {
            cb.await();
            System.out.println("await 2");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("complete");

    }

    public static void main(String[] args){
        test();
    }

}
