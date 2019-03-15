package org.java.learn.java8.interfaces;

public interface Defaultable {

    default String notRequired(){
        return "not required";
    }
}
