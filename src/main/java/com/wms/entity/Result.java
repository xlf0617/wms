package com.wms.entity;

import lombok.Data;

@Data
public class Result {
    private Integer code;      //编码 200/400
    private String msg;     //成功、失败
    private Long total;     //总记录数
    private Object data;    //数据

    public Result(Integer code, String msg, Long total, Object data) {
        this.code = code;
        this.msg = msg;
        this.total = total;
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result() {
    }
}
