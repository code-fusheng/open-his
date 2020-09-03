package xyz.fusheng.utils; /**
 * @author: code-fusheng
 * @Date: 2020/9/3 13:07
 */

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import xyz.fusheng.constants.Constants;
import xyz.fusheng.domain.SimpleUser;
import xyz.fusheng.domain.User;
import xyz.fusheng.vo.ActiveUser;

import java.util.List;

/**
 * @FileName: ShiroSecurityUtils
 * @Author: code-fusheng
 * @Date: 2020/9/3 13:07
 * @version: 1.0
 * Description: 当前登录用户对象信息工具类
 */

public class ShiroSecurityUtils {

    /**
     * 得到当前登录用户对象 ActiveUser
     * @return
     */
    public static ActiveUser getCurrentActiveUser() {
        Subject subject = SecurityUtils.getSubject();
        ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
        return activeUser;
    }

    /**
     * 获取当前登录用户对象 User
     * @return
     */
    public static User getCurrentUser() {
        return getCurrentActiveUser().getUser();
    }

    /**
     * 获取当前登录用户对象 SimpleUser
     * @return
     */
    public static SimpleUser getCurrentSimpleUser() {
        User user = getCurrentActiveUser().getUser();
        return new SimpleUser(user.getUserId(), user.getUserName());
    }

    /**
     * 获取当前登录用户名
     * @return
     */
    public static String getCurrentUserName(){
        return getCurrentActiveUser().getUser().getUserName();
    }

    /**
     * 获取当前登录用户角色
     * @return
     */
    public static List<String> getCurrentUserRoles(){
        return getCurrentActiveUser().getRoles();
    }

    /**
     * 获取当前用户权限
     * @return
     */
    public static List<String> getCurrentUserPermissions(){
        return getCurrentActiveUser().getPermissions();
    }

    /**
     * 判断当前用户是否为超级管理员
     * @return
     */
    public static boolean isAdmin(){
        return getCurrentUser().getUserType().equals(Constants.USER_ADMIN);
    }



}
