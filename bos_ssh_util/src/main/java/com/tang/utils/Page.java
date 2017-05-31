package com.tang.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页实体类
 * @author: Tang Jiujia
 * @version: 2017/4/28 0028 11:12
 */
@JsonAutoDetect
@Component("pageBean")
public class Page<T> {

    @JsonIgnore
    private int currentPage=1; //当前页数默认为1
    @JsonIgnore
    private int totalPage;
    @JsonIgnore
    private int count; //每页记录数
    private int totalCount;
    private List<T> list=new ArrayList<T>();

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage() {
        this.totalPage = totalCount%count==0?
                totalCount/count:totalCount/count+1;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
    * 获取Hibernate中的firstResult
    */
    @JsonIgnore
    public int getFirstResult(){
        int first=(getCurrentPage()-1)*getCount();
        return first;
    }

    /**
    *
    *获取Hibernate中的lastResult
    */
    @JsonIgnore
    public int getLastResult(){
        return getFirstResult()+getCount();
    }

}