package com.github.dubbo.demo.facade.api;

import com.github.dubbo.demo.facade.bean.response.BaseResponse;

public interface HelloService {

    BaseResponse<String> hello(String name);
    
}
