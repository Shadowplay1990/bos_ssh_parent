package com.tang.base;

import com.tang.base.impl.BaseDaoImpl;
import com.tang.domain.Decidedzone;
import com.tang.utils.Parameters;
import org.springframework.stereotype.Repository;

/**
 * @author: Tang Jiujia
 * @version: 2017/5/10 0010 20:55
 */
@Repository
public class DecidezoneDao extends BaseDaoImpl<Decidedzone>{

    public Decidedzone findById(String id){
        return get("from Decidedzone where decidedzoneId=:p1",
                new Parameters(id));
    }
}
