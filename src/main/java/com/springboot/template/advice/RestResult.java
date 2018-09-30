package com.springboot.template.advice;

/**
 * Created by jinyu on 2018/9/30.
 */
public class RestResult<T> {

    private boolean result;
    private String message;
    private T data;

    private RestResult(){}

    public static <T> RestResult<T> newInstance(){
        return new RestResult<T>();
    }

    @Override
    public String toString() {
        return "RestResult{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }


    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }




}
