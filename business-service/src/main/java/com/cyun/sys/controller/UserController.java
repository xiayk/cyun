package com.cyun.sys.controller;

import com.alibaba.fastjson.JSON;
import com.cyun.dto.LoginUserDTO;
import com.cyun.dto.UserDTO;
import com.cyun.model.SysUserRole;
import com.cyun.param.EditPasswordParam;
import com.cyun.param.LoginUserParam;
import com.cyun.param.SaveAndUpdateUserParam;
import com.cyun.param.UserParam;
import com.cyun.sys.service.UserService;
import com.cyun.utils.http.HttpUtil;
import com.cyun.utils.http.JSONResult;
import com.cyun.utils.misc.PageResult;
import com.cyun.utils.token.UserTokenUtils;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@Api(value = "用户管理", tags = {"用户管理"})
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @ApiOperation("登录：使用账号和密码")
    @PostMapping("/login")
    public JSONResult<String> login(@RequestBody LoginUserParam param) {
        log.info(JSON.toJSONString(param));
        LoginUserDTO userDTO = userService.login(param);
        String token = UserTokenUtils.saveUserToken(userDTO, userDTO.getId());
        return HttpUtil.writeJSONObject(token);
    }

    @ApiOperation("退出登陆")
    @GetMapping("/quit")
    public JSONResult<String> outLogin() throws Exception {
        UserTokenUtils.removeUserToken(UserTokenUtils.getLoginUserId());
        return HttpUtil.writeSuccessJSON();
    }

    @ApiOperation("新增/修改用户/")
    @PostMapping("/addOrUpdate")
    public JSONResult<String> addOrUpdate(@RequestBody SaveAndUpdateUserParam param) throws Exception {
        userService.addOrUpdate(param, UserTokenUtils.getLoginUserId());
        return HttpUtil.writeSuccessJSON();
    }

    @ApiOperation("启用/禁用/删除用户")
    @PostMapping("/update/status")
    public JSONResult<String> updateStatus(@RequestBody SaveAndUpdateUserParam param) throws Exception {
        param.setUpdateUserId(UserTokenUtils.getLoginUserId());
        userService.updateStatus(param);
        log.info("移除"+param.getId()+"的缓存");
        UserTokenUtils.removeUserToken(param.getId());
        return HttpUtil.writeSuccessJSON();
    }

    @ApiOperation("修改用户密码")
    @PostMapping("/update/password")
    public JSONResult<String> updatePassWord(@RequestBody EditPasswordParam param) throws Exception {
        param.setMd5Password(UserTokenUtils.getLoginUserDTO().getPassword());
        param.setUserId(UserTokenUtils.getLoginUserId());
        userService.updatePassword(param);
        return HttpUtil.writeSuccessJSON();
    }

    @ApiOperation("修改用户角色")
    @PostMapping("/update/role")
    public JSONResult<String> updatePassWord(@RequestBody SysUserRole sysUserRole) throws Exception {
        userService.updateUserRole(sysUserRole);
        return HttpUtil.writeSuccessJSON();
    }

    @ApiOperation("查询用户列表")
    @PostMapping("/list")
    public JSONResult findByPage(@RequestBody UserParam param) throws Exception {
        LoginUserDTO loginUserDTO = UserTokenUtils.getLoginUserDTO();
        param.setIsAdmin(loginUserDTO.getAccount().equals("admin") ? 1 : 0);
        param.setUserId(loginUserDTO.getId());
        return HttpUtil.writeSuccessJSON(userService.findByPage(param));
    }

    @ApiOperation("查询我的信息")
    @PostMapping("/info/my")
    public JSONResult findMyInfo() throws Exception {
        return HttpUtil.writeSuccessJSON(UserTokenUtils.getLoginUserDTO());
    }

    @ApiOperation("查询用户信息")
    @PostMapping("/detail/{userId}")
    public JSONResult findUserInfo(@PathVariable("userId") String userId) throws Exception {
        return HttpUtil.writeSuccessJSON(userService.findUserDetail(userId));
    }

    @ApiOperation("重置密码(666666)")
    @PostMapping("/reset/{id}")
    public JSONResult<Boolean> resetPassword(@PathVariable("id") String id) {
        return HttpUtil.writeSuccessJSON(userService.resetPassword(id));
    }

    @ApiOperation("锁屏密码效验(666666)")
    @PostMapping("/lock/login/{password}")
    public JSONResult<String> lockLogin(@PathVariable("password") String password) throws Exception {
        LoginUserDTO loginUserDTO = UserTokenUtils.getLoginUserDTO();
        if (!passwordEncoder.matches(password, loginUserDTO.getPassword())) {
            throw new IllegalArgumentException("密码错误");
        }
        return HttpUtil.writeSuccessJSON();
    }
}
