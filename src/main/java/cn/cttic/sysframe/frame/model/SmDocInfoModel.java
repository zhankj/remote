package cn.cttic.sysframe.frame.model;

import cn.cttic.sysframe.frame.domain.SmDocInfo;

public class SmDocInfoModel extends SmDocInfo {

	public String uploadTimeStr;

	public String operate;

	public String getUploadTimeStr() {
		return uploadTimeStr;
	}

	public void setUploadTimeStr(String uploadTimeStr) {
		this.uploadTimeStr = uploadTimeStr;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}
}
