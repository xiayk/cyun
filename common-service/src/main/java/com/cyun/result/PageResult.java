package com.cyun.result;

import lombok.Data;

import java.util.List;

/**
 * Created LimitResult with IDEA
 * Description: class
 *
 * @Auther: xiayk
 * @date: 2019/11/16 下午10:17
 **/
@Data
public class PageResult {

    private List<?> data;

    private Integer total;

    public PageResult(List<?> data, Integer total){
        this.data = data;
        this.total = total;
    }

    public PageResult(){}

    public static PageResult PageResult(List<?> data, Integer total){
        return new PageResult(data, total);
    }
}
