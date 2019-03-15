package org.java.learn.other;

import java.lang.ref.WeakReference;

public class WeakReferenceTest {
    
    public static void main(String args[]){
        A a = new A();
        a.setA("Hello");
        
        WeakReference<A> weak = new WeakReference<A>(a);
        a = null;
        int i = 0;
        while(weak.get() != null){
            System.out.println(String.format("Get str from object of WeakReference: %s, count: %d", weak.get().getA(), ++i));  
            if (i % 10 == 0) {  
                System.gc();  
                System.out.println("System.gc() was invoked!");  
            }  
            try {  
                Thread.sleep(500);  
            } catch (InterruptedException e) {  
  
            } 
        }
        System.out.println("JVM clear");
    }
}
