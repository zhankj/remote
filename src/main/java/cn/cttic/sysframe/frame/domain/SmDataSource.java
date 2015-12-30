package cn.cttic.sysframe.frame.domain;

import java.util.Date;

public class SmDataSource {
    private String dataCode;

    private String dataName;

    private String sqlSelect;

    private String sqlFrom;

    private String sqlWhere;

    private Short sourceSts;

    private Date stsDate;

    private String sqlOrder;

    private Short isDistinct;

    private Short sourceType;

    private String implClass;

    private String sqlTrans;

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode == null ? null : dataCode.trim();
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName == null ? null : dataName.trim();
    }

    public String getSqlSelect() {
        return sqlSelect;
    }

    public void setSqlSelect(String sqlSelect) {
        this.sqlSelect = sqlSelect == null ? null : sqlSelect.trim();
    }

    public String getSqlFrom() {
        return sqlFrom;
    }

    public void setSqlFrom(String sqlFrom) {
        this.sqlFrom = sqlFrom == null ? null : sqlFrom.trim();
    }

    public String getSqlWhere() {
        return sqlWhere;
    }

    public void setSqlWhere(String sqlWhere) {
        this.sqlWhere = sqlWhere == null ? null : sqlWhere.trim();
    }

    public Short getSourceSts() {
        return sourceSts;
    }

    public void setSourceSts(Short sourceSts) {
        this.sourceSts = sourceSts;
    }

    public Date getStsDate() {
        return stsDate;
    }

    public void setStsDate(Date stsDate) {
        this.stsDate = stsDate;
    }

    public String getSqlOrder() {
        return sqlOrder;
    }

    public void setSqlOrder(String sqlOrder) {
        this.sqlOrder = sqlOrder == null ? null : sqlOrder.trim();
    }

    public Short getIsDistinct() {
        return isDistinct;
    }

    public void setIsDistinct(Short isDistinct) {
        this.isDistinct = isDistinct;
    }

    public Short getSourceType() {
        return sourceType;
    }

    public void setSourceType(Short sourceType) {
        this.sourceType = sourceType;
    }

    public String getImplClass() {
        return implClass;
    }

    public void setImplClass(String implClass) {
        this.implClass = implClass == null ? null : implClass.trim();
    }

    public String getSqlTrans() {
        return sqlTrans;
    }

    public void setSqlTrans(String sqlTrans) {
        this.sqlTrans = sqlTrans == null ? null : sqlTrans.trim();
    }
}