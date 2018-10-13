package com.wwj.springboot.mapper;

import com.wwj.springboot.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    @Select("select * from user where username=#{0} and password=#{1}")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password")
    })
    User selectUserByUserNameAndPassword(String username,String password);

    @Select("select * from user")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password")
    })
    List<User> getAllUser();

    @Insert("insert into user(username,password) values (#{0},#{1})")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password")
    })
    public int addUser(String username,String password);

    @Delete("delete from user where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password")
    })
    public int deleteUser(int id);

    @Select("select * from user where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password")
    })
    public User selectUserById(int id);

    @Update("update user set username=#{user.username} , password=#{user.password} where id=#{user.id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password")
    })
    public int updateUser(@Param("user") User user);

}
