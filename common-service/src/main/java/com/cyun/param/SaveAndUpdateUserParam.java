package com.cyun.param;

import com.cyun.model.SysUser;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 添加用户或者修改用户传入参数
 */
@Data
@ApiModel(value = "com.cyun.param.SaveAndUpdateUserParam", description = "添加菜单参数")
public class SaveAndUpdateUserParam extends SysUser {
    private String roleId;
}
