package cn.cttic.sysframe.frame.domain;

import java.util.Date;

import cn.cttic.sysframe.frame.common.constants.FrameConstants;

public class SmSendmailLog {
    private Long logMailId;

    private String mailSubject;

    private String busiType;

    private String mailContent;

    private String mailFiles;

    private Short failureRetryNum;

    private String sendType;

    private Date preSendDate;

    private Date successSendDate;

    private Short failureNum;

    private String state;

    private String sendResultDesc;

    private String mailFrom;

    private String mailTo;

    private String mailCc;

    private String mailBcc;

    private Long busiId;

    public SmSendmailLog() {
	}
    
	public SmSendmailLog(String mailSubject, String busiType, String sendType,
			Date preSendDate, String mailFrom, String mailTo) {
		this.mailSubject = mailSubject;
		this.busiType = busiType;
		this.failureRetryNum = FrameConstants.SmSendmailLog.FAILURE_RETRY_NUM;
		this.sendType = sendType;
		this.preSendDate = preSendDate;
		this.mailFrom = mailFrom;
		this.mailTo = mailTo;
	}

    public Long getLogMailId() {
        return logMailId;
    }

    public void setLogMailId(Long logMailId) {
        this.logMailId = logMailId;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject == null ? null : mailSubject.trim();
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent == null ? null : mailContent.trim();
    }

    public String getMailFiles() {
        return mailFiles;
    }

    public void setMailFiles(String mailFiles) {
        this.mailFiles = mailFiles == null ? null : mailFiles.trim();
    }

    public Short getFailureRetryNum() {
        return failureRetryNum;
    }

    public void setFailureRetryNum(Short failureRetryNum) {
        this.failureRetryNum = failureRetryNum;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType == null ? null : sendType.trim();
    }

    public Date getPreSendDate() {
        return preSendDate;
    }

    public void setPreSendDate(Date preSendDate) {
        this.preSendDate = preSendDate;
    }

    public Date getSuccessSendDate() {
        return successSendDate;
    }

    public void setSuccessSendDate(Date successSendDate) {
        this.successSendDate = successSendDate;
    }

    public Short getFailureNum() {
        return failureNum;
    }

    public void setFailureNum(Short failureNum) {
        this.failureNum = failureNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getSendResultDesc() {
        return sendResultDesc;
    }

    public void setSendResultDesc(String sendResultDesc) {
        this.sendResultDesc = sendResultDesc == null ? null : sendResultDesc.trim();
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom == null ? null : mailFrom.trim();
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo == null ? null : mailTo.trim();
    }

    public String getMailCc() {
        return mailCc;
    }

    public void setMailCc(String mailCc) {
        this.mailCc = mailCc == null ? null : mailCc.trim();
    }

    public String getMailBcc() {
        return mailBcc;
    }

    public void setMailBcc(String mailBcc) {
        this.mailBcc = mailBcc == null ? null : mailBcc.trim();
    }

    public Long getBusiId() {
        return busiId;
    }

    public void setBusiId(Long busiId) {
        this.busiId = busiId;
    }
}