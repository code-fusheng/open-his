package xyz.fusheng.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.fusheng.constants.Constants;
import xyz.fusheng.domain.DictType;
import xyz.fusheng.dto.DictTypeDto;
import xyz.fusheng.mapper.DictTypeMapper;
import xyz.fusheng.service.DictTypeService;
import xyz.fusheng.vo.DataGridView;

/**
 * @author: code-fusheng
 * @Date: 2020/9/3 11:42
 */
@Service
public class DictTypeServiceImpl implements DictTypeService {

    @Resource
    private DictTypeMapper dictTypeMapper;

    @Override
    public DataGridView listPage(DictTypeDto dictTypeDto) {
        // mybatis-plus 提供的page分页对象
        Page<DictType> page = new Page<>(dictTypeDto.getPageNum(), dictTypeDto.getPageSize());
        // 查询条件构建器
        QueryWrapper<DictType> qw = new QueryWrapper<>();
        // 字典名称模糊查询你
        qw.like(StringUtils.isNotBlank(dictTypeDto.getDictName()), DictType.COL_DICT_NAME, dictTypeDto.getDictName());
        // 字典类型模糊查询
        qw.like(StringUtils.isNotBlank(dictTypeDto.getDictType()), DictType.COL_DICT_TYPE, dictTypeDto.getDictType());
        // 字典状态
        qw.eq(StringUtils.isNotBlank(dictTypeDto.getStatus()), DictType.COL_STATUS, dictTypeDto.getStatus());
        // 创建时间查询 ge 大于 le 小于
        qw.ge(dictTypeDto.getBeginTime() != null, DictType.COL_CREATE_TIME, dictTypeDto.getBeginTime());
        qw.le(dictTypeDto.getEndTime() != null, DictType.COL_CREATE_TIME, dictTypeDto.getEndTime());
        this.dictTypeMapper.selectPage(page,qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public DataGridView list() {
        QueryWrapper<DictType> qw = new QueryWrapper<>();
        qw.eq(DictType.COL_STATUS, Constants.STATUS_TRUE);
        return new DataGridView(null, this.dictTypeMapper.selectList(qw));
    }

    @Override
    public Boolean checkDictTypeUnique(Long dictId, String dictType) {
        // 修改和插入的时候检查是否已经存在相同的字典分类
        dictId = (dictId == null) ? -1L : dictId;
        QueryWrapper<DictType> qw = new QueryWrapper<>();
        qw.eq(DictType.COL_DICT_TYPE, dictType);
        DictType sysDictType = this.dictTypeMapper.selectOne(qw);
        // 如果字典类型名和字典ID都相同，说明插入的是重复的
        if (null != sysDictType && dictId.longValue() != sysDictType.getDictId().longValue()) {
            // 不存在
            return true;
        }
        // 存在
        return false;
    }

    @Override
    public int insert(DictTypeDto dictTypeDto) {
        DictType dictType = new DictType();
        BeanUtil.copyProperties(dictTypeDto, dictType);
        // 设置创建者、创建时间
        dictType.setCreateBy(dictTypeDto.getSimpleUser().getUserName());
        dictType.setCreateTime(DateUtil.date());
        return this.dictTypeMapper.insert(dictType);
    }

    @Override
    public int update(DictTypeDto dictTypeDto) {
        DictType dictType = new DictType();
        BeanUtil.copyProperties(dictTypeDto, dictType);
        //设置修改人
        dictType.setUpdateBy(dictTypeDto.getSimpleUser().getUserName());
        return this.dictTypeMapper.updateById(dictType);
    }

    @Override
    public int deleteDictTypeByIds(Long[] dictIds) {
        List<Long> ids = Arrays.asList(dictIds);
        if (null != ids && ids.size() > 0) {
            return this.dictTypeMapper.deleteBatchIds(ids);
        }
        return -1;
    }

    @Override
    public DictType selectDictTypeById(Long dictId) {
        return this.dictTypeMapper.selectById(dictId);
    }

    @Override
    public void dictCacheAsync() {

    }
}
