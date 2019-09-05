package com.github.dubbo.demo.facade.api;

import javax.validation.Valid;

import com.alibaba.dubbo.validation.MethodValidated;
import com.github.dubbo.demo.facade.bean.request.ValidationParameter;
import com.github.dubbo.demo.facade.bean.response.BaseResponse;

public interface ValidationService {

    @MethodValidated(Add.class)
    BaseResponse<String> addBean(@Valid ValidationParameter parameter);

    @MethodValidated(Update.class)
    BaseResponse<String> updateBean(@Valid ValidationParameter parameter);

    @interface Add {}

    @interface Update {}

}
