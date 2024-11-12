package com.example.demo.mapper.Impl;

import com.example.demo.DTO.Response;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.IUserMapper;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserMapper implements IUserMapper {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Tag(name = "selectUser", description = "查找用户")
    public UserEntity selectUser(String account) {
        String sql = "select * from usr where user_account = ?";
        try {
            return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(UserEntity.class), account);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    @Tag(name = "register", description = "注册")
    public Response register(UserEntity userEntity) {
        String sql = "insert into usr(user_account,user_password) values(?,?)";
        try {
            return jdbcTemplate.update(sql, userEntity.getUserAccount(), userEntity.getUserPassword()) > 0 ?
                    new Response(200, "注册成功", null) : new Response(400, "注册失败", null);
        }catch (DataAccessException e){
            return new Response(300, "账号重复", null);
        }
    }

    @Override
    public Boolean register2(UserEntity userEntity){
        String sql = "insert into usr(user_account,user_password,user_type," +
                "user_name,user_email,user_phone) values(?,?,?,?,?,?)";
        if (jdbcTemplate.update(sql, userEntity.getUserAccount(), userEntity.getUserPassword(),
                userEntity.getUserType(), userEntity.getUserName(),
                userEntity.getUserEmail(), userEntity.getUserPhone()) > 0){
            return true;
        }else
            return false;
    }

    @Override
    public Response checkAccount(String account){
        String sql="select count(*) from usr where user_account = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, account);
        if (count == 1)
            return new Response(400, "账号已存在", null);
        else
            return new Response(200, "账号不存在", null);
    }
//
//    @Override
////    @Tag(name = "login", description = "登录")
////    public Response login(UserEntity userEntity) {
////        String sql = "select * from usr where user_account = ? and user_password = ?";
////            System.out.println(userEntity);
////            userEntity = jdbcTemplate.queryForObject(sql, UserEntity.class,
////                    userEntity.getUserAccount(), userEntity.getUserPassword());
////            System.out.println(userEntity);
////        if (userEntity.getUserId() != null){
////            return new Response(200, "登录成功", userEntity);
////        }
////        return new Response(400, "账号或密码输入错误", null);
////    }
    @Override
    @Tag(name = "login", description = "登录")
    public Response login(UserEntity userEntity) {
        String sql = "SELECT * FROM usr WHERE user_account = ? AND user_password = ?";

        try {
            System.out.println(userEntity);
            UserEntity result = jdbcTemplate.queryForObject(sql,
                    new BeanPropertyRowMapper<>(UserEntity.class),
                    userEntity.getUserAccount(), userEntity.getUserPassword());

            if (result != null) {
                System.out.println(result);
                return new Response(200, "登录成功", result);
            }
        } catch (EmptyResultDataAccessException e) {
            // 处理未找到用户的情况
        }

        return new Response(400, "用户名或密码错误", null);
    }

    @Override
    public Boolean checkId(Integer id) {
        String sql="select count(*) from usr where user_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, id) == 1;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Integer resetPassword(UserEntity userEntity){
        String sql = "update usr set user_password = ? where user_id = ?";
        try {
            return jdbcTemplate.update(sql, userEntity.getUserPassword(), userEntity.getUserId());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }


}
