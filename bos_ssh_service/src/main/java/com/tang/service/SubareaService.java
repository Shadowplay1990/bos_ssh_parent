package com.tang.service;

import com.tang.base.SubareaDao;
import com.tang.domain.Subarea;
import com.tang.utils.Page;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Tang Jiujia
 * @version: 2017/5/9 0009 16:50
 */
@Service
@Transactional
public class SubareaService {

    @Autowired
    private SubareaDao subareaDao;

    public void addSubarea(Subarea subarea){
        subareaDao.add(subarea);
    }

    public Page<Subarea> findByPage(Page<Subarea> page){
        return subareaDao.findSubareasByPage(page);
    }

    public Page<Subarea> findByCriteria(Page<Subarea> page, DetachedCriteria dc){
        return subareaDao.find(page,dc,DetachedCriteria.ROOT_ENTITY);
    }

    public List<Subarea> findAll(){
        return subareaDao.findAll();
    }

    public List<Subarea> findNotAssociation(){
        DetachedCriteria dc=DetachedCriteria.forClass(Subarea.class);
//        dc.setProjection(Projections.projectionList().
//                add(Projections.distinct(Projections.property("decidedzone")))
//                .add(Projections.distinct(Projections.property("region"))));
        dc.add(Restrictions.isNull("decidedzone"));
        return subareaDao.find(dc);
    }

    public List<Subarea> findByDzId(String dzId){
        return subareaDao.findByDzId(dzId);
    }
}


















