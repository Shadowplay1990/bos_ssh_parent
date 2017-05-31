package com.tang.web.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tang.crm.service.Customer;
import com.tang.domain.Decidedzone;
import com.tang.service.DecidezoneService;
import com.tang.utils.Page;
import com.tang.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author: Tang Jiujia
 * @version: 2017/5/10 0010 20:50
 */
@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone>{

    @Autowired
    private DecidezoneService decidezoneService;
    private String[] subareaId;
    private List<Integer> customerIds;

    public String add(){
        decidezoneService.add(getModel(),subareaId);
        return "reflash";
    }

    public String queryByPage(){
        Page<Decidedzone> pageBean2 = decidezoneService.findDByPage(pageBean);
        pageHelp.setTotal(pageBean2.getTotalCount());
        pageHelp.setRows(pageBean2.getList());
        String jsonStr=null;
        ObjectMapper mapper = new ObjectMapper();
        try{
               jsonStr=mapper.writeValueAsString(pageHelp);
            }catch (Exception e){
                e.printStackTrace();
            }
            writeJsonBack(jsonStr);
        return "reflash";
    }

    public String findAss(){
        List<Customer> list = customerService.
                findAssociation(getModel().getDecidedzoneId());
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr=null;
        try{
                jsonStr=mapper.writeValueAsString(list);
            }catch (Exception e){
                e.printStackTrace();
            }
            writeJsonBack(jsonStr);
        return NONE;
    }

    public String findNotAss(){
        List<Customer> list = customerService.findNotAssociation();
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr=null;
        try{
            jsonStr=mapper.writeValueAsString(list);
        }catch (Exception e){
            e.printStackTrace();
        }
        writeJsonBack(jsonStr);
        return NONE;
    }

    public String ass(){
        customerService.ass
                (getModel().getDecidedzoneId(),customerIds);
        return "reflash";
    }

    public String[] getSubareaId() {
        return subareaId;
    }

    public void setSubareaId(String[] subareaId) {
        this.subareaId = subareaId;
    }

    public List<Integer> getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(List<Integer> customerIds) {
        this.customerIds = customerIds;
    }
}
