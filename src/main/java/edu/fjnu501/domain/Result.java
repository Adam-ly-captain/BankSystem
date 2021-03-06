package edu.fjnu501.domain;

import java.util.List;

public class Result {

    private int code;
    private String msg;
    private Object data;
    private List<Object> dataList;

    public Result() {}

//    public Result(int code, String msg, List<Object> dataList) {
//        this.code = code;
//        this.msg = msg;
//        this.dataList = dataList;
//    }

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
