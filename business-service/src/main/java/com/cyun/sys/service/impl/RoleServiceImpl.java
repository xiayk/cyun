package com.cyun.sys.service.impl;

import com.cyun.dao.SysMenuMapper;
import com.cyun.dao.SysRoleMapper;
import com.cyun.dao.SysRoleMenuMapper;
import com.cyun.dto.MenuDTO;
import com.cyun.dto.RoleDTO;
import com.cyun.dto.RoleDetailDTO;
import com.cyun.dto.RoleTreeDTO;
import com.cyun.model.SysRole;
import com.cyun.model.SysRoleMenu;
import com.cyun.param.*;
import com.cyun.result.PageResult;
import com.cyun.sys.service.RoleService;
import com.cyun.utils.bean.BeanRewriteUtils;
import com.cyun.utils.spring.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;

import java.util.Date;
import java.util.List;

/**
 * Created RoleServiceImpl with IDEA
 * Description: class
 *
 * @Auther: xiayk
 * @date: 2019/11/17 下午12:23
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public void saveRole(SaveRoleParam param) {
        if (sysRoleMapper.selectRoleByName(param.getRoleName()) > 0) {
            throw new IllegalArgumentException("角色名已存在");
        }
        SysRole role = (SysRole) BeanRewriteUtils.populate(param, new SysRole());
        role.setCreateDate(new Date());
        role.setId(UUIDFactory.newUUID());
        if (BeanRewriteUtils.isNotNullOrEmpty(param.getMenuIds())) {
            param.getMenuIds().forEach(menuId -> {
                //获取菜单对象
                MenuDTO menuDTO = sysMenuMapper.findMenuById(menuId);
                //判断是否为父菜单
                if (!StringUtil.isEmpty(menuDTO.getParentId()) && !"0".equals(menuDTO.getParentId())) {
                    SysRoleMenu roleMenu = new SysRoleMenu();
                    roleMenu.setId(UUIDFactory.newUUID());
                    roleMenu.setRoleId(role.getId());
                    roleMenu.setMenuId(menuId);
                    sysRoleMenuMapper.insert(roleMenu);

                    //关系表中获取父菜单是否添加
                    if (sysRoleMenuMapper.countRoleIdAndMenuId(roleMenu.getRoleId(), menuDTO.getParentId()) <= 0) {
                        roleMenu.setId(UUIDFactory.newUUID());
                        roleMenu.setMenuId(menuDTO.getParentId());
                        sysRoleMenuMapper.insert(roleMenu);
                    }
                }
            });
        }
        sysRoleMapper.insert(role);
    }

    @Override
    public PageResult listRoles(ListRoleParam param) {
        return PageResult.PageResult(sysRoleMapper.listLimitRole(param), sysRoleMapper.countAllRoles(param));
    }

    @Override
    public void delRole(String roleId, String delUserId) {
        isRole(roleId);
        SysRole role = new RoleDTO();
        role.setId(roleId);
        role.setStatus(2);
        role.setUpdateUserId(delUserId);
        role.setUpdateDate(new Date());
        sysRoleMapper.updateByPrimaryKeySelective(role);
    }

    /**
     * 角色是否存在
     *
     * @param roleId
     */
    private void isRole(String roleId) {
        if (sysRoleMapper.selectByPrimaryKey(roleId) == null) {
            throw new IllegalArgumentException("角色不存在");
        }
    }

    @Override
    public void editRole(EditRoleParam param) {
        isRole(param.getRoleId());
        SysRole role = (SysRole) BeanRewriteUtils.populate(param, new SysRole());
        role.setId(param.getRoleId());
        role.setUpdateUserId(param.getUpdateUserId());
        role.setUpdateDate(new Date());
        //删除原菜单
        sysRoleMenuMapper.delMenuByRoleId(param.getRoleId(), param.getDefaultMenuIdStr());
        //重新给菜单
        param.getMenuIds().forEach(menuId -> {
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setId(UUIDFactory.newUUID());
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(param.getRoleId());
            sysRoleMenuMapper.insert(roleMenu);
        });
        sysRoleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public void SaveMenuToRole(SaveMenuToRoleParam param) {
        SysRoleMenu sysRoleMenu = (SysRoleMenu) BeanRewriteUtils.populate(param, new SysRoleMenu());
        sysRoleMenu.setId(UUIDFactory.newUUID());
        sysRoleMenuMapper.insert(sysRoleMenu);
    }

    @Override
    public List<RoleDTO> getRoleTree(Integer isAdmin) {
        return sysRoleMapper.listRoleTree(isAdmin);
    }

    @Override
    public RoleDTO getRoleDetail(String roleId) {
        RoleDTO roleDTO = sysRoleMapper.selectRoleDetail(roleId);
        // 查询角色对应菜单id
        roleDTO.setMenuIds(sysRoleMenuMapper.lisMenuByRoleId(roleId));
        return roleDTO;
    }
}
