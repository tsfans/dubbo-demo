package com.github.dubbo.demo.provider.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.rpc.RpcException;
import com.github.dubbo.demo.facade.api.HelloService;

/**
 * 直连调用服务
 */
public class DirectInvokeProviderTest {

    private static final String NAME = "SWIFT";
    private static final String APP_NAME = "dubbo-demo-consumer";
    private static final String PROVIDER_URL = "dubbo://127.0.0.1:12345";
    private static final String VERSION1 = "1.0.0";
    private static final String VERSION2 = "2.0.0";
    private static final String WRONG_VERSION = "3.0.0";

    private HelloService helloService;

    @Test
    void testHello() {
        buildReferenceFromProvider(VERSION1);
        String hello = helloService.hello(NAME).getData();
        System.out.println(hello);
        assertEquals(true, hello.contains(NAME));
    }
    
    @Test
    void testWrongVersion() {
        buildReferenceFromProvider(WRONG_VERSION);
        assertThrows(RpcException.class, () -> helloService.hello(NAME));
    }

    private void buildReferenceFromProvider(String version) {
        ReferenceConfig<HelloService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig(APP_NAME));
        reference.setUrl(PROVIDER_URL);
        reference.setInterface(HelloService.class);
        reference.setVersion(version);
        helloService = reference.get();
    }

}
