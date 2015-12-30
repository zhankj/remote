package cn.cttic.sysframe.frame.domain;

public class SmDataRight {
    private String dataCode;

    private String dataName;

    private String provinceId;

    private String areaId;

    private String countyId;

    private Long menuId;

    private Short rightType;

    private Short configAim;

    private String rightLevel;
    
    private String rightStatus;

    public String getRightStatus() {
		return rightStatus;
	}

	public void setRightStatus(String right_status) {
		this.rightStatus = right_status;
	}

	public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode == null ? null : dataCode.trim();
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName == null ? null : dataName.trim();
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

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Short getRightType() {
        return rightType;
    }

    public void setRightType(Short rightType) {
        this.rightType = rightType;
    }

    public Short getConfigAim() {
        return configAim;
    }

    public void setConfigAim(Short configAim) {
        this.configAim = configAim;
    }

    public String getRightLevel() {
        return rightLevel;
    }

    public void setRightLevel(String rightLevel) {
        this.rightLevel = rightLevel == null ? null : rightLevel.trim();
    }
}