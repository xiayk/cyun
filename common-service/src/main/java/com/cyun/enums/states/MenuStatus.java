package com.cyun.enums.states;

/**
 * Created with IDEA
 * Description: 菜单枚举状态(0,正常; 1,禁用, 删除)
 *
 * @Auther: xiayk
 * @date: 2019/11/16 下午5:05
 **/
public enum MenuStatus {

    Create("0","正常"),
    Enable("1","禁用"),
    Delete("2","删除");

    private String value;
    private String desc;

    MenuStatus(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public MenuStatus setValue(String value) {
        this.value = value;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public MenuStatus setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    private MenuStatus(String desc) {
        this.desc = desc;
    }

    public static MenuStatus parse(String value) {
        if (null == value) {
            return null;
        }
        MenuStatus[] coll = values();
        for (MenuStatus item : coll) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
    public static MenuStatus parseDesc(String desc) {
        if (null == desc) {
            return null;
        }
        MenuStatus[] coll = values();
        for (MenuStatus item : coll) {
            if (item.getDesc().equals(desc)) {
                return item;
            }
        }
        return null;
    }

}
