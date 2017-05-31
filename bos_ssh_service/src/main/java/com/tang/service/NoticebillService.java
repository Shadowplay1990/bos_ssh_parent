package com.tang.service;

import com.tang.base.DecidezoneDao;
import com.tang.base.NoticeBillDao;
import com.tang.base.WorkbillDao;
import com.tang.base.WorkordermanagerDao;
import com.tang.crm.service.CustomerService;
import com.tang.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * @author: Tang Jiujia
 * @version: 2017/5/12 0012 21:43
 */
@Service
@Transactional
public class NoticebillService {

    @Autowired
    private NoticeBillDao noticeBillDao;
    @Autowired
    private DecidezoneDao decidezoneDao;
    @Autowired
    private WorkordermanagerDao workordermanagerDao;
    @Autowired
    private WorkbillDao workbillDao;
    @Autowired
    private CustomerService customerService;

    public void save(Noticebill noticebill){
        String address = noticebill.getPickaddress();
        String dzId = customerService.findDzIdByAddress(address);

        if(dzId!=null){
            Decidedzone dz = decidezoneDao.findById(dzId);
            Staff staff = dz.getStaff();
            noticebill.setStaff(staff);
            noticebill.setOrderType(Noticebill.ORDERTYPE_AUTO);
            noticeBillDao.add(noticebill);

            Workbill workbill = new Workbill();
            workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
            workbill.setNoticebill(noticebill);
            workbill.setPickstate(Workbill.PICKSTATE_PRE);
            workbill.setRemark("b");
            workbill.setStaff(staff);
            workbill.setType(Workbill.TYPE_NEW);

            workbillDao.add(workbill);
        }else {
            noticebill.setOrderType(Noticebill.ORDERTYPE_MAN);
            noticeBillDao.add(noticebill);
        }
    }

}























































