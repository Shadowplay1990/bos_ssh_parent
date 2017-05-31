package com.tang.web.action.staff;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tang.domain.Staff;
import com.tang.service.StaffService;
import com.tang.utils.Page;
import com.tang.utils.PageHelp;
import com.tang.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author: Tang Jiujia
 * @version: 2017/5/7 0007 22:51
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff>{

    @Autowired
    private StaffService staffService;
    private String ids;

    public String addStaff(){
        Staff staff = getModel();
        System.out.println(staff.getStaffid());
        staff.setDeltag("0");
        staffService.addStaff(staff);
        return "staff";
    }

    public String queryStaff(){
        Page pageBean2 = staffService.findByPage(pageBean);
        pageHelp.setTotal(pageBean2.getTotalCount());
        pageHelp.setRows(pageBean2.getList());
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr=null;
        try{
               jsonStr=mapper.writeValueAsString(pageHelp);
            }catch (Exception e){
                e.printStackTrace();
            }
        writeJsonBack(jsonStr);
        return NONE;
    }

    public String updateDeltag(){
        System.out.println(ids);
        String[] ids2 = ids.split(",");
        staffService.updateByIds(ids2);
        return "staff";
    }

    public String editStaff(){
        staffService.editStaff(getModel());
        return "staff";
    }

    /**
    *  查询未被标记为删除的取派员
    */
    public String listAjax(){

        List<Staff> list = staffService.findByTag();
        ObjectMapper mapper = new ObjectMapper();

        String jsonStr=null;
        try {
            jsonStr=mapper.writeValueAsString(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        writeJsonBack(jsonStr);
        return NONE;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}


















