package com.example.demo.service.Impl;

import com.example.demo.entity.MsgEntity;
import com.example.demo.mapper.MsgMapper;
import com.example.demo.service.IMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsgService implements IMsgService {
    @Autowired
    private MsgMapper msgMapper;

    @Override
    public MsgEntity getMsgById(Integer id) {
        return msgMapper.getMsgById(id);
    }

    @Override
    public Integer deleteMsg(Integer id) {
        return msgMapper.deleteMsg(id);
    }

    @Override
    public List<MsgEntity> getMsgReceiver(Integer id) {
        return msgMapper.getMsg(id);
    }

    @Override
    public List<MsgEntity> getMsgSender(Integer id) {
        return msgMapper.getMsgSendByMe(id);
    }

    @Override
    public Integer sendMsg(MsgEntity msgEntity) {
        return msgMapper.sendMsg(msgEntity);
    }

    @Override
    public Boolean updateMsgState(MsgEntity msgEntity) {
        return msgMapper.updateMsg(msgEntity) > 0;
    }

    @Override
    public Boolean readMsg(Integer id) {
        return msgMapper.readMsg(id) > 0;
    }

    @Override
    public Boolean readMsgToMe(Integer id) {
        return msgMapper.readMsgToMe(id) > 0;
    }


    @Override
    public Integer deleteMsgRead(Integer id) {
        return msgMapper.deleteMsgRead(id);
    }

}
