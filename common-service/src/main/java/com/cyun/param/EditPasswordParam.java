package com.cyun.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created EditPasswordParam with IDEA
 * Description: class
 *
 * @Auther: nigei
 * @date: 2019/11/17 7:45 下午
 **/
@Data
@ApiModel(value = "com.cyun.param.EditPasswordParam", description = "修改密码信息")
public class EditPasswordParam {
    @ApiModelProperty(value = "旧密码", dataType = "String", required = true)
    private String oldPassword;

    @ApiModelProperty(value = "新密码", dataType = "String", required = true)
    private String newPassword;

    @ApiModelProperty(value = "加密后的密码", dataType = "String", hidden = true)
    private String md5Password;

    @ApiModelProperty(value = "用户id", dataType = "String", hidden = true)
    private String userId;
}
