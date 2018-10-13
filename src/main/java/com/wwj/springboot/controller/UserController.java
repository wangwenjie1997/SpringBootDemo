package com.wwj.springboot.controller;


import com.wwj.springboot.entity.User;
import com.wwj.springboot.exception.MyException;
import com.wwj.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/gotohtml")
    public String GotoHtml(String htmlName) {
//        String url="page/";
//        return url+htmlName;
        log.info("UserController-GotoHtml"+htmlName);
        return htmlName;
    }

    @RequestMapping("/test")
    public String test() {
//        return "page/login";
        return "login";
    }


    @PostMapping("/login")
    public String login(HttpSession session, HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestParam(value = "username", required = true) String username,
                        @RequestParam(value = "password", required = true) String password,
                        RedirectAttributes redirectAttributes) {
        if (userService.selectUserByUserNameAndPassword(username, password) != null) {
            User u = new User();
            u.setUsername(username);
            u.setPassword(password);
            session.setAttribute("user", u);
//            redirectAttributes.addAttribute("htmlName","index");
//            return "redirect:/gotohtml";
            return "redirect:/gotohtml?htmlName=index";
        } else {
            response.setContentType("text/html; charset=UTF-8"); //转码
            PrintWriter out= null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.print("<script>alert('登录失败！');</script>");
            request.setAttribute("err_msg", "账号密码错误");
            return "login";
        }
    }

    @ResponseBody
    @GetMapping("/login")
    public String getLogin(){
        return "这是get请求";
    }

//    @RequestMapping("/regist")
    @PostMapping("/regist")
    public String Regist(HttpServletRequest request,
                         @RequestParam("username")String username,
                         @RequestParam("password") String password){
        if (userService.selectUserByUserNameAndPassword(username, password) != null) {
            request.setAttribute("err_msg","当前账号已存在");
        }
        else{
            userService.addUser(username,password);
            request.setAttribute("err_msg","注册成功");
        }
        return "register";
    }

//    @RequestMapping("/getAllUser")
    @GetMapping("/getAllUser")
    public String getAllUser(Map<String,Object> map){
        ArrayList<User> users= (ArrayList<User>) userService.getAllUser();
        map.put("users",users);
        return "allUsers";
    }

//    @RequestMapping("/gotoEditUser/{id}")
    @GetMapping("/gotoEditUser/{id}")
    public String gotoEditUser(@PathVariable("id") int id,
                           Map<String,Object> map) {
        log.info("editUser=" + id);
        map.put("editUser",userService.selectUserById(id));
        return "editUser";
    }

//    @RequestMapping("/editUser")
    @PutMapping("/editUser")
    public String editUser(User user){
        userService.updateUser(user);
        return "redirect:/getAllUser";
    }

//    @RequestMapping("/deleteUser/{id}")
    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/getAllUser";
    }

    @ResponseBody
    @RequestMapping("jsonerror")
    public String jsonError(@RequestParam("username") String username){
        if(username.equals("wwj")) {
            log.info("5xxError");
            throw new MyException();
        }
        return "login";
    }

}
