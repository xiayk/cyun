package com.cyun.result;

import com.cyun.dto.MenuDTO;
import lombok.Data;

import java.util.List;

/**
 * Created with IDEA
 * Description:
 *
 * @Auther: xiayk
 * @date: 2019/11/16 上午12:56
 **/
@Data
public class MenuResult extends MenuDTO {
    /**
     * 子菜单
     */
    private List<MenuResult> childrens;

}
