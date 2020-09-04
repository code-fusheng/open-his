package xyz.fusheng.controller.system; /**
 * @author: code-fusheng
 * @Date: 2020/9/4 22:00
 */

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.dto.LoginInfoDto;
import xyz.fusheng.service.LoginInfoService;
import xyz.fusheng.vo.AjaxResult;
import xyz.fusheng.vo.DataGridView;

import javax.annotation.Resource;

/**
 * @FileName: LoginInfoController
 * @Author: code-fusheng
 * @Date: 2020/9/4 22:00
 * @version: 1.0
 * Description: 登录日志信息控制类
 */

@Log4j2
@RestController
@RequestMapping("system/loginInfo")
public class LoginInfoController {

    @Resource
    private LoginInfoService loginInfoService;

    /**
     * 分页查询
     */
    @GetMapping("listForPage")
    public AjaxResult listForPage(LoginInfoDto loginInfoDto){
        DataGridView gridView = loginInfoService.listForPage(loginInfoDto);
        return AjaxResult.success("查询成功",gridView.getData(),gridView.getTotal());
    }

    /**
     * 删除
     */
    @DeleteMapping("deleteLoginInfoByIds/{infoIds}")
    public AjaxResult deleteLoginInfoByIds(@PathVariable Long[] infoIds){
        return AjaxResult.toAjax(this.loginInfoService.deleteLoginInfoByIds(infoIds));
    }
    /**
     * 清空删除
     */
    @DeleteMapping("clearLoginInfo")
    public AjaxResult clearLoginInfo(){
        return AjaxResult.toAjax(this.loginInfoService.clearLoginInfo());
    }

}
