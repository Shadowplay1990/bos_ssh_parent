package com.tang.realm;

import com.tang.base.UserDao;
import com.tang.domain.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Tang Jiujia
 * @version: 2017/5/13 0013 23:30
 */
@Component
public class UserRealm extends AuthenticatingRealm{

    @Autowired
    private UserDao userDao;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken t) throws AuthenticationException {
        UsernamePasswordToken myToken= (UsernamePasswordToken) t;
        String username = myToken.getUsername();
        User user = userDao.findByName(username);
        if(user==null) return null;
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo
                (user, user.getPassword(), this.getName());
        return info;
    }


}
