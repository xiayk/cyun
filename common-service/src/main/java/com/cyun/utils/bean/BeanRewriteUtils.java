package com.cyun.utils.bean;

import com.alibaba.fastjson.JSON;
import com.cyun.dto.MenuDTO;
import com.cyun.model.SysMenu;
import com.cyun.result.MenuResult;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 当把Person类作为BeanUtilTest的内部类时，程序出错<br>
 * java.lang.NoSuchMethodException: Property '**' has no setter method<br>
 * 本质：内部类 和 单独文件中的类的区别 <br>
 * BeanUtils.populate方法的限制：<br>
 * The class must be public, and provide a public constructor that accepts no
 * arguments. <br>
 * This allows tools and applications to dynamically create new instances of
 * your bean, <br>
 * without necessarily knowing what Java class name will be used ahead of time
 */
public class BeanRewriteUtils {
    private static List<String> notGetListProperties = new ArrayList<String>();

    static {
        notGetListProperties.add("serialVersionUID");
        notGetListProperties.add("nowPage");
        notGetListProperties.add("rowCount");
        notGetListProperties.add("pageSize");
        notGetListProperties.add("pageCount");
        notGetListProperties.add("pageOffset");
        notGetListProperties.add("pageTail");
        notGetListProperties.add("orderField");
        notGetListProperties.add("orderDirection");
        notGetListProperties.add("length");
        notGetListProperties.add("startIndex");
        notGetListProperties.add("endIndex");
        notGetListProperties.add("indexs");
        notGetListProperties.add("list");
        notGetListProperties.add("list2");
        notGetListProperties.add("id");
    }

    /**
     * @param object
     * @return false : 对象为null,或者【map】【list】【set】为空时,true：对象不为null ,或者【map】【list】【set】 不为空
     */
    public static boolean isNullOrEmpty(Object object) {
        boolean resultFlag = false;

        if (null == object) {
            resultFlag = true;
        } else if ("".equals(object)) {
            resultFlag = true;
        } else {
            resultFlag = false;

            if (object instanceof Map) {
                resultFlag = ((Map) object).isEmpty();
            }
            if (object instanceof List) {

                resultFlag = ((List) object).isEmpty();
            }
            if (object instanceof Set) {
                resultFlag = ((Set) object).isEmpty();
            }
        }
        return resultFlag;
    }

    /**
     * @param object
     * @return true : 对象不为null,并且【map】【list】【set】不为空时,flse：对象为null ,或者【map】【list】【set】 为空
     */
    public static boolean isNotNullOrEmpty(Object object) {
        return !isNullOrEmpty(object);
    }

    public static Object copyBean(Object oldObject, Class cls) {
        Object newObject = null;
        try {
            newObject = cls.newInstance();
            Class oldClass = oldObject.getClass();
            Class newClass = newObject.getClass();

            Field[] oldDeclaredFields = oldClass.getDeclaredFields();
            Field[] newDeclaredFields = newClass.getDeclaredFields();
            for (Field field : oldDeclaredFields) {
                field.setAccessible(true);
                Object o = field.get(oldObject);
                if (null == o) {
                    continue;
                }
                for (Field f : newDeclaredFields) {
                    if (f.getName().equals(field.getName())) {
                        f.setAccessible(true);
                        f.set(newObject, o);
                        continue;
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return newObject;
    }

    /**
     * 将dto和entity之间的属性互相转换,dto中属性一般为String等基本类型,
     * 但是entity中可能有复合主键等复杂类型,需要注意同名问题
     *
     * @param src
     * @param target
     */
    public static Object populate(Object src, Object target) {
        Method[] srcMethods = src.getClass().getMethods();
        Method[] targetMethods = target.getClass().getMethods();
        for (Method m : srcMethods) {
            String srcName = m.getName();
            if (srcName.startsWith("get")) {
                try {
                    Object result = m.invoke(src);
                    for (Method mm : targetMethods) {
                        String targetName = mm.getName();
                        if (targetName.startsWith("set") && targetName.substring(3, targetName.length())
                                .equals(srcName.substring(3, srcName.length()))) {
                            mm.invoke(target, result);
                        }
                    }
                } catch (Exception e) {

                }
            }
        }
        return target;
    }

    /**
     * dto集合和实体类集合间的互相属性映射
     *
     * @param src
     * @param target
     * @param targetClass
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <S, T> List<T> populateList(List<S> src, List<T> target, Class<?> targetClass) {
        for (int i = 0; i < src.size(); i++) {
            try {
                Object object = targetClass.newInstance();
                target.add((T) object);
                populate(src.get(i), object);

            } catch (Exception e) {
                continue;//某个方法反射异常
            }
        }
        return target;
    }


//    public static void main(String args[]) {
//        MenuResult dto = new MenuResult();
//        dto.setId("1");
//        dto.setCreateDate(new Date());
//        dto.setMenuName("name1");
//        System.out.println(((MenuDTO)populate(dto, new MenuDTO())).getMenuName());
//    }

}


