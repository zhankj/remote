package cn.cttic.sysframe.frame.model;

import java.util.List;
import java.util.Map;

import cn.cttic.sysframe.frame.domain.SmOper;
import cn.cttic.sysframe.frame.domain.SmOrgan;
import cn.cttic.sysframe.frame.domain.SmRole;
import cn.cttic.sysframe.frame.domain.SmStaff;

public class SmOperModel extends SmOper {
   
	private String orgName;
	
	private String staffName;

	private String provinceName;

	private String areaName;

	private String countyName;

	private SmOrgan organ;

	private SmStaff staff;
	
	private List<SmRole> roleList;
	
	//最小区域信息
	private String regionId;
	
	private String regionName;

	// 菜单权限
	private Map<String, String> menuRigthMap;

	// 数据权限
	private Map<String,List<String>> dataRightMap;

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Map<String, List<String>> getDataRightMap() {
		return dataRightMap;
	}

	public void setDataRightMap(Map<String, List<String>> dataRightMap) {
		this.dataRightMap = dataRightMap;
	}

	public Map<String, String> getMenuRigthMap() {
		return menuRigthMap;
	}

	public void setMenuRigthMap(Map<String, String> menuRigthMap) {
		this.menuRigthMap = menuRigthMap;
	}

	public SmStaff getStaff() {
		return staff;
	}

	public SmOrgan getOrgan() {
		return organ;
	}

	public void setOrgan(SmOrgan organ) {
		this.organ = organ;
	}

	public void setStaff(SmStaff staff) {
		this.staff = staff;
	}

	public String getCountyName() {
		return countyName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

    public List<SmRole> getRoleList() {
    	return roleList;
    }

	
    public void setRoleList(List<SmRole> roleList) {
    	this.roleList = roleList;
    }
	
}
