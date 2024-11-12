package com.example.demo.mapper;

import com.example.demo.DTO.Response;
import com.example.demo.entity.AdmEntity;

public interface IAdmMapper {
    Response login(AdmEntity admEntity);
    Boolean checkId(Integer id);

    Integer checkAccount(String account);

    AdmEntity selectAdmByAccount(String account);

    Integer resetPassword(AdmEntity admEntity);

    Response register(AdmEntity admEntity);
}
