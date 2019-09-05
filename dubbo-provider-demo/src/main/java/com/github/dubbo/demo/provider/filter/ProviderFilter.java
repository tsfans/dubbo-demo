package com.github.dubbo.demo.provider.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Activate(group = {Constants.PROVIDER}, order = Integer.MIN_VALUE + 1)
public class ProviderFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        log.info(">>>>>> ProviderFilter has been invoked <<<<<<");
        return invoker.invoke(invocation);
    }

}
