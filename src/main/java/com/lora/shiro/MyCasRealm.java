package com.lora.shiro;

import java.util.Iterator;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

import com.lora.model.User;

public class MyCasRealm extends CasRealm {

    /**
     * 授权，获取用户的角色、权限
     */
    @SuppressWarnings("rawtypes")
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("Principal对象不能为空");
        }
        Iterator it = principals.fromRealm(getName()).iterator();
        String username = null;
        if (it.hasNext()) {
            username = (String) it.next();
        } else {
            username = principals.toString();
        }
        //获取用户响应的permission
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (username != null) {
            // 添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
            info.addRole("admin");
            // 添加权限
            info.addStringPermission("admin:manage");
            System.out.println("已为用户赋予了[admin]角色和[admin:manage]权限");
            return info;
        }
        return info;
    }

    /**
     * 认证，登录验证。
     */
    @SuppressWarnings("rawtypes")
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        AuthenticationInfo authenticationInfo = super
                .doGetAuthenticationInfo(token);
        PrincipalCollection principalCollection = authenticationInfo
                .getPrincipals();
        Map loginDataMap = (Map) principalCollection.asList().get(1);
        String userAccount = (String) loginDataMap.get("name");
        String userCode = (String) loginDataMap.get("employee");
        String fullName = (String) loginDataMap.get("sn");
        String department = (String) loginDataMap.get("department");
        String email = (String) loginDataMap.get("mail");
        User user = new User();
        user.setUserCode(userCode);
        user.setUserAccount(userAccount);
        user.setUname(fullName);
        user.setDepartment(department);
        user.setEmail(email);
        // 将用户保存在SESSION回话中
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("currentUser", user);
        return authenticationInfo;
    }
}
