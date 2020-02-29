package com.cyun.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created with IDEA
 * Description:
 *
 * @Auther: xiayk
 * @date: 2019/11/16 下午2:37
 **/
@Data
@ApiModel(value = "com.cyun.param.EditMenuParam", description = "编辑菜单参数")
public class EditMenuParam extends SaveMenuParam {
    /**
     * 菜单id
     */
    @ApiModelProperty(value = "菜单id", dataType = "String", required = true)
    private String menuId;

    /**
     * 修改者
     */
    @ApiModelProperty(value = "修改者", dataType = "String", required = false)
    private String updateUserId;

    private Date updateDate;

}
