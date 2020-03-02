package com.cyun.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @createUser daiyuan
 * @createTime 2019/11/17 20:40
 * @describe 用户列表请求参数
 **/
@Data
@ApiModel(value = "com.cyun.param.UserParam" , description = "用户列表请求参数")
public class UserParam {

    @ApiModelProperty(value = "用户id", dataType = "String", required = false, hidden = true)
    private String userId;

    @ApiModelProperty(value = "用户名", dataType = "String", required = false)
    private String userName;

    @ApiModelProperty(value = "账号", dataType = "String", required = false)
    private String account;

    @ApiModelProperty(value = "手机号", dataType = "String", required = false)
    private String phone;

    @ApiModelProperty(value = "分页数量", dataType = "Integer", required = true)
    private Integer limit;

    @ApiModelProperty(value = "索引偏移值", dataType = "Integer", required = true)
    private Integer offset;

    @ApiModelProperty(value = "是否是管理员(1=是，0=否)", dataType = "String", required = true, hidden = true)
    private Integer isAdmin;
}
