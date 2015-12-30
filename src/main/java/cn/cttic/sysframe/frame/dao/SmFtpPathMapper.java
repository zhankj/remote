package cn.cttic.sysframe.frame.dao;

import cn.cttic.sysframe.common.basedao.BaseMapper;
import cn.cttic.sysframe.frame.domain.SmFtpPath;
import cn.cttic.sysframe.frame.domain.SmFtpPathExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmFtpPathMapper extends BaseMapper {
    int countByExample(SmFtpPathExample example);

    int deleteByExample(SmFtpPathExample example);

    int deleteByPrimaryKey(String ftpPathCode);

    int insert(SmFtpPath record);

    int insertSelective(SmFtpPath record);

    List<SmFtpPath> selectByExample(SmFtpPathExample example);

    SmFtpPath selectByPrimaryKey(String ftpPathCode);

    int updateByExampleSelective(@Param("record") SmFtpPath record, @Param("example") SmFtpPathExample example);

    int updateByExample(@Param("record") SmFtpPath record, @Param("example") SmFtpPathExample example);

    int updateByPrimaryKeySelective(SmFtpPath record);

    int updateByPrimaryKey(SmFtpPath record);
}