package com.trj.tlib.bean;

public class RespBean<T> {

//    "result":"0", "接口调用失败"
//            "error_no":"接口调用错误标识号",
//            "error_msg":"失败原因说明"


    private String result;
    private T data;
    private T dataList;
    private String error_no;
    private String msg;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getDataList() {
        return dataList;
    }

    public void setDataList(T dataList) {
        this.dataList = dataList;
    }

    public String getError_no() {
        return error_no;
    }

    public void setError_no(String error_no) {
        this.error_no = error_no;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
