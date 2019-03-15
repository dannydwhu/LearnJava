package org.java.learn.javascript;

import java.util.Base64;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class TestScript {

    public static void main(String[] args) throws ScriptException{
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName( "JavaScript" );
                 
        System.out.println( engine.getClass().getName() );
        System.out.println( "Result:" + engine.eval( "function f() { return 1; }; f() + 1;" ) );
        
        final String text = "Base64";
        final String encoded = Base64.getEncoder().encodeToString(text.getBytes());
        System.out.println(encoded);
        
        System.out.println(new String(Base64.getDecoder().decode(encoded)));
    }
}
