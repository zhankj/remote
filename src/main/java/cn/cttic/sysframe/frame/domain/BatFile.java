package cn.cttic.sysframe.frame.domain;


public class BatFile {
	public long fileId; //处理文件的id
	public String ftpPathCode; //处理文件对应ftppathcode
	public String fileName; //文件名称
	public String opId;//导入操作员id
	public String orgId;//导入操作员组织
	public String opCode;//导入操作员工号
	
	public String getOpId() {
		return opId;
	}
	public void setOpId(String opId) {
		this.opId = opId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOpCode() {
		return opCode;
	}
	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getFileId() {
		return fileId;
	}
	public void setFileId(long fileId) {
		this.fileId = fileId;
	}
	public String getFtpPathCode() {
		return ftpPathCode;
	}
	public void setFtpPathCode(String ftpPathCode) {
		this.ftpPathCode = ftpPathCode;
	}
}
