package xyz.fusheng.config.shiro; /**
 * @author: code-fusheng
 * @Date: 2020/9/1 21:39
 */

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import xyz.fusheng.domain.User;
import xyz.fusheng.service.UserService;
import xyz.fusheng.vo.ActiveUser;

/**
 * @FileName: UserRealm
 * @Author: code-fusheng
 * @Date: 2020/9/1 21:39
 * @version: 1.0
 * Description: 自定义 UserRealm 匹配用户名和密码规则
 * 认证 + 授权
 */

public class UserRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private UserService userService;

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    /**
     * 认证 - 登录处理
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 得到用户登陆名
        String phone = token.getPrincipal().toString();
        // 根据电话查询用户是否存在
        User user = userService.queryUserByPhone(phone);
        // 判断用户是否存在，但是密码也可能不正确
        if(null != user){
            // 组装存放到 redis 里面的对象
            ActiveUser activeUser = new ActiveUser();
            activeUser.setUser(user);
            // 匹配密码
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activeUser, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
            return info;
        }else{
            // 代表用户不存在
            return null;
        }
    }

    /**
     * 授权 - 菜单+按钮 权限 （登陆成功之后判断用户是否有某个菜单或按钮的权限）
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 身份得到的就是上一个方法的返回值的值 第一个参数activeUser
        ActiveUser activeUser= (ActiveUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

}
