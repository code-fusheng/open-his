package xyz.fusheng.controller.system; /**
 * @author: code-fusheng
 * @Date: 2020/9/7 10:43
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.constants.Constants;
import xyz.fusheng.domain.Menu;
import xyz.fusheng.dto.MenuDto;
import xyz.fusheng.service.MenuService;
import xyz.fusheng.utils.ShiroSecurityUtils;
import xyz.fusheng.vo.AjaxResult;

import javax.annotation.Resource;
import java.util.List;

/**
 * @FileName: MenuController
 * @Author: code-fusheng
 * @Date: 2020/9/7 10:43
 * @version: 1.0
 * Description: 菜单控制器
 */

@RestController
@RequestMapping("system/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     * 查询所有菜单及权限信息
     */
    @GetMapping("listAllMenus")
    public AjaxResult listAllMenus(MenuDto menuDto){
        List<Menu> list=this.menuService.listAllMenus(menuDto);
        return AjaxResult.success(list);
    }

    /**
     * 查询菜单的下拉树
     */
    @GetMapping("selectMenuTree")
    public AjaxResult selectMenuTree(){
        MenuDto menuDto=new MenuDto();
        // 只查询可用的
        menuDto.setStatus(Constants.STATUS_TRUE);
        return AjaxResult.success(this.menuService.listAllMenus(menuDto));
    }

    /**
     * 添加菜单
     */
    @PostMapping("addMenu")
    public AjaxResult addMenu(@Validated MenuDto menuDto){
        menuDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.menuService.addMenu(menuDto));
    }

    /**
     * 修改菜单
     */
    @PutMapping("updateMenu")
    public AjaxResult updateMenu(@Validated MenuDto menuDto){
        menuDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.menuService.updateMenu(menuDto));
    }

    /**
     * 根据菜单ID查询一个
     */
    @GetMapping("getMenuById/{menuId}")
    public AjaxResult getMenuById(@PathVariable Long menuId){
        Menu menu=this.menuService.getOne(menuId);
        return AjaxResult.success(menu);
    }

    /**
     * 根据菜单ID删除
     */
    @DeleteMapping("deleteMenuById/{menuId}")
    public AjaxResult deleteMenuById(@PathVariable Long menuId){
        //删除之前要判断当前菜单有没有子节点
        if(this.menuService.hasChildByMenuId(menuId)){
            return AjaxResult.fail("当前要删除的菜单有子节点，请先删除子节点");
        }
        return AjaxResult.toAjax(this.menuService.deleteMenuById(menuId));
    }

}
