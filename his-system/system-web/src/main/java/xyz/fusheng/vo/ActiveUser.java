package xyz.fusheng.vo; /**
 * @author: code-fusheng
 * @Date: 2020/9/1 21:50
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.fusheng.domain.User;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @FileName: ActiveUser
 * @Author: code-fusheng
 * @Date: 2020/9/1 21:50
 * @version: 1.0
 * Description: 登录用户用于存储在 Redis 中的信息对象
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveUser implements Serializable {

    /**
     * 用户对象
     */
    private User user;

    /**
     * 角色列表
     */
    private List<String> roles = Collections.EMPTY_LIST;

    /**
     * 权限列表
     */
    private List<String> permissions = Collections.EMPTY_LIST;
}
