package xyz.fusheng.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.fusheng.constants.Constants;
import xyz.fusheng.domain.Menu;
import xyz.fusheng.domain.SimpleUser;
import xyz.fusheng.dto.MenuDto;
import xyz.fusheng.mapper.MenuMapper;
import xyz.fusheng.mapper.RoleMapper;
import xyz.fusheng.service.MenuService;

/**
 * @author code-fusheng
 */
@Service
public class MenuServiceImpl implements MenuService{

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private RoleMapper roleMapper;

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

    @Override
    public List<Menu> listAllMenus(MenuDto menuDto) {
        QueryWrapper<Menu> qw=new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(menuDto.getMenuName()),Menu.COL_MENU_NAME,menuDto.getMenuName());
        qw.eq(StringUtils.isNotBlank(menuDto.getStatus()),Menu.COL_STATUS,menuDto.getStatus());
        return this.menuMapper.selectList(qw);
    }

    @Override
    public int addMenu(MenuDto menuDto) {
        Menu menu=new Menu();
        BeanUtil.copyProperties(menuDto,menu);
        //设置创建人和创建时间
        menu.setCreateTime(DateUtil.date());
        menu.setCreateBy(menuDto.getSimpleUser().getUserName());
        return this.menuMapper.insert(menu);
    }

    @Override
    public int updateMenu(MenuDto menuDto) {
        Menu menu=new Menu();
        BeanUtil.copyProperties(menuDto,menu);
        //设置修改人
        menu.setUpdateBy(menuDto.getSimpleUser().getUserName());
        return this.menuMapper.updateById(menu);
    }

    @Override
    public Menu getOne(Long menuId) {
        return this.menuMapper.selectById(menuId);
    }

    @Override
    public boolean hasChildByMenuId(Long menuId) {
        Long count=this.menuMapper.queryChildCountByMenuId(menuId);
        return count>0L?true:false;
    }

    @Override
    public int deleteMenuById(Long menuId) {
        //删除sys_role_menu中间表的数据[后面完成]
        this.roleMapper.deleteRoleMenuByMenuIds(Arrays.asList(menuId));
        return this.menuMapper.deleteById(menuId);
    }

    @Override
    public List<Long> getMenusIdsByRoleId(Long roleId) {
        return this.menuMapper.queryMenuIdsByRoleId(roleId);
    }
}
