package com.cyun.sys.service;

import com.cyun.dto.MenuDTO;
import com.cyun.dto.MenuDetailDTO;
import com.cyun.param.SaveMenuParam;
import com.cyun.param.EditMenuParam;
import com.cyun.param.PageParam;
import com.cyun.result.EditMenuResult;
import com.cyun.result.PageResult;
import com.cyun.result.MenuResult;

import java.util.List;
import java.util.Map;

public interface MenuService {

    /**
     * 获取菜单
     * @param userId
     * @return
     */
    List<MenuResult> listMyMenu(String userId);

    /**
     * 分页获取菜单
     * @param pageParam
     * @return
     */
    PageResult listAllMenu(PageParam pageParam);

    /**
     * 添加菜单
     * @param param
     */
    void saveMenu(SaveMenuParam param);

    /**
     * 编辑菜单
     * @param param
     */
    void editMenu(EditMenuParam param);

    /**
     * 删除菜单
     * @param menuId
     */
    void delMenu(String menuId, String delUserId);

    /**
     * 获取菜单详情
     * @param menuId
     * @return
     */
    MenuDetailDTO menuDetail(String menuId);

    /**
     * 格式化所有菜单
     * @return
     */
    List<MenuResult> getTreeMenuList(String roleId);

    /**
     * 获取父菜单
     * @return
     */
    List<MenuDTO> getAllParentMenu();
}
