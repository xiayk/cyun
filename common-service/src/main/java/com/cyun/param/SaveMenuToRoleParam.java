package com.cyun.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created SaveMenuToRoleParam with IDEA
 * Description: class
 *
 * @Auther: xiayk
 * @date: 2019/11/17 下午5:13
 **/
@Data
@ApiModel(value = "com.cyun.param.SaveMenuToRoleParam", description = "把菜单添加至权限")
public class SaveMenuToRoleParam {

    @ApiModelProperty(value = "权限ID", dataType = "String", required = true)
    private String roleId;

    @ApiModelProperty(value = "菜单ID", dataType = "String", required = true)
    private String menuId;
}
