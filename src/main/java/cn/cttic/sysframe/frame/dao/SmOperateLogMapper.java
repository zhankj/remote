package cn.cttic.sysframe.frame.dao;

import cn.cttic.sysframe.common.basedao.BaseMapper;
import cn.cttic.sysframe.frame.domain.SmOperateLog;
import cn.cttic.sysframe.frame.domain.SmOperateLogExample;
import cn.cttic.sysframe.frame.domain.SmOperateLogWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface SmOperateLogMapper extends BaseMapper {
    int countByExample(SmOperateLogExample example);

    int deleteByExample(SmOperateLogExample example);

    int deleteByPrimaryKey(Long logId);

    int insert(SmOperateLogWithBLOBs record);

    int insertSelective(SmOperateLogWithBLOBs record);

    List<SmOperateLogWithBLOBs> selectByExampleWithBLOBs(SmOperateLogExample example);

    List<SmOperateLog> selectByExample(SmOperateLogExample example);

    SmOperateLogWithBLOBs selectByPrimaryKey(Long logId);

    int updateByExampleSelective(@Param("record") SmOperateLogWithBLOBs record, @Param("example") SmOperateLogExample example);

    int updateByExampleWithBLOBs(@Param("record") SmOperateLogWithBLOBs record, @Param("example") SmOperateLogExample example);

    int updateByExample(@Param("record") SmOperateLog record, @Param("example") SmOperateLogExample example);

    int updateByPrimaryKeySelective(SmOperateLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SmOperateLogWithBLOBs record);

    int updateByPrimaryKey(SmOperateLog record);
    
    public PageList<SmOperateLogWithBLOBs> query(SmOperateLogExample example, PageBounds pageBounds);
}