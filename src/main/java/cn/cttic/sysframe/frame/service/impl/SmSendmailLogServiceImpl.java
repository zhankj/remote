package cn.cttic.sysframe.frame.service.impl;

import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.common.util.DESEncryptUtil;
import cn.cttic.sysframe.common.util.ExceptionUtil;
import cn.cttic.sysframe.common.util.StringUtil;
import cn.cttic.sysframe.frame.common.constants.FrameConstants;
import cn.cttic.sysframe.frame.dao.SmSendmailFailureLogMapper;
import cn.cttic.sysframe.frame.dao.SmSendmailLogMapper;
import cn.cttic.sysframe.frame.dao.SmSendmailSuccessLogMapper;
import cn.cttic.sysframe.frame.domain.SmSendmailFailureLog;
import cn.cttic.sysframe.frame.domain.SmSendmailLog;
import cn.cttic.sysframe.frame.domain.SmSendmailLogExample;
import cn.cttic.sysframe.frame.domain.SmSendmailSuccessLog;
import cn.cttic.sysframe.frame.service.SmSendmailLogService;
import cn.cttic.sysframe.frame.service.SmSysDictService;
import cn.cttic.sysframe.frame.service.SystemService;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
public class SmSendmailLogServiceImpl implements SmSendmailLogService {

	private final static String desKey = "BITh/NMh70dxkpHi0P9SFquMAUNMHGehS6qxl88njQ0=";
	
	@Autowired
	private SmSendmailLogMapper smSendmailLogDao;
	
	@Autowired
	private SmSendmailSuccessLogMapper smSendmailSuccessLogDao;
	
	@Autowired
	private SmSendmailFailureLogMapper smSendmailFailureLogDao;

	@Autowired
	private SystemService systemService;
	
	@Autowired
	private SmSysDictService smSysDictService;

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
	 *       preSendDate : 预发送时间（当发送类型为[延时发送]时才有效）
	 *       mailFrom : 发件人（邮箱地址，例如：kefu@cttic.cn），字符串
	 *       mailTo : 收件人（邮箱地址串，例如：kefu@cttic.cn,kefu@cttic.cn），字符串，使用英文,进行分割
	 *       mailCc : 抄送人（邮箱地址串，例如：kefu@cttic.cn,kefu@cttic.cn），字符串
	 *       mailBcc : 密送人（邮箱地址串，例如：kefu@cttic.cn,kefu@cttic.cn），字符串
	 *       
	 * build-param : SmSendmailLog(String mailSubject, String busiType, String sendType,
			Date preSendDate, String mailFrom, String mailTo)
	 * build-param-desc : 提供构造函数构建参数，例如：SmSendmailLog smSendmailLog = new SmSendmailLog("JavaMail测试",
					"XXXXXX", "0", null, "kefu@cttic.cn", "kefu@cttic.cn,kefu@cttic.cn");
	 */
	@Override
	public void sendMail(SmSendmailLog smSendmailLog) throws Exception {
		Long logMailId = systemService.generateId(
				"SM_SENDMAIL_LOG", "LOG_MAIL_ID");
		try {
			String sendType = smSendmailLog.getSendType();
			// 立即发送，如果发送成功则写发送成功记录表
			if (FrameConstants.SmSendmailLog.SendType.IMMEDIATELY
					.equals(sendType)) {
				send(smSendmailLog);
				// 发送成功则写发送成功记录表
				SmSendmailSuccessLog smSendmailSuccessLog = new SmSendmailSuccessLog();
				BeanUtils.copyProperties(smSendmailLog, smSendmailSuccessLog);
				smSendmailSuccessLog.setLogMailId(logMailId);
				smSendmailSuccessLog
						.setState(FrameConstants.SmSendmailLog.State.SUCCESS);
				smSendmailSuccessLog.setSuccessSendDate(systemService.getSystemDate());
				smSendmailSuccessLog.setSendResultDesc("发送成功");
				smSendmailSuccessLogDao.insert(smSendmailSuccessLog);
			} 
			// 延时发送，将发送记录写入发送记录表
			else if (FrameConstants.SmSendmailLog.SendType.DELAY
					.equals(sendType)) {
				if (null == smSendmailLog.getPreSendDate()) {
					throw new SystemException(
							StatusCodeForFrame.MAIL_SEND_DELAY);
				}
				smSendmailLog.setLogMailId(logMailId);
				smSendmailLog
						.setState(FrameConstants.SmSendmailLog.State.UNDO);
				smSendmailLog.setSendResultDesc("延时发送");
				smSendmailLogDao.insert(smSendmailLog);
			} else {
				throw new SystemException(
						StatusCodeForFrame.UNKNOW_MAIL_SEND_TYPE);
			}
		} catch (Exception ex) {
			// 如果发送失败，则写入发送记录表，等待重新发送
			smSendmailLog.setLogMailId(logMailId);
			smSendmailLog
					.setState(FrameConstants.SmSendmailLog.State.UNDO);
			smSendmailLog.setFailureNum((short) 1);
			smSendmailLog.setPreSendDate(systemService.getSystemDate());
			String exStr = ExceptionUtil.getTrace(ex);
			exStr = exStr.substring(0, exStr.length() > 800 ? 800 : exStr.length());
			smSendmailLog.setSendResultDesc("[发送失败]" + exStr);
			smSendmailLogDao.insert(smSendmailLog);
			ex.printStackTrace();
//			throw new SystemException(StatusCodeForFrame.MAIL_SEND_FAILURE,
//					ExceptionUtil.getTrace(ex).substring(0, 200));
		}
	}

