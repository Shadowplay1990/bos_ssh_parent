package com.tang.base;

import java.io.Serializable;
import java.util.List;
import com.tang.utils.Page;
import com.tang.utils.Parameters;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.transform.ResultTransformer;

/**
 * @author: Tang Jiujia
 * @version: 2017/4/28 0028 15:11
 */
public interface BaseDao<T> {

    /**
    * 添加单个实体
    * @param
    * @return
    */
    Serializable add(T entity);

    /**
    * 添加多个
    * @param
    * @return
    */
    void add(List<T> list);

    /**
    * 添加或者更新
    * @param
    * @return
    */
    void saveOrUpdate(T entity);

    /**
    * 通过实例删除
    * @param
    * @return
    */
    void delete(T entity);

    /**
    * 根据所给实体更新
    * @param
    * @return
    */
    void update(T entity);

    /**
    * 根据传入参数更新,删除,插入
    * @param
    * @return
    */
    int excuteUpdate(String sql, Parameters parameters);

//----------------------普通查寻------------------------------

    /**
    * 按数据库主键查询单个
    * @param
    * @return
    */
    T get(Serializable id);

    /**
    * 按给定条件查询单个
    * @param
    * @return
    */
    T get(String hql, Parameters parameters);

    T get(String hql);

    /**
    * 查询当前类所有结果
    * @param
    * @return 查询结果肯定为当前类
    */
    List<T> findAll();

    /**
    * 按给定条件查询
    * @param
    * @return  查询结果类型不确定
    */
    <E> List<E> find(String hql, Parameters parameters);

    <E> List<E> find(String hql);

    <E> List<E> find(String hql, Parameters parameters, int begin, int count);

//---------------------------分页查询----------------------------------
    /**
    *
    * 根据page查询，并且返回一个page
    */
    Page<T> findByPage(Page<T> page, String hql, Parameters parameters);


//-------------------------------与会话无关的条件查询----------------------------------------

    /**
    * 创建与会话无关的检索标准对象
    */
    DetachedCriteria createDetachedCriteria(Criterion... criterions);

    /**
    * 使用检索标准查询记录数
    */
    int count(DetachedCriteria detachedCriteria);

    /**
    *  使用标准检索的分页查询
    */
    Page<T> find(Page<T> page, DetachedCriteria detachedCriteria, ResultTransformer transformer);

    Page<T> find(Page<T> page,DetachedCriteria detachedCriteria);

    Page<T> find(Page<T> page);

    /**
    *  使用标准检索的查询
    */
    List<T> find(DetachedCriteria detachedCriteria,ResultTransformer transformer);

    List<T> find(DetachedCriteria detachedCriteria);
}






















































































































































