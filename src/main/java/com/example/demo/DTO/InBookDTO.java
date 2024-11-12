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
@Tag(name = "入库信息")
public class InBookDTO {
    @Schema(description = "入库单id")
    private Integer inId;
    @Schema(description = "入库书籍id")
    private Integer inBookId;
    @Schema(description = "书籍名称")
    private String bookName;
    @Schema(description = "入库书籍数量")
    private Integer inQuantity;
    @Schema(description = "入库日期")
    private Date inDate;
}
