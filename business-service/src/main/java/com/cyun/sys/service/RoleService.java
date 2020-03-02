package com.cyun.sys.service;

import com.cyun.dto.RoleDTO;
import com.cyun.dto.RoleDetailDTO;
import com.cyun.dto.RoleTreeDTO;
import com.cyun.param.*;
import com.cyun.result.PageResult;

import java.util.List;

/**
 * Created RoleService with IDEA
 * Description: interface
 *
 * @Auther: xiayk
 * @date: 2019/11/16 下午5:21
 **/
public interface RoleService {

    /**
     * 添加角色
     * @param param
     */
    void saveRole(SaveRoleParam param);

    /**
     * 编辑角色
     * @param param
     */
    void editRole(EditRoleParam param);

    /**
     * 删除角色
     * @param roleId
     */
    void delRole(String roleId, String delUserId);

    /**
     * 分页获取角色
     * @param param
     * @return
     */
    PageResult listRoles(ListRoleParam param);

    /**
     * 菜单赋给角色
     * @param param
     */
    void SaveMenuToRole(SaveMenuToRoleParam param);

    /**
     * 获取所有角色名称 ID
     * @return
     */
    List<RoleDTO> getRoleTree(Integer isAdmin);

    /**
     * 获取角色详情
     * @param roleId
     * @return
     */
    RoleDTO getRoleDetail(String roleId);
}
