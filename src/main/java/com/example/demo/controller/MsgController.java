package com.example.demo.controller;

import com.example.demo.DTO.Response;
import com.example.demo.entity.MsgEntity;
import com.example.demo.service.IMsgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/msg")
@Tag(name = "消息管理")
public class MsgController {
    @Autowired
    private IMsgService msgService;

    @PostMapping("/sendMsg")
    @Operation(summary = "发送消息")
    public Response sendMsg(String msgTitle, String msgContent, Integer msgFromId, Integer msgToId,
                            Date msgDate){
        MsgEntity msgEntity = new MsgEntity();
        msgEntity.setMsgTitle(msgTitle);
        msgEntity.setMsgContent(msgContent);
        msgEntity.setMsgFromId(msgFromId);
        msgEntity.setMsgToId(msgToId);
        msgEntity.setMsgDate(msgDate);
        msgEntity.setMsgState(0);
        /*
         * 0 未读
         */
        if (msgService.sendMsg(msgEntity)>0)
            return new Response(200,"发送成功",null);
        return new Response(400,"发送失败",null);
    }

    @DeleteMapping("/deleteMsg")
    @Operation(summary = "删除消息")
    public Response deleteMsg(Integer id){
        if (msgService.deleteMsg(id)>0)
            return new Response(200,"删除成功",null);
        return new Response(400,"删除失败",null);
    }

    @PostMapping("/getMsgR")
    @Operation(summary = "获取收到的消息")
    public Response getMsg(Integer id){
        List<MsgEntity> msgEntities = msgService.getMsgReceiver(id);
        if (msgEntities==null)
            return new Response(400,"获取失败",null);
        return new Response(200,"获取成功",msgEntities);
    }

    @PostMapping("/getMsgS")
    @Operation(summary = "获取发出的消息")
    public Response getMsgS(Integer id){
        List<MsgEntity> msgEntities = msgService.getMsgSender(id);
        if (msgEntities==null)
            return new Response(400,"获取失败",null);
        return new Response(200,"获取成功",msgEntities);
    }

    @PutMapping("/updateMsg")
    @Operation(summary = "更新消息")
    public Response updateMsg(Integer msgId){
        MsgEntity msgEntity = msgService.getMsgById(msgId);
        if (msgService.updateMsgState(msgEntity))
            return new Response(200,"更新成功",null);
        return new Response(400,"更新失败",null);
    }

    @PutMapping("/seMsgToMeRead")
    @Operation(summary = "根据用户id设置该用户所有消息已读")
    public Response seMsgToMeRead(Integer id){
        if (msgService.readMsgToMe(id))
            return new Response(200,"设置成功",null);
        return new Response(400,"设置失败",null);
    }

    @DeleteMapping("/deleteMsgRead")
    @Operation(summary = "根据用户id删除全部已读消息")
    public Response deleteMsgRead(Integer id){
        if (msgService.deleteMsgRead(id)>0)
            return new Response(200,"删除成功",null);
        return new Response(400,"无可删除消息",null);
    }

    @PutMapping("/setMsgRead")
    @Operation(summary = "根据消息id设置该消息已读")
    public Response setMsgRead(Integer msgId){
        MsgEntity msgEntity = msgService.getMsgById(msgId);
        msgEntity.setMsgState(1);
        if (msgService.updateMsgState(msgEntity))
            return new Response(200,"设置成功",null);
        return new Response(400,"设置失败",null);
    }
}
