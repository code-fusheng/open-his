package xyz.fusheng.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import xyz.fusheng.constants.Constants;
import xyz.fusheng.domain.DictData;
import xyz.fusheng.dto.DictDataDto;
import xyz.fusheng.mapper.DictDataMapper;
import xyz.fusheng.service.DictDataService;
import xyz.fusheng.vo.DataGridView;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author: code-fusheng
 * @Date: 2020/9/3 18:33
 */
@Service
public class DictDataServiceImpl implements DictDataService {

    @Resource
    private DictDataMapper dictDataMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public DataGridView listPage(DictDataDto dictDataDto) {
        Page<DictData> page = new Page<>(dictDataDto.getPageNum(), dictDataDto.getPageSize());
        QueryWrapper<DictData> qw = new QueryWrapper<>();
        // 字典类型
        qw.eq(StringUtils.isNotBlank(dictDataDto.getDictType()), DictData.COL_DICT_TYPE, dictDataDto.getDictType());
        // 字典标签
        qw.like(StringUtils.isNotBlank(dictDataDto.getDictLabel()), DictData.COL_DICT_LABEL, dictDataDto.getDictLabel());
        // 字典状态
        qw.eq(StringUtils.isNotBlank(dictDataDto.getStatus()), DictData.COL_STATUS, dictDataDto.getStatus());
        this.dictDataMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public int insert(DictDataDto dictDataDto) {
        DictData dictData = new DictData();
        BeanUtil.copyProperties(dictDataDto, dictData);
        //设置创建者，创建时间
        dictData.setCreateBy(dictDataDto.getSimpleUser().getUserName());
        dictData.setCreateTime(DateUtil.date());
        return this.dictDataMapper.insert(dictData);
    }

    @Override
    public int update(DictDataDto dictDataDto) {
        DictData dictData = new DictData();
        BeanUtil.copyProperties(dictDataDto, dictData);
        //设置修改人
        dictData.setUpdateBy(dictDataDto.getSimpleUser().getUserName());
        return this.dictDataMapper.updateById(dictData);
    }

    @Override
    public int deleteDictDataByIds(Long[] dictCodeIds) {
        List<Long> ids= Arrays.asList(dictCodeIds);
        if (null != ids && ids.size() > 0) {
            return this.dictDataMapper.deleteBatchIds(ids);
        } else {
            return -1;
        }
    }

    @Override
    public List<DictData> selectDictDataByDictType(String dictType) {
        String key = Constants.DICT_REDIS_PROFIX + dictType;
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String json = opsForValue.get(key);
        List<DictData> dictDataList= JSON.parseArray(json,DictData.class);
        return dictDataList;
    }

    @Override
    public DictData selectDictDataById(Long dictCode) {
        return this.dictDataMapper.selectById(dictCode);
    }
}
