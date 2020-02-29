package com.cyun.dto;

import com.cyun.model.SysUser;

/**
 * Created with IDEA
 * Description:
 *
 * @Auther: xiayk
 * @date: 2019-12-03 23:16
 **/
public class UserDetailDTO extends SysUser {

    private String roleId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
