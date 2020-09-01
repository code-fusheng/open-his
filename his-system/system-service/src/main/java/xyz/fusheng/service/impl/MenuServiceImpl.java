package xyz.fusheng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.fusheng.constants.Constants;
import xyz.fusheng.domain.Menu;
import xyz.fusheng.domain.SimpleUser;
import xyz.fusheng.mapper.MenuMapper;
import xyz.fusheng.service.MenuService;

/**
 * @author code-fusheng
 */
@Service
public class MenuServiceImpl implements MenuService{

    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<Menu> selectMenuTree(boolean isAdmin, SimpleUser simpleUser) {
        QueryWrapper<Menu> qw = new QueryWrapper<>();
        // 菜单状态有效
        qw.eq(Menu.COL_STATUS, Constants.STATUS_TRUE);
        // M,C 菜单 F 按钮级别权限
        qw.in(Menu.COL_MENU_TYPE, Constants.MENU_TYPE_M, Constants.MENU_TYPE_C);
        qw.orderByAsc(Menu.COL_PARENT_ID);
        if (isAdmin) {
            return menuMapper.selectList(qw);
        } else {
            // 根据用户id查询用户拥有的菜单信息
            return menuMapper.selectList(qw);
        }
    }
}
