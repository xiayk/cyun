package com.cyun.dto;

import lombok.Data;

import java.util.List;

/**
 * Created with IDEA
 * Description:
 *
 * @Auther: xiayk
 * @date: 2019-11-24 12:42
 **/
@Data
public class RoleDetailDTO extends RoleDTO {

    private List<String> menuIds;
}
