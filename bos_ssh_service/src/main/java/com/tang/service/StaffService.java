package com.tang.service;

import com.tang.base.StaffDao;
import com.tang.domain.Staff;
import com.tang.utils.Page;
import com.tang.utils.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Tang Jiujia
 * @version: 2017/5/7 0007 22:55
 */
@Service
@Transactional
public class StaffService {

    @Autowired
    private StaffDao staffDao;

    public void addStaff(Staff staff){
        staffDao.add(staff);
    }

    public Page findByPage(Page page){
        return staffDao.findByPage(page,"from Staff where 1=:p1",new Parameters(1));
    }

    public void updateByIds(String[] ids){
        staffDao.updateByIds(ids);
    }

    public void editStaff(Staff staff){
        staffDao.update(staff);
    }

    public List<Staff> findByTag(){
        return staffDao.find("from Staff where deltag=0");
    }
}














































