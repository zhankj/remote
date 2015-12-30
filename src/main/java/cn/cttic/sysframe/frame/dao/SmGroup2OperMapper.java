package cn.cttic.sysframe.frame.dao;

import cn.cttic.sysframe.common.basedao.BaseMapper;
import cn.cttic.sysframe.frame.domain.SmGroup2Oper;
import cn.cttic.sysframe.frame.domain.SmGroup2OperExample;
import cn.cttic.sysframe.frame.domain.SmGroup2OperKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmGroup2OperMapper extends BaseMapper {
    int countByExample(SmGroup2OperExample example);

    int deleteByExample(SmGroup2OperExample example);

    int deleteByPrimaryKey(SmGroup2OperKey key);

    int insert(SmGroup2Oper record);

    int insertSelective(SmGroup2Oper record);

    List<SmGroup2Oper> selectByExample(SmGroup2OperExample example);

    SmGroup2Oper selectByPrimaryKey(SmGroup2OperKey key);

    int updateByExampleSelective(@Param("record") SmGroup2Oper record, @Param("example") SmGroup2OperExample example);

    int updateByExample(@Param("record") SmGroup2Oper record, @Param("example") SmGroup2OperExample example);

    int updateByPrimaryKeySelective(SmGroup2Oper record);

    int updateByPrimaryKey(SmGroup2Oper record);
}