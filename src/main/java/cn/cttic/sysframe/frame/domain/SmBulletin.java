package cn.cttic.sysframe.frame.domain;

import java.util.Date;

public class SmBulletin {
    private Integer bulletinId;

    private String bulletinTitle;

    private String bulletinContent;

    private String bulletinPublisher;

    private String bulletinType;

    private String createOperId;

    private Date startDate;

    private Date endDate;

    private Date createDate;

    private String fileNames;

    private String state;

    private String modifyOperId;

    private Date modifyDate;

    public Integer getBulletinId() {
        return bulletinId;
    }

    public void setBulletinId(Integer bulletinId) {
        this.bulletinId = bulletinId;
    }

    public String getBulletinTitle() {
        return bulletinTitle;
    }

    public void setBulletinTitle(String bulletinTitle) {
        this.bulletinTitle = bulletinTitle == null ? null : bulletinTitle.trim();
    }

    public String getBulletinContent() {
        return bulletinContent;
    }

    public void setBulletinContent(String bulletinContent) {
        this.bulletinContent = bulletinContent == null ? null : bulletinContent.trim();
    }

    public String getBulletinPublisher() {
        return bulletinPublisher;
    }

    public void setBulletinPublisher(String bulletinPublisher) {
        this.bulletinPublisher = bulletinPublisher == null ? null : bulletinPublisher.trim();
    }

    public String getBulletinType() {
        return bulletinType;
    }

    public void setBulletinType(String bulletinType) {
        this.bulletinType = bulletinType == null ? null : bulletinType.trim();
    }

    public String getCreateOperId() {
        return createOperId;
    }

    public void setCreateOperId(String createOperId) {
        this.createOperId = createOperId == null ? null : createOperId.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getFileNames() {
        return fileNames;
    }

    public void setFileNames(String fileNames) {
        this.fileNames = fileNames == null ? null : fileNames.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getModifyOperId() {
        return modifyOperId;
    }

    public void setModifyOperId(String modifyOperId) {
        this.modifyOperId = modifyOperId == null ? null : modifyOperId.trim();
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}