package cn.cttic.sysframe.frame.dao;

import cn.cttic.sysframe.frame.domain.SmJobConfig;
import cn.cttic.sysframe.frame.domain.SmJobConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmJobConfigMapper {
    int countByExample(SmJobConfigExample example);

    int deleteByExample(SmJobConfigExample example);

    int deleteByPrimaryKey(String jobCode);

    int insert(SmJobConfig record);

    int insertSelective(SmJobConfig record);

    List<SmJobConfig> selectByExample(SmJobConfigExample example);

    SmJobConfig selectByPrimaryKey(String jobCode);

    int updateByExampleSelective(@Param("record") SmJobConfig record, @Param("example") SmJobConfigExample example);

    int updateByExample(@Param("record") SmJobConfig record, @Param("example") SmJobConfigExample example);

    int updateByPrimaryKeySelective(SmJobConfig record);

    int updateByPrimaryKey(SmJobConfig record);
}