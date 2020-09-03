package xyz.fusheng.controller.system; /**
 * @author: code-fusheng
 * @Date: 2020/9/3 13:18
 */

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.dto.DictTypeDto;
import xyz.fusheng.service.DictTypeService;
import xyz.fusheng.utils.ShiroSecurityUtils;
import xyz.fusheng.vo.AjaxResult;
import xyz.fusheng.vo.DataGridView;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @FileName: DictTypeController
 * @Author: code-fusheng
 * @Date: 2020/9/3 13:18
 * @version: 1.0
 * Description: 字典分类控制器
 */

@RestController
@RequestMapping("system/dict/type")
public class DictTypeController {

    @Resource
    private DictTypeService dictTypeService;

    /**
     * 分页查询
     * @param dictTypeDto
     * @return
     */
    @GetMapping("listForPage")
    public AjaxResult listForPage(DictTypeDto dictTypeDto) {
        DataGridView gridView = this.dictTypeService.listPage(dictTypeDto);
        return AjaxResult.success("查询成功！", gridView.getData(), gridView.getTotal());
    }

    /**
     * 添加字典类型
     * @param dictTypeDto
     * @return
     */
    @PostMapping("addDictType")
    public AjaxResult addDictType(@Validated DictTypeDto dictTypeDto) {
        // 先检查字典是否已经存在
        if (dictTypeService.checkDictTypeUnique(dictTypeDto.getDictId(), dictTypeDto.getDictType())) {
            return AjaxResult.fail("新增字典【" + dictTypeDto.getDictName() + "】失败，字典类型已存在");
        }
        dictTypeDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.dictTypeService.insert(dictTypeDto));
    }

    /**
     * 修改字典类型
     * @param dictTypeDto
     * @return
     */
    @PutMapping("updateDictType")
    public AjaxResult updateDictType(@Validated DictTypeDto dictTypeDto) {
        if (dictTypeService.checkDictTypeUnique(dictTypeDto.getDictId(), dictTypeDto.getDictType())) {
            return AjaxResult.fail("修改字典【" + dictTypeDto.getDictName() + "】失败，字典类型已存在");
        }
        dictTypeDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.dictTypeService.update(dictTypeDto));
    }

    /**
     * 根据ID查询一个字典信息
     * @param dictId
     * @return
     */
    @GetMapping("getOne/{dictId}")
    public AjaxResult getDictType(@PathVariable @Validated @NotNull(message = "字典ID不能为空") Long dictId) {
        return AjaxResult.success(this.dictTypeService.selectDictTypeById(dictId));
    }

    /**
     * 根据ID删除字典类型
     * @param dictIds
     * @return
     */
    @DeleteMapping("deleteDictTypeByIds/{dictIds}")
    public AjaxResult updateDictType(@PathVariable @Validated @NotEmpty(message = "要删除的ID不能为空") Long[] dictIds) {
        return AjaxResult.toAjax(this.dictTypeService.deleteDictTypeByIds(dictIds));
    }

    /**
     * 查询所有可用字典类型
     * @return
     */
    @GetMapping("selectAllDictType")
    public AjaxResult selectAllDictType(){
        return AjaxResult.success(this.dictTypeService.list().getData());
    }

    /**
     * 同步字典数据到 Redis
     * @return
     */
    @GetMapping("dictCacheAsync")
    public AjaxResult dictCacheAsync(){
        try {
            this.dictTypeService.dictCacheAsync();
            return AjaxResult.success();
        }catch (Exception e){
            System.out.println(e);
            return AjaxResult.error();
        }
    }

}