	@Override
	public void send(SmSendmailLog smSendmailLog) throws Exception {
		String mailSmtpHost = smSysDictService.getSmSysDictValue("MAIL_PROPERTY", "mail.smtp.host");
		String user = smSysDictService.getSmSysDictValue("MAIL_PROPERTY", "user");
		String password = smSysDictService.getSmSysDictValue("MAIL_PROPERTY", "password");
		
		password = DESEncryptUtil.decrypt(password, desKey);
		Properties props = new Properties();
		// 设置邮件发送链接协议
		props.setProperty("mail.transport.protocol", "smtp");
		// 设置邮件发送链接地址
		props.setProperty("mail.smtp.host", mailSmtpHost);
		// 设置邮件是否验证
		props.setProperty("mail.smtp.auth", "true");
		// 设置是否调试模式
		props.setProperty("mail.debug", "true");
		Session session = Session.getInstance(props);
		// 定义邮件消息体
		MimeMessage msg = new MimeMessage(session);
		// 设置邮件发送人
		msg.addFrom(InternetAddress.parse(smSendmailLog.getMailFrom()));
		// 设置邮件发送主题
		msg.setSubject(smSendmailLog.getMailSubject(), "utf-8");
		// 设置邮件发送时间
		msg.setSentDate(systemService.getSystemDate());
		// 设置邮件收件人
		msg.setRecipients(Message.RecipientType.TO, smSendmailLog.getMailTo());
		// 设置邮件抄送人
		if (!StringUtil.isBlank(smSendmailLog.getMailCc())) {
			msg.setRecipients(Message.RecipientType.CC,
					smSendmailLog.getMailCc());
		}
		// 设置邮件密送人
		if (!StringUtil.isBlank(smSendmailLog.getMailBcc())) {
			msg.setRecipients(Message.RecipientType.BCC,
					smSendmailLog.getMailBcc());
		}
		// 定义邮件主体内容
		Multipart mp = new MimeMultipart();
		// 添加邮件主体内容
		MimeBodyPart mbpContent = new MimeBodyPart();
		mbpContent.setContent(smSendmailLog.getMailContent(),
				"text/html;charset=utf-8");
		mp.addBodyPart(mbpContent);
		// 设置邮件附件
		if (!StringUtil.isBlank(smSendmailLog.getMailFiles())) {
			String[] files = smSendmailLog.getMailFiles().split(";");
			for (int i = 0; i < files.length; i++) {
				MimeBodyPart mbpFile = new MimeBodyPart();
				mbpFile.setContent(new String(), "text/html;charset=utf-8");
				// 得到数据源
				FileDataSource fds = new FileDataSource(files[i]);
				// 得到附件本身并至入BodyPart
				mbpFile.setDataHandler(new DataHandler(fds));
				// 得到文件名同样至入BodyPart
				mbpFile.setFileName(MimeUtility.encodeText(fds.getName(),
						"gbk", "B"));
				mp.addBodyPart(mbpFile);
			}
		}
		// 将邮件主体内容加入到邮件消息中
		msg.setContent(mp);
		// 建立发送链接
		Transport transport = session.getTransport();
		// 设置发送链接
		transport.connect(user, password);
		// 发送消息内容
		transport.sendMessage(msg, msg.getAllRecipients());
		// 关闭发送链接
		transport.close();
	}

