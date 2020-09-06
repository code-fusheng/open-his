package xyz.fusheng.controller.system; /**
 * @author: code-fusheng
 * @Date: 2020/9/6 16:12
 */

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.aspectj.annotation.Log;
import xyz.fusheng.aspectj.enums.BusinessType;
import xyz.fusheng.dto.DeptDto;
import xyz.fusheng.service.DeptService;
import xyz.fusheng.utils.ShiroSecurityUtils;
import xyz.fusheng.vo.AjaxResult;
import xyz.fusheng.vo.DataGridView;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @FileName: DeptController
 * @Author: code-fusheng
 * @Date: 2020/9/6 16:12
 * @version: 1.0
 * Description: 部门科室控制类
 */

@RestController
@RequestMapping("system/dept")
public class DeptController {

    @Resource
    private DeptService deptService;

    /**
     * 分页查询
     */
    @GetMapping("listDeptForPage")
    public AjaxResult listDeptForPage(DeptDto deptDto){
        DataGridView gridView = this.deptService.listPage(deptDto);
        return AjaxResult.success("查询成功",gridView.getData(),gridView.getTotal());
    }
    /**
     * 添加
     */
    @PostMapping("addDept")
    @Log(title = "添加科室",businessType = BusinessType.INSERT)
    public AjaxResult addDept(@Validated DeptDto deptDto) {
        deptDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.deptService.addDept(deptDto));
    }

    /**
     * 修改
     */
    @PutMapping("updateDept")
    @Log(title = "修改科室",businessType = BusinessType.UPDATE)
    public AjaxResult updateDept(@Validated DeptDto deptDto) {
        deptDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.deptService.updateDept(deptDto));
    }


    /**
     * 根据ID查询一个科室信息
     */
    @GetMapping("getDeptById/{deptId}")
    public AjaxResult getDeptById(@PathVariable @Validated @NotNull(message = "科室ID不能为空") Long deptId) {
        return AjaxResult.success(this.deptService.getOne(deptId));
    }

    /**
     * 删除
     */
    @DeleteMapping("deleteDeptByIds/{deptIds}")
    @Log(title = "删除科室",businessType = BusinessType.DELETE)
    public AjaxResult deleteDeptByIds(@PathVariable @Validated @NotEmpty(message = "要删除的ID不能为空") Long[] deptIds) {
        return AjaxResult.toAjax(this.deptService.deleteDeptByIds(deptIds));
    }

    /**
     * 查询所有可用的科室
     */
    @GetMapping("selectAllDept")
    public AjaxResult selectAllDept(){
        return AjaxResult.success(this.deptService.list());
    }

}
