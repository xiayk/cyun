package com.cyun.dto;

import lombok.Data;

import java.util.List;

/**
 * Created with IDEA
 * Description:
 *
 * @Auther: xiayk
 * @date: 2019-11-27 17:01
 **/
@Data
public class MenuDetailDTO extends MenuDTO {
    private List<String> roleIds;
}
