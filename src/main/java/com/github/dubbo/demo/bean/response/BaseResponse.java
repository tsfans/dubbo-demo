package com.github.dubbo.demo.bean.response;

import java.io.Serializable;

import com.github.dubbo.demo.common.enums.ResponseCode;

public class BaseResponse<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1408130576367656818L;

    private String code = ResponseCode.SUCCESS.getCode();
    private String msg = ResponseCode.SUCCESS.getMsg();
    private T data;

    public static <T extends Serializable> BaseResponse<T> success(T data) {
        BaseResponse<T> resp = new BaseResponse<>();
        resp.setCode(ResponseCode.SUCCESS.getCode());
        resp.setMsg(ResponseCode.SUCCESS.getMsg());
        resp.setData(data);
        return resp;
    }

    public static <T extends Serializable> BaseResponse<T> fail(ResponseCode responseCode) {
        BaseResponse<T> resp = new BaseResponse<>();
        resp.setCode(responseCode.getCode());
        resp.setMsg(responseCode.getMsg());
        return resp;
    }

    public static <T extends Serializable> BaseResponse<T> fail(String code, String msg) {
        BaseResponse<T> resp = new BaseResponse<>();
        resp.setCode(code);
        resp.setMsg(msg);
        return resp;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getData() {
        return this.data;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponse[code=" + this.getCode() + ", msg=" + this.getMsg() + ", data=" + this.getData() + "]";
    }

}
