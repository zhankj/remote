package cn.cttic.sysframe.frame.domain;

import java.sql.Timestamp;
import java.util.Date;

public class SmTimerTaskDef {
    private Long taskId;

    private String taskName;

    private String taskDesc;

    private Integer taskType;

    private Integer periodType;

    private String syncTimeFormat;

    private Long runTimeInterval;

    private Integer runNum;

    private Integer implType;

    private String implMeth;

    private String implExtraParam;

    private String taskCreator;

    private String systemId;

    private String taskStatus;

    private Timestamp preRunTime;

    private Date updateTime;

    private String updateOperId;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc == null ? null : taskDesc.trim();
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Integer getPeriodType() {
        return periodType;
    }

    public void setPeriodType(Integer periodType) {
        this.periodType = periodType;
    }

    public String getSyncTimeFormat() {
        return syncTimeFormat;
    }

    public void setSyncTimeFormat(String syncTimeFormat) {
        this.syncTimeFormat = syncTimeFormat == null ? null : syncTimeFormat.trim();
    }

    public Long getRunTimeInterval() {
        return runTimeInterval;
    }

    public void setRunTimeInterval(Long runTimeInterval) {
        this.runTimeInterval = runTimeInterval;
    }

    public Integer getRunNum() {
        return runNum;
    }

    public void setRunNum(Integer runNum) {
        this.runNum = runNum;
    }

    public Integer getImplType() {
        return implType;
    }

    public void setImplType(Integer implType) {
        this.implType = implType;
    }

    public String getImplMeth() {
        return implMeth;
    }

    public void setImplMeth(String implMeth) {
        this.implMeth = implMeth == null ? null : implMeth.trim();
    }

    public String getImplExtraParam() {
        return implExtraParam;
    }

    public void setImplExtraParam(String implExtraParam) {
        this.implExtraParam = implExtraParam == null ? null : implExtraParam.trim();
    }

    public String getTaskCreator() {
        return taskCreator;
    }

    public void setTaskCreator(String taskCreator) {
        this.taskCreator = taskCreator == null ? null : taskCreator.trim();
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus == null ? null : taskStatus.trim();
    }

    public Timestamp getPreRunTime() {
        return preRunTime;
    }

    public void setPreRunTime(Timestamp preRunTime) {
        this.preRunTime = preRunTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateOperId() {
        return updateOperId;
    }

    public void setUpdateOperId(String updateOperId) {
        this.updateOperId = updateOperId == null ? null : updateOperId.trim();
    }
}