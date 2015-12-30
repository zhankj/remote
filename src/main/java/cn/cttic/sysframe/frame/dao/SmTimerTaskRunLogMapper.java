package cn.cttic.sysframe.frame.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.cttic.sysframe.common.basedao.BaseMapper;
import cn.cttic.sysframe.frame.domain.SmTimerTaskRunLog;
import cn.cttic.sysframe.frame.domain.SmTimerTaskRunLogExample;

public interface SmTimerTaskRunLogMapper extends BaseMapper {
    int countByExample(SmTimerTaskRunLogExample example);

    int deleteByExample(SmTimerTaskRunLogExample example);

    int insert(SmTimerTaskRunLog record);

    int insertSelective(SmTimerTaskRunLog record);
    
    Map<String, Object> selectByPrimaryKey2(Long logId);
    
    PageList<Map<String, Object>>  queryTaskLogList(Map<String, Object> qryMap,PageBounds pageBounds);
    
    List<SmTimerTaskRunLog> selectByExample(SmTimerTaskRunLogExample example);

    int updateByExampleSelective(@Param("record") SmTimerTaskRunLog record, @Param("example") SmTimerTaskRunLogExample example);

    int updateByExample(@Param("record") SmTimerTaskRunLog record, @Param("example") SmTimerTaskRunLogExample example);
}