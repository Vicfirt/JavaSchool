package com.javaschool.onlineshop.Exception;

public class MyException extends RuntimeException {

    private Integer code;

    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
