package cn.cttic.sysframe.frame.domain;

import java.util.Date;

public class SmFuncSet {
    private String fsId;

    private String fsName;

    private String fsDesc;

    private Short fsOrderNo;

    private String provinceId;

    private String areaId;

    private String countyId;

    private String systemId;

    private Date effDate;

    private Date expDate;

    private Date createDate;

    private String createOperId;

    private String fsLevel;

    private String state;

    private Date modifyDate;

    private String modifyOperId;

    public String getFsId() {
        return fsId;
    }

    public void setFsId(String fsId) {
        this.fsId = fsId == null ? null : fsId.trim();
    }

    public String getFsName() {
        return fsName;
    }

    public void setFsName(String fsName) {
        this.fsName = fsName == null ? null : fsName.trim();
    }

    public String getFsDesc() {
        return fsDesc;
    }

    public void setFsDesc(String fsDesc) {
        this.fsDesc = fsDesc == null ? null : fsDesc.trim();
    }

    public Short getFsOrderNo() {
        return fsOrderNo;
    }

    public void setFsOrderNo(Short fsOrderNo) {
        this.fsOrderNo = fsOrderNo;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId == null ? null : provinceId.trim();
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId == null ? null : countyId.trim();
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    public Date getEffDate() {
        return effDate;
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateOperId() {
        return createOperId;
    }

    public void setCreateOperId(String createOperId) {
        this.createOperId = createOperId == null ? null : createOperId.trim();
    }

    public String getFsLevel() {
        return fsLevel;
    }

    public void setFsLevel(String fsLevel) {
        this.fsLevel = fsLevel == null ? null : fsLevel.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifyOperId() {
        return modifyOperId;
    }

    public void setModifyOperId(String modifyOperId) {
        this.modifyOperId = modifyOperId == null ? null : modifyOperId.trim();
    }
}