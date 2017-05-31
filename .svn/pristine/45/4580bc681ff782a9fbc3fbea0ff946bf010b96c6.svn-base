package com.tang.web.intercepter;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.tang.domain.User;
import org.apache.struts2.ServletActionContext;

/**
 * @author: Tang Jiujia
 * @version: 2017/5/7 0007 13:58
 */
public class LoginIntercepter extends MethodFilterInterceptor{

    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        User user= (User) ServletActionContext.getRequest().getSession().getAttribute("user");
        if(user!=null){
            return invocation.invoke();
        }
        return "login";
    }
}
