package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "管理员信息")
public class AdmEntity {
    @Schema(description = "管理员id")
    private Integer admId;
    @Schema(description = "管理员名称")
    private String admName;
    @Schema(description = "管理员手机号")
    private String admPhone;
    @Schema(description = "管理员邮箱")
    private String admEmail;
    @Schema(description = "管理员账号")
    private String admAccount;
    @Schema(description = "管理员密码")
    private String admPassword;
}
