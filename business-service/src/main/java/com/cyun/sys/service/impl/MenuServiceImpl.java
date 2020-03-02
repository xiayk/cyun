package com.cyun.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.cyun.dao.SysMenuMapper;
import com.cyun.dao.SysRoleMenuMapper;
import com.cyun.dto.EditMenuDTO;
import com.cyun.dto.MenuDTO;
import com.cyun.dto.MenuDetailDTO;
import com.cyun.enums.states.MenuType;
import com.cyun.model.SysMenu;
import com.cyun.model.SysRoleMenu;
import com.cyun.param.SaveMenuParam;
import com.cyun.param.EditMenuParam;
import com.cyun.param.PageParam;
import com.cyun.result.EditMenuResult;
import com.cyun.result.PageResult;
import com.cyun.result.MenuResult;
import com.cyun.sys.service.MenuService;
import com.cyun.utils.bean.BeanRewriteUtils;
import com.cyun.utils.spring.UUIDFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.util.*;

/**
 * Created with IDEA
 * Description:
 *
 * @Auther: xiayk
 * @date: 2019/11/16 上午12:24
 **/
@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<MenuResult> listMyMenu(String userId) {
        return parentMenu(sysRoleMenuMapper.listMyMenu(userId));
    }

    @Override
    public PageResult listAllMenu(PageParam pageParam) {
        return PageResult.PageResult(sysMenuMapper.limitMenu(pageParam), sysMenuMapper.countAllMenu());
    }

    @Override
    public void editMenu(EditMenuParam param) {
        isMenu(param.getMenuId());
        isRepeatMenuName(param.getMenuId(), param.getParentId(), param.getMenuName());
        param.setUpdateDate(new Date());
        SysMenu menu = (SysMenu) BeanRewriteUtils.populate(param, new SysMenu());
        menu.setId(param.getMenuId());

        //角色存入菜单角色关系表
        SysRoleMenu roleMenu = new SysRoleMenu();

        //接收父菜单为空 设该菜单为父菜单
        if (menu.getParentId() == null) {
            menu.setParentId("0");
        }
        roleMenu.setMenuId(menu.getId());
        sysRoleMenuMapper.delMenuByMenuId(menu.getId());
        param.getRoleIds().forEach(roleId -> {
            if (sysRoleMenuMapper.countRoleIdAndMenuId(roleId, menu.getId()) == 0) {
                roleMenu.setId(UUIDFactory.newUUID());
                roleMenu.setRoleId(roleId);
                sysRoleMenuMapper.insert(roleMenu);
            }
        });
        sysMenuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public List<MenuDTO> getAllParentMenu() {
        return sysMenuMapper.listMenuByParent();
    }

    /**
     * 菜单是否存在
     *
     * @param menuId
     */
    private void isMenu(String menuId) {
        if (sysMenuMapper.selectByPrimaryKey(menuId) == null) {
            throw new IllegalArgumentException("菜单不存在");
        }
    }

    @Override
    public void delMenu(String menuId, String delUserId) {
        isMenu(menuId);
        //获取菜单类型0无法被删除
        if (sysMenuMapper.selectByPrimaryKey(menuId).getType() == 0) {
            throw new IllegalArgumentException("该菜单无法被删除");
        }
        SysMenu menu = new SysMenu();
        menu.setId(menuId);
        menu.setStatus(2);
        menu.setUpdateUserId(delUserId);
        menu.setUpdateDate(new Date());
        sysMenuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public void saveMenu(SaveMenuParam param) {
        SysMenu menu = (SysMenu) BeanRewriteUtils.populate(param, new SysMenu());
        isRepeatMenuName(null, menu.getParentId(), menu.getMenuName());
        menu.setCreateDate(new Date());
        menu.setId(UUIDFactory.newUUID());
        menu.setStatus(0);
        menu.setType(2);

        //menu role关系表添加数据
        if (BeanRewriteUtils.isNotNullOrEmpty(param.getRoleIds())) {
            param.getRoleIds().forEach(roleId -> {
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setId(UUIDFactory.newUUID());
                roleMenu.setMenuId(menu.getId());
                roleMenu.setRoleId(roleId);
                sysRoleMenuMapper.insert(roleMenu);
            });
        }
        sysMenuMapper.insert(menu);
    }

    @Override
    public MenuDetailDTO menuDetail(String menuId) {
        isMenu(menuId);
        //sysMenuMapper.findMenuById(menuId);
        return sysRoleMenuMapper.selectMenuDetailById(menuId);
    }

    /**
     * 判断菜单名重复
     *
     * @param pid
     * @param menuName
     */
    private void isRepeatMenuName(String menuId, String pid, String menuName) {
        String id = sysMenuMapper.countMenuByNameAndPId(pid, menuName);
        if (!StringUtils.isEmpty(id)) {
            if (id.equals(menuId)) {
                return;
            }
            throw new IllegalArgumentException("该目录下存在相同名称的菜单");
        }
    }

    @Override
    public List<MenuResult> getTreeMenuList(String roleId) {
        //获取所有菜单
        List<MenuDTO> dtoList = sysMenuMapper.listTreeMenu(roleId);
//
//        if (StringUtils.isEmpty(roleId)){
//            return formatSelectMenu(dtoList, null);
//        }
//        return formatSelectMenu(dtoList, sysMenuMapper.listTreeMenu(roleId));
        return parentMenu(dtoList);
    }

    @Override
    public List<MenuResult> listTreeMenuListForUser() {
        //获取所有菜单
        List<MenuDTO> dtoList = sysMenuMapper.listTreeMenuListForUser();
        return parentMenu(dtoList);
    }

    /**
     * 获取角色选中菜单
     *
     * @param treeList       所有菜单的树形
     * @param selectMenuList 选中的菜单
     * @return
     */
    private List<EditMenuResult> formatSelectMenu(List<MenuDTO> treeList, List<MenuDTO> selectMenuList) {
        List<EditMenuResult> selectMenus = BeanRewriteUtils.populateList(parentMenu(treeList), new ArrayList<EditMenuResult>(), EditMenuResult.class);
        log.info("formatSelectMenu() selectMenus={}", treeList);
        if (selectMenuList.isEmpty()) {
            return selectMenus;
        }
        selectMenus.forEach(menuDTO -> {
            log.info(menuDTO.getId());
            selectMenuList.forEach(menu -> {
                boolean flag = false;
                if (menu.getId().equals(menuDTO.getId())) {
                    flag = true;
                }
                menuDTO.setChecked(flag);
            });
        });
        return selectMenus;
    }

    /**
     * 处理菜单
     *
     * @param menuDTOList
     * @return
     */
    private List<MenuResult> parentMenu(List<MenuDTO> menuDTOList) {
        List<MenuResult> list = new ArrayList<>();

        //获取一级菜单
        menuDTOList.forEach(menuDTO -> {
            if ("".equals(menuDTO.getParentId()) || menuDTO.getParentId().equals("0") && menuDTO.getStatus() == 0) {
                list.add((MenuResult) BeanRewriteUtils.populate(menuDTO, new MenuResult()));
            }
        });

        //子菜单迭代
        list.forEach(menuResult -> {
            List<MenuResult> node = new ArrayList<>();
            menuDTOList.forEach(menuDTO -> {
                //菜单状态为0 父菜单不为空 父菜单
                if (menuDTO.getStatus() == 0 && BeanRewriteUtils.isNotNullOrEmpty(menuDTO.getParentId()) && menuDTO.getParentId().equals(menuResult.getId())) {
                    node.add((MenuResult) BeanRewriteUtils.populate(menuDTO, new MenuResult()));
                }
            });
            menuResult.setChildrens(node);
        });
        return list;
    }

//    public static void main(String[] args) {
//        List<MenuDTO> menuDTOList = new ArrayList<>();
//        MenuDTO dto = new MenuDTO();
//        dto.setMenuName("name0");
//        dto.setCreateDate(new Date());
//        dto.setId("1");
//        dto.setParentId(null);
//        menuDTOList.add(dto);
//
//        MenuDTO dto1 = new MenuDTO();
//        dto1.setMenuName("name1");
//        dto1.setCreateDate(new Date());
//        dto1.setId("2");
//        dto1.setParentId(null);
//        menuDTOList.add(dto1);
//
//        MenuDTO dto2 = new MenuDTO();
//        dto2.setMenuName("name3");
//        dto2.setCreateDate(new Date());
//        dto2.setId("3");
//        dto2.setParentId("1");
//        menuDTOList.add(dto2);
//
//        MenuDTO dto3 = new MenuDTO();
//        dto3.setMenuName("name4");
//        dto3.setCreateDate(new Date());
//        dto3.setId("4");
//        dto3.setParentId("2");
//        menuDTOList.add(dto3);
//
//        MenuDTO dto4 = new MenuDTO();
//        dto4.setMenuName("name5");
//        dto4.setCreateDate(new Date());
//        dto4.setId("5");
//        dto4.setParentId("1");
//        menuDTOList.add(dto4);
//
//        List<MenuResult> parentMen = new MenuServiceImpl().parentMenu(menuDTOList);
//        parentMen.forEach(menuResult -> {
//            System.out.println(JSON.toJSON(menuResult));
//        });
//    }
}
