package cn.cttic.sysframe.frame.model;

import java.util.Date;


public class SmParamModel {
	
	private String typeCode;
	private String typeDesc;
    private String typeParamCode;
    private String typeParamDesc;
    private String parentTypeCode;
    private String parentParamCode;
    private String languageType;
    private String state;
    private String systemId;
    private Short paramOrder;
    private String operId;
    private String paramType;
    private Date modifyDate;
    private String sourceCode;
	
    public String getTypeCode() {
    	return typeCode;
    }
	
    public void setTypeCode(String typeCode) {
    	this.typeCode = typeCode;
    }
	
    public String getTypeDesc() {
    	return typeDesc;
    }
	
    public void setTypeDesc(String typeDesc) {
    	this.typeDesc = typeDesc;
    }
	
    public String getTypeParamCode() {
    	return typeParamCode;
    }
	
    public void setTypeParamCode(String typeParamCode) {
    	this.typeParamCode = typeParamCode;
    }
	
    public String getTypeParamDesc() {
    	return typeParamDesc;
    }
	
    public void setTypeParamDesc(String typeParamDesc) {
    	this.typeParamDesc = typeParamDesc;
    }
	
    public String getParentTypeCode() {
    	return parentTypeCode;
    }
	
    public void setParentTypeCode(String parentTypeCode) {
    	this.parentTypeCode = parentTypeCode;
    }
	
    public String getParentParamCode() {
    	return parentParamCode;
    }
	
    public void setParentParamCode(String parentParamCode) {
    	this.parentParamCode = parentParamCode;
    }
	
    public String getLanguageType() {
    	return languageType;
    }
	
    public void setLanguageType(String languageType) {
    	this.languageType = languageType;
    }
	
    public String getState() {
    	return state;
    }
	
    public void setState(String state) {
    	this.state = state;
    }
	
    public String getSystemId() {
    	return systemId;
    }
	
    public void setSystemId(String systemId) {
    	this.systemId = systemId;
    }
	
    public Short getParamOrder() {
    	return paramOrder;
    }
	
    public void setParamOrder(Short paramOrder) {
    	this.paramOrder = paramOrder;
    }
	
    public String getOperId() {
    	return operId;
    }
	
    public void setOperId(String operId) {
    	this.operId = operId;
    }
	
    public String getParamType() {
    	return paramType;
    }
	
    public void setParamType(String paramType) {
    	this.paramType = paramType;
    }
	
    public Date getModifyDate() {
    	return modifyDate;
    }
	
    public void setModifyDate(Date modifyDate) {
    	this.modifyDate = modifyDate;
    }
	
    public String getSourceCode() {
    	return sourceCode;
    }
	
    public void setSourceCode(String sourceCode) {
    	this.sourceCode = sourceCode;
    }

	@Override
    public String toString() {
	    return "SmParamModel [typeCode=" + typeCode + ", typeDesc=" + typeDesc + ", typeParamCode=" + typeParamCode + ", typeParamDesc="
	            + typeParamDesc + ", parentTypeCode=" + parentTypeCode + ", parentParamCode=" + parentParamCode + ", languageType=" + languageType
	            + "]";
    }

}
