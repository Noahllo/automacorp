package com.emse.spring.automacorp.hello;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public class ConsoleGreetingService implements GreetingService
{
    public void greet(String name)
    {
        System.out.println("Hello, " + name + "!");
    }
}