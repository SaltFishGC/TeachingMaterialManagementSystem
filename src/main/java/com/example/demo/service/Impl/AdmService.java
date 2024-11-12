package com.example.demo.service.Impl;

import com.example.demo.DTO.Response;
import com.example.demo.entity.AdmEntity;
import com.example.demo.entity.MsgEntity;
import com.example.demo.mapper.IAdmMapper;
import com.example.demo.mapper.IUserMapper;
import com.example.demo.mapper.MsgMapper;
import com.example.demo.service.IAdmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdmService implements IAdmService {
    @Autowired
    IAdmMapper admMapper;
    @Autowired
    IUserMapper userMapper;
    @Autowired
    MsgMapper msgMapper;
    @Override
    public Response login(AdmEntity admEntity) {
        return admMapper.login(admEntity);
    }

    @Override
    public AdmEntity selectAdm(String account) {
        return admMapper.selectAdmByAccount(account);
    }

    @Override
    public Boolean resetPassword(AdmEntity admEntity) {
        return admMapper.resetPassword(admEntity) > 0;
    }

    @Override
    public Boolean sendMsg(MsgEntity msgEntity) {
        if (userMapper.checkId(msgEntity.getMsgToId())){
            return msgMapper.sendMsg(msgEntity) > 0;
        }
        return false;
    }

    @Override
    public List<MsgEntity> getMsg(Integer id) {
        if (admMapper.checkId(id)){
            return msgMapper.getMsg(id);
        }
        return null;
    }

    @Override
    public Response register(AdmEntity admEntity) {
        if (admMapper.checkAccount(admEntity.getAdmAccount())>0)
            return new Response(400,"账号已存在",null);
        return admMapper.register(admEntity);
    }
}
