package com.cyun.sys.service.impl;

import com.cyun.dao.SysUserMapper;
import com.cyun.dao.SysUserRoleMapper;
import com.cyun.dto.LoginUserDTO;
import com.cyun.dto.UserDTO;
import com.cyun.dto.UserDetailDTO;
import com.cyun.enums.states.UserStatus;
import com.cyun.model.SysUser;
import com.cyun.model.SysUserRole;
import com.cyun.param.EditPasswordParam;
import com.cyun.param.LoginUserParam;
import com.cyun.param.SaveAndUpdateUserParam;
import com.cyun.param.UserParam;
import com.cyun.result.PageResult;
import com.cyun.sys.service.UserService;
import com.cyun.utils.bean.BeanRewriteUtils;
import com.cyun.utils.misc.IdGenerator;
import com.cyun.utils.spring.UUIDFactory;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final String pwd = "666666";

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public LoginUserDTO login(LoginUserParam param) {
        // passwordEncoder.encode("1");密码加密
        LoginUserDTO user = sysUserMapper.getUserByAccount(param.getAccount());
        if (BeanRewriteUtils.isNullOrEmpty(user)) {
            throw new IllegalArgumentException("账号不存在");
        }
        if (0 != user.getStatus()) {
            throw new IllegalArgumentException("账号已被移除或者冻结");
        }
        if (!passwordEncoder.matches(param.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("用户名或者密码错误");
        }
        // 修改最近登录时间
        sysUserMapper.updateLastTimeByUserId(user.getId());
        return user;
    }

    @Override
    public UserDetailDTO findUserDetail(String userId) {
        return sysUserRoleMapper.selectUserAndRoleIdByUserId(userId);
    }

    @Override
    public void addOrUpdate(SaveAndUpdateUserParam param, String userId) {
        // 一个用户只能对应一个角色并且必填，所以用户和角色同步
        // 新增没有id
        if (StringUtils.isBlank(param.getId())) {
            addUser(param, userId);
        } else { // 修改有id
            updateUser(param, userId);
        }
    }

    /**
     * 新增用户
     */
    public void addUser(SaveAndUpdateUserParam param, String userId) {
        // 判断用户名是否存在
        if (sysUserMapper.countUserByAccount(param.getAccount()) > 0) {
            throw new IllegalArgumentException("账号已存在");
        }
        // 新增用户
        param.setId(IdGenerator.shardId());
        param.setCreateDate(new Date());
        param.setCreateUserId(userId);
        param.setParentId(userId);
        param.setPassword(passwordEncoder.encode(param.getPassword()));
        sysUserMapper.insertSelective(param);
        // 新增角色
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setId(UUIDFactory.newUUID());
        sysUserRole.setRoleId(param.getRoleId());
        sysUserRole.setUserId(param.getId());
        sysUserRoleMapper.insert(sysUserRole);
    }

    /**
     * 修改用户
     */
    public void updateUser(SaveAndUpdateUserParam param, String userId) {
        // 判断用户名是否存在
        if (sysUserMapper.countUserByAccountAndUserId(param.getAccount(), param.getId()) > 0) {
            throw new IllegalArgumentException("账号已存在");
        }
        // 编辑用户
        param.setUpdateDate(new Date());
        param.setUpdateUserId(userId);
        sysUserMapper.updateByPrimaryKeySelective(param);
        //编辑角色
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleId(param.getRoleId());
        sysUserRole.setUserId(param.getId());
        sysUserRoleMapper.updateRole(sysUserRole);
    }

    @Override
    public void updateStatus(SaveAndUpdateUserParam param) {
        param.setUpdateDate(new Date());
        sysUserMapper.updateByPrimaryKeySelective(param);
    }

    @Override
    public void updatePassword(EditPasswordParam param) {
        // 判断旧密码是否正确
        if (!passwordEncoder.matches(param.getOldPassword(), param.getMd5Password())) {
            throw new IllegalArgumentException("旧密码错误");
        }
        //新密码加密
        param.setNewPassword(passwordEncoder.encode(param.getNewPassword()));
        sysUserMapper.updatePassword(param);
    }

    @Override
    public void updateUserRole(SysUserRole sysUserRole) {
        // 用户角色表已有记录
        if (sysUserRoleMapper.countByUserId(sysUserRole.getUserId()) > 0) {
            sysUserRoleMapper.updateRole(sysUserRole);
        } else { // 没有记录
            sysUserRole.setId(IdGenerator.shardId());
            sysUserRoleMapper.insertSelective(sysUserRole);
        }
    }

    @Override
    public PageResult findByPage(UserParam param) {
        return PageResult.PageResult(sysUserMapper.selectUser(param), sysUserMapper.selectCounts(param));
    }

    @Override
    public Boolean resetPassword(String id) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        Preconditions.checkArgument(null != sysUser, "帐号信息不存在");
        if (sysUser.getStatus() == UserStatus.Create.getValue()) {
            sysUser.setPassword(passwordEncoder.encode(pwd));
        } else {
            throw new IllegalArgumentException("该用户已被禁用，不能重置密码");
        }
        return sysUserMapper.updateByPrimaryKeySelective(sysUser) > 0;
    }

}
