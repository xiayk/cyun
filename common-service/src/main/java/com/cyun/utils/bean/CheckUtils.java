package com.cyun.utils.bean;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/**
 * 判断内容是否存在,减少系统错误的可能
 * IllegalArgumentException是非法参数异常，报这个异常说明传入的参数违反了一个方法要求的某些特性。
 */
public class CheckUtils {

    /**
     * 判断字符串是否为空，如果为空抛出异常提示内容
     *
     * @param content 字符串内容
     * @param msg     异常提示内容
     */
    public void checkString(String content, String msg) {
        if (StringUtils.isBlank(content)) {
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * 根据flag的状态判断是否抛出异常提示
     * flag为true，抛出异常。flag为false，不抛出异常
     *
     * @param flag boolean类型的值
     * @param msg  异常提示内容
     */
    public void checkBoolean(Boolean flag, String msg) {
        if (!flag) {
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * 判断对象是否为空，如果为空抛出异常提示内容
     *
     * @param object 除String以外的其他类型
     * @param msg    异常提示内容
     */
    public void checkNull(Object object, String msg) {
        if (object == null) {
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * 判断分页必要字段是否为空，如果为空抛出异常提示内容
     *
     * @param object
     */
    public void checkPagesParam(Object object) {
        Class<?> aClass = object.getClass();
        // 通过反射获取继承类
        Class<?> superclass = aClass.getSuperclass();
        Field[] fields = superclass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            try {
                if ("commerceId".equals(fieldName) || "page".equals(fieldName) || "pageSize".equals(fieldName)) {
                    checkBoolean(field.get(object) != null, "分页必要字段不能为空");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
