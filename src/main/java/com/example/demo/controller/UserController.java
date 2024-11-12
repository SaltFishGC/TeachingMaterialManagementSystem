package com.example.demo.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.Response;
import com.example.demo.entity.MsgEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.IUserService;

import cn.hutool.crypto.SecureUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@Tag(name = "用户管理")
public class UserController {
    @Autowired
    private IUserService userService;

    @Operation(summary = "根据账号查找用户")
    @PostMapping("/selectByAccount")
    public Response selectByAccount(String account){
        UserEntity userEntity = userService.selectUserByAccount(account);
        if (userEntity==null)
            return new Response(400, "用户不存在", null);
        return new Response(200, "查询成功",userEntity);
    }

    @Operation(summary = "根据id重置密码")
    @PutMapping("/resetPwd")
    public Response resetPwd(Integer id, String password){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(id);
        userEntity.setUserPassword(SecureUtil.md5(password+"nuist"));
        if (userService.resetPassword(userEntity))
            return new Response(200, "重置成功", null);
        else
            return new Response(400, "重置失败", null);
    }


    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Response register(String account, String password){

        UserEntity userEntity = new UserEntity();
        userEntity.setUserAccount(account);
        userEntity.setUserPassword(SecureUtil.md5(password+"nuist"));
        return userService.register(userEntity);
    }

    @Operation(summary = "用户完整注册")
    @PostMapping("/register2")
    public Response register2(String account, String password, String name,String type,
                              String phone, String email,String regCode){
        if (!regCode.equals("nuist"))
            return new Response(401, "验证码错误", null);
        UserEntity userEntity = new UserEntity();
        userEntity.setUserAccount(account);
        userEntity.setUserPassword(SecureUtil.md5(password+"nuist"));
        userEntity.setUserName(name);
        userEntity.setUserType(type);
        userEntity.setUserPhone(phone);
        userEntity.setUserEmail(email);
        return userService.register2(userEntity);
    }

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Response login(String account, String password){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserAccount(account);
        userEntity.setUserPassword(SecureUtil.md5(password+"nuist"));
        Response res = userService.login(userEntity);
        
        // 确保返回的用户信息中包含 userName
        if (res.getCode() == 200 && res.getData() instanceof UserEntity) {
            UserEntity loggedInUser = (UserEntity) res.getData();
            if (loggedInUser.getUserName() == null || loggedInUser.getUserName().isEmpty()) {
                loggedInUser.setUserName("用户" + loggedInUser.getUserId());
            }
        }
        
        return res;
    }

    @Operation(summary = "用户接收消息")
    @PostMapping("/getMsg")
    public Response getMsg(Integer id){
        List<MsgEntity> ans;
        try {
            ans = userService.getMsg(id);
            if (ans==null)
                return new Response(400, "获取失败", null);
            return new Response(200, "获取成功", ans);
        } catch (Exception e){
            return new Response(400, "获取失败", null);
        }
    }

    @Operation(summary = "用户发送消息")
    @PostMapping("/sendMsg")
    public Response sendMsg(String msgTitle, String msgContent,
                            Integer msgFromId, Integer msgToId, Date msgDate,
                            Integer msgState){
        MsgEntity msgEntity = new MsgEntity();
        msgEntity.setMsgTitle(msgTitle);
        msgEntity.setMsgContent(msgContent);
        msgEntity.setMsgFromId(msgFromId);
        msgEntity.setMsgToId(msgToId);
        msgEntity.setMsgDate(msgDate);
        msgEntity.setMsgState(msgState);
        Boolean ans = userService.sendMsg(msgEntity);
        if (ans)
            return new Response(200, "发送成功", null);
        else
            return new Response(400, "发送失败", null);
    }
}
