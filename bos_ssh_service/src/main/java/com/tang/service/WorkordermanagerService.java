package com.tang.service;

import com.tang.base.WorkordermanagerDao;
import com.tang.domain.Workordermanager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Tang Jiujia
 * @version: 2017/5/12 0012 21:44
 */
@Service
@Transactional
public class WorkordermanagerService {

    @Autowired
    private WorkordermanagerDao wkdao;

    public void save(Workordermanager wk){
        wkdao.add(wk);
    }
}
