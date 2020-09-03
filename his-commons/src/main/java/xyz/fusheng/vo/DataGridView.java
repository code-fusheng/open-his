package xyz.fusheng.vo; /**
 * @author: code-fusheng
 * @Date: 2020/9/3 11:53
 */

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @FileName: DataGridView
 * @Author: code-fusheng
 * @Date: 2020/9/3 11:53
 * @version: 1.0
 * Description: 分页数据封装
 */

@ApiModel(value="xyz.fusheng-vo-DataGridView")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGridView implements Serializable{

    private static final long serialVersionUID = 6308738600715350755L;

    /**
     * 总条数
     */
    private Long total;

    /**
     * 分页数据
     */
    private List<?> data;

}
