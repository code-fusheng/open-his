package xyz.fusheng.service;

import xyz.fusheng.domain.LoginInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.fusheng.dto.LoginInfoDto;
import xyz.fusheng.vo.DataGridView;

/**
 * @author: code-fusheng
 * @Date: 2020/9/4 18:41
 */
public interface LoginInfoService {

     /**
      * 添加
      * @param loginInfo
      * @return
      */
     int insertLoginInfo(LoginInfo loginInfo);

     /**
      * 分页查询
      * @param loginInfoDto
      * @return
      */
     DataGridView listForPage(LoginInfoDto loginInfoDto);

     /**
      * 删除登陆日志
      * @param infoIds
      * @return
      */
     int deleteLoginInfoByIds(Long[] infoIds);

     /**
      * 清空登陆日志
      * @return
      */
     int clearLoginInfo();

}
