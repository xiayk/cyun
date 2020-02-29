package com.cyun.enums.states;

/**
 * Created with IDEA
 * Description: 菜单类型枚举(0,无法删除, 1,普通)
 *
 * @Auther: xiayk
 * @date: 2019-11-19 22:51
 **/
public enum MenuType {
    NOTDELETE(0, "无法删除"),
    DELETE(2, "");

    private Integer value;
    private String desc;

    MenuType(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public MenuType setValue(Integer value) {
        this.value = value;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public MenuType setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    private MenuType(String desc) {
        this.desc = desc;
    }

    public static MenuType parse(Integer value) {
        if (0 == value) {
            return null;
        }
        MenuType[] coll = values();
        for (MenuType item : coll) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
    public static MenuType parseDesc(String desc) {
        if (null == desc) {
            return null;
        }
        MenuType[] coll = values();
        for (MenuType item : coll) {
            if (item.getDesc().equals(desc)) {
                return item;
            }
        }
        return null;
    }
}
