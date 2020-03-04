package com.cyun.dto;

import com.cyun.model.SysRole;
import lombok.Data;

import java.util.List;

/**
 * Created RoleDTO with IDEA
 * Description: class
 *
 * @Auther: xiayk
 * @date: 2019/11/17 下午4:09
 **/
@Data
public class RoleDTO extends SysRole {
    /** 操作状态(1不能操作，0能操作) */
    public Integer optStatus;

    private List<String> menuIds;
}
