package cn.cttic.sysframe.frame.domain;

import java.util.Date;

import net.sf.json.JSONObject;
import cn.cttic.sysframe.common.util.DateUtil;
import cn.cttic.sysframe.common.util.SpringContextUtil;
import cn.cttic.sysframe.frame.service.SmOrganService;
import cn.cttic.sysframe.frame.service.SmSysDictService;
import cn.cttic.sysframe.frame.util.LogUtil;

public class SmStaff {
    private String staffId;

    private String staffName;

    private String staffCode;

    private String state;

    private String orgId;

    private String staffPost;

    private String provinceId;

    private String areaId;

    private String countyId;

    private String mobilePhone;

    private Date birthdate;

    private String certType;

    private String certNum;

    private String contactPhone;

    private String contactName;

    private String contactAddr;

    private String contactZip;

    private String contactFax;

    private String contactEmail;

    private String leader;

    private String orgPhone;

    private String education;

    private String nation;

    private String political;

    private Date inCompany;

    private Date inStation;

    private String gender;

    private String operId;

    private Date createDate;

    private Date modifyDate;

    private String staffType;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode == null ? null : staffCode.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getStaffPost() {
        return staffPost;
    }

    public void setStaffPost(String staffPost) {
        this.staffPost = staffPost == null ? null : staffPost.trim();
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

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType == null ? null : certType.trim();
    }

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum == null ? null : certNum.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactAddr() {
        return contactAddr;
    }

    public void setContactAddr(String contactAddr) {
        this.contactAddr = contactAddr == null ? null : contactAddr.trim();
    }

    public String getContactZip() {
        return contactZip;
    }

    public void setContactZip(String contactZip) {
        this.contactZip = contactZip == null ? null : contactZip.trim();
    }

    public String getContactFax() {
        return contactFax;
    }

    public void setContactFax(String contactFax) {
        this.contactFax = contactFax == null ? null : contactFax.trim();
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail == null ? null : contactEmail.trim();
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }

    public String getOrgPhone() {
        return orgPhone;
    }

    public void setOrgPhone(String orgPhone) {
        this.orgPhone = orgPhone == null ? null : orgPhone.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political == null ? null : political.trim();
    }

    public Date getInCompany() {
        return inCompany;
    }

    public void setInCompany(Date inCompany) {
        this.inCompany = inCompany;
    }

    public Date getInStation() {
        return inStation;
    }

    public void setInStation(Date inStation) {
        this.inStation = inStation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId == null ? null : operId.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType == null ? null : staffType.trim();
    }
    
    @Override
    public String toString() {
    	JSONObject json = new JSONObject();
    	SmSysDictService smSysDictService = (SmSysDictService) SpringContextUtil.getBean(SmSysDictService.class);
    	SmOrganService smOrganService = (SmOrganService) SpringContextUtil.getBean(SmOrganService.class);
    	json.element(LogUtil.LOG_NAME, staffName);
    	json.element("人员姓名", staffName);
    	json.element("人员编号", staffCode);
    	SmSysDict sexBean = smSysDictService.getSmSysDict("SM_SEX", gender);
    	json.element("性别", sexBean!=null?sexBean.getParamDesc():"");
    	SmSysDict staffTypeBean = smSysDictService.getSmSysDict("SM_STAFF.STAFF_TYPE", staffType);
    	json.element("人员类别", staffTypeBean!=null?staffTypeBean.getParamDesc():"");
    	SmOrgan org = smOrganService.selectByPrimaryKey(orgId);
    	json.element("所属部门", org!=null?org.getOrgName():"");
    	SmSysDict staffPostBean = smSysDictService.getSmSysDict("SM_STAFF.STAFF_POST", staffPost);
    	json.element("人员职位", staffPostBean!=null?staffPostBean.getParamDesc():"");
    	SmSysDict province = smSysDictService.getSmSysDict("SM_PROVINCE", provinceId);
    	json.element("省份", province!=null?province.getParamDesc():"");
    	SmSysDict area = areaId!=null?smSysDictService.getSmSysDict("SM_AREA", areaId):null;
    	json.element("地区", area!=null?area.getParamDesc():"");
    	SmSysDict county = countyId!=null?smSysDictService.getSmSysDict("SM_COUNTY", countyId):null;
    	json.element("县市", county!=null?county.getParamDesc():"");
    	json.element("手机", mobilePhone);
    	json.element("生日",  DateUtil.format(birthdate, DateUtil.PATTERN_YYYY_MM_DD));
    	SmSysDict certTypeBean = smSysDictService.getSmSysDict("CERT_TYPE", certType);
    	json.element("证件类型", certTypeBean!=null?certTypeBean.getParamDesc():"");
    	json.element("证件号码", certNum);
    	json.element("联系电话", contactPhone);
    	json.element("联系姓名", contactName);
    	json.element("联系地址", contactAddr);
    	json.element("联系邮编", contactZip);
    	json.element("传真号码", contactFax);
    	json.element("联系邮箱", contactEmail);
    	json.element("领导", leader);
    	json.element("办公电话", orgPhone);
    	SmSysDict educationBean = smSysDictService.getSmSysDict("EDUCATION", education);
    	json.element("学历", educationBean!=null?educationBean.getParamDesc():"");
    	SmSysDict nationBean = smSysDictService.getSmSysDict("NATION", nation);
    	json.element("民族", nationBean!=null?nationBean.getParamDesc():"");
    	SmSysDict politicalBean = smSysDictService.getSmSysDict("POLITICAL", political);
    	json.element("政治面貌", politicalBean!=null?politicalBean.getParamDesc():"");
    	json.element("进入公司时间", DateUtil.format(inCompany, DateUtil.PATTERN_YYYY_MM_DD));
    	json.element("进入职位时间", DateUtil.format(inStation, DateUtil.PATTERN_YYYY_MM_DD));
    	SmSysDict stateBean = smSysDictService.getSmSysDict("SM_STAFF.STAFF_STATUS", state);
    	json.element("状态", stateBean!=null?stateBean.getParamDesc():"");
    	//json.element("创建人", createOperId);
    	//json.element("创建时间", DateUtil.format(createTime, DateUtil.PATTERN_YYYY_MM_DD));
        return json.toString();
    }
}