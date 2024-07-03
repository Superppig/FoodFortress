package com.team602.foodfortress.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;

@Data
public class JsonResult {
    private int status = 200;

    private Boolean result;
    private String errCode;
    private String msg;
    private Object data;

    public JsonResult() {
    }

    public JsonResult(Object data) {
        this.result = true;
        this.data = data;
    }

    public JsonResult(boolean rs, String msg) {
        this.result = rs;
        this.msg = msg;
    }

    public JsonResult(boolean rs, Object data, String msg) {
        this.result = rs;
        this.data = data;
        this.msg = msg;
    }

    public JsonResult(boolean rs, String errCode, String msg) {
        this.result = rs;
        this.errCode = errCode;
        this.msg = msg;
    }

    public JsonResult(boolean rs,String errCode ,String msg,int status)
    {
        this.result=rs;
        this.errCode = errCode;
        this.msg=msg;
        this.status=status;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}