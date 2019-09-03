package com.github.dubbo.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.dubbo.demo.facade.bean.response.BaseResponse;

@RestController
public class PingController {

    @GetMapping("/ping")
    public BaseResponse<String> ping() {
        return BaseResponse.success("pong");
    }
}
