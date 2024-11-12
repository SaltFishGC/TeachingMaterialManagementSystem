package com.example.demo.mapper;

import com.example.demo.DTO.Response;
import com.example.demo.entity.MsgEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MsgMapper {

    @Select("select * from msg where msg_id = #{id}")
    MsgEntity getMsgById(Integer id);

    @Select("select * from msg where msg_to_id = #{id}")
    List<MsgEntity> getMsg(Integer id);

    @Select("select * from msg where msg_from_id = #{id}")
    List<MsgEntity> getMsgSendByMe(Integer id);

    @Insert("insert into msg(msg_title,msg_content,msg_from_id,msg_to_id," +
            "msg_date,msg_state) values (#{msgTitle},#{msgContent},#{msgFromId},#{msgToId},#{msgDate},#{msgState})")
    Integer sendMsg(MsgEntity msgEntity);

    @Delete("delete from msg where msg_id = #{id}")
    Integer deleteMsg(Integer id);

    @Update("update msg set msg_content=#{msgContent},msg_title=#{msgTitle},msg_date=#{msgDate}," +
            "msg_from_id=#{msgFromId},msg_to_id=#{msgToId},msg_state=#{msgState} " +
            "where msg_id=#{msgId}")
    Integer updateMsg(MsgEntity msgEntity);

    @Update("update msg set msg_state = 1 where msg_id = #{id}")
    Integer readMsg(Integer id);

    @Update("update msg set msg_state = 1 where msg_to_id = #{id}")
    Integer readMsgToMe(Integer id);

    @Delete("delete from msg where msg_state = 1 and msg_to_id = #{id}")
    Integer deleteMsgRead(Integer id);
}
