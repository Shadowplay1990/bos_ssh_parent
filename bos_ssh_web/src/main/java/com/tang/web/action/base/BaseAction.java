package com.tang.web.action.base;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tang.crm.service.CustomerService;
import com.tang.utils.Page;
import com.tang.utils.PageHelp;
import com.tang.utils.ReflectUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: Tang Jiujia
 * @version: 2017/5/6 0006 23:08
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

    protected T entity;
    protected Class<T> entityClass;

    @Autowired
    protected CustomerService customerService;

    @Autowired
    protected Page<T> pageBean;

    @Autowired
    protected PageHelp<T> pageHelp;

    public void setPage(int page){
        pageBean.setCurrentPage(page);
    }

    public void setRows(int rows){
        pageBean.setCount(rows);
    }

    @Override
    public T getModel() {
        return entity;
    }

    public BaseAction(){
        entityClass= ReflectUtils.getGenricType(getClass(),0);
        try {
            entity=entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected HttpSession getSession(){
       return ServletActionContext.getRequest().getSession();
    }

    protected HttpServletRequest getRequest(){
        return ServletActionContext.getRequest();
    }

    protected HttpServletResponse getRespone(){
        return ServletActionContext.getResponse();
    }

    protected void writeJsonBack(String jsonStr){

        getRespone().setContentType("text/json;charset=utf-8");

        try (PrintWriter writer = getRespone().getWriter()){
            writer.write(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
