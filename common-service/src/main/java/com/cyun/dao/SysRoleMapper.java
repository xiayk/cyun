package com.cyun.dao;

import com.cyun.dto.RoleDTO;
import com.cyun.dto.RoleDetailDTO;
import com.cyun.dto.RoleTreeDTO;
import com.cyun.model.SysRole;
import com.cyun.param.ListRoleParam;
import com.cyun.param.PageParam;
import com.cyun.utils.mapper.CommonMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysRoleMapper extends CommonMapper<SysRole> {

    /**
     * 获取角色个数
     * @return
     */
    Integer countAllRoles(ListRoleParam param);

    /**
     * 分页获取角色
     * @return
     */
    List<RoleDTO> listLimitRole(ListRoleParam param);

    /**
     * 获取对应角色
     * @param isAdmin
     * @return
     */
    List<RoleDTO> listRoleTree(@Param("isAdmin") Integer isAdmin);


    /**
     * 查看
     * @param roleName
     * @return
     */
    @Select("select count(*) from sys_role where role_name = #{roleName} and status != 2")
    int selectRoleByName(@Param("roleName") String roleName);

    /**
     * 获取所有角色 名称 ID
     * @return
     */
    @Select("select id roleId, role_name roleName from sys_role where status != 2")
    List<RoleTreeDTO> selectRoleNameAndId();


    /**
     * 通过Id获取角色
     * @param roleId
     * @return
     */
    RoleDTO selectRoleDetail(String roleId);
}