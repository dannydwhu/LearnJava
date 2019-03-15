package org.java.learn.clazz;

public class Test {

    public static void main(String[] arg) throws ClassNotFoundException{
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println(loader);
        System.out.println(loader.getParent());
        System.out.println(loader.getParent().getParent());
        
        loader.loadClass("org.java.learn.clazz.Test");
    }
}
