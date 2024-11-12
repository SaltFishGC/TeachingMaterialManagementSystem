package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "消息信息")
public class MsgEntity {
    @Schema(description = "消息id")
    private Integer msgId;
    @Schema(description = "消息标题")
    private String msgTitle;
    @Schema(description = "消息内容")
    private String msgContent;
    @Schema(description = "消息发送者id")
    private Integer msgFromId;
    @Schema(description = "消息接收者id")
    private Integer msgToId;
    @Schema(description = "消息发送日期")
    private Date msgDate;
    @Schema(description = "消息状态")
    private Integer msgState;
    /*
     * 0代表未读，1代表已读
     */
}
