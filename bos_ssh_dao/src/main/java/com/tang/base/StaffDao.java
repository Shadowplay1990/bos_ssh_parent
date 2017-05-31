package com.tang.base;

import com.tang.base.impl.BaseDaoImpl;
import com.tang.domain.Staff;
import com.tang.utils.Page;
import com.tang.utils.Parameters;
import org.springframework.stereotype.Repository;

/**
 * @author: Tang Jiujia
 * @version: 2017/5/7 0007 22:54
 */
@Repository
public class StaffDao extends BaseDaoImpl<Staff>{

    public void updateByIds(String[] ids){
        for (String id:ids){
            excuteUpdate("update Staff s set s.deltag=1 " +
                    "where staffid=:p1",new Parameters(id));
        }
    }

}
