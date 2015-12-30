package cn.cttic.sysframe.frame.facade;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.cttic.sysframe.frame.domain.SmSendmailLog;
import cn.cttic.sysframe.frame.domain.SmSendmailLogExample;
import cn.cttic.sysframe.frame.model.MailQueryModel;

public interface MailFacadeService {

	public void sendMail(SmSendmailLog smSendmailLog) throws Exception;
	

	/**
     *
     * @param mailId
     * @return
     * @throws Exception 
     * @author zhankaijin_cttic
     * @date 2014年7月23日 下午3:55:30
     */
    SmSendmailLog queryMailDetail(Long mailId) throws Exception;

	/**
     *
     * @param mailQueryModel
     * @return
     * @throws Exception 
     * @author zhankaijin_cttic
     * @date 2014年7月24日 下午4:50:13
     */
    PageList<SmSendmailLog> queryPage(MailQueryModel mailQueryModel) throws Exception; 
}
