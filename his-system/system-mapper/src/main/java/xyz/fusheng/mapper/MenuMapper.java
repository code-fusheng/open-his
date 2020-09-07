package xyz.fusheng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import xyz.fusheng.domain.Menu;

/**
 * @author code-fusheng
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据菜单ID查询当前菜单的子节点的个数
     *
     * @param menuId
     * @return
     */
    Long queryChildCountByMenuId(@Param("menuId") Long menuId);

}
