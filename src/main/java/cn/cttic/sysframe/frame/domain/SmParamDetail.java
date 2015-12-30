package cn.cttic.sysframe.frame.domain;

import java.util.Date;

public class SmParamDetail extends SmParamDetailKey {
    private String typeParamDesc;

    private String parentTypeCode;

    private String parentParamCode;

    private String state;

    private String systemId;

    private Short paramOrder;

    private String operId;

    private Date modifyDate;

    private String sourceCode;

    public String getTypeParamDesc() {
        return typeParamDesc;
    }

    public void setTypeParamDesc(String typeParamDesc) {
        this.typeParamDesc = typeParamDesc == null ? null : typeParamDesc.trim();
    }

    public String getParentTypeCode() {
        return parentTypeCode;
    }

    public void setParentTypeCode(String parentTypeCode) {
        this.parentTypeCode = parentTypeCode == null ? null : parentTypeCode.trim();
    }

    public String getParentParamCode() {
        return parentParamCode;
    }

    public void setParentParamCode(String parentParamCode) {
        this.parentParamCode = parentParamCode == null ? null : parentParamCode.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
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
        this.operId = operId == null ? null : operId.trim();
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
        this.sourceCode = sourceCode == null ? null : sourceCode.trim();
    }
}