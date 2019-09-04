package com.github.dubbo.demo.provider.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.github.dubbo.demo.facade.api.HelloService;

/**
 * 直连调用服务
 */
public class DirectInvokeProviderTest {

    private static final String APP_NAME = "dubbo-demo-consumer";
    private static final String PROVIDER_URL = "dubbo://127.0.0.1:12345";
    private static final String VERSION = "1.0.0";

    private static HelloService helloService;

    @Test
    void testHello() {
        String name = "swift";
        String hello = helloService.hello(name);
        System.out.println(hello);
        assertEquals(true, hello.contains(name));
    }

    @BeforeAll
    public static void buildReferenceFromProvider() {
        ReferenceConfig<HelloService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig(APP_NAME));
        reference.setUrl(PROVIDER_URL);
        reference.setInterface(HelloService.class);
        reference.setVersion(VERSION);
        helloService = reference.get();
    }

}
