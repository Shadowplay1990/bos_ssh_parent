package com.tang.base.impl;

import com.tang.base.BaseDao;
import com.tang.utils.Page;
import com.tang.utils.Parameters;
import com.tang.utils.ReflectUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 增，删，改，查基类。封装简化了Hibernate中，按entity操作和按条件操作两类方法
 * @author: Tang Jiujia
 * @version: 2017/4/28 0028 16:31
 */
public class BaseDaoImpl<T> implements BaseDao<T>{

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public void flush(){
        getSession().flush();
    }

    public void clear(){
        getSession().clear();
    }

    private Class<?> entityClass;

    public BaseDaoImpl(){
        entityClass= ReflectUtils.getGenricType(getClass(),0);
    }

    /**
    * 根据参数类型，设置Hibernate query的参数
    * @param
    * @return
    */
    public void setParameters(Query query, Parameters parameters){
        if (parameters!=null){
            final Set<String> keys = parameters.keySet();
            for(String key:keys){
                Object value = parameters.get(key);
                if (value instanceof Collection<?>){
                    query.setParameterList(key, (Collection<?>) value);
                }else if (value instanceof Object[]){
                    query.setParameterList(key,(Object[])value);
                }else{
                    query.setParameter(key,value);
                }
            }
        }
    }

    /**
    * 生成一个设置了hql和相关参数的query
    * @param
    * @return
    */
    public Query createQuery(String hql,Parameters parameters){
        Query query = getSession().createQuery(hql);
        if(parameters!=null){
            this.setParameters(query,parameters);
        }
        return query;
    }

    @Override
    public Serializable add(T entity) {
        return getSession().save(entity);
    }

    @Override
    public void add(List<T> list) {
        if (list!=null&&list.size()>0){
            for (T entity:list){
                add(entity);
            }
        }
    }

    @Override
    public void saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
    }


    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

    @Override
    public void update(T entity) {
        getSession().update(entity);
    }

    @Override
    public int excuteUpdate(String sql, Parameters parameters) {
        return createQuery(sql,parameters).executeUpdate();
    }

    @Override
    public T get(Serializable id) {
        return (T)getSession().get(entityClass,id);
    }

    @Override
    public T get(String hql, Parameters parameters) {
        return (T) createQuery(hql,parameters).uniqueResult();
    }

    @Override
    public T get(String hql) {
        return get(hql,null);
    }

    @Override
    public List<T> findAll() {
        return getSession().createCriteria(entityClass).list();
    }

    @Override
    public <E> List<E> find(String hql, Parameters parameters) {
        return createQuery(hql,parameters).list();
    }

    @Override
    public <E> List<E> find(String hql) {
        return find(hql,null);
    }

    @Override
    public <E> List<E> find(String hql,Parameters parameters,int first,int count){
        return createQuery(hql,parameters).
                setFirstResult(first).setMaxResults(count).list();
    }

    @Override
    public Page<T> findByPage(Page<T> page, String hql, Parameters parameters) {
        String countHql="select count(*) "+removeSelect(removeOrders(hql));
        List<Object> list = createQuery(countHql, parameters).list();

        if (list.size()>0){
            page.setTotalCount(Integer.valueOf(list.get(0).toString()));
        }else{
            page.setTotalCount(0);
        }

        if (page.getTotalCount()<1) return page;

        Query query = createQuery(hql, parameters);
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getCount());
        page.setTotalPage();
        page.setList(query.list());
        return page;
    }


    private String removeSelect(String hql){
        int index = hql.toLowerCase().indexOf("from");
        return hql.substring(index);
    }

    private String removeOrders(String qlString) {
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(qlString);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }

//-------------------------------------使用Criteria查询--------------------------------------
    @Override
    public DetachedCriteria createDetachedCriteria(Criterion... criterions) {
         DetachedCriteria detachedCriteria=DetachedCriteria.forClass(entityClass);
         for(Criterion criterion:criterions){
             detachedCriteria.add(criterion);
         }
         return detachedCriteria;
    }

    @Override
    public int count(DetachedCriteria detachedCriteria) {
        Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
        int totalCount=0;
        try{
            Field f = CriteriaImpl.class.getDeclaredField("orderEntries");
            f.setAccessible(true);
            List orderEnteries=(List)f.get(criteria);
            f.set(criteria,new ArrayList());
            criteria.setProjection(Projections.rowCount());
            totalCount = Integer.valueOf(criteria.uniqueResult().toString());
            criteria.setProjection(null);
            f.set(criteria,orderEnteries);
        }catch (Exception e){
                e.printStackTrace();
            }
        return totalCount;
    }

    @Override
    public Page<T> find(Page<T> page, DetachedCriteria detachedCriteria, ResultTransformer transformer) {
        page.setCount(count(detachedCriteria));
        if(page.getCount()<1){
            return page;
        }

        Criteria c = detachedCriteria.getExecutableCriteria(getSession());
        c.setResultTransformer(transformer);
        c.setFirstResult(page.getFirstResult());
        c.setMaxResults(page.getCount());
        page.setList(c.list());
        return page;
    }

    @Override
    public Page<T> find(Page<T> page, DetachedCriteria detachedCriteria) {
        return find(page,detachedCriteria,Criteria.DISTINCT_ROOT_ENTITY);
    }

    @Override
    public Page<T> find(Page<T> page) {
        return find(page,createDetachedCriteria());
    }


    @Override
    public List<T> find(DetachedCriteria detachedCriteria, ResultTransformer transformer) {
        Criteria c = detachedCriteria.getExecutableCriteria(getSession());
        c.setResultTransformer(transformer);
        return c.list();
    }

    @Override
    public List<T> find(DetachedCriteria detachedCriteria) {
        return find(detachedCriteria,Criteria.DISTINCT_ROOT_ENTITY);
    }


}
