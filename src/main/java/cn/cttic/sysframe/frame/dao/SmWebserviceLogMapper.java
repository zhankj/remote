package cn.cttic.sysframe.frame.dao;

import cn.cttic.sysframe.frame.domain.SmWebserviceLog;
import cn.cttic.sysframe.frame.domain.SmWebserviceLogExample;
import cn.cttic.sysframe.frame.domain.SmWebserviceLogWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmWebserviceLogMapper {
    int countByExample(SmWebserviceLogExample example);

    int deleteByExample(SmWebserviceLogExample example);

    int insert(SmWebserviceLogWithBLOBs record);

    int insertSelective(SmWebserviceLogWithBLOBs record);

    List<SmWebserviceLogWithBLOBs> selectByExampleWithBLOBs(SmWebserviceLogExample example);

    List<SmWebserviceLog> selectByExample(SmWebserviceLogExample example);

    int updateByExampleSelective(@Param("record") SmWebserviceLogWithBLOBs record, @Param("example") SmWebserviceLogExample example);

    int updateByExampleWithBLOBs(@Param("record") SmWebserviceLogWithBLOBs record, @Param("example") SmWebserviceLogExample example);

    int updateByExample(@Param("record") SmWebserviceLog record, @Param("example") SmWebserviceLogExample example);
}