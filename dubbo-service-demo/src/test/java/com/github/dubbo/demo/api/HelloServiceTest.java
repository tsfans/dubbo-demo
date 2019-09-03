package com.github.dubbo.demo.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.github.dubbo.demo.facade.api.HelloService;

public class HelloServiceTest {

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
