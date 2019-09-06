package com.github.dubbo.demo.provider.service;

import org.springframework.context.annotation.Profile;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.github.dubbo.demo.facade.api.RpcContextService;
import com.github.dubbo.demo.facade.bean.response.BaseResponse;
import com.github.dubbo.demo.facade.bean.response.UnSerializableResponse;

@Profile("!consumer")
@Service(version = "${dubbo.service.version.v1}")
public class RpcContextServiceImpl implements RpcContextService {

    @Override
    public BaseResponse<String> rpcContext() {
        String data = RpcContext.getContext().getAttachment("ping");
        return BaseResponse.success(data);
    }

    @Override
    public UnSerializableResponse unSerializable() {
        return new UnSerializableResponse(1024L, "response", "unserializable");
    }

}
