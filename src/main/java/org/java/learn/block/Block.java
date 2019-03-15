package org.java.learn.block;

import java.util.concurrent.locks.LockSupport;
import java.util.function.Predicate;

public class Block {
    static{
        LockSupport.park();
        
        Predicate p = t->{
            
            return false;
        };
    }
    

}
