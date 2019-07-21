package com.len.service;

import com.len.base.BaseService;
import com.len.entity.SysRole;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author zhuxiaomeng
 * @date 2017/12/19.
 * @email 154040976@qq.com
 */
public interface RoleService extends BaseService<SysRole,String> {

  @Override
  int deleteByPrimaryKey(String id);

  @Override
  int insert(SysRole record);

  @Override
  int insertSelective(SysRole record);

  @Override
  SysRole selectByPrimaryKey(String id);

  @Override
  int updateByPrimaryKeySelective(SysRole record);

  @Override
  int updateByPrimaryKey(SysRole record);

  List<SysRole> selectListByPage(SysRole sysRole);
}
