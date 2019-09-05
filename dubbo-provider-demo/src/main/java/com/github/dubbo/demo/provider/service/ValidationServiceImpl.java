package com.github.dubbo.demo.provider.service;

import org.springframework.context.annotation.Profile;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.dubbo.demo.facade.api.ValidationService;
import com.github.dubbo.demo.facade.bean.request.ValidationParameter;
import com.github.dubbo.demo.facade.bean.response.BaseResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Profile("!consumer")
@Service(version = "${dubbo.service.version.v1}")
public class ValidationServiceImpl implements ValidationService {

    @Override
    public BaseResponse<String> addBean(ValidationParameter parameter) {
        log.info("addBean parameter: {}", parameter);
        return BaseResponse.success("ADD SUCCESS");
    }

    @Override
    public BaseResponse<String> updateBean(ValidationParameter parameter) {
        log.info("updateBean parameter: {}", parameter);
        return BaseResponse.success("UPDATE SUCCESS");
    }

}
