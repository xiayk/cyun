package com.cyun.dao;

import com.cyun.dto.MenuDTO;
import com.cyun.dto.MenuDetailDTO;
import com.cyun.dto.RoleDetailDTO;
import com.cyun.model.SysRoleMenu;
import com.cyun.utils.mapper.CommonMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysRoleMenuMapper extends CommonMapper<SysRoleMenu> {

    List<MenuDTO> listMyMenu(String userId);

    /**
     *
     * @param roleId
     * @param menuId
     * @return
     */
    @Select("select count(*) from sys_role_menu where menu_id = #{menuId} and role_id = #{roleId}")
    Integer countRoleIdAndMenuId(@Param("roleId") String roleId, @Param("menuId") String menuId);

    /**
     * 获取菜单详情(含有改菜单的角色)
     * @param menuId
     * @return
     */
    MenuDetailDTO selectMenuDetailById(String menuId);

    /**
     * 获取角色所有菜单
     * @param roleId
     * @return
     */
    @Select("select id,menu_id menuId, role_id roleId from sys_role_menu where role_id = #{roleId}")
    List<SysRoleMenu> listMenuIdByRoleId(@Param("roleId") String roleId);

    @Delete("delete from sys_role_menu where role_id = #{roleId}")
    void delMenuByRoleId(@Param("roleId") String roleId);

    @Delete("delete from sys_role_menu where menu_id = #{menuId}")
    void delMenuByMenuId(@Param("menuId") String menuId);
}