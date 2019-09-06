package com.github.dubbo.demo.facade.api;

import com.github.dubbo.demo.facade.bean.response.BaseResponse;
import com.github.dubbo.demo.facade.bean.response.UnSerializableResponse;

public interface RpcContextService {

    BaseResponse<String> rpcContext();

    UnSerializableResponse unSerializable();

}
