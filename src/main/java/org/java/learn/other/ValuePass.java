package org.java.learn.other;

import java.util.HashMap;
import java.util.Map;

public class ValuePass {

    public static void f1(int a){
        a = 2;
    }
    
    public static void f2(Map m){
        m.put(1, "one");
    }
    
    public static void main(String[] args){
        int a =1;
        f1(a);
        System.out.println(a);
        
        Map m = new HashMap();
        m.put(1, "two");
        f2(m);
        System.out.println(m.get(1));
    }
}
