package com.tang.web.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tang.crm.service.Customer;
import com.tang.domain.Noticebill;
import com.tang.domain.User;
import com.tang.service.NoticebillService;
import com.tang.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author: Tang Jiujia
 * @version: 2017/5/12 0012 20:48
 */
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill>{

    @Autowired
    private NoticebillService noticebillService;

    public String findByCustomerByTel(){
        Customer customer = customerService.findByTele(getModel().getTelephone());
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr=null;
        try{
              jsonStr= mapper.writeValueAsString(customer);
            }catch (Exception e){
                e.printStackTrace();
            }
            writeJsonBack(jsonStr);
        return NONE;
    }

    public String addBill(){
        Noticebill model = getModel();
        model.setUser((User)getSession().getAttribute("user"));
        noticebillService.save(model);
        return NONE;
    }
}
