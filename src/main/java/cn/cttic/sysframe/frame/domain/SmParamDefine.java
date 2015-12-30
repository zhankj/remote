package cn.cttic.sysframe.frame.domain;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;

public class SmParamDefine {
    private String typeCode = "";

    private String typeDesc = "";

    private String parentTypeCode = "";

    private String systemId = "";

    private String paramType = "";

    private String operId = "";

    private Date modifyDate;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc == null ? null : typeDesc.trim();
    }

    public String getParentTypeCode() {
        return parentTypeCode;
    }

    public void setParentTypeCode(String parentTypeCode) {
        this.parentTypeCode = parentTypeCode == null ? null : parentTypeCode.trim();
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType == null ? null : paramType.trim();
    }

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId == null ? null : operId.trim();
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

	@Override
    public boolean equals(Object other) {
		if (!(other instanceof SmParamDefine))
			return false;
		SmParamDefine castOther = (SmParamDefine) other;
	    return new EqualsBuilder()
	    	.append(this.getTypeCode(), castOther.getTypeCode())
	    	.append(this.getTypeDesc(), castOther.getTypeDesc())
	    	.append(this.getParentTypeCode(), castOther.getParentTypeCode())
	    	.append(this.getParamType(), castOther.getParamType())
	    	.append(this.getSystemId(), castOther.getSystemId())
	    	.isEquals();
    }
    
    
}