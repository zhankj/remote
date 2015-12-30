package cn.cttic.sysframe.frame.domain;

import java.util.Date;

public class IntfSms {
    private Long soNbr;

    private Short priority;

    private Integer busiType;

    private String objectId;

    private String objectType;

    private String svcNum;

    private String phoneNum;

    private String smsContent;

    private Date createDate;

    private Date sendDate;

    private Short smsSts;

    private Date stsDate;

    private String rsltDesc;

    private String note;

    public Long getSoNbr() {
        return soNbr;
    }

    public void setSoNbr(Long soNbr) {
        this.soNbr = soNbr;
    }

    public Short getPriority() {
        return priority;
    }

    public void setPriority(Short priority) {
        this.priority = priority;
    }

    public Integer getBusiType() {
        return busiType;
    }

    public void setBusiType(Integer busiType) {
        this.busiType = busiType;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType == null ? null : objectType.trim();
    }

    public String getSvcNum() {
        return svcNum;
    }

    public void setSvcNum(String svcNum) {
        this.svcNum = svcNum == null ? null : svcNum.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent == null ? null : smsContent.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Short getSmsSts() {
        return smsSts;
    }

    public void setSmsSts(Short smsSts) {
        this.smsSts = smsSts;
    }

    public Date getStsDate() {
        return stsDate;
    }

    public void setStsDate(Date stsDate) {
        this.stsDate = stsDate;
    }

    public String getRsltDesc() {
        return rsltDesc;
    }

    public void setRsltDesc(String rsltDesc) {
        this.rsltDesc = rsltDesc == null ? null : rsltDesc.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}