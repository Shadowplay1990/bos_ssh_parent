package com.tang.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 反射工具类
 * @author: Tang Jiujia
 * @version:
 */
public class ReflectUtils {

    /**
    * 获得父类的泛型参数类型
    * @param: clazz 被反射的类型
    * @param: index
    * @return
    */
    public static Class getGenricType(final Class clazz,int index){

        Type rootType = clazz.getGenericSuperclass();

        if(!(rootType instanceof ParameterizedType)) return Object.class;

        Type[] types = ((ParameterizedType) rootType).getActualTypeArguments();
        if (index<0||index>types.length) return Object.class;

        if (!(types[index] instanceof Class)) return Object.class;
        return (Class) types[index];
    }
}

















