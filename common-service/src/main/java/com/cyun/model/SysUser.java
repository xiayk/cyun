package com.cyun.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_user")
@Data
@ApiModel(value = "com.cyun.param.SysUser", description = "用户参数")
public class SysUser {
    /**
     * id
     */
    @Id
    @ApiModelProperty(value = "id", dataType = "String", required = false)
    private String id;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号", dataType = "String", required = false)
    private String account;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", dataType = "String", required = false)
    private String password;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    @ApiModelProperty(value = "用户名", dataType = "String", required = false)
    private String userName;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", dataType = "String", required = false)
    private String phone;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    @ApiModelProperty(value = "创建时间", dataType = "String", required = false, hidden = true)
    private Date createDate;

    /**
     * 修改时间
     */
    @Column(name = "update_date")
    @ApiModelProperty(value = "修改时间", dataType = "String", required = false, hidden = true)
    private Date updateDate;

    /**
     * 上次登录时间
     */
    @Column(name = "last_date")
    @ApiModelProperty(value = "上次登录时间", dataType = "String", required = false, hidden = true)
    private Date lastDate;

    /**
     * 状态(0正常，1禁用，2删除)
     */
    @ApiModelProperty(value = "状态(0正常，1禁用，2删除)", dataType = "String", required = false)
    private Integer status;

    /**
     * 父id
     */
    @ApiModelProperty(value = "父id", dataType = "String", required = false, hidden = true)
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 创建者id
     */
    @Column(name = "create_user_id")
    @ApiModelProperty(value = "创建者id", dataType = "String", required = false, hidden = true)
    private String createUserId;

    /**
     * 修改者id
     */
    @Column(name = "update_user_id")
    @ApiModelProperty(value = "修改者id", dataType = "String", required = false, hidden = true)
    private String updateUserId;
}