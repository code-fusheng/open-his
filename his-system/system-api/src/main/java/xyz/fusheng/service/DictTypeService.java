package xyz.fusheng.service;

import xyz.fusheng.domain.DictType;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.fusheng.dto.DictTypeDto;
import xyz.fusheng.vo.DataGridView;

/**
 * @author:   code-fusheng
 * @Date:     2020/9/3 11:42
 */
public interface DictTypeService{

    /**
     * 分页查询字典类型
     * @param dictTypeDto
     * @return
     */
    DataGridView listPage(DictTypeDto dictTypeDto);

    /**
     * 查询所有字典类型
     * @return
     */
    DataGridView list();

    /**
     * 检查字典类型是否存在
     * @param dictId
     * @param dictType
     * @return
     */
    Boolean checkDictTypeUnique(Long dictId, String dictType);

    /**
     * 插入新的字典类型
     * @param dictTypeDto
     * @return
     */
    int insert(DictTypeDto dictTypeDto);

    /**
     * 修改字典类型
     * @param dictTypeDto
     * @return
     */
    int update(DictTypeDto dictTypeDto);

    /**
     * 根据 ID 删除字典类型
     * @param dictIds
     * @return
     */
    int deleteDictTypeByIds(Long[] dictIds);

    /**
     * 根据 ID 查询单个字典类型
     * @param dictId
     * @return
     */
    DictType selectDictTypeById(Long dictId);

    /**
     * 同步字典数据到缓存
     */
    void dictCacheAsync();

}
