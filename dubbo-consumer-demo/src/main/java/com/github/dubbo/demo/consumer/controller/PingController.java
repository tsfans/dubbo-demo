package com.github.dubbo.demo.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.dubbo.demo.facade.api.HelloService;
import com.github.dubbo.demo.facade.bean.response.BaseResponse;

@RestController
public class PingController {

    @Reference(version = "${dubbo.service.version.v1}")
    private HelloService helloServiceV1;

    @Reference(version = "${dubbo.service.version.v2}")
    private HelloService helloServiceV2;

    @GetMapping("/ping")
    public BaseResponse<String> ping() {
        return BaseResponse.success("pong");
    }

    @GetMapping("/hello")
    public BaseResponse<String> hello(String name, String version) {
        if (version != null && version.equalsIgnoreCase("V2")) {
            return BaseResponse.success(helloServiceV2.hello(name));
        } else {
            return BaseResponse.success(helloServiceV1.hello(name));
        }
    }

}
