package com.example.demo.DTO;


import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "订购书籍信息记录")
public class BuyBookDTO {
    @Schema(description = "购书单id")
    private Integer buyId;
    @Schema(description = "购书人id")
    private Integer buyBuyerId;
    @Schema(description = "购买书籍id")
    private Integer buyBookId;
    @Schema(description = "书籍名称")
    private String bookName;
    @Schema(description = "购买书籍数量")
    private Integer buyQuantity;
    @Schema(description = "购买时间")
    private Date buyDate;
    @Schema(description = "订单状态")
    private Integer buyState;
    /*
      订单状态：
      0. 未付款
      1. 已付款未到
      2. 已付款已到
     */
}
