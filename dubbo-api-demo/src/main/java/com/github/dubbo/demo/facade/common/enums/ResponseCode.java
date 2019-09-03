package com.github.dubbo.demo.facade.common.enums;

public enum ResponseCode {

    SUCCESS("200", "success"), ILLEGAL_ARGUMENT("400", "illegal arguments"), FAILURE("500", "fail");

    private final String code;
    private final String msg;

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    @Override
    public String toString() {
        return "ResponseCode[code=" + this.getCode() + ", msg=" + this.getMsg() + "]";
    }

    private ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
