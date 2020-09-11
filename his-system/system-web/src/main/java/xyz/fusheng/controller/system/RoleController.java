package xyz.fusheng.controller.system; /**
 * @author: code-fusheng
 * @Date: 2020/9/10 22:33
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.aspectj.annotation.Log;
import xyz.fusheng.aspectj.enums.BusinessType;
import xyz.fusheng.dto.RoleDto;
import xyz.fusheng.service.RoleService;
import xyz.fusheng.utils.ShiroSecurityUtils;
import xyz.fusheng.vo.AjaxResult;
import xyz.fusheng.vo.DataGridView;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @FileName: RoleController
 * @Author: code-fusheng
 * @Date: 2020/9/10 22:33
 * @version: 1.0
 * Description: 角色控制类
 */

@RestController
@RequestMapping("system/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 分页查询
     */
    @GetMapping("listRoleForPage")
    public AjaxResult listRoleForPage(RoleDto roleDto){
        DataGridView gridView = this.roleService.listRolePage(roleDto);
        return AjaxResult.success("查询成功",gridView.getData(),gridView.getTotal());
    }
    /**
     * 添加
     */
    @PostMapping("addRole")
    @Log(title = "添加角色",businessType = BusinessType.INSERT)
    public AjaxResult addRole(@Validated RoleDto roleDto) {
        roleDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.roleService.addRole(roleDto));
    }

    /**
     * 修改
     */
    @PutMapping("updateRole")
    @Log(title = "修改角色",businessType = BusinessType.UPDATE)
    public AjaxResult updateRole(@Validated RoleDto roleDto) {
        roleDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.roleService.updateRole(roleDto));
    }


    /**
     * 根据ID查询一个角色信息
     */
    @GetMapping("getRoleById/{roleId}")
    public AjaxResult getRoleById(@PathVariable @Validated @NotNull(message = "角色ID不能为空") Long roleId) {
        return AjaxResult.success(this.roleService.getOne(roleId));
    }

    /**
     * 删除
     */
    @DeleteMapping("deleteRoleByIds/{roleIds}")
    @Log(title = "删除角色",businessType = BusinessType.DELETE)
    public AjaxResult deleteRoleByIds(@PathVariable @Validated @NotEmpty(message = "要删除的ID不能为空") Long[] roleIds) {
        return AjaxResult.toAjax(this.roleService.deleteRoleByIds(roleIds));
    }

    /**
     * 保存角色和菜单之间的关系
     */
    @PostMapping("saveRoleMenu/{roleId}/{menuIds}")
    public AjaxResult saveRoleMenu(@PathVariable Long roleId,@PathVariable Long[] menuIds){
        /**
         * 因为我们用的路径参数，前端可能传过来的menuIds是一个空的，但是为空的话无法匹配上面的路径
         * 所以如果为空，我们让前端传一个-1过来，如果是-1说明当前角色一个权限也没有选择
         */
        if(menuIds.length==1&&menuIds[0].equals(-1L)){
            menuIds = new Long[]{};
        }
        this.roleService.saveRoleMenu(roleId, menuIds);
        return AjaxResult.success();
    }

}
