 package com.lora.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

public class myRealm extends AuthorizingRealm {

	@Override
	/**
	 * 授权信息
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String username = (String) principals.fromRealm(getName()).iterator()
				.next();
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		if (username != null && "lora".equals(username)) {
			// 添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
			simpleAuthorInfo.addRole("admin");
			// 添加权限
			simpleAuthorInfo.addStringPermission("admin:manage");
//			simpleAuthorInfo.addStringPermission("admin:create");
			System.out.println("已为用户[lora]赋予了[admin]角色和[admin:manage]权限");
			return simpleAuthorInfo;
		}
		// 若该方法什么都不做直接返回null的话,就会导致任何用户访问都会自动跳转到unauthorizedUrl指定的地址
		return null;
	}

	/**
	 * 认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String userName = token.getUsername();
		String password = new String(token.getPassword());
		//此处无需比对,比对的逻辑Shiro会做,我们只需返回一个和令牌相关的正确的验证信息  
        //说白了就是第一个参数填登录用户名,第二个参数填合法的登录密码(可以是从数据库中取到的,本例中为了演示就硬编码了) 
		if ("lora".equals(userName) && "lora".equals(password)) {
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(userName, password, this.getName());  
			// 将用户保存在SESSION回话中
			Session session = SecurityUtils.getSubject().getSession();
			session.setAttribute("currentUser", authcInfo);
            return authcInfo;  
		}
		return null;
	}

}
