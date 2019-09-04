package com.github.dubbo.demo.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.dubbo.demo.DubboDemoApplicationTests;
import com.github.dubbo.demo.facade.api.HelloService;

/**
 * 依托spring注册到注册中心进行测试
 */
@ActiveProfiles("provider")
public class HelloServiceTest extends DubboDemoApplicationTests {

    @Reference(version = "1.0.0")
    private HelloService helloService;

    @Test
    void testHello() {
        String name = "swift";
        String hello = helloService.hello(name);
        assertEquals(true, hello.contains(name));
    }
}
