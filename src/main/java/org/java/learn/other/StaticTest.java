package org.java.learn.other;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StaticTest {

    
    private static int v;
    static{
        System.out.println(v +=1);
        System.out.println(v +=1);
    }
    
    public StaticTest deepClone() throws Exception{
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(this);
        
        
        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bi);
        
        return (StaticTest) ois.readObject();
    }
    
}
