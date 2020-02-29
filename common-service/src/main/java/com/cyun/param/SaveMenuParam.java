package com.cyun.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created with IDEA
 * Description:
 *
 * @Auther: xiayk
 * @date: 2019/11/16 下午2:14
 **/
@Data
@ApiModel(value = "com.cyun.param.SaveMenuParam", description = "添加菜单参数")
public class SaveMenuParam {

    @ApiModelProperty(value = "菜单名称", dataType = "String", required = true)
    private String menuName;

    /**
     * 创建人
     */
    private String userId;


    @ApiModelProperty(value = "菜单代码", dataType = "String", required = true)
    private String menuCode;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标", dataType = "String", required = true)
    private String menuIco;

    /**
     * 菜单url
     */
    @ApiModelProperty(value = "菜单url", dataType = "String", required = true)
    private String menuUrl;

    /**
     * 父id
     */
    @ApiModelProperty(value = "父id", dataType = "String", required = true)
    private String parentId;

    /**
     * 状态(0正常，1禁用，2删除)
     */
    @ApiModelProperty(value = "状态(0正常，1禁用，2删除)", dataType = "String", required = true)
    private Integer status;

    /**
     * 能否删除(0不能，2能)
     */
    @ApiModelProperty(value = "能否删除(0不能，2能)", dataType = "String", required = true)
    private Integer type;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", dataType = "String", required = true)
    private Integer sort;

    /**
     * 有该菜单的角色Id
     */
    private List<String> roleIds;
}
