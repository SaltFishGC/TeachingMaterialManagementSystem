package com.example.demo.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.Response;
import com.example.demo.entity.AdmEntity;
import com.example.demo.entity.MsgEntity;
import com.example.demo.service.IAdmService;

import cn.hutool.crypto.SecureUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/adm")
@Tag(name = "管理员管理")
public class AdmController {

    @Autowired
    private IAdmService admService;

    @PostMapping("/login")
    @Operation(summary = "管理员登录")
    public Response login(String account, String password){
        AdmEntity admEntity = new AdmEntity();
        admEntity.setAdmAccount(account);
        admEntity.setAdmPassword(SecureUtil.md5(password+"nuist"));
        Response res = admService.login(admEntity);
        return res;
    }

    @PostMapping("/register")
    @Operation(summary = "管理员注册")
    public Response register(String account, String password){
        AdmEntity admEntity = new AdmEntity();
        admEntity.setAdmAccount(account);
        admEntity.setAdmPassword(SecureUtil.md5(password+"nuist"));
        return admService.register(admEntity);
    }

    @PostMapping("/sendMsg")
    @Operation(summary = "管理员发送消息")
    public Response sendMsg(String msgTitle, String msgContent,
                            Integer msgFromId, Integer msgToId, Date msgDate,
                            Integer msgState)
    {
        MsgEntity msgEntity =new MsgEntity();
        msgEntity.setMsgTitle(msgTitle);
        msgEntity.setMsgContent(msgContent);
        msgEntity.setMsgFromId(msgFromId);
        msgEntity.setMsgToId(msgToId);
        msgEntity.setMsgDate(msgDate);
        msgEntity.setMsgState(msgState);
        if (admService.sendMsg(msgEntity)){
            return new Response(200,"发送成功",null);
        }
        return new Response(400,"发送失败",null);
    }

    @PostMapping("/getMsg")
    @Operation(summary = "管理员接收消息")
    public Response getMsg(Integer id){
        List<MsgEntity> ans;
        try{
            System.out.println("ok");
            ans = admService.getMsg(id);
            if (ans==null)
                return new Response(400,"获取失败",null);
            return new Response(200,"获取成功",ans);
        } catch (Exception e){
            return new Response(400,"获取失败",null);
        }
    }

    @PostMapping("/selectAdm")
    @Operation(summary = "根据账号查找管理员账号")
    public Response selectAdmByAccount(@RequestParam String account){
        try {
            AdmEntity ans = admService.selectAdm(account);
            if (ans == null)
                return new Response(400, "未找到管理员", null);
            return new Response(200, "查找成功", ans);
        } catch (Exception e) {
            return new Response(500, "服务器错误: " + e.getMessage(), null);
        }
    }

    @PostMapping("/resetAdmPass")
    @Operation(summary = "根据id重置管理员账号密码")
    public Response resetAdmPass(Integer id, String password){
        AdmEntity admEntity = new AdmEntity();
        admEntity.setAdmId(id);
        admEntity.setAdmPassword(SecureUtil.md5(password+"nuist"));
        if (admService.resetPassword(admEntity))
            return new Response(200,"重置成功",null);
        return new Response(400,"重置失败",null);
    }
}
