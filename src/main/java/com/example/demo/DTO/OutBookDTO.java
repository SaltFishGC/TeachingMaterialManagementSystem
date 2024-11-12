package com.example.demo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "出库书籍信息记录")
public class OutBookDTO {
    @Schema(description = "出库单id")
    private int outId;
    @Schema(description = "出库人id")
    private Integer outBorrowerId;
    @Schema(description = "出库书籍id")
    private Integer outBookid;
    @Schema(description = "书籍名称")
    private String bookName;
    @Schema(description = "出库数量")
    private Integer outQuantity;
    @Schema(description = "出库时间")
    private Date outDate;
    @Schema(description = "接收人id")
    private Integer outToId;
}
