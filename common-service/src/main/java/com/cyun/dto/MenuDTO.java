package com.cyun.dto;

import com.cyun.model.SysMenu;

/**
 * Created with IDEA
 * Description:
 *
 * @Auther: xiayk
 * @date: 2019/11/15 下午11:53
 **/
public class MenuDTO extends SysMenu {

    private String parentName;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
