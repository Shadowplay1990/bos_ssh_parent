package com.tang.service;

import com.tang.base.DecidezoneDao;
import com.tang.base.SubareaDao;
import com.tang.domain.Decidedzone;
import com.tang.domain.Subarea;
import com.tang.utils.Page;
import com.tang.utils.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Tang Jiujia
 * @version: 2017/5/10 0010 20:54
 */
@Service
@Transactional
public class DecidezoneService {

    @Autowired
    private DecidezoneDao decidezoneDao;
    @Autowired
    private SubareaDao subareaDao;

    public void add(Decidedzone decidedzone,String[] subareId){
        decidezoneDao.add(decidedzone);
        for (String id:subareId){
            Subarea subarea = subareaDao.
                    get("from Subarea where subareaId=:p1",new Parameters(id));
            subarea.setDecidedzone(decidedzone);
        }
    }

    public Page<Decidedzone> findDByPage(Page<Decidedzone> page){
        return decidezoneDao.find(page);
    }
}
