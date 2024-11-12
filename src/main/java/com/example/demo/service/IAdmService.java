package com.example.demo.service;

import com.example.demo.DTO.Response;
import com.example.demo.entity.AdmEntity;
import com.example.demo.entity.MsgEntity;

import java.util.List;

public interface IAdmService {
    Response login(AdmEntity admEntity);

    AdmEntity selectAdm(String account);

    Boolean resetPassword(AdmEntity admEntity);

    Boolean sendMsg(MsgEntity msgEntity);
    List<MsgEntity> getMsg(Integer id);
    Response register(AdmEntity admEntity);
}
