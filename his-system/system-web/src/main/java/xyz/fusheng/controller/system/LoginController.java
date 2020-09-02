package xyz.fusheng.controller.system;
/**
 * @author: code-fusheng
 * @Date: 2020/9/1 22:16
 */

import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.constants.Constants;
import xyz.fusheng.constants.HttpStatus;
import xyz.fusheng.domain.Menu;
import xyz.fusheng.domain.SimpleUser;
import xyz.fusheng.dto.LoginBodyDto;
import xyz.fusheng.service.MenuService;
import xyz.fusheng.vo.ActiveUser;
import xyz.fusheng.vo.AjaxResult;
import xyz.fusheng.vo.MenuTreeVo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @FileName: LoginController
 * @Author: code-fusheng
 * @Date: 2020/9/1 22:16
 * @version: 1.0
 * Description: 系统登录控制类
 */

@RestController
@Log4j2
public class LoginController {

    @Resource
    private MenuService menuService;

    /**
     * 登录方法
     * @Validated 注解用于配合 his-common 中 LoginBodyDto 对象属性上的注解
     * @param loginBodyDto
     * @param request
     * @return
     */
    @PostMapping("login/doLogin")
    public AjaxResult login(@RequestBody @Validated LoginBodyDto loginBodyDto, HttpServletRequest request) {
        AjaxResult ajax = AjaxResult.success();
        String username = loginBodyDto.getUsername();
        String password = loginBodyDto.getPassword();
        // 根据用户名和密码构造 Token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        // 封装 LoginInfo 登录信息
        try {
            subject.login(token);
            // 得到会话 ID 存储 Token 到 Redis
            Serializable webToken = subject.getSession().getId();
            ajax.put(Constants.TOKEN, webToken);
        } catch (Exception e) {
            log.error("用户名或密码不正确！", e);
            ajax = AjaxResult.error(HttpStatus.ERROR, "用户名或密码不正确！");
        }
        return ajax;
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("login/getInfo")
    public AjaxResult getInfo() {
        Subject subject = SecurityUtils.getSubject();
        ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
        AjaxResult ajax = AjaxResult.success();
        ajax.put("username", activeUser.getUser().getUserName());
        ajax.put("picture", activeUser.getUser().getPicture());
        ajax.put("roles", activeUser.getRoles());
        ajax.put("permissions", activeUser.getPermissions());
        return ajax;
    }

    /**
     * 用户退出
     * @return
     */
    @PostMapping("login/logout")
    public AjaxResult logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return AjaxResult.success("用户退出成功！");
    }

    /**
     * 获取应该显示的菜单单位
     * @return
     */
    @GetMapping("login/getMenus")
    public AjaxResult getMenus() {
        Subject subject = SecurityUtils.getSubject();
        ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
        // 判断是不是超级管理员 通过系统常量 USER_ADMIN
        boolean isAdmin = activeUser.getUser().getUserType().equals(Constants.USER_ADMIN);
        SimpleUser simpleUser = null;
        if (!isAdmin) {
            simpleUser = new SimpleUser(activeUser.getUser().getUserId(), activeUser.getUser().getUserName());
        }
        List<Menu> menus = menuService.selectMenuTree(isAdmin, simpleUser);
        List<MenuTreeVo> menuVos = new ArrayList<>();
        for (Menu menu : menus) {
            menuVos.add(new MenuTreeVo(menu.getMenuId().toString(), menu.getPath()));
        }
        return AjaxResult.success(menuVos);
    }

}
