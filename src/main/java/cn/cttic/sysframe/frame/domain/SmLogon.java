package cn.cttic.sysframe.frame.domain;

import java.util.Date;

public class SmLogon {
    private String operId;

    private String staffId;

    private String operCode;

    private String clientIp;

    private Date logonDate;

    private Short errorCnt;

    private String note;

    private String appId;

    private String state;

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId == null ? null : operId.trim();
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }

    public String getOperCode() {
        return operCode;
    }

    public void setOperCode(String operCode) {
        this.operCode = operCode == null ? null : operCode.trim();
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp == null ? null : clientIp.trim();
    }

    public Date getLogonDate() {
        return logonDate;
    }

    public void setLogonDate(Date logonDate) {
        this.logonDate = logonDate;
    }

    public Short getErrorCnt() {
        return errorCnt;
    }

    public void setErrorCnt(Short errorCnt) {
        this.errorCnt = errorCnt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}