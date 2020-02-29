package com.cyun.dto;

import lombok.Data;

import java.util.Date;

/**
 * @createUser daiyuan
 * @createTime 2019/11/17 20:32
 * @describe 用户列表响应
 **/
@Data
public class UserDTO {

    /**
     * id
     */
    private String id;

    /**
     * 账号
     */
    private String account;

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
    private Date createDate;

    /**
     * 修改时间
     */
    private Date updateDate;

    /**
     * 状态(0正常，1禁用，2删除)
     */
    private Integer status;

    /**
     * 状态(0正常，1禁用，2删除)
     */
    private String statusDesc;

    /**
     * 创建者
     */
    private String createUserID;


}
