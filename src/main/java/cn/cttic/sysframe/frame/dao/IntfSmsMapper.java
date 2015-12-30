package cn.cttic.sysframe.frame.dao;

import cn.cttic.sysframe.frame.domain.IntfSms;
import cn.cttic.sysframe.frame.domain.IntfSmsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IntfSmsMapper {
    int countByExample(IntfSmsExample example);

    int deleteByExample(IntfSmsExample example);

    int insert(IntfSms record);

    int insertSelective(IntfSms record);

    List<IntfSms> selectByExample(IntfSmsExample example);

    int updateByExampleSelective(@Param("record") IntfSms record, @Param("example") IntfSmsExample example);

    int updateByExample(@Param("record") IntfSms record, @Param("example") IntfSmsExample example);
}