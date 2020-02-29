package com.cyun.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_user_role")
@Data
@ApiModel(value = "com.cyun.param.SysUserRole", description = "用户角色参数")
public class SysUserRole {
    /**
     * id
     */
    @Id
    @ApiModelProperty(value = "id", dataType = "id", required = false, hidden = true)
    private String id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    @ApiModelProperty(value = "用户id", dataType = "String", required = false)
    private String userId;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    @ApiModelProperty(value = "角色id", dataType = "String", required = false)
    private String roleId;

}