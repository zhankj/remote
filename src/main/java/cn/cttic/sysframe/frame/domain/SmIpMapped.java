package cn.cttic.sysframe.frame.domain;

public class SmIpMapped {
    private String systemId;

    private String connectIp;

    private String connectPort;

    private String mappedId;

    private String note;

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    public String getConnectIp() {
        return connectIp;
    }

    public void setConnectIp(String connectIp) {
        this.connectIp = connectIp == null ? null : connectIp.trim();
    }

    public String getConnectPort() {
        return connectPort;
    }

    public void setConnectPort(String connectPort) {
        this.connectPort = connectPort == null ? null : connectPort.trim();
    }

    public String getMappedId() {
        return mappedId;
    }

    public void setMappedId(String mappedId) {
        this.mappedId = mappedId == null ? null : mappedId.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}