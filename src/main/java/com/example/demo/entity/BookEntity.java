package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "图书信息")
public class BookEntity {
    @Schema(description = "图书id")
    private Integer bookId;
    @Schema(description = "图书名")
    private String bookName;
    @Schema(description = "图书作者")
    private String bookAuthor;
    @Schema(description = "图书出版社")
    private String bookPublisher;
    @Schema(description = "图书出版日期")
    private Date bookPublishdate;
    @Schema(description = "图书价格")
    private Double bookPrice;
    @Schema(description = "图书数量")
    private Integer bookQuantity;
    @Schema(description = "图书描述")
    private String bookDescription;
}
