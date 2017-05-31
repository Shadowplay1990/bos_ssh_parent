package com.tang.base;

import com.tang.base.impl.BaseDaoImpl;
import com.tang.domain.Subarea;
import com.tang.utils.Page;
import com.tang.utils.Parameters;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Tang Jiujia
 * @version: 2017/5/9 0009 19:57
 */
@Repository
public class SubareaDao extends BaseDaoImpl<Subarea>{

    public Page<Subarea> findSubareasByPage(Page<Subarea> page){
        return findByPage(page,"from Subarea where 1=:p1",
                new Parameters(1));
    }

    public List<Subarea> findByDzId(String dzId){
        DetachedCriteria dc = createDetachedCriteria();
        dc.createAlias("decidedzone","d").
                add(Restrictions.eq("d.decidedzoneId",dzId));
        Criteria c = dc.getExecutableCriteria(getSession());
        return c.list();
    }
}
