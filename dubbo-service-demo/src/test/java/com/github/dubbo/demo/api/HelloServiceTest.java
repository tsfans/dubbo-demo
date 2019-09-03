package com.github.dubbo.demo.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import com.github.dubbo.demo.DubboDemoApplicationTests;
import com.github.dubbo.demo.facade.api.HelloService;

@ActiveProfiles("consumer")
public class HelloServiceTest extends DubboDemoApplicationTests {

    @Reference(version = "1.0.0")
    private HelloService helloService;

    @Test
    void testAutoReference() {
        String name = "swift";
        String hello = helloService.hello(name);
        assertEquals(true, hello.contains(name));
    }
}
