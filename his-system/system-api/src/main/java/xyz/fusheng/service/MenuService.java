package xyz.fusheng.service;

import xyz.fusheng.domain.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.fusheng.domain.SimpleUser;

import java.util.List;

/**
 * @author code-fusheng
 */
public interface MenuService{

    /**
     * 查询菜单信息
     * 如查用户是超级管理员，那么查询所以菜单和权限
     * 如果用户是普通用户，那么根据用户ID关联角色和权限
     * @param isAdmin   是否是超级管理员
     * @param simpleUser   当前用户 ID 如果isAdmin=true  simpleUser可以为空
     * @return
     */
    public List<Menu> selectMenuTree(boolean isAdmin, SimpleUser simpleUser);

}
