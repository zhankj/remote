package cn.cttic.sysframe.frame.service;

import java.util.Date;


public interface SmWebserviceLogService {
	void saveLog(Date startDate, Date endDate, String requestMessage, String responseMessage);
}
