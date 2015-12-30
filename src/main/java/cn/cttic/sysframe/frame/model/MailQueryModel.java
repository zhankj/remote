package cn.cttic.sysframe.frame.model;

import com.sun.mail.imap.protocol.Status;

import cn.cttic.sysframe.common.model.DataGridModel;


/**
 * 标题、简要说明. <br>
 * 类详细说明.
 * @author zhankj@cttic.cn
 * @date 2014年7月7日 下午3:57:41 <br>
 * Copyright: Copyright (c) 2014 CTTIC
 */
public class MailQueryModel extends  DataGridModel  {
	
	DataGridModel model;
	private String mailId;
	private String orderId;
	private String status;
	private String mailBox;
	private String optType;
	
    /**
     * 获取 model
     * @return model
     */
    public DataGridModel getModel() {
    	return model;
    }
	
    /**
     * 设置 model
     * @param model model
     */
    public void setModel(DataGridModel model) {
    	this.model = model;
    }
	
    /**
     * 获取 mailId
     * @return mailId
     */
    public String getMailId() {
    	return mailId;
    }
	
    /**
     * 设置 mailId
     * @param mailId mailId
     */
    public void setMailId(String mailId) {
    	this.mailId = mailId;
    }
	
    /**
     * 获取 orderId
     * @return orderId
     */
    public String getOrderId() {
    	return orderId;
    }
	
    /**
     * 设置 orderId
     * @param orderId orderId
     */
    public void setOrderId(String orderId) {
    	this.orderId = orderId;
    }
	
    /**
     * 获取 status
     * @return status
     */
    public String getStatus() {
    	return status;
    }
	
    /**
     * 设置 status
     * @param status status
     */
    public void setStatus(String status) {
    	this.status = status;
    }
	
    /**
     * 获取 mailBox
     * @return mailBox
     */
    public String getMailBox() {
    	return mailBox;
    }
	
    /**
     * 设置 mailBox
     * @param mailBox mailBox
     */
    public void setMailBox(String mailBox) {
    	this.mailBox = mailBox;
    }
	
    /**
     * 获取 optType
     * @return optType
     */
    public String getOptType() {
    	return optType;
    }
	
    /**
     * 设置 optType
     * @param optType optType
     */
    public void setOptType(String optType) {
    	this.optType = optType;
    }
}
