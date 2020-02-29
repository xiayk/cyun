package com.cyun.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_menu")
@Data
public class SysMenu {
    /**
     *  id
     */
    @Id
    private String id;

    /**
     * 菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 菜单code
     */
    @Column(name = "menu_code")
    private String menuCode;

    /**
     * 菜单图标
     */
    @Column(name = "menu_ico")
    private String menuIco;

    /**
     * 菜单url
     */
    @Column(name = "menu_url")
    private String menuUrl;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 创建者
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 修改时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 修改者
     */
    @Column(name = "update_user_id")
    private String updateUserId;

    /**
     * 父id
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 状态(0正常，1禁用，2删除)
     */
    private Integer status;

    /**
     * 能否删除(0不能，2能)
     */
    private Integer type;

    /**
     * 排序
     */
    private Integer sort;
}