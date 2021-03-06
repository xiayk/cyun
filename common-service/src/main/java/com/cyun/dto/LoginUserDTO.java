package com.cyun.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
public class LoginUserDTO {
    /**
     * id
     */
    private String id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 创建时间

     */
    @JsonIgnore
    private Date createDate;

    /**
     * 修改时间
     */
    @JsonIgnore
    private Date updateDate;

    /**
     * 上次登录时间
     */
    private Date lastDate;

    /**
     * 状态(0正常，1禁用，2删除)
     */
    private Integer status;

    /**
     * 父id
     */
    @JsonIgnore
    private String parentId;

    /**
     * 用户token
     */
    private String token;
}
