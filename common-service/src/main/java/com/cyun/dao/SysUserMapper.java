package com.cyun.dao;

import com.cyun.dto.LoginUserDTO;
import com.cyun.dto.UserDTO;
import com.cyun.model.SysUser;
import com.cyun.param.EditPasswordParam;
import com.cyun.param.LoginUserParam;
import com.cyun.param.UserParam;
import com.cyun.utils.mapper.CommonMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SysUserMapper extends CommonMapper<SysUser> {

    /**
     * 根据用户名查询用户信息
     *
     * @param account
     * @return
     */
    @Select("select id, account, password, user_name userName, phone, status, parent_id parentId from sys_user where account = #{account} ")
    LoginUserDTO getUserByAccount(@Param("account") String account);

    /**
     * 修改用户状态，根据用户id
     *
     * @param userId
     * @param status
     * @return
     */
    @Update("update sys_menu set status = #{status} where id = #{userId}")
    Integer updateStatusByUserId(String userId, Integer status);

    /**
     * 根据账号查询用户数量
     *
     * @param account
     * @return
     */
    @Select("select count(id) from sys_user where account = #{account} and status != 2 and id != #{userId}")
    Integer countUserByAccountAndUserId(@Param("account") String account, @Param("userId") String userId);

    /**
     * 根据账号查询用户数量
     *
     * @param account
     * @return
     */
    @Select("select count(id) from sys_user where account = #{account} and status != 2")
    Integer countUserByAccount(@Param("account") String account);

    /**
     * 根据id修改用户密码
     *
     * @param param
     * @return
     */
    @Select("update sys_user set password = #{param.newPassword} where id = #{param.userId} and status != 2")
    Integer updatePassword(@Param("param") EditPasswordParam param);

    /**
     * 修改最近登录时间
     *
     * @param userId
     * @return
     */
    @Update("update sys_user set last_date = now() where id = #{userId}")
    Integer updateLastTimeByUserId(String userId);

    /**
     * @createUser daiyuan
     * @createTime 2019/11/19 12:27 PM
     * @describe 查询用户列表
     **/
    List<UserDTO> selectUser (UserParam param);

    /**
     * @createUser daiyuan
     * @createTime 2019/11/19 12:48 PM
     * @describe 查询总数
     **/
    Integer selectCounts (UserParam param);
}