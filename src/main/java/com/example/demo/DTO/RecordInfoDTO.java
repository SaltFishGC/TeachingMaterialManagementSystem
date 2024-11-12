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
@Tag(name = "订购书籍信息记录")
public class RecordInfoDTO {
    @Schema(description = "需求单号")
    private Integer reqId;
    @Schema(description = "订购时间")
    private Date reqDate;
    @Schema(description = "书籍编号")
    private Integer bookId;
    @Schema(description = "书籍名称")
    private String bookName;
    @Schema(description = "订购数量")
    private Integer reqQuantity;
    @Schema(description = "书籍单价")
    private Double bookPrice;
    @Schema(description = "订购状态")
    private Integer reqIsfinished;
}
