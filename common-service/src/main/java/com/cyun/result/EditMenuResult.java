package com.cyun.result;

import lombok.Data;

/**
 * Created with IDEA
 * Description:
 *
 * @Auther: xiayk
 * @date: 2019-12-02 13:15
 **/
@Data
public class EditMenuResult extends MenuResult {

    /**
     * 是否选中
     */
    private boolean checked;
}
