package com.example.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "用户信息")
public class UserEntity {
    @Schema(description = "用户id")
    private Integer userId;
    @Schema(description = "用户名")
    private String userName;
    @Schema(description = "用户账号")
    private String userAccount;
    @Schema(description = "用户密码")
    private String userPassword;
    @Schema(description = "用户邮箱")
    private String userEmail;
    @Schema(description = "用户电话")
    private String userPhone;
    @Schema(description = "用户角色")
    private String userType;
}