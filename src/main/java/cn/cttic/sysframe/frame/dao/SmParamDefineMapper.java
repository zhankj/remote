package cn.cttic.sysframe.frame.dao;

import cn.cttic.sysframe.frame.domain.SmParamDefine;
import cn.cttic.sysframe.frame.domain.SmParamDefineExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmParamDefineMapper {
    int countByExample(SmParamDefineExample example);

    int deleteByExample(SmParamDefineExample example);

    int deleteByPrimaryKey(String typeCode);

    int insert(SmParamDefine record);

    int insertSelective(SmParamDefine record);

    List<SmParamDefine> selectByExample(SmParamDefineExample example);

    SmParamDefine selectByPrimaryKey(String typeCode);

    int updateByExampleSelective(@Param("record") SmParamDefine record, @Param("example") SmParamDefineExample example);

    int updateByExample(@Param("record") SmParamDefine record, @Param("example") SmParamDefineExample example);

    int updateByPrimaryKeySelective(SmParamDefine record);

    int updateByPrimaryKey(SmParamDefine record);
    
    List<String> selectByParentTypeCode(String parentTypeCode);

}