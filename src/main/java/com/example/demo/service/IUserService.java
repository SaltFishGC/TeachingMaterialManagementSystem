package com.example.demo.service;

import com.example.demo.DTO.Response;
import com.example.demo.entity.MsgEntity;
import com.example.demo.entity.UserEntity;

import java.util.List;

public interface IUserService {
    Response login(UserEntity userEntity);
    Response register(UserEntity userEntity);

    Response register2(UserEntity userEntity);

    UserEntity selectUserByAccount(String account);

    Boolean sendMsg(MsgEntity msgEntity);
    List<MsgEntity> getMsg(Integer id);

    Boolean resetPassword(UserEntity userEntity);
}
