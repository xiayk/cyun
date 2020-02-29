package com.cyun.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * Created Page with IDEA
 * Description: class
 *
 * @Auther: xiayk
 * @date: 2019/11/16 下午10:13
 **/
@Data
@ApiModel(value = "com.cyun.param.PageParam", description = "分页参数")
public class PageParam {

    @ApiModelProperty(value = "Limit", dataType = "Integer", required = true)
    private Integer limit;

    @ApiModelProperty(value = "offset", dataType = "Integer", required = false)
    private Integer offset;
}
