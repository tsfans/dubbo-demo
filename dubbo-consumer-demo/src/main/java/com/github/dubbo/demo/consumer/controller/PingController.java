package com.github.dubbo.demo.consumer.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.dubbo.demo.facade.api.HelloService;
import com.github.dubbo.demo.facade.bean.response.BaseResponse;

@RestController
public class PingController {

    @Reference(version = "1.0.0", url = "dubbo://127.0.0.1:12345")
    private HelloService helloService;

    @GetMapping("/ping")
    public BaseResponse<String> ping() {
        return BaseResponse.success("pong");
    }

    @GetMapping("/hello")
    public BaseResponse<String> hello(String name) {
        return BaseResponse.success(helloService.hello(name));
    }
}
