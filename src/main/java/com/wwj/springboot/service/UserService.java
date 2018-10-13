package com.wwj.springboot.service;

import com.wwj.springboot.entity.User;
import com.wwj.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User selectUserByUserNameAndPassword(String username,String password){
        return userMapper.selectUserByUserNameAndPassword(username,password);
    }

    public List<User> getAllUser(){
        return userMapper.getAllUser();
    }

    public int addUser(String username,String password){
        return userMapper.addUser(username,password);
    }

    public int deleteUser(int id){
        return userMapper.deleteUser(id);
    }
    public User selectUserById(int id){
        return userMapper.selectUserById(id);
    }

    public int updateUser(User user){
        return userMapper.updateUser(user);
    }

}
