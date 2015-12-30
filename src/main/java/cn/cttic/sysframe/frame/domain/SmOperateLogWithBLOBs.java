package cn.cttic.sysframe.frame.domain;

public class SmOperateLogWithBLOBs extends SmOperateLog {
    private String oldObject;

    private String newObject;

    public String getOldObject() {
        return oldObject;
    }

    public void setOldObject(String oldObject) {
        this.oldObject = oldObject == null ? null : oldObject.trim();
    }

    public String getNewObject() {
        return newObject;
    }

    public void setNewObject(String newObject) {
        this.newObject = newObject == null ? null : newObject.trim();
    }
}