package com.cyun.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created SaveRoleParam with IDEA
 * Description: class
 *
 * @Auther: xiayk
 * @date: 2019/11/17 下午12:14
 **/
@Data
@ApiModel(value = "com.cyun.param.SaveRoleParam", description = "保存角色")
public class SaveRoleParam {

    @ApiModelProperty(value = "角色名称", dataType = "String", required = true)
    private String roleName;

    @ApiModelProperty(value = "角色代码", dataType = "String", required = true)
    private String roleCode;

    private String userId;

    @ApiModelProperty(value = "状态", dataType = "Integer", required = true)
    private Integer status;

    @ApiModelProperty(value = "类型", dataType = "Integer", required = true)
    private Integer type;

    @ApiModelProperty(value = "菜单Id", dataType = "String", required = false)
    private List<String> menuIds;
}
