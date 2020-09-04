package xyz.fusheng.controller.system; /**
 * @author: code-fusheng
 * @Date: 2020/9/4 15:57
 */

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.dto.OperLogDto;
import xyz.fusheng.service.OperLogService;
import xyz.fusheng.vo.AjaxResult;
import xyz.fusheng.vo.DataGridView;

import javax.annotation.Resource;

/**
 * @FileName: OperLogController
 * @Author: code-fusheng
 * @Date: 2020/9/4 15:57
 * @version: 1.0
 * Description: 操作日志控制类
 */

@Log4j2
@RestController
@RequestMapping("system/operLog")
public class OperLogController {

    @Resource
    private OperLogService operLogService;

    /**
     * 分页查询
     */
    @GetMapping("listForPage")
    public AjaxResult listForPage(OperLogDto operLogDto){
        DataGridView gridView = operLogService.listForPage(operLogDto);
        return AjaxResult.success("查询成功",gridView.getData(),gridView.getTotal());
    }

    /**
     * 删除
     */
    @DeleteMapping("deleteOperLogByIds/{infoIds}")
    public AjaxResult deleteOperLogByIds(@PathVariable Long[] infoIds){
        return AjaxResult.toAjax(this.operLogService.deleteOperLogByIds(infoIds));
    }
    /**
     * 清空删除
     */
    @DeleteMapping("clearAllOperLog")
    public AjaxResult clearAllOperLog(){
        return AjaxResult.toAjax(this.operLogService.clearAllOperLog());
    }

}
