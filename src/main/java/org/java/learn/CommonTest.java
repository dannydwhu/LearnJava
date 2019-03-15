package org.java.learn;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class CommonTest {

    public static void main(String[] args){
        
//        HashMap m = new HashMap();
//        Stack s = new Stack();
//        s.add(1);
//        s.add(2);
//        
//        System.out.println(s.pop() + " " + s.pop());
//        
//        String s1 = "he"+"llo";
//        String s2 = "hello";
//        if(s2=="hello"){
//
//            System.out.println("s1 = \"hello\"");
//
//          }else{
//
//           System.out.println("s1 !=hello");
//
//          }
        
//        int i =0;
//        for(int j=0;j<10;j++){
//            i=i++;
//        }
//        
//        System.out.println(i);
//        ConcurrentHashMap cm = new ConcurrentHashMap();
//
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ArrayBlockingQueue blockingQueue;
        ThreadPoolExecutor tpe;
        AbstractQueuedSynchronizer qas;
        AtomicInteger ai = new AtomicInteger();
        
        AbstractQueuedSynchronizer aqs ;
        
        ReentrantLock lock;

        ArrayList linkedList = new ArrayList();
        new LinkedList().get(1);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        List list = linkedList.subList(0,3);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        list.add(5);
        list = list;

        TreeSet<String> treeSet =new TreeSet<>((String a, String b)->a.compareTo(b));

        System.out.println(linkedList.size());

//        LinkedList linkedList = new LinkedList();
//        linkedList.add(1);
//        linkedList.add(2);
//        linkedList.add(3);
//        linkedList.add(4);
//        List list = linkedList.subList(0,3);
//        Iterator it = list.iterator();
//        while (it.hasNext()) {
//            System.out.println(it.next());
//        }
//        System.out.println(list.size());
//        LockSupport.park();
//        LockSupport.unpark(new Thread());

        ConcurrentHashMap hashMap;
//        ai.compareAndSet(1, 2);
//        
//        String s1 = "aaa";
//        String s2 = "aaa";
//        System.out.println(s1.hashCode());
//        int h;
//        System.out.println((h = s1.hashCode()) ^ h >>>16);
//        Map m = new HashMap();
//        m = new ConcurrentHashMap();
//        Queue q = new ConcurrentLinkedQueue();
//        
//        Stack stack = new Stack();
//        
//        HashMap hMap = new HashMap();
//        
//        ThreadLocal tl = new ThreadLocal();
//        
//        ExecutorService pool = Executors.newFixedThreadPool(1); 
//                //Executors.newCachedThreadPool();
//        for(int i=0; i<10; i++){
//            pool.execute(new MyThread(i+""));
//        };
        
        
//        ThreadLocal<Integer> tl = new ThreadLocal<>();
//        tl.set(12);
//        
//        int COUNT_BITS = Integer.SIZE - 3;
//        int RUNNING    = -1 << COUNT_BITS;
//        int SHUTDOWN   =  0 << COUNT_BITS;
//        int STOP       =  1 << COUNT_BITS;
//        int TIDYING    =  2 << COUNT_BITS;
//        int TERMINATED =  3 << COUNT_BITS;
//        System.out.println("RUNNING" + RUNNING);
//        System.out.println("SHUTDOWN" + SHUTDOWN);
//        System.out.println("STOP" + STOP);
//        System.out.println("TIDYING" + TIDYING);
//        System.out.println("TERMINATED" + TERMINATED);
        
//        System.out.println(( 1L<<31) -1);
        System.out.println(Integer.toBinaryString(-2));
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-3));
        System.out.println(-2>>>1);
        System.out.println(-1>>>1);
        System.out.println(-1<<2);
    }
    
    static class MyThread implements Runnable {
        
        private String name;
        
        public MyThread(String name){
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("run " + name);
            try {
                Thread.sleep(100000l);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("end " + name);
        }
        
    }
}