	@Override
	public void delaySend() throws Exception {
		SmSendmailLogExample example = new SmSendmailLogExample();
		SmSendmailLogExample.Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(FrameConstants.SmSendmailLog.State.UNDO);
		example.setOrderByClause("pre_send_date");
		List<SmSendmailLog> smSendmailLogList = smSendmailLogDao
				.selectByExample(example);
		for (SmSendmailLog smSendmailLog : smSendmailLogList) {
			// 如果发送方式为延时发送
			if (FrameConstants.SmSendmailLog.SendType.DELAY
					.equals(smSendmailLog.getSendType())) {
				// 如果延时发送时间未到，则本条跳过
				if (smSendmailLog.getPreSendDate().after(systemService.getSystemDate())) {
					continue;
				}
			}
			try {
				// 发送邮件
				send(smSendmailLog);
				// 发送成功则将发送记录挪入成功发送记录，并标记为成功发送
				SmSendmailSuccessLog smSendmailSuccessLog = new SmSendmailSuccessLog();
				BeanUtils.copyProperties(smSendmailLog, smSendmailSuccessLog);
				smSendmailSuccessLog
						.setState(FrameConstants.SmSendmailLog.State.SUCCESS);
				smSendmailSuccessLog.setSuccessSendDate(systemService.getSystemDate());
				smSendmailSuccessLog.setSendResultDesc("发送成功");
				smSendmailSuccessLogDao.insert(smSendmailSuccessLog);
				// 删除发送记录
				smSendmailLogDao.deleteByPrimaryKey(smSendmailLog.getLogMailId());
			} catch (Exception ex) {
				// 正常发送失败后记录失败信息
				SmSendmailLog failureLog = new SmSendmailLog();
				failureLog.setLogMailId(smSendmailLog.getLogMailId());
				failureLog
						.setState(FrameConstants.SmSendmailLog.State.FAILURE);
				failureLog.setFailureNum((short) ((smSendmailLog
						.getFailureNum() == null ? 0 : smSendmailLog
						.getFailureNum()) + 1));
				String exStr = ExceptionUtil.getTrace(ex);
				exStr = exStr.substring(0, exStr.length() > 800 ? 800 : exStr.length());
				failureLog.setSendResultDesc("[发送失败]"
						+ exStr);
				smSendmailLogDao.updateByPrimaryKeySelective(failureLog);
				// 根据失败重试次数进行重发
				int failureRetryNum = smSendmailLog.getFailureRetryNum();
				for (int retryNum = 1; retryNum <= failureRetryNum; retryNum++) {
					java.util.concurrent.TimeUnit.SECONDS.sleep(5 * retryNum);
					System.out.println("[" + FrameConstants.SDF_YYYY_MM_DD_HH_MM_SS.format(systemService.getSystemDate()) + "]------after " + 5 * retryNum + " second retry send " + retryNum
							+ " time------");
					try {
						// 如果重发成功，则记录发送成功信息并跳出重发
						send(smSendmailLog);
						// 发送成功则将发送记录挪入成功发送记录，并标记为成功发送
						SmSendmailSuccessLog smSendmailSuccessLog = new SmSendmailSuccessLog();
						BeanUtils.copyProperties(smSendmailLog, smSendmailSuccessLog);
						smSendmailSuccessLog
								.setState(FrameConstants.SmSendmailLog.State.SUCCESS);
						smSendmailSuccessLog.setSuccessSendDate(systemService.getSystemDate());
						smSendmailSuccessLog.setSendResultDesc("发送成功");
						smSendmailSuccessLog.setFailureNum((short) ((smSendmailLog
								.getFailureNum() == null ? 0 : smSendmailLog
								.getFailureNum()) + retryNum));
						smSendmailSuccessLogDao.insert(smSendmailSuccessLog);
						// 删除发送记录
						smSendmailLogDao.deleteByPrimaryKey(smSendmailLog.getLogMailId());
						break;
					} catch (Exception exc) {
						// 如果重发失败，则记录发送失败信息并继续重发
						// 如果重试次数未到最大
						if (retryNum != failureRetryNum) {
							failureLog = new SmSendmailLog();
							failureLog.setLogMailId(smSendmailLog.getLogMailId());
							failureLog
									.setState(FrameConstants.SmSendmailLog.State.FAILURE);
							failureLog.setFailureNum((short) ((smSendmailLog
									.getFailureNum() == null ? 0 : smSendmailLog
									.getFailureNum()) + retryNum));
							exStr = ExceptionUtil.getTrace(ex);
							exStr = exStr.substring(0, exStr.length() > 800 ? 800 : exStr.length());
							failureLog.setSendResultDesc("[发送失败]"
									+ exStr);
							smSendmailLogDao
									.updateByPrimaryKeySelective(failureLog);
							continue;
						} 
						// 如果失败次数已达最大，则将发送记录挪入失败记录表中
						else if (retryNum == failureRetryNum) {
							SmSendmailFailureLog smSendmailFailureLog = new SmSendmailFailureLog();
							BeanUtils.copyProperties(smSendmailLog, smSendmailFailureLog);
							smSendmailFailureLog
							.setState(FrameConstants.SmSendmailLog.State.FAILURE);
							smSendmailFailureLog.setFailureNum((short) ((smSendmailLog
							.getFailureNum() == null ? 0 : smSendmailLog
							.getFailureNum()) + retryNum));
							exStr = ExceptionUtil.getTrace(ex);
							exStr = exStr.substring(0, exStr.length() > 800 ? 800 : exStr.length());
							failureLog.setSendResultDesc("[发送失败]"
									+ exStr);
							smSendmailFailureLogDao.insert(smSendmailFailureLog);
							// 删除发送记录
							smSendmailLogDao.deleteByPrimaryKey(smSendmailLog.getLogMailId());
							continue;
						}
					}
				}
			}
		}
	}
	
	@Override
	public PageList<SmSendmailLog> queryPage(SmSendmailLogExample example, PageBounds pageBounds) throws Exception {
		return smSendmailLogDao.queryPage(example, pageBounds);
	}
	
	@Override
	public SmSendmailLog selectSmSendmailLogByPrimaryKey(long mailId) throws Exception {
		return smSendmailLogDao.selectByPrimaryKey(mailId);
	}
	
}
