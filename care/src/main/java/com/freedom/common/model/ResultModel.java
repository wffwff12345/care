package com.freedom.common.model;

public class ResultModel {

    public static final String SUCCESS = "0";
    public static final String ERROR = "-1";
    public static final String CHECK_ERROR = "1";

    private String error;
    private Object data;
    private String message;

    public ResultModel(Object data) {
        this.error = SUCCESS;
        this.data = data;
    }

    public ResultModel(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ResultModel error(String error, String message) {
        return new ResultModel(error, message);
    }

    public static ResultModel checkError(String message) {
        return new ResultModel(CHECK_ERROR, message);
    }

    public static ResultModel error(String message) {
        return new ResultModel(ERROR, message);
    }

    public static ResultModel data(Object data) {
        return new ResultModel(data);
    }
}
