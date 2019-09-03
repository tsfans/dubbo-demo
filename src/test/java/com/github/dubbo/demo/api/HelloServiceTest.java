package com.github.dubbo.demo.api;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.junit.BeforeClass;

public class HelloServiceTest {

    private static final String APP_NAME = "dubbo-demo-consumer";
    private static final String DEV_DUBBO_REGISTRY_URL = "zookeeper://127.0.0.1:2181";

    private static HelloService helloService;

    @BeforeClass
    public static void buildReference() {
        ReferenceConfig<HelloService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig(APP_NAME));
        reference.setRegistry(new RegistryConfig(DEV_DUBBO_REGISTRY_URL));
        reference.setInterface(HelloService.class);
        reference.setVersion("1.0.0");
        helloService = reference.get();
    }
}
