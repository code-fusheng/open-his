package xyz.fusheng.controller.system; /**
 * @author: code-fusheng
 * @Date: 2020/9/3 19:02
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.dto.DictDataDto;
import xyz.fusheng.service.DictDataService;
import xyz.fusheng.utils.ShiroSecurityUtils;
import xyz.fusheng.vo.AjaxResult;
import xyz.fusheng.vo.DataGridView;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @FileName: DictDataController
 * @Author: code-fusheng
 * @Date: 2020/9/3 19:02
 * @version: 1.0
 * Description: 字典数据控制类
 */

@RestController
@RequestMapping("system/dict/data")
public class DictDataController {

    @Resource
    private DictDataService dictDataService;

    /**
     * 分页查询字典数据
     * @param dictDataDto
     * @return
     */
    @GetMapping("listForPage")
    public AjaxResult listForPage(DictDataDto dictDataDto){
        DataGridView gridView = this.dictDataService.listPage(dictDataDto);
        return AjaxResult.success("查询成功",gridView.getData(),gridView.getTotal());
    }

    /**
     * 添加字典数据
     * @param dictDataDto
     * @return
     */
    @PostMapping("addDictData")
    public AjaxResult addDictData(@Validated DictDataDto dictDataDto) {
        dictDataDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.dictDataService.insert(dictDataDto));
    }

    /**
     * 修改字典数据
     * @param dictDataDto
     * @return
     */
    @PutMapping("updateDictData")
    public AjaxResult updateDictData(@Validated DictDataDto dictDataDto) {
        dictDataDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.dictDataService.update(dictDataDto));
    }

    /**
     * 根据ID查询一个字典信息
     * @param dictId
     * @return
     */
    @GetMapping("getOne/{dictId}")
    public AjaxResult getDictData(@PathVariable @Validated @NotNull(message = "字典数据ID不能为空") Long dictId) {
        return AjaxResult.success(this.dictDataService.selectDictDataById(dictId));
    }

    /**
     * 删除字典数据
     * @param dictIds
     * @return
     */
    @DeleteMapping("deleteDictDataByIds/{dictIds}")
    public AjaxResult updateDictData(@PathVariable @Validated @NotEmpty(message = "要删除的数据ID不能为空") Long[] dictIds) {
        return AjaxResult.toAjax(this.dictDataService.deleteDictDataByIds(dictIds));
    }

    /**
     * 根据字典类型查询可用的数据
     * @param dictType
     * @return
     */
    @GetMapping("getDataByType/{dictType}")
    public AjaxResult getDataByType(@PathVariable @Validated @NotEmpty(message = "字典类型不能为空")  String dictType){
        return AjaxResult.success(this.dictDataService.selectDictDataByDictType(dictType));
    }

}
