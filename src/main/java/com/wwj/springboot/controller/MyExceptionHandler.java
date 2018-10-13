package com.wwj.springboot.controller;

import com.wwj.springboot.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

    //浏览器和客户端返回到都是json数据，没有自适应
//    @ResponseBody
//    @ExceptionHandler(MyException.class)
//    public Map<String,Object> noUserException(Exception e){
//        Map<String,Object> map=new HashMap<String,Object>();
//        map.put("code","user not exist");
//        map.put("message",e.getMessage());
//        return map;
//    }

    //自适应
//    @ExceptionHandler(MyException.class)
//    public String noUserException(Exception e, HttpServletRequest request){
//        //设置错误状态码
//        request.setAttribute("javax.servlt.error.status_code",400);
//        Map<String,Object> map=new HashMap<String,Object>();
//        map.put("code","user not exist");
//        map.put("message",e.getMessage());
//        return "forward:/page/error";//转发到/error，里面有自适应
//    }

    //能将map中的错误信息发送出去 太麻烦


    //默认异常处理页面
    public static final String DEFAUL_ERROR_VIEW = "error";

    /**
     * 默认异常处理方法,返回异常请求路径和异常信息
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaulErrorHandler(HttpServletRequest request, Exception e) throws  Exception{

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception",e);                   //异常信息
        mav.addObject("url","请求路径：" + request.getRequestURI());   //异常请求路径
        mav.setViewName(DEFAUL_ERROR_VIEW);                          //返回异常处理页面
        return mav;
    }

}
