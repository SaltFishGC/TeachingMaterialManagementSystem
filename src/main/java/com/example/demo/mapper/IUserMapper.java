package com.example.demo.mapper;

import com.example.demo.DTO.Response;
import com.example.demo.entity.UserEntity;
import io.swagger.v3.oas.annotations.tags.Tag;

public interface IUserMapper {
    @Tag(name = "selectUser", description = "查找用户")
    UserEntity selectUser(String account);

    Response register(UserEntity userEntity);
    Response login(UserEntity userEntity);
    Boolean checkId(Integer id);

    Boolean register2(UserEntity userEntity);

    Response checkAccount(String account);

    Integer resetPassword(UserEntity userEntity);
}
