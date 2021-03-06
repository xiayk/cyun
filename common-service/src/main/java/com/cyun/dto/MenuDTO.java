package com.cyun.dto;

import com.cyun.model.SysMenu;
import lombok.Data;

/**
 * Created with IDEA
 * Description:
 *
 * @Auther: xiayk
 * @date: 2019/11/15 下午11:53
 **/
@Data
public class MenuDTO extends SysMenu {

    private String parentName;

    /**
     * 操作状态(1不能操作，0能操作)
     */
    private Integer optStatus;

    /**
     * 是否选中true选中，false未选中
     */
    private boolean checked;
}
