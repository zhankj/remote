package cn.cttic.sysframe.frame.dao;

import cn.cttic.sysframe.frame.domain.SmDocDoneLog;
import cn.cttic.sysframe.frame.domain.SmDocDoneLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmDocDoneLogMapper {
    int countByExample(SmDocDoneLogExample example);

    int deleteByExample(SmDocDoneLogExample example);

    int deleteByPrimaryKey(Long logId);

    int insert(SmDocDoneLog record);

    int insertSelective(SmDocDoneLog record);

    List<SmDocDoneLog> selectByExample(SmDocDoneLogExample example);

    SmDocDoneLog selectByPrimaryKey(Long logId);

    int updateByExampleSelective(@Param("record") SmDocDoneLog record, @Param("example") SmDocDoneLogExample example);

    int updateByExample(@Param("record") SmDocDoneLog record, @Param("example") SmDocDoneLogExample example);

    int updateByPrimaryKeySelective(SmDocDoneLog record);

    int updateByPrimaryKey(SmDocDoneLog record);
}