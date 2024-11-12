package com.example.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "(管理员)购书信息")
public class BuyBookEntity {
    @Schema(description = "购书单id")
    private Integer buyId;
    @Schema(description = "购书人id")
    private Integer buyBuyerId;
    @Schema(description = "购买书籍id")
    private Integer buyBookId;
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
