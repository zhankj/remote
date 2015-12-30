package cn.cttic.sysframe.frame.dao;

import cn.cttic.sysframe.frame.domain.SmFtp;
import cn.cttic.sysframe.frame.domain.SmFtpExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmFtpMapper {
    int countByExample(SmFtpExample example);

    int deleteByExample(SmFtpExample example);

    int deleteByPrimaryKey(String ftpCode);

    int insert(SmFtp record);

    int insertSelective(SmFtp record);

    List<SmFtp> selectByExample(SmFtpExample example);

    SmFtp selectByPrimaryKey(String ftpCode);

    int updateByExampleSelective(@Param("record") SmFtp record, @Param("example") SmFtpExample example);

    int updateByExample(@Param("record") SmFtp record, @Param("example") SmFtpExample example);

    int updateByPrimaryKeySelective(SmFtp record);

    int updateByPrimaryKey(SmFtp record);
}