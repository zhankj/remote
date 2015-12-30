package cn.cttic.sysframe.frame.domain;

import java.util.Date;

public class SmFtpPath {
    private String ftpPathCode;

    private String ftpCode;

    private String remotePath;

    private String remotePathHis;

    private String remotePathTemp;

    private String localPath;

    private String localPathHis;

    private String localPathTemp;

    private String note;

    private String state;

    private Date createDate;

    public String getFtpPathCode() {
        return ftpPathCode;
    }

    public void setFtpPathCode(String ftpPathCode) {
        this.ftpPathCode = ftpPathCode == null ? null : ftpPathCode.trim();
    }

    public String getFtpCode() {
        return ftpCode;
    }

    public void setFtpCode(String ftpCode) {
        this.ftpCode = ftpCode == null ? null : ftpCode.trim();
    }

    public String getRemotePath() {
        return remotePath;
    }

    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath == null ? null : remotePath.trim();
    }

    public String getRemotePathHis() {
        return remotePathHis;
    }

    public void setRemotePathHis(String remotePathHis) {
        this.remotePathHis = remotePathHis == null ? null : remotePathHis.trim();
    }

    public String getRemotePathTemp() {
        return remotePathTemp;
    }

    public void setRemotePathTemp(String remotePathTemp) {
        this.remotePathTemp = remotePathTemp == null ? null : remotePathTemp.trim();
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath == null ? null : localPath.trim();
    }

    public String getLocalPathHis() {
        return localPathHis;
    }

    public void setLocalPathHis(String localPathHis) {
        this.localPathHis = localPathHis == null ? null : localPathHis.trim();
    }

    public String getLocalPathTemp() {
        return localPathTemp;
    }

    public void setLocalPathTemp(String localPathTemp) {
        this.localPathTemp = localPathTemp == null ? null : localPathTemp.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}