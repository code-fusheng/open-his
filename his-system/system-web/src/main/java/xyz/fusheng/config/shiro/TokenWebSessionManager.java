package xyz.fusheng.config.shiro; /**
 * @author: code-fusheng
 * @Date: 2020/9/1 21:31
 */

import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.annotation.Configuration;
import xyz.fusheng.constants.Constants;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.UUID;

/**
 * @FileName: TokenWebSessionManager
 * @Author: code-fusheng
 * @Date: 2020/9/1 21:31
 * @version: 1.0
 * Description: 初次访问生成 Token
 * 如果没有token生成一个返回到前台，如果有就从请求头里面取出来
 */

@Configuration
public class TokenWebSessionManager extends DefaultWebSessionManager {

    /**
     * 重写 GetSessionId 方法
     * @param request
     * @param response
     * @return
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        // 从 request 请求的请求头中获取 Token
        String token = WebUtils.toHttp(request).getHeader(Constants.TOKEN);
        // 如果不存在就生成一个
        if (StringUtils.hasText(token)) {
            return token;
        } else {
            return UUID.randomUUID().toString();
        }
    }
}
