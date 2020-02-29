package com.cyun.dto;

import java.util.List;

/**
 * Created with IDEA
 * Description:
 *
 * @Auther: xiayk
 * @date: 2019-11-21 15:56
 **/
public class EditMenuDTO extends MenuDTO {

    private List<String> parentNames;

    public List<String> getParentNames() {
        return parentNames;
    }

    public void setParentNames(List<String> parentNames) {
        this.parentNames = parentNames;
    }
}
