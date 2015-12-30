package cn.cttic.sysframe.frame.dao;

import cn.cttic.sysframe.common.basedao.BaseMapper;
import cn.cttic.sysframe.frame.domain.SmFuncSet;
import cn.cttic.sysframe.frame.domain.SmTimerTaskDef;
import cn.cttic.sysframe.frame.domain.SmTimerTaskDefExample;
import cn.cttic.sysframe.frame.model.SmTimerTaskModel;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface SmTimerTaskDefMapper extends BaseMapper {
    int countByExample(SmTimerTaskDefExample example);

    int deleteByExample(SmTimerTaskDefExample example);

    int deleteByPrimaryKey(Long taskId);

    int insert(SmTimerTaskModel timerTaskModel);

    int insertSelective(SmTimerTaskDef record);

    List<SmTimerTaskDef> selectByExample(SmTimerTaskDefExample example);

    SmTimerTaskDef selectByPrimaryKey(Long taskId);

    int updateByExampleSelective(@Param("record") SmTimerTaskDef record, @Param("example") SmTimerTaskDefExample example);

    int updateByExample(@Param("record") SmTimerTaskDef record, @Param("example") SmTimerTaskDefExample example);

//    int updateByPrimaryKeySelective(SmTimerTaskDef record);

    int updateByPrimaryKey(SmTimerTaskDef record);
    
	PageList<SmTimerTaskDef> queryPage(SmTimerTaskDefExample taskCriteria, PageBounds pageBounds);
	
	public int updateByPrimaryKeySelective(SmTimerTaskModel timerTaskModel);
	
	public int updateTimerTaskDefRunDate(SmTimerTaskModel timerTaskModel);
	
	public List<SmTimerTaskDef> getSmTimerTaskDefListByTimer(Map qryMap);
}