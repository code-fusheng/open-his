package xyz.fusheng.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.fusheng.constants.Constants;
import xyz.fusheng.domain.Role;
import xyz.fusheng.dto.RoleDto;
import xyz.fusheng.mapper.RoleMapper;
import xyz.fusheng.service.RoleService;
import xyz.fusheng.vo.DataGridView;

/**
 * @author: code-fusheng
 * @Date: 2020/9/10 19:43
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public DataGridView listRolePage(RoleDto roleDto) {
        Page<Role> page = new Page<>(roleDto.getPageNum(), roleDto.getPageSize());
        QueryWrapper<Role> qw = new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(roleDto.getRoleName()), Role.COL_ROLE_NAME, roleDto.getRoleName());
        qw.like(StringUtils.isNotBlank(roleDto.getRoleCode()), Role.COL_ROLE_CODE, roleDto.getRoleCode());
        qw.eq(StringUtils.isNotBlank(roleDto.getStatus()), Role.COL_STATUS, roleDto.getStatus());
        qw.ge(null != roleDto.getBeginTime(), Role.COL_CREATE_TIME, roleDto.getBeginTime());
        qw.le(null != roleDto.getEndTime(), Role.COL_CREATE_TIME, roleDto.getEndTime());
        qw.orderByAsc(Role.COL_ROLE_SORT);
        this.roleMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public List<Role> listAllRoles() {
        QueryWrapper<Role> qw = new QueryWrapper<>();
        qw.eq(Role.COL_STATUS, Constants.STATUS_TRUE);
        qw.orderByAsc(Role.COL_ROLE_SORT);
        return this.roleMapper.selectList(qw);
    }

    @Override
    public Role getOne(Long roleId) {
        return this.roleMapper.selectById(roleId);
    }

    @Override
    public int addRole(RoleDto roleDto) {
        Role role = new Role();
        BeanUtil.copyProperties(roleDto, role);
        // 设置创建人和创建时间
        role.setCreateTime(DateUtil.date());
        role.setCreateBy(roleDto.getSimpleUser().getUserName());
        return this.roleMapper.insert(role);
    }

    @Override
    public int updateRole(RoleDto roleDto) {
        Role role = new Role();
        BeanUtil.copyProperties(roleDto, role);
        // 设置修改人
        role.setUpdateBy(roleDto.getSimpleUser().getUserName());
        return this.roleMapper.updateById(role);
    }

    @Override
    public int deleteRoleByIds(Long[] roleIds) {
        List<Long> rids = Arrays.asList(roleIds);
        if (rids != null && !rids.isEmpty()) {
            // 根据角色ID删除 sys_role_menu 的数据
            this.roleMapper.deleteRoleMenuByRoleIds(rids);
            // 根据角色ID删除sys_role_user里面的数据
            // this.roleMapper.deleteRoleUserByRoleIds(rids);
            // 删除角色
            return this.roleMapper.deleteBatchIds(rids);
        }
        return 0;
    }

}
