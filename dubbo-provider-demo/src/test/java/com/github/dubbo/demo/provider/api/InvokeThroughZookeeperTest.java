package com.github.dubbo.demo.provider.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.RpcException;
import com.github.dubbo.demo.facade.api.HelloService;

/**
 * 直接注册到注册中心进行服务调用测试
 */
public class InvokeThroughZookeeperTest {

    private static final String NAME = "SWIFT";
    private static final String APP_NAME = "dubbo-demo-consumer";
    private static final String DUBBO_REGISTRY_URL = "zookeeper://127.0.0.1:2181";
    private static final String VERSION1 = "1.0.0";
    private static final String VERSION2 = "2.0.0";
    private static final String WRONG_VERSION = "3.0.0";

    private HelloService helloService;

    @Test
    void testHello() {
        buildReferenceFromZookeeper(VERSION2, true);
        String hello = helloService.hello(NAME);
        System.out.println(hello);
        assertEquals(true, hello.contains(NAME));
    }

    @Test
    void testWrongVersion() {
        buildReferenceFromZookeeper(WRONG_VERSION, false);
        assertThrows(RpcException.class, () -> helloService.hello(NAME));
    }

    @Test
    void testIllegalState() {
        assertThrows(IllegalStateException.class, () -> buildReferenceFromZookeeper(WRONG_VERSION, true));
    }

    private void buildReferenceFromZookeeper(String version, Boolean isCheck) {
        ReferenceConfig<HelloService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig(APP_NAME));
        reference.setRegistry(new RegistryConfig(DUBBO_REGISTRY_URL));
        reference.setInterface(HelloService.class);
        reference.setCheck(isCheck);
        reference.setVersion(version);
        helloService = reference.get();
    }

}
