package com.java.sbz.util;

/**
 * Created by sirko on 9/2/17.
 */
public class ServiceReturn {

    private boolean ok;
    private String message;

    public ServiceReturn(){}

    public ServiceReturn(boolean ok,String message){
        this.ok=ok;
        this.message=message;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
