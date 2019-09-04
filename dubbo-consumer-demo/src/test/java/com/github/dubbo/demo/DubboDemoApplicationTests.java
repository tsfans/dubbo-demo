package com.github.dubbo.demo;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.dubbo.demo.consumer.ConsumerApp;
import com.github.dubbo.demo.facade.bean.response.BaseResponse;
import com.github.dubbo.demo.facade.common.enums.ResponseCode;


@SpringBootTest(classes = {ConsumerApp.class})
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@DisplayName("基础测试类，继承此类可获取spring测试框架支持")
public class DubboDemoApplicationTests {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    @DisplayName("Test ping controller")
    void ping() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/ping");
        MvcResult result = mockMvc.perform(req).andReturn();
        int httpStatus = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();
        BaseResponse<String> response = JSON.parseObject(content, new TypeReference<BaseResponse<String>>() {});
        assertAll("Test Passed", () -> assertEquals(200, httpStatus),
            () -> assertEquals(ResponseCode.SUCCESS.getCode(), response.getCode()),
            () -> assertEquals(ResponseCode.SUCCESS.getMsg(), response.getMsg()),
            () -> assertEquals("pong", response.getData()));
    }

}
