package cn.cttic.sysframe.frame.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.cttic.sysframe.common.util.StringUtil;
import cn.cttic.sysframe.frame.domain.SmSendmailLog;
import cn.cttic.sysframe.frame.domain.SmSendmailLogExample;
import cn.cttic.sysframe.frame.domain.SmSendmailLogExample.Criteria;
import cn.cttic.sysframe.frame.facade.MailFacadeService;
import cn.cttic.sysframe.frame.model.MailQueryModel;
import cn.cttic.sysframe.frame.service.SmSendmailLogService;
import cn.cttic.sysframe.remote.annotation.ReflectionInvokable;

@Service
@ReflectionInvokable(name="mail")
public class MailFacadeServiceImpl implements MailFacadeService {

	@Autowired
	SmSendmailLogService smSendmailLogService;

	/*
	 * desc : 邮件发送
	 * param : smSendmailLog
	 * param-desc : 
	 *       mailSubject : 邮件主题，字符串
	 *       busiType : 业务类型
	 *       mailContent : 邮件内容，字符串
	 *       mailFiles : 邮件附件（多附件使用英文;进行分割，为附件文件的绝对本地路径）
	 *       failureRetryNum : 失败重试次数，默认3次
	 *       sendType : 发送类型
	 *                  FrameConstants.SmSendmailLog.SendType.IMMEDIATELY-立即发送
	 *                  FrameConstants.SmSendmailLog.SendType.DELAY-延时发送
	 *       preSendTime : 预发送时间（当发送类型为[延时发送]时才有效）
	 *       mailFrom : 发件人（邮箱地址，例如：kefu@cttic.cn），字符串
	 *       mailTo : 收件人（邮箱地址串，例如：kefu@cttic.cn,kefu@cttic.cn），字符串，使用英文,进行分割
	 *       mailCc : 抄送人（邮箱地址串，例如：kefu@cttic.cn,kefu@cttic.cn），字符串
	 *       mailBcc : 密送人（邮箱地址串，例如：kefu@cttic.cn,kefu@cttic.cn），字符串
	 *       
	 * build-param : SmSendmailLog(String mailSubject, String busiType, String sendType,
			Date preSendTime, String mailFrom, String mailTo)
	 * build-param-desc : 提供构造函数构建参数，例如：SmSendmailLog smSendmailLog = new SmSendmailLog("JavaMail测试",
					"XXXXXX", "0", null, "kefu@cttic.cn", "kefu@cttic.cn,kefu@cttic.cn");
	 */
	@Override
	public void sendMail(SmSendmailLog smSendmailLog) throws Exception {
		smSendmailLogService.sendMail(smSendmailLog);
	}
	
	@Override
	@ReflectionInvokable(name="queryMailPage")
	public PageList<SmSendmailLog> queryPage(MailQueryModel mailQueryModel) throws Exception {
		int pageNumber = mailQueryModel.getModel().getPage();
		int pageSize = mailQueryModel.getModel().getRows();
		PageBounds pageBounds = new PageBounds();
		pageBounds.setLimit(pageSize);
		pageBounds.setPage(pageNumber);

		SmSendmailLogExample sc = new SmSendmailLogExample();
		Criteria criteria = sc.or();
		
		if (!StringUtil.isBlank(mailQueryModel.getMailId())) {
			criteria.andLogMailIdEqualTo( Long.parseLong(mailQueryModel.getMailId()));
		}
		
		if (!StringUtil.isBlank(mailQueryModel.getMailBox())) {
			criteria.andMailToEqualTo(mailQueryModel.getMailBox());
		}
		
		if (!StringUtil.isBlank(mailQueryModel.getOptType())) {
			criteria.andBusiTypeEqualTo(mailQueryModel.getOptType());
		}
		
		if (!StringUtil.isBlank(mailQueryModel.getStatus())) {
			criteria.andStateEqualTo(mailQueryModel.getStatus());
		}
		return smSendmailLogService.queryPage(sc, pageBounds);
	}
	
	
	@Override
	@ReflectionInvokable(name="queryMailDetail")
	public SmSendmailLog queryMailDetail(Long mailId) throws Exception {
		return smSendmailLogService.selectSmSendmailLogByPrimaryKey(mailId);
	}

}
