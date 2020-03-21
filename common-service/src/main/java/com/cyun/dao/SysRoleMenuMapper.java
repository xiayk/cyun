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

    /**
     * 获取当前角色对应的所有菜单id
     * @param roleId
     * @return
     */
    List<String> lisMenuByRoleId(String roleId);

    /**
     * 删除角色以前的菜单，但是不删除默认菜单
     * @param roleId
     */
    @Delete("delete from sys_role_menu where role_id = #{roleId} and menu_id not in " +
            "(${defaultMenuIdStr})")
    void delMenuByRoleId(@Param("roleId") String roleId, @Param("defaultMenuIdStr") String defaultMenuIdStr);

    @Delete("delete from sys_role_menu where menu_id = #{menuId}")
    void delMenuByMenuId(@Param("menuId") String menuId);

    /**
     * pfhe
     * 查询当前角色拥有的菜单id
     * @param roleId
     * @return
     */
    @Select("select menu_id menuId from sys_role_menu where role_id = #{roleId}")
    List<String> listMyMenuIdsByUserId(String roleId);
}