package cn.cttic.sysframe.frame.dao;

import cn.cttic.sysframe.frame.domain.SmLogon;
import cn.cttic.sysframe.frame.domain.SmLogonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmLogonMapper {
    int countByExample(SmLogonExample example);

    int deleteByExample(SmLogonExample example);

    int deleteByPrimaryKey(String operId);

    int insert(SmLogon record);

    int insertSelective(SmLogon record);

    List<SmLogon> selectByExample(SmLogonExample example);

    SmLogon selectByPrimaryKey(String operId);

    int updateByExampleSelective(@Param("record") SmLogon record, @Param("example") SmLogonExample example);

    int updateByExample(@Param("record") SmLogon record, @Param("example") SmLogonExample example);

    int updateByPrimaryKeySelective(SmLogon record);

    int updateByPrimaryKey(SmLogon record);
}