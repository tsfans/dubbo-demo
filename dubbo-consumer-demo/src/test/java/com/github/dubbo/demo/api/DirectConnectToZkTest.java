package com.github.dubbo.demo.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.github.dubbo.demo.facade.api.HelloService;

/**
 * 直接注册到注册中心进行服务调用测试
 */
public class DirectConnectToZkTest{

    private static final String APP_NAME = "dubbo-demo-consumer";
    private static final String DUBBO_REGISTRY_URL = "zookeeper://127.0.0.1:2181";

    private static HelloService helloService;

    @Test
    void testHello() {
        String name = "swift";
        String hello = helloService.hello(name);
        assertEquals(true, hello.contains(name));
    }
    
    @BeforeAll
    public static void buildReference() {
        ReferenceConfig<HelloService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig(APP_NAME));
        reference.setRegistry(new RegistryConfig(DUBBO_REGISTRY_URL));
        reference.setInterface(HelloService.class);
        reference.setVersion("1.0.0");
        helloService = reference.get();
    }
}
