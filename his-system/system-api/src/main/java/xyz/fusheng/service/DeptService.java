package xyz.fusheng.service;

import xyz.fusheng.domain.Dept;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.fusheng.dto.DeptDto;
import xyz.fusheng.vo.DataGridView;

import java.util.List;

/**
  * @author:   code-fusheng
  * @Date:     2020/9/6 15:58
  */
public interface DeptService{

     /**
      * 分页查询
      *
      * @param deptDto
      * @return
      */
     DataGridView listPage(DeptDto deptDto);

     /**
      * 查询所有有效部门
      *
      * @return
      */
     List<Dept> list();

     /**
      * 根据ID查询一个
      *
      * @param deptId
      * @return
      */
     Dept getOne(Long deptId);

     /**
      * 添加一个部门
      *
      * @param deptDto
      * @return
      */
     int addDept(DeptDto deptDto);

     /**
      * 修改部门
      *
      * @param deptDto
      * @return
      */
     int updateDept(DeptDto deptDto);

     /**
      * 根据IDS删除部门
      *
      * @param deptIds
      * @return
      */
     int deleteDeptByIds(Long[] deptIds);


}
