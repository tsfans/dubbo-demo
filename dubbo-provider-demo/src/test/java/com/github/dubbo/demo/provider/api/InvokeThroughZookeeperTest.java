package com.github.dubbo.demo.provider.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.github.dubbo.demo.facade.api.HelloService;

@DisplayName("直接注册到注册中心进行服务调用测试")
public class InvokeThroughZookeeperTest {

    private static final String NAME = "SWIFT";
    private static final String APP_NAME = "dubbo-demo-consumer";
    private static final String DUBBO_REGISTRY_URL = "zookeeper://127.0.0.1:2181";
    private static final String VERSION1 = "1.0.0";
    private static final String VERSION2 = "2.0.0";
    private static final String WRONG_VERSION = "3.0.0";

    @Test
    @DisplayName("测试VERSION1的HelloService")
    void testHelloV1() {
        HelloService helloService = buildReferenceFromZookeeper(HelloService.class, VERSION1, false);
        String hello = helloService.hello(NAME).getData();
        System.out.println(hello);
        assertEquals(true, hello.contains(NAME) && hello.contains(VERSION1));
    }

    @Test
    @DisplayName("测试VERSION2的HelloService")
    void testHelloV2() {
        HelloService helloService = buildReferenceFromZookeeper(HelloService.class, VERSION2, false);
        String hello = helloService.hello(NAME).getData();
        System.out.println(hello);
        assertEquals(true, hello.contains(NAME) && hello.contains(VERSION2));
    }

    @Test
    @DisplayName("测试VERSION错误时的HelloService")
    void testWrongVersion() {
        assertThrows(IllegalStateException.class, () -> buildReferenceFromZookeeper(HelloService.class, WRONG_VERSION, true));
    }

    private <T> T buildReferenceFromZookeeper(Class<T> clazz, String version, Boolean isCheck) {
        ReferenceConfig<T> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig(APP_NAME));
        reference.setRegistry(new RegistryConfig(DUBBO_REGISTRY_URL));
        reference.setInterface(clazz);
        reference.setCheck(isCheck);
        reference.setVersion(version);
        return reference.get();
    }

}
