package cn.cttic.sysframe.frame.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.cttic.sysframe.frame.domain.SmSysDict;
import cn.cttic.sysframe.frame.domain.SmSysDictCriteria;

public interface SmSysDictMapper {
    int countByExample(SmSysDictCriteria example);

    int deleteByExample(SmSysDictCriteria example);

    int insert(SmSysDict record);

    int insertSelective(SmSysDict record);

    List<SmSysDict> selectByExample(SmSysDictCriteria example);

    int updateByExampleSelective(@Param("record") SmSysDict record, @Param("example") SmSysDictCriteria example);

    int updateByExample(@Param("record") SmSysDict record, @Param("example") SmSysDictCriteria example);
    
    List<SmSysDict> selectByRootTypeCode(Map<String,String> param);
}