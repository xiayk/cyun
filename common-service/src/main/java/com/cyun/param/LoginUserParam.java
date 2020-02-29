package com.cyun.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 *
 */
@Data
@ApiModel(value = "com.cyun.param.LoginUserParam", description = "登录请求信息")
public class LoginUserParam {

    @ApiModelProperty(value = "账号", dataType = "String", required = true)
    private String account;

    @ApiModelProperty(value = "密码", dataType = "String", required = true)
    private String password;
}
