package com.github.dubbo.demo.provider.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.dubbo.demo.facade.api.HelloService;
import com.github.dubbo.demo.facade.bean.response.BaseResponse;

@Profile("!consumer")
@Service(version = "${dubbo.service.version.v1}")
public class HelloServiceImpl implements HelloService {

    @Value("${dubbo.application.name}")
    private String serviceName;

    @Value("${dubbo.service.version.v1}")
    private String serviceVersion;

    @Override
    public BaseResponse<String> hello(String name) {
        String data = String.format("[%s][%s] : Hello, %s", serviceName, serviceVersion, name);
        return BaseResponse.success(data);
    }

}
