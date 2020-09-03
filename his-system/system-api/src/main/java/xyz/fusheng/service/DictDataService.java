package xyz.fusheng.service;

import xyz.fusheng.domain.DictData;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.fusheng.dto.DictDataDto;
import xyz.fusheng.vo.DataGridView;

import java.util.List;

/**
 * @author: code-fusheng
 * @Date: 2020/9/3 18:33
 */
public interface DictDataService {

     /**
      * 分页查询字典数据
      * @param dictDataDto
      * @return
      */
     DataGridView listPage(DictDataDto dictDataDto);

     /**
      * 插入字典数据
      * @param dictDataDto
      * @return
      */
     int insert(DictDataDto dictDataDto);

     /**
      * 修改字典数据
      * @param dictDataDto
      * @return
      */
     int update(DictDataDto dictDataDto);

     /**
      * 根据ID删除字典数据
      * @param dictCodeIds
      * @return
      */
     int deleteDictDataByIds(Long[] dictCodeIds);

     /**
      * 根据字典类型查询字典数据
      * @param dictType
      * @return
      */
     List<DictData> selectDictDataByDictType(String dictType);

     /**
      * 根据ID查询一个字典数据
      * @param dictCode
      * @return
      */
     DictData selectDictDataById(Long dictCode);

}
