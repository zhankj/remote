package cn.cttic.sysframe.frame.dao;

import cn.cttic.sysframe.common.basedao.BaseMapper;
import cn.cttic.sysframe.frame.domain.SmRegisteredService;
import cn.cttic.sysframe.frame.domain.SmRegisteredServiceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmRegisteredServiceMapper extends BaseMapper {
    int countByExample(SmRegisteredServiceExample example);

    int deleteByExample(SmRegisteredServiceExample example);

    int deleteByPrimaryKey(Long sId);

    int insert(SmRegisteredService record);

    int insertSelective(SmRegisteredService record);

    List<SmRegisteredService> selectByExample(SmRegisteredServiceExample example);

    SmRegisteredService selectByPrimaryKey(Long sId);

    int updateByExampleSelective(@Param("record") SmRegisteredService record, @Param("example") SmRegisteredServiceExample example);

    int updateByExample(@Param("record") SmRegisteredService record, @Param("example") SmRegisteredServiceExample example);

    int updateByPrimaryKeySelective(SmRegisteredService record);

    int updateByPrimaryKey(SmRegisteredService record);
}