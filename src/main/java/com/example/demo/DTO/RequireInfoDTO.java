package com.example.demo.DTO;


import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "需求信息")
public class RequireInfoDTO {
    @Schema(description = "需求编号")
    private Integer reqId;
    @Schema(description = "需求者编号")
    private Integer userId;
    @Schema(description = "需求者姓名")
    private String userName;
    @Schema(description = "书籍编号")
    private Integer bookId;
    @Schema(description = "书籍名称")
    private String bookName;
    @Schema(description = "需求数量")
    private Integer reqQuantity;
    /*
reqIsfinished:
    0 未接收
    1 正在处理
    2 待取
    3 已完成
*/
    @Schema(description = "需求解决情况")
    private Integer reqIsfinished;
    @Schema(description = "需求提交时间")
    private Date reqDate;

}
