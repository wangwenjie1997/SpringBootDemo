package com.wwj.springboot.exception;

public class MyException extends RuntimeException{

    public MyException() {
        super("用户不存在");
    }

}

