package com.github.dubbo.demo.provider.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.dubbo.demo.facade.api.HelloService;
import com.github.dubbo.demo.provider.ProviderAppTest;

/**
 * 依托spring注册到注册中心进行测试
 */
@ActiveProfiles("consumer")
public class HelloServiceTest extends ProviderAppTest {
    
    private static final String NAME = "SWIFT";

    @Reference(version = "${dubbo.service.version.v1}")
    private HelloService helloService;

    @Test
    void testHello() {
        String hello = helloService.hello(NAME).getData();
        System.out.println(hello);
        assertEquals(true, hello.contains(NAME));
    }
}
