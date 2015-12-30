package cn.cttic.sysframe.common.support;

import org.apache.commons.dbcp.BasicDataSource;

import cn.cttic.sysframe.common.util.DESEncryptUtil;

public class EncryptedDataSource extends BasicDataSource {

	@Override
	public void setPassword(String password) {
		final String key = "BITh/NMh70dxkpHi0P9SFquMAUNMHGehS6qxl88njQ0=";
    	this.password = DESEncryptUtil.decrypt(password, key);
	}

}
