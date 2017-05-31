package com.tang.utils;

import java.util.HashMap;

/**
 * 查询参数实体类
 * @author: Tang Jiujia
 * @version: 2017/4/28 0028 13:58
 */
public class Parameters extends HashMap<String,Object> {

    public Parameters(Object... values){
        if(values!=null){
            for(int i=0;i<values.length;i++){
                this.put("p"+(i+1),values[i]);
            }
        }
    }
}




















