package xyz.fusheng.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import xyz.fusheng.domain.SimpleUser;

import java.io.Serializable;
import java.util.Date;

/**
 * @FileName: BaseDto
 * @Author: code-fusheng
 * @Date: 2020/9/1 15:15
 * @version: 1.0
 * Description: 基础数据传输对象
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDto implements Serializable {

    /**
     * 页码 默认:1
     */
    private Integer pageNum = 1;

    /**
     * 每页显示条数 默认:10
     */
    private Integer pageSize = 10;

    /**
     * 当前操作对象
     */
    private SimpleUser simpleUser;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

}
