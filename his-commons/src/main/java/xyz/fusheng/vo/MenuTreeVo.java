package xyz.fusheng.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @FileName: MenuTreeVo
 * @Author: code-fusheng
 * @Date: 2020/9/1 15:11
 * @version: 1.0
 * Description: 构造树形菜单返回前台 Vo
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuTreeVo {

    private String id;

    /**
     * 菜单表里面的 url
     */
    private String serPath;

    /**
     * 是否显示菜单
     */
    private boolean show = true;

    /**
     * 构造方法
     * @param id
     * @param serPath
     */
    public MenuTreeVo(String id, String serPath) {
        this.id = id;
        this.serPath = serPath;
    }
}
