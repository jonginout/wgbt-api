package com.wgbt.wgbtapi.common.domain;

public class Result {

    private boolean success;
    private String errorMessage;

    private Result(){
        this(true,null);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    private  Result(boolean success, String errorMessage){
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public static Result ok(){
        return new Result();
    }

    public static Result fail(String errorMessage){
        return new Result(false, errorMessage);
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
