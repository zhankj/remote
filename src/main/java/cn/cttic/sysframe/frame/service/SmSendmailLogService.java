package cn.cttic.sysframe.frame.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.cttic.sysframe.frame.domain.SmSendmailLog;
import cn.cttic.sysframe.frame.domain.SmSendmailLogExample;

public interface SmSendmailLogService {

	public void sendMail(SmSendmailLog smSendmailLog) throws Exception;
	
	public void send(SmSendmailLog smSendmailLog) throws Exception;
	
	public void delaySend() throws Exception;
	
	public PageList<SmSendmailLog> queryPage(SmSendmailLogExample example, PageBounds pageBounds) throws Exception;

	/**
     *
     * @param mailId
     * @return
     * @throws Exception 
     * @author zhankaijin_cttic
     * @date 2014年7月23日 下午3:40:05
     */
    SmSendmailLog selectSmSendmailLogByPrimaryKey(long mailId) throws Exception; 
}
