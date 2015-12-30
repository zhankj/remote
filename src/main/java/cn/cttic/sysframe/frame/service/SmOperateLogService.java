package cn.cttic.sysframe.frame.service;

import java.util.List;

import cn.cttic.sysframe.frame.domain.SmOperateLogExample;
import cn.cttic.sysframe.frame.domain.SmOperateLogWithBLOBs;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;



public interface SmOperateLogService {
	
	
	List<SmOperateLogWithBLOBs> selectByExampleWithBLOBs(SmOperateLogExample example);
	
	int insertSelective(SmOperateLogWithBLOBs record);
	
	public PageList<SmOperateLogWithBLOBs> query(SmOperateLogExample example, PageBounds pageBounds);
	
	SmOperateLogWithBLOBs selectByPrimaryKey(Long logId);
	
}
