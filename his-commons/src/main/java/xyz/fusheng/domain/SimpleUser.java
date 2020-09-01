package xyz.fusheng.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @FileName: SimpleUser
 * @Author: code-fusheng
 * @Date: 2020/9/1 15:03
 * @version: 1.0
 * Description: 登陆用户的数据传输对象
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleUser implements Serializable {
    private Serializable userId;
    private String userName;
}
