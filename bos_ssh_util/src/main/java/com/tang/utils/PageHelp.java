package com.tang.utils;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Tang Jiujia
 * @version: 2017/5/8 0008 13:33
 */
@Component
public class PageHelp<T> {

    int total;
    List<T> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
