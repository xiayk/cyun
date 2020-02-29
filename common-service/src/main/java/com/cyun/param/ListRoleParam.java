package com.cyun.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created ListRoleParam with IDEA
 * Description: class
 *
 * @Auther: xiayk
 * @date: 2019/11/17 下午4:13
 **/
@Data
@ApiModel(value = "com.cyun.param.ListRoleParam", description = "条件查询角色")
public class ListRoleParam extends PageParam {

    @ApiModelProperty(value = "角色ID", dataType = "String", required = false)
    private String roleId;

    @ApiModelProperty(value = "角色名称", dataType = "String", required = false)
    private String roleName;

    @ApiModelProperty(value = "角色代码", dataType = "String", required = false)
    private String roleCode;

    @ApiModelProperty(value = "创建时间1", dataType = "String", required = false)
    private String createDate;

    @ApiModelProperty(value = "创建时间2", dataType = "String", required = false)
    private String createDate1;

    @ApiModelProperty(value = "创建人id", dataType = "String", required = false)
    private String userId;

    @ApiModelProperty(value = "角色类型", dataType = "String", required = false)
    private Integer type;
}
