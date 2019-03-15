package org.java.learn.java8.interfaces;

public class OverrideableImpl implements Defaultable {

    @Override
    public String notRequired(){
        return "not required override";
    }
    
    public static void main(String[] args){
        Defaultable d = DefaultFactory.create(DefaultableImpl::new);
        
        System.out.println(d.notRequired());
        
        d = DefaultFactory.create(OverrideableImpl::new);
        System.out.println(d.notRequired());
    }
}
