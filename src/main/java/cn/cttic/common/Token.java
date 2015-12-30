package cn.cttic.common;

import java.util.Date;

public class Token {
private String loginName;
private String tokenId;
private Date expireDate;
private int callCount;
public String getLoginName() {
	return loginName;
}
public void setLoginName(String loginName) {
	this.loginName = loginName;
}
public String getTokenId() {
	return tokenId;
}
public void setTokenId(String tokenId) {
	this.tokenId = tokenId;
}
public Date getExpireDate() {
	return expireDate;
}
public void setExpireDate(Date expireDate) {
	this.expireDate = expireDate;
}
public int getCallCount() {
	return callCount;
}
public void setCallCount(int callCount) {
	this.callCount = callCount;
}
public int getMaxCount() {
	return maxCount;
}
public void setMaxCount(int maxCount) {
	this.maxCount = maxCount;
}
private int maxCount;
}
