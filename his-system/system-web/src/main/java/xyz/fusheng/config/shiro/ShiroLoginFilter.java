package xyz.fusheng.config.shiro; /**
 * @author: code-fusheng
 * @Date: 2020/9/1 21:24
 */

import com.alibaba.fastjson.JSON;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import xyz.fusheng.constants.HttpStatus;
import xyz.fusheng.vo.AjaxResult;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * @FileName: ShiroLoginFilter
 * @Author: code-fusheng
 * @Date: 2020/9/1 21:24
 * @version: 1.0
 * Description: 用户登录过滤器 - 判断用户是否登录
 */

public class ShiroLoginFilter extends FormAuthenticationFilter {

    /**
     * 在访问controller前判断是否登录，返回json，不进行重定向。
     * @param request
     * @param response
     * @return true-继续往下执行，false-该filter过滤器已经处理，不继续执行其他过滤器
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        // 封装返回信息
        AjaxResult ajaxResult=AjaxResult.fail();
        // 未授权状态码 401
        ajaxResult.put("code", HttpStatus.UNAUTHORIZED);
        ajaxResult.put("msg", "登录认证失效，请重新登录!");
        httpServletResponse.getWriter().write(JSON.toJSON(ajaxResult).toString());
        return false;
    }
}
