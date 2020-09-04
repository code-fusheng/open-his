package xyz.fusheng.service;

import xyz.fusheng.domain.OperLog;
import xyz.fusheng.dto.OperLogDto;
import xyz.fusheng.vo.DataGridView;

/**
 * @author: code-fusheng
 * @Date: 2020/9/4 15:09
 */
public interface OperLogService {

     /**
      * 插入操作日志
      *
      * @param operLog
      */
     void insertOperLog(OperLog operLog);

     /**
      * 分页查询操作日志
      *
      * @param operLogDto
      * @return
      */
     DataGridView listForPage(OperLogDto operLogDto);

     /**
      * 根据ID删除操作日志
      *
      * @param infoIds
      * @return
      */
     int deleteOperLogByIds(Long[] infoIds);

     /**
      * 清空操作日志
      *
      * @return
      */
     int clearAllOperLog();

}
