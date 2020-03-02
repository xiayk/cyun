package com.cyun.enums.states;

/**
 * @createUser daiyuan
 * @createTime 2019/11/16 19:50
 * @describe 用户状态枚举(0正常,1禁用,2删除)
 **/
public enum UserStatus {

    Create(0,"正常"),
    Enable(1,"禁用"),
    Delete(2,"删除");

    private Integer value;
    private String desc;

    UserStatus(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public UserStatus setValue(Integer value) {
        this.value = value;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public UserStatus setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    private UserStatus(String desc) {
        this.desc = desc;
    }

    public static UserStatus parse(Integer value) {
        if (null == value) {
            return null;
        }
        UserStatus[] coll = values();
        for (UserStatus item : coll) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
    public static UserStatus parseDesc(String desc) {
        if (null == desc) {
            return null;
        }
        UserStatus[] coll = values();
        for (UserStatus item : coll) {
            if (item.getDesc().equals(desc)) {
                return item;
            }
        }
        return null;
    }


}
