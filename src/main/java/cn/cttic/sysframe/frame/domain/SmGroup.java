package cn.cttic.sysframe.frame.domain;

import java.util.Date;

import net.sf.json.JSONObject;
import cn.cttic.sysframe.common.util.DateUtil;
import cn.cttic.sysframe.common.util.SpringContextUtil;
import cn.cttic.sysframe.frame.service.SmSysDictService;
import cn.cttic.sysframe.frame.util.LogUtil;

public class SmGroup {
    private Long groupId;

    private String groupName;

    private String state;

    private String groupDesc;

    private String operId;

    private Date createDate;

    private Date updateDate;

    private String groupType;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc == null ? null : groupDesc.trim();
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType == null ? null : groupType.trim();
    }
    
    @Override
    public String toString() {
    	JSONObject json = new JSONObject();
    	SmSysDictService smSysDictService = (SmSysDictService) SpringContextUtil.getBean(SmSysDictService.class);
    	json.element(LogUtil.LOG_NAME, groupName);
    	json.element("虚拟组名称", groupName);
    	SmSysDict groupTypeBean = smSysDictService.getSmSysDict("SM_GROUP.GROUP_TYPE", groupType);
    	json.element("虚拟组类型", groupTypeBean!=null?groupTypeBean.getParamDesc():"");
    	json.element("虚拟组描述", groupDesc);
    	json.element("操作员", operId);
    	json.element("创建时间",DateUtil.format(updateDate, DateUtil.PATTERN_YYYY_MM_DD));
        return json.toString();
    }
}