package com.wwj.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloWorld {

    @RequestMapping("/hello")
    @ResponseBody
    public String HelloWorld(){
        return "Hello World!!!";
    }

    @RequestMapping("/success")
    public String Success(Map<String,Object> map){
        map.put("hello","<h1>hello</h1>");
        map.put("users", Arrays.asList("张三","李四","王五"));
        return "page/success";
    }

}
