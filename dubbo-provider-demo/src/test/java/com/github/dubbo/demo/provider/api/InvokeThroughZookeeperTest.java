package com.github.dubbo.demo.provider.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.fastjson.JSON;
import com.github.dubbo.demo.facade.api.HelloService;
import com.github.dubbo.demo.facade.api.ValidationService;
import com.github.dubbo.demo.facade.bean.request.ValidationParameter;
import com.github.dubbo.demo.facade.bean.response.BaseResponse;
import com.github.dubbo.demo.facade.common.enums.ResponseCode;

/**
 * 直接注册到注册中心进行服务调用测试
 */
public class InvokeThroughZookeeperTest {
    
    private static final ZoneId ZONE = ZoneId.systemDefault();

    private static final String NAME = "SWIFT";
    private static final String APP_NAME = "dubbo-demo-consumer";
    private static final String DUBBO_REGISTRY_URL = "zookeeper://127.0.0.1:2181";
    private static final String VERSION1 = "1.0.0";
    private static final String VERSION2 = "2.0.0";
    private static final String WRONG_VERSION = "3.0.0";

    @Test
    void testHello() {
        HelloService helloService = buildReferenceFromZookeeper(HelloService.class, VERSION2, true);
        String hello = helloService.hello(NAME).getData();
        System.out.println(hello);
        assertEquals(true, hello.contains(NAME));
    }

    @Test
    void testWrongVersion() {
        HelloService helloService = buildReferenceFromZookeeper(HelloService.class, WRONG_VERSION, false);
        assertThrows(RpcException.class, () -> helloService.hello(NAME));
    }

    @Test
    void testIllegalState() {
        assertThrows(IllegalStateException.class, () -> buildReferenceFromZookeeper(HelloService.class, WRONG_VERSION, true));
    }
    
    @Test
    void testAddValidation() {
        ValidationService validationService = buildReferenceFromZookeeper(ValidationService.class, VERSION1, true);
        
        ValidationParameter parameter = new ValidationParameter();
        parameter.setAge(18);
        parameter.setName("tom");
        parameter.setEmail("swift@qq.com");
        parameter.setLoginDate(new Date());
        Instant instant = LocalDate.now().plusDays(1L).atStartOfDay(ZONE).toInstant();
        parameter.setExpiryDate(Date.from(instant));
        
        BaseResponse<String> response = validationService.addBean(parameter);
        System.out.println(JSON.toJSONString(response));
        assertEquals(ResponseCode.SUCCESS.getCode(), response.getCode());
    }
    
    @Test
    void testUpdateValidation() {
        ValidationService validationService = buildReferenceFromZookeeper(ValidationService.class, VERSION1, true);
        
        ValidationParameter parameter = new ValidationParameter();
        parameter.setId(1024L);
        parameter.setAge(18);
        parameter.setName("tom");
        parameter.setEmail("swift@qq.com");
        parameter.setLoginDate(new Date());
        Instant instant = LocalDate.now().plusDays(1L).atStartOfDay(ZONE).toInstant();
        parameter.setExpiryDate(Date.from(instant));
        
        BaseResponse<String> response = validationService.updateBean(parameter);
        System.out.println(JSON.toJSONString(response));
        assertEquals(ResponseCode.SUCCESS.getCode(), response.getCode());
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
