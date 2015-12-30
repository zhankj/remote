package cn.cttic.sysframe.frame.domain;

public class SmSysDict {
    private String typeCode;

    private String paramCode;

    private String paramDesc;

    private String parentTypeCode;

    private String parentParamCode;
    //
    private String languageType;
    
    public String getTypeCode() {
        return typeCode;
    }

    public String getLanguageType() {
    	return languageType;
    }

	
    public void setLanguageType(String languageType) {
    	this.languageType = languageType;
    }

	public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode == null ? null : paramCode.trim();
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc == null ? null : paramDesc.trim();
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
}