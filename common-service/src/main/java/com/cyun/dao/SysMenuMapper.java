package com.cyun.dao;

import com.cyun.dto.MenuDTO;
import com.cyun.model.SysMenu;
import com.cyun.param.PageParam;
import com.cyun.utils.mapper.CommonMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface SysMenuMapper extends CommonMapper<SysMenu> {

    /**
     * 查询同级目录是否存在相同菜单名称
     *
     * @param pid
     * @param menuName
     * @return
     */
    String countMenuByNameAndPId(String pid, String menuName);

    /**
     * 获取所有父菜单
     * @return
     */
    List<MenuDTO> listMenuByParent();

    /**
     * 获取总页数
     *
     * @return
     */
    Integer countAllMenu();

    /**
     * 分页获取所有菜单
     *
     * @param pageParam
     * @return
     */
    List<MenuDTO> limitMenu(PageParam pageParam);

    /**
     *
     * @param menuId
     * @return
     */
    MenuDTO findMenuById(String menuId);

    /**
     *
     * @return
     */
    List<MenuDTO> listTreeMenu(String roleId);


    List<MenuDTO> listTreeMenuListForUser();
}