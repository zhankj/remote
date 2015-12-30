package cn.cttic.sysframe.frame.domain;

public class SmRegisteredService {
    private Long sId;

    private String sType;

    private String sUrl;

    private Long pSId;

    private String sName;

    private String description;

    private String systemId;

    private String netType;

    private String instIdentifySts;

    public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
    }

    public String getsType() {
        return sType;
    }

    public void setsType(String sType) {
        this.sType = sType == null ? null : sType.trim();
    }

    public String getsUrl() {
        return sUrl;
    }

    public void setsUrl(String sUrl) {
        this.sUrl = sUrl == null ? null : sUrl.trim();
    }

    public Long getpSId() {
        return pSId;
    }

    public void setpSId(Long pSId) {
        this.pSId = pSId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName == null ? null : sName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType == null ? null : netType.trim();
    }

    public String getInstIdentifySts() {
        return instIdentifySts;
    }

    public void setInstIdentifySts(String instIdentifySts) {
        this.instIdentifySts = instIdentifySts == null ? null : instIdentifySts.trim();
    }
}