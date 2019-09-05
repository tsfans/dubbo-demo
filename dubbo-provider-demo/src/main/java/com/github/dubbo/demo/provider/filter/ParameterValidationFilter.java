package com.github.dubbo.demo.provider.filter;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcResult;
import com.alibaba.dubbo.validation.Validation;
import com.alibaba.dubbo.validation.Validator;
import com.alibaba.dubbo.validation.support.jvalidation.JValidation;
import com.github.dubbo.demo.facade.bean.response.BaseResponse;
import com.github.dubbo.demo.facade.common.enums.ResponseCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Activate(group = {Constants.CONSUMER, Constants.PROVIDER}, order = Integer.MIN_VALUE)
public class ParameterValidationFilter implements Filter {
    
    private final Validation validation = new JValidation();

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        log.info(">>>>>> ParameterValidationFilter has been invoked <<<<<<");
        
        try {
            Validator validator = validation.getValidator(invoker.getUrl());
            if (validator != null) {
                validator.validate(invocation.getMethodName(), invocation.getParameterTypes(), invocation.getArguments());
            }
        } catch (RpcException e) {
            throw e;
        } catch (ConstraintViolationException ce) {
            StringBuilder error = new StringBuilder();
            for (ConstraintViolation<?> constraintViolation : ce.getConstraintViolations()) {
                error.append(" " + constraintViolation.getMessageTemplate());
            }
            BaseResponse<String> errorResponse = BaseResponse.fail(ResponseCode.ILLEGAL_ARGUMENT.getCode(), error.toString());
            return new RpcResult(errorResponse);
        } catch (Throwable t) {
            return new RpcResult(t);
        }
        return invoker.invoke(invocation);
    }

}
