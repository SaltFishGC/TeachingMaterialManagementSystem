package com.example.demo.mapper.Impl;

import com.example.demo.DTO.Response;
import com.example.demo.entity.AdmEntity;
import com.example.demo.mapper.IAdmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdmMapper implements IAdmMapper {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Response login(AdmEntity admEntity) {
        String sql = "SELECT * FROM adm WHERE adm_account = ? AND adm_password = ?";
        try {
            AdmEntity result = jdbcTemplate.queryForObject(sql,
                    new BeanPropertyRowMapper<>(AdmEntity.class),
                    admEntity.getAdmAccount(), admEntity.getAdmPassword());
            return new Response(200, "登录成功", result);
        } catch (Exception e) {
            return new Response(400, "账号或密码错误", null);
        }
    }

    @Override
    public Boolean checkId(Integer id) {
        String sql="SELECT count(*) FROM adm WHERE adm_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, id) == 1;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Integer checkAccount(String account) {
        String sql="SELECT count(*) FROM adm WHERE adm_account = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, account);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public AdmEntity selectAdmByAccount(String account) {
        String sql="SELECT * FROM adm WHERE adm_account = ?";
        try {
            return jdbcTemplate.queryForObject(sql,
                    new BeanPropertyRowMapper<>(AdmEntity.class),
                    account);
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public Integer resetPassword(AdmEntity admEntity) {
        String sql = "update adm set adm_password = ? where adm_id = ?";
        try {
            return jdbcTemplate.update(sql, admEntity.getAdmPassword(), admEntity.getAdmId());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Response register(AdmEntity admEntity) {
        String sql = "insert into adm(adm_account,adm_password) values(?,?)";
        try {
            return jdbcTemplate.update(sql, admEntity.getAdmAccount(), admEntity.getAdmPassword()) > 0 ?
                    new Response(200, "注册成功", null) : new Response(400, "注册失败", null);
        }catch (Exception e){
            return new Response(300, "账号重复", null);
        }
    }
}
