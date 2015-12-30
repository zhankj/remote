package cn.cttic.sysframe.frame.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import cn.cttic.sysframe.frame.dao.SmWebserviceLogMapper;
import cn.cttic.sysframe.frame.domain.SmWebserviceLogWithBLOBs;
import cn.cttic.sysframe.frame.service.SmWebserviceLogService;
import cn.cttic.sysframe.frame.support.RestWebServiceSupport;
import cn.cttic.sysframe.frame.support.RestWebServiceSupport.NodeName;

@Service
public class SmWebserviceLogServiceImpl implements SmWebserviceLogService {
	private static Logger LOG = LoggerFactory
			.getLogger(SmWebserviceLogServiceImpl.class);

	@Autowired
	private SmWebserviceLogMapper smWebserviceLogMapper;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Override
	public void saveLog(Date startDate, Date endDate, String requestMessage,
			String responseMessage) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			SmWebserviceLogWithBLOBs log = new SmWebserviceLogWithBLOBs();
			log.setStartDate(startDate);
			log.setEndDate(endDate);
			log.setReqMessage(requestMessage);

			log.setChannel(parseNode(requestMessage, NodeName.channel));
			log.setSid(parseNode(requestMessage, NodeName.sid));
			log.setLocale(parseNode(requestMessage, NodeName.locale));
			log.setServiceCode(parseNode(requestMessage, NodeName.serviceCode));
			log.setStatusCode(parseNode(responseMessage, NodeName.statusCode));
			log.setStatusDesc(parseNode(responseMessage, NodeName.statusDesc));
			log.setExceptionDesc(parseNode(responseMessage, NodeName.exceptionDesc));

			smWebserviceLogMapper.insertSelective(log);
			transactionManager.commit(status);
		} catch (Exception e) {
			LOG.error("记录数据库日志失败", e);
			transactionManager.rollback(status);
		}
	}

	public static String parseNode(String text, NodeName nodename) {
		try {
			return RestWebServiceSupport.parseNode(text, nodename);
		} catch (Exception e) {
			return null;
		}
	}

}
