package com.github.dubbo.demo.provider.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.fastjson.JSON;
import com.github.dubbo.demo.facade.api.HelloService;
import com.github.dubbo.demo.facade.api.RpcContextService;
import com.github.dubbo.demo.facade.api.ValidationService;
import com.github.dubbo.demo.facade.bean.request.ValidationParameter;
import com.github.dubbo.demo.facade.bean.response.BaseResponse;
import com.github.dubbo.demo.facade.common.enums.ResponseCode;

@DisplayName("直连调用服务")
public class DirectInvokeProviderTest {

    private static final String NAME = "SWIFT";
    private static final String APP_NAME = "dubbo-demo-consumer";
    private static final String PROVIDER_URL = "dubbo://127.0.0.1:12345";
    private static final String VERSION1 = "1.0.0";
    private static final String VERSION2 = "2.0.0";
    private static final String WRONG_VERSION = "3.0.0";
    private static final ZoneId ZONE = ZoneId.systemDefault();

    @Test
    @DisplayName("测试VERSION1的HelloService")
    void testHelloV1() {
        HelloService helloService = buildReferenceFromProvider(HelloService.class, VERSION1);
        String hello = helloService.hello(NAME).getData();
        System.out.println(hello);
        assertEquals(true, hello.contains(NAME) && hello.contains(VERSION1));
    }

    @Test
    @DisplayName("测试VERSION2的HelloService")
    void testHelloV2() {
        HelloService helloService = buildReferenceFromProvider(HelloService.class, VERSION2);
        String hello = helloService.hello(NAME).getData();
        System.out.println(hello);
        assertEquals(true, hello.contains(NAME) && hello.contains(VERSION2));
    }

    @Test
    @DisplayName("测试VERSION错误时的HelloService")
    void testWrongVersion() {
        HelloService helloService = buildReferenceFromProvider(HelloService.class, WRONG_VERSION);
        assertThrows(RpcException.class, () -> helloService.hello(NAME));
    }

    @Test
    @DisplayName("测试新增时的参数校验")
    void testAddValidation() {
        ValidationService validationService = buildReferenceFromProvider(ValidationService.class, VERSION1);

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
    @DisplayName("测试更新时的参数校验")
    void testUpdateValidation() {
        ValidationService validationService = buildReferenceFromProvider(ValidationService.class, VERSION1);

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

    @Test
    @DisplayName("测试上下文隐式传参")
    void testRpcContext() {
        RpcContextService rpcContextService = buildReferenceFromProvider(RpcContextService.class, VERSION1);
        RpcContext.getContext().setAttachment("ping", "pong");
        BaseResponse<String> response = rpcContextService.rpcContext();
        assertEquals("pong", response.getData());
    }
    
    @Test
    @DisplayName("测试返回DTO未序列化")
    void testUnserializable() {
        RpcContextService rpcContextService = buildReferenceFromProvider(RpcContextService.class, VERSION1);
        assertThrows(RpcException.class, () -> rpcContextService.unSerializable());
    }

    private <T> T buildReferenceFromProvider(Class<T> clazz, String version) {
        ReferenceConfig<T> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig(APP_NAME));
        reference.setUrl(PROVIDER_URL);
        reference.setInterface(clazz);
        reference.setVersion(version);
        return reference.get();
    }

}
