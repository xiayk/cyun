package com.cyun.sys.service;

import com.cyun.dto.LoginUserDTO;
import com.cyun.dto.UserDetailDTO;
import com.cyun.model.SysUserRole;
import com.cyun.param.EditPasswordParam;
import com.cyun.param.LoginUserParam;
import com.cyun.param.SaveAndUpdateUserParam;
import com.cyun.param.UserParam;
import com.cyun.result.PageResult;

public interface UserService {

    /**
     * 登录
     *
     * @param param
     * @return
     */
    LoginUserDTO login(LoginUserParam param);

    /**
     * 新增或者修改用户信息
     *
     * @param param
     */
    void addOrUpdate(SaveAndUpdateUserParam param, String userId);

    /**
     * 启用/禁用/删除用户
     *
     * @param param
     */
    void updateStatus(SaveAndUpdateUserParam param);

    /**
     * 修改用户密码
     *
     * @param param
     */
    void updatePassword(EditPasswordParam param);

    /**
     * 修改用户角色
     *
     * @param sysUserRole
     */
    void updateUserRole(SysUserRole sysUserRole);

    /**
     * @createUser daiyuan
     * @createTime 2019/11/19 1:33 PM
     * @describe 查询用户列表
     **/

    PageResult findByPage(UserParam param);

    /**
     * @createUser daiyuan
     * @createTime 2019/11/24 5:36 PM
     * @describe 重置密码
     **/

    Boolean resetPassword (String id);

    /**
     * 获取用户详情
     * @param userId
     * @return
     */
    UserDetailDTO findUserDetail(String userId);
}
