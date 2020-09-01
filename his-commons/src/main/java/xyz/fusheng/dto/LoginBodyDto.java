package xyz.fusheng.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @FileName: LoginBodyDto
 * @Author: code-fusheng
 * @Date: 2020/9/1 15:19
 * @version: 1.0
 * Description: 登录用户信息传输对象
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginBodyDto implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

}
