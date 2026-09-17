package com.leap.myapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreeterTest {

    @Test 
    void greetsTheWorld() {
        Greeter greeter = new Greeter();

        assertEquals("Hello World!", greeter.greet());
    }

    @Test 
    void greetsAPersonByName() {
        Greeter greeter = new Greeter();
        assertEquals("Hello, Pat!", greeter.greet("Pat"));
    }
    
}
