package cn.cttic.sysframe.frame.dao;

import cn.cttic.sysframe.common.basedao.BaseMapper;
import cn.cttic.sysframe.frame.domain.SmIpMapped;
import cn.cttic.sysframe.frame.domain.SmIpMappedExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmIpMappedMapper extends BaseMapper {
    int countByExample(SmIpMappedExample example);

    int deleteByExample(SmIpMappedExample example);

    int insert(SmIpMapped record);

    int insertSelective(SmIpMapped record);

    List<SmIpMapped> selectByExample(SmIpMappedExample example);

    int updateByExampleSelective(@Param("record") SmIpMapped record, @Param("example") SmIpMappedExample example);

    int updateByExample(@Param("record") SmIpMapped record, @Param("example") SmIpMappedExample example);
}