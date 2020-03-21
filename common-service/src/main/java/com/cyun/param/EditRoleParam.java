package com.cyun.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created EditRoleParam with IDEA
 * Description: class
 *
 * @Auther: xiayk
 * @date: 2019/11/17 下午12:18
 **/
@Data
@ApiModel(value = "com.cyun.param.EditRoleParam", description = "编辑角色参数")
public class EditRoleParam extends SaveRoleParam {

    @ApiModelProperty(value = "角色ID", dataType = "String", required = true)
    private String roleId;

    private String updateUserId;

    @ApiModelProperty(value = "默认菜单ids", dataType = "String", hidden = true)
    private String defaultMenuIdStr;
}
