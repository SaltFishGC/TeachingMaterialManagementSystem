package com.example.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "书籍入库信息单")
public class InBookEntity {
    @Schema(description = "入库单id")
    private Integer inId;
    @Schema(description = "入库书籍id")
    private Integer inBookId;
    @Schema(description = "入库书籍数量")
    private Integer inQuantity;
    @Schema(description = "入库日期")
    private Date inDate;
}
