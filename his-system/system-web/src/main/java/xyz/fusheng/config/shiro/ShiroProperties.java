package xyz.fusheng.config.shiro; /**
 * @author: code-fusheng
 * @Date: 2020/9/1 22:01
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @FileName: ShiroProperties
 * @Author: code-fusheng
 * @Date: 2020/9/1 22:01
 * @version: 1.0
 * Description: shiro 属性类
 */

@ConfigurationProperties(prefix = "shiro")
@Data
public class ShiroProperties {

    /**
     * 密码加密方式
     */
    private String hashAlgorithmName="md5";
    /**
     * 密码散列次数
     */
    private Integer hashIterations=2;

    /**
     * 不拦击的路径
     */
    private String [] anonUrls;

    /**
     * 拦截的路径
     */
    private String [] authcUrls;

}
