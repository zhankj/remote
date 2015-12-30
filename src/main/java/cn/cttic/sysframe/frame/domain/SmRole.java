package cn.cttic.sysframe.frame.domain;

import java.util.Date;

public class SmRole {
    private String roleId;

    private String roleName;

    private String roleDesc;

    private Short roleOrderNo;

    private String provinceId;

    private String areaId;

    private String countyId;

    private String systemId;

    private String state;

    private Date effDate;

    private Date expDate;

    private Date createDate;

    private String operId;

    private String roleCode;

    private String roleLevel;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    public Short getRoleOrderNo() {
        return roleOrderNo;
    }

    public void setRoleOrderNo(Short roleOrderNo) {
        this.roleOrderNo = roleOrderNo;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
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

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId == null ? null : operId.trim();
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public String getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(String roleLevel) {
        this.roleLevel = roleLevel == null ? null : roleLevel.trim();
    }
//    
//    private transient Long fsId;
//    
//    public Long getFsId() {
//    	return fsId;
//    }
//	
//    public void setFsId(Long fsId) {
//    	this.fsId = fsId;
//    }

	@Override
	public String toString() {
		return "SmRole [roleId=" + roleId + ", roleName=" + roleName
				+ ", roleDesc=" + roleDesc + ", roleOrderNo=" + roleOrderNo
				+ ", provinceId=" + provinceId + ", areaId=" + areaId
				+ ", countyId=" + countyId + ", systemId=" + systemId
				+ ", roleStatus=" + state + ", effDate=" + effDate
				+ ", expDate=" + expDate + ", createDate=" + createDate
				+ ", operId=" + operId + ", roleCode=" + roleCode
				+ ", roleLevel=" + roleLevel + "]";
	}
}