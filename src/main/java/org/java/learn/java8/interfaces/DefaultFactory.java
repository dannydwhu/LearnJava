package org.java.learn.java8.interfaces;

import java.util.function.Supplier;

public interface DefaultFactory {

    static Defaultable create(Supplier<Defaultable> supplier){
        return supplier.get();
    }
}
