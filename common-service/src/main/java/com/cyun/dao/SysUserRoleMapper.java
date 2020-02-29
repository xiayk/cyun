package com.cyun.dao;

import com.cyun.dto.UserDetailDTO;
import com.cyun.model.SysUserRole;
import com.cyun.utils.mapper.CommonMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SysUserRoleMapper extends CommonMapper<SysUserRole> {

    /**
     * 根据用户id查询用户角色数量
     *
     * @param userId
     * @return
     */
    @Select("select count(*) from sys_user_role where user_id = #{userId}")
    Integer countByUserId(String userId);

    /**
     * 根据用户id修改角色
     *
     * @param sysUserRole
     * @return
     */
    @Update("update sys_user_role set role_id = #{roleId} where user_id = #{userId}")
    Integer updateRole(SysUserRole sysUserRole);

    /**
     * 根据用户id获取用户信息,角色id
     * @param userId
     * @return
     */
    UserDetailDTO selectUserAndRoleIdByUserId(String userId);
}