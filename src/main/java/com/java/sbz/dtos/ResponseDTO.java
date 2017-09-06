package com.java.sbz.dtos;

/**
 * Created by sirko on 9/5/17.
 */
public class ResponseDTO {

    private String message;

    public ResponseDTO(){

    }

    public ResponseDTO(String msg){
        this.message=msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
