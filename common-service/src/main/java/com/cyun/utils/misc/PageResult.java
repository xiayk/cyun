package com.cyun.utils.misc;

import com.google.common.base.MoreObjects;

/**
 * @createUser daiyuan
 * @createTime 2019/4/24 10:55
 * @describe TODO
 **/
public class PageResult<T> {

    private long total;

    private T rows;

    public T getRows() {
        return rows;
    }

    public PageResult setRows(T rows) {
        this.rows = rows;
        return this;
    }

    public PageResult() {

    }

    public long getTotal() {
        return total;
    }

    public PageResult setTotal(long total) {
        this.total = total;
        return this;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("total", total)
                .add("rows", rows)
                .toString();
    }
}
