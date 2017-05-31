package com.tang.web.action.user;

import com.tang.domain.User;
import com.tang.service.UserService;
import com.tang.web.action.base.BaseAction;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: Tang Jiujia
 * @version: 2017/5/6 0006 23:27
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{

    @Autowired
    private UserService userService;
    private String checkcode;

    public String login(){

        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token=
                new UsernamePasswordToken
                        (getModel().getUsername(),getModel().getPassword());
        try{
            subject.login(token);
            User user=(User) subject.getPrincipal();
            getSession().setAttribute("user",user);
        }catch (Exception e){
            e.printStackTrace();
            return "login";
        }
        return "index";
    }

    public String updatePwd(){
        String result="1";
        User user= (User) getSession().getAttribute("user");
        user.setPassword(getModel().getPassword());
        try {
           userService.updatePwd(user);
        }catch (Exception e){
            e.printStackTrace();
            result="0";
        }
        getRespone().setContentType("text/html;charset=utf-8");
        try {
            PrintWriter writer = getRespone().getWriter();
            writer.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NONE;
    }

    public String getCheckcode() {
        return checkcode;
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
}
