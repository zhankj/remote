package cn.cttic.sysframe.frame.dao;

import cn.cttic.sysframe.common.basedao.BaseMapper;
import cn.cttic.sysframe.frame.domain.SmSendmailLog;
import cn.cttic.sysframe.frame.domain.SmSendmailLogExample;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface SmSendmailLogMapper extends BaseMapper<Long, SmSendmailLog, SmSendmailLogExample> {

	PageList<SmSendmailLog> queryPage(SmSendmailLogExample example, PageBounds pageBounds);
	SmSendmailLog selectByPrimaryKey(Long mailId);
}