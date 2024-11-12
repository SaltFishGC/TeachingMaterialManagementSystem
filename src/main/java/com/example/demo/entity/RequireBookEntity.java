package com.example.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "需求表信息")
public class RequireBookEntity {
    @Schema(description = "需求编号")
    private Integer reqId;
    @Schema(description = "需求者编号")
    private Integer reqDemenderId;
    /*
    reqIsfinished:
        0 未接收
        1 正在处理
        2 待取
        3 已完成
 */
    @Schema(description = "需求解决情况")
    private Integer reqIsfinished;
    @Schema(description = "需求书籍编号")
    private Integer reqBookId;
    @Schema(description = "需求数量")
    private Integer reqQuantity;
    @Schema(description = "需求提交时间")
    private Date reqDate;
    @Schema(description = "需求原因")
    private String reqReason;
    @Schema(description = "需求接收者编号")
    private Integer reqToId;
}
