package xyz.fusheng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import xyz.fusheng.domain.Role;

import java.util.List;

/**
  * @author:   code-fusheng
  * @Date:     2020/9/10 19:43
  */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据角色IDS删除sys_role_menu中间表的数据
     *
     * @param ids
     */
    void deleteRoleMenuByRoleIds(@Param("ids") List<Long> ids);

    /**
     * 根据角色IDS删除sys_role_user中间表的数据
     *
     * @param ids
     */
    void deleteRoleUserByRoleIds(@Param("ids") List<Long> ids);

}
