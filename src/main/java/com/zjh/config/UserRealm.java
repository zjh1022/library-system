package com.zjh.config;

import com.zjh.pojo.Reader;
import com.zjh.pojo.User;
import com.zjh.service.ReaderService;
import com.zjh.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.Authenticator;

/**
 * @auther zheng jianghai
 * @create 2021-05-21 21:55
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    @Autowired
    ReaderService readerService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //拿到当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        String role = (String) subject.getSession().getAttribute("role");
        if ("admin".equals(role)){  //管理员
            User currentUser = (User) subject.getPrincipal();//拿到当前对象
            //设置当前用户的权限
            info.addStringPermission(currentUser.getRole());
        }else {   //学生
            Reader currentReader = (Reader)subject.getPrincipal();
            info.addStringPermission(currentReader.getRole());
        }
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        User user=null;
        Reader reader=null;
        Subject subject = SecurityUtils.getSubject();
        String role = (String) subject.getSession().getAttribute("role");
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        //通过数据库验证
        if ("admin".equals(role)){ //管理员
           user = userService.selectUserByUsername(token.getUsername());
           if (user==null){
               return null;  //抛出异常
           }
           //密码认证,shiro帮我们做
            return new SimpleAuthenticationInfo(user,user.getPassword(),"");

        }else { //学生
           reader=readerService.selectReaderByUsername(token.getUsername());
           if (reader==null){
               return null;// 抛出异常
           }
            //密码认证,shiro帮我们做
            return  new SimpleAuthenticationInfo(reader,reader.getPassword(),"");
        }
    }
}
