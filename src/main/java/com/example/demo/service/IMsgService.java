package com.example.demo.service;

import com.example.demo.entity.MsgEntity;

import java.util.List;

public interface IMsgService {
    MsgEntity getMsgById(Integer id);

    Integer deleteMsg(Integer id);

    List<MsgEntity> getMsgReceiver(Integer id);

    List<MsgEntity> getMsgSender(Integer id);

    Integer sendMsg(MsgEntity msgEntity);

    Boolean updateMsgState(MsgEntity msgEntity);

    Boolean readMsg(Integer id);

    Boolean readMsgToMe(Integer id);

    Integer deleteMsgRead(Integer id);
}
