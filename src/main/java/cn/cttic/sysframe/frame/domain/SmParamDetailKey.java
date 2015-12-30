package cn.cttic.sysframe.frame.domain;

public class SmParamDetailKey {
    private String typeCode;

    private String typeParamCode;

    private String languageType;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    public String getTypeParamCode() {
        return typeParamCode;
    }

    public void setTypeParamCode(String typeParamCode) {
        this.typeParamCode = typeParamCode == null ? null : typeParamCode.trim();
    }

    public String getLanguageType() {
        return languageType;
    }

    public void setLanguageType(String languageType) {
        this.languageType = languageType == null ? null : languageType.trim();
    }
}