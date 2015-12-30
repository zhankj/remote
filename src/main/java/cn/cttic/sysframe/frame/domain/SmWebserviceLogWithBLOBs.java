package cn.cttic.sysframe.frame.domain;

public class SmWebserviceLogWithBLOBs extends SmWebserviceLog {
    private String exceptionDesc;

    private String reqMessage;

    public String getExceptionDesc() {
        return exceptionDesc;
    }

    public void setExceptionDesc(String exceptionDesc) {
        this.exceptionDesc = exceptionDesc == null ? null : exceptionDesc.trim();
    }

    public String getReqMessage() {
        return reqMessage;
    }

    public void setReqMessage(String reqMessage) {
        this.reqMessage = reqMessage == null ? null : reqMessage.trim();
    }
}