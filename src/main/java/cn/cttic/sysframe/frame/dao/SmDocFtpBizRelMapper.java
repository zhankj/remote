package cn.cttic.sysframe.frame.dao;

import cn.cttic.sysframe.common.basedao.BaseMapper;
import cn.cttic.sysframe.frame.domain.SmDocFtpBizRel;
import cn.cttic.sysframe.frame.domain.SmDocFtpBizRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmDocFtpBizRelMapper extends BaseMapper {
    int countByExample(SmDocFtpBizRelExample example);

    int deleteByExample(SmDocFtpBizRelExample example);

    int deleteByPrimaryKey(Long relId);

    int insert(SmDocFtpBizRel record);

    int insertSelective(SmDocFtpBizRel record);

    List<SmDocFtpBizRel> selectByExample(SmDocFtpBizRelExample example);

    SmDocFtpBizRel selectByPrimaryKey(Long relId);

    int updateByExampleSelective(@Param("record") SmDocFtpBizRel record, @Param("example") SmDocFtpBizRelExample example);

    int updateByExample(@Param("record") SmDocFtpBizRel record, @Param("example") SmDocFtpBizRelExample example);

    int updateByPrimaryKeySelective(SmDocFtpBizRel record);

    int updateByPrimaryKey(SmDocFtpBizRel record);
}