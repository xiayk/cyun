package com.cyun.sys.controller;

import com.cyun.param.EditMenuParam;
import com.cyun.param.SaveMenuParam;
import com.cyun.param.PageParam;
import com.cyun.sys.service.MenuService;
import com.cyun.utils.http.HttpUtil;
import com.cyun.utils.http.JSONResult;
import com.cyun.utils.token.UserTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IDEA
 * Description:
 *
 * @Auther: xiayk
 * @date: 2019/11/15 下午7:53
 **/
@RestController
@RequestMapping("/menu")
@Api(value = "菜单管理", tags = {"菜单管理"})
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 获取菜单
     * @param
     * @return
     */
    @PostMapping("/my")
    @ApiOperation(value = "获取我的菜单")
    public JSONResult getMyMenu() throws Exception {
        return HttpUtil.writeJSONObject(menuService.listMyMenu(UserTokenUtils.getLoginUserDTO().getId()));
    }

    @PostMapping("/save")
    @ApiOperation("添加菜单")
    public JSONResult saveMenu(@RequestBody SaveMenuParam param) throws Exception{
        param.setUserId(UserTokenUtils.getLoginUserDTO().getId());
        menuService.saveMenu(param);
        return HttpUtil.writeSuccessJSON();
    }

    @PostMapping("/list")
    @ApiOperation("获取所有菜单")
    public JSONResult listAllMenu(@RequestBody PageParam pageParam){
        return HttpUtil.writeJSONObject(menuService.listAllMenu(pageParam));
    }

    @PostMapping("/edit")
    @ApiOperation("编辑菜单")
    public JSONResult editMenu(@RequestBody EditMenuParam param) throws Exception{
        param.setUpdateUserId(UserTokenUtils.getLoginUserDTO().getId());
        menuService.editMenu(param);
        return HttpUtil.writeSuccessJSON();
    }

    @PostMapping("/select/parent")
    @ApiOperation("获取所有父菜单")
    public JSONResult parentMenu(){
        return HttpUtil.writeSuccessJSON(menuService.getAllParentMenu());
    }

    @PostMapping("/detail/{menuId}")
    @ApiOperation("获取菜单详情")
    public JSONResult detailMenu(@PathVariable("menuId") String menuId){
        return HttpUtil.writeSuccessJSON(menuService.menuDetail(menuId));
    }

    @PostMapping("/del/{menuId}")
    @ApiOperation("删除菜单")
    public JSONResult delMenu(@PathVariable("menuId") String menuId) throws Exception{
        menuService.delMenu(menuId, UserTokenUtils.getLoginUserDTO().getId());
        return HttpUtil.writeSuccessJSON();
    }

    @PostMapping("/tree")
    @ApiOperation("获取树形菜单")
    public JSONResult getAllMenuList(){
        return HttpUtil.writeSuccessJSON(menuService.getTreeMenuList(null));
    }

    @PostMapping("/tree/{roleId}")
    @ApiOperation("获取角色树形菜单")
    public JSONResult getRoleMenuList(@PathVariable("roleId") String roleId){
        return HttpUtil.writeSuccessJSON(menuService.getTreeMenuList(roleId));
    }
}
