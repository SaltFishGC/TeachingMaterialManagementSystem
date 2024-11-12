package com.example.demo.service.Impl;

import com.example.demo.DTO.Response;
import com.example.demo.entity.MsgEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.IAdmMapper;
import com.example.demo.mapper.IUserMapper;
import com.example.demo.mapper.MsgMapper;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserMapper userMapper;
    @Autowired
    IAdmMapper admMapper;
    @Autowired
    MsgMapper msgMapper;
    @Override
    public Response login(UserEntity userEntity) {
        return userMapper.login(userEntity);
    }

    @Override
    public Response register(UserEntity userEntity) {
        if (userMapper.checkAccount(userEntity.getUserAccount()).getCode()==400){
            return new Response(300, "账号已存在", null);
        }
        return userMapper.register(userEntity);
    }

    @Override
    public Response register2(UserEntity userEntity){
        if (userMapper.checkAccount(userEntity.getUserAccount()).getCode()==400)
            return new Response(300, "账号已存在", null);
        if (userMapper.register2(userEntity))
            return new Response(200, "注册成功", null);
        return new Response(400, "注册失败", null);
    }

    @Override
    public UserEntity selectUserByAccount(String account) {
        return userMapper.selectUser(account);
    }

    @Override
    public Boolean sendMsg(MsgEntity msgEntity) {
        if (admMapper.checkId(msgEntity.getMsgToId())){
            return msgMapper.sendMsg(msgEntity) > 0;
        }
        return false;
    }

    @Override
    public List<MsgEntity> getMsg(Integer id) {
        if (userMapper.checkId(id)){
            System.out.println("ok");
            return msgMapper.getMsg(id);
        }
        return null;
    }

    @Override
    public Boolean resetPassword(UserEntity userEntity){
        if (userMapper.checkAccount(userEntity.getUserAccount()).getCode()==400){
            return false;
        }
        return userMapper.resetPassword(userEntity)>0;
    }
}
