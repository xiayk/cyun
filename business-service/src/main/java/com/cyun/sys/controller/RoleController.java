package com.cyun.sys.controller;

import com.cyun.dto.LoginUserDTO;
import com.cyun.param.*;
import com.cyun.sys.service.RoleService;
import com.cyun.utils.http.HttpUtil;
import com.cyun.utils.http.JSONResult;
import com.cyun.utils.token.UserTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IDEA
 * Description:
 *
 * @Auther: xiayk
 * @date: 2019/11/16 下午5:05
 **/
@RestController
@RequestMapping("/role")
@Api(value = "角色管理", tags = {"角色管理"})
public class RoleController {

    @Value("${sys.param.default_menu_id}")
    private String defaultMenuIdStr;

    @Autowired
    private RoleService roleService;

    @PostMapping("/save")
    @ApiOperation("添加角色")
    public JSONResult saveRole(@RequestBody SaveRoleParam param) throws Exception {
        param.setUserId(UserTokenUtils.getLoginUserDTO().getId());
        roleService.saveRole(param);
        return HttpUtil.writeSuccessJSON();
    }

    @PostMapping("/detail/{roleId}")
    @ApiOperation("获取角色详情")
    public JSONResult getRoleDetail(@PathVariable("roleId") String roleId) {
        return HttpUtil.writeSuccessJSON(roleService.getRoleDetail(roleId));
    }

    @PostMapping("/list")
    @ApiOperation("获取所有角色")
    public JSONResult getAllRole(@RequestBody ListRoleParam param) {
        return HttpUtil.writeSuccessJSON(roleService.listRoles(param));
    }

    @PostMapping("/edit")
    @ApiOperation("编辑角色")
    public JSONResult editRole(@RequestBody EditRoleParam param) throws Exception {
        param.setUpdateUserId(UserTokenUtils.getLoginUserDTO().getId());
        param.setDefaultMenuIdStr(defaultMenuIdStr);
        roleService.editRole(param);
        return HttpUtil.writeSuccessJSON();
    }

    @PostMapping("/save/menu")
    @ApiOperation("角色赋菜单")
    public JSONResult saveMenuToRole(@RequestBody SaveMenuToRoleParam param) {
        roleService.SaveMenuToRole(param);
        return HttpUtil.writeSuccessJSON();
    }

    @PostMapping("/del/{roleId}")
    @ApiOperation("删除角色")
    public JSONResult delRole(@PathVariable String roleId) throws Exception {
        roleService.delRole(roleId, UserTokenUtils.getLoginUserDTO().getId());
        return HttpUtil.writeSuccessJSON();
    }

    @PostMapping("/tree")
    @ApiOperation("角色列表")
    public JSONResult getRoleTree() throws Exception {
        LoginUserDTO loginUserDTO = UserTokenUtils.getLoginUserDTO();
        return HttpUtil.writeSuccessJSON(roleService.getRoleTree(loginUserDTO.getAccount().equals("admin") ? 1 : 0));
    }
}
