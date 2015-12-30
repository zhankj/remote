package cn.cttic.sysframe.frame.dao;

import cn.cttic.sysframe.common.basedao.BaseMapper;
import cn.cttic.sysframe.frame.domain.SmTimerEngineConfig;
import cn.cttic.sysframe.frame.domain.SmTimerEngineConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmTimerEngineConfigMapper extends BaseMapper {
    int countByExample(SmTimerEngineConfigExample example);

    int deleteByExample(SmTimerEngineConfigExample example);

    int insert(SmTimerEngineConfig record);

    int insertSelective(SmTimerEngineConfig record);

    List<SmTimerEngineConfig> selectByExample(SmTimerEngineConfigExample example);

    int updateByExampleSelective(@Param("record") SmTimerEngineConfig record, @Param("example") SmTimerEngineConfigExample example);

    int updateByExample(@Param("record") SmTimerEngineConfig record, @Param("example") SmTimerEngineConfigExample example);
}