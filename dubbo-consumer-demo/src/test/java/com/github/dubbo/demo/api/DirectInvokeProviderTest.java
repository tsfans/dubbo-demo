package com.github.dubbo.demo.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.dubbo.demo.DubboDemoApplicationTests;
import com.github.dubbo.demo.facade.api.HelloService;

/**
 * 直连调用服务
 */
public class DirectInvokeProviderTest extends DubboDemoApplicationTests {

    @Reference(version = "1.0.0", url = "dubbo://127.0.0.1:12345")
    private HelloService helloService;

    @Test
    void testHello() {
        String name = "swift";
        String hello = helloService.hello(name);
        assertEquals(true, hello.contains(name));
    }

}
