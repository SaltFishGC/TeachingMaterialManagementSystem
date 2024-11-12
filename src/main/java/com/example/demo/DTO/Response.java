package com.example.demo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "返回结果")
public class Response {
    @Schema(description = "返回状态码")
    private Integer code;
    @Schema(description = "返回信息")
    private String msg;
    @Schema(description = "返回数据")
    private Object data;
}
