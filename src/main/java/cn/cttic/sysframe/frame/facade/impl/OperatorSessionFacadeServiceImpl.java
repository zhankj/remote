package cn.cttic.sysframe.frame.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.common.util.BeanValiditionUtil;
import cn.cttic.sysframe.common.util.StringUtil;
import cn.cttic.sysframe.frame.common.constants.FrameConstants;
import cn.cttic.sysframe.frame.dao.SmDataRightMapper;
import cn.cttic.sysframe.frame.dao.SmMenuMapper;
import cn.cttic.sysframe.frame.dao.SmOrganMapper;
import cn.cttic.sysframe.frame.dao.SmRoleMapper;
import cn.cttic.sysframe.frame.dao.SmStaffMapper;
import cn.cttic.sysframe.frame.domain.SmMenu;
import cn.cttic.sysframe.frame.domain.SmOper;
import cn.cttic.sysframe.frame.domain.SmOrgan;
import cn.cttic.sysframe.frame.domain.SmRole;
import cn.cttic.sysframe.frame.domain.SmStaff;
import cn.cttic.sysframe.frame.domain.SmSysDict;
import cn.cttic.sysframe.frame.facade.OperatorSessionFacadeService;
import cn.cttic.sysframe.frame.model.SmOperModel;
import cn.cttic.sysframe.frame.service.SmOperService;
import cn.cttic.sysframe.frame.service.SmSysDictService;

@Service
public class OperatorSessionFacadeServiceImpl implements
		OperatorSessionFacadeService {
	
	@Autowired
	private SmOperService smOperService;
	
	@Autowired
	private SmStaffMapper staffMapper;
	
	@Autowired
	private SmOrganMapper organMapper;
	
	@Autowired
	private SmSysDictService sysDictService;
	
	@Autowired
	private SmRoleMapper roleMapper;
	
	@Autowired
	private SmMenuMapper menuMapper;
	
	@Autowired
	private SmDataRightMapper dataRightMapper;
	
	protected void loadOperatorInfo(String operCode, SmOperModel modelInSession) {
		BeanValiditionUtil.checkString(operCode, "工号编码");
		SmOper smOper = smOperService.getByOperCode(operCode);
		BeanUtils.copyProperties(smOper, modelInSession);
	}
	
	@Override
	public SmOperModel initOperatorSession(String operCode){

		SmOperModel operModel = new SmOperModel();
		
		try{
			/**1.加载工号信息**************************************************************/
			this.loadOperatorInfo(operCode, operModel);
			/**2.加载人员信息**************************************************************/
			if(operModel.getStaffId()!=null){
			   SmStaff staff = staffMapper.selectByPrimaryKey(operModel.getStaffId());
			   if(staff!=null&&FrameConstants.SmStaff.StaffStatus.NORMAL.equals(staff.getState())){
				   operModel.setStaff(staff);
				   operModel.setStaffName(staff.getStaffName());
			   }else{
				   throw new SystemException("工号编码"+operCode+"对应人员不存在或人员已失效,请联系系统管理员！", StatusCodeForFrame.RECORD_NOT_EXISTS);
			   }
			}else{
				throw new SystemException("工号编码"+operCode+"对应人员不存在或人员已失效,请联系系统管理员！", StatusCodeForFrame.RECORD_NOT_EXISTS);
			}
			/**3.加载部门信息**************************************************************/
			if (operModel.getOrgId() != null) {
				SmOrgan organ = organMapper.selectByPrimaryKey(operModel.getOrgId());
				if (organ != null
						&& FrameConstants.SmOrgan.State.VALID.equals(organ.getState())) {
					operModel.setOrgan(organ);
					operModel.setOrgName(organ.getOrgName());
				} else {
					throw new SystemException("工号编码" + operCode	+ "对应部门不存在或部门已失效,请联系系统管理员！",
							StatusCodeForFrame.RECORD_NOT_EXISTS);
				}
			}else{
//				throw new SystemException("工号编码"+operCode+"对应部门不存在或部门已失效,请联系系统管理员！", StatusCodeForFrame.RECORD_NOT_EXISTS);
			}
			/**4.区域信息翻译***************************************************************************/
			if(!StringUtil.isBlank(operModel.getProvinceId())){
				SmSysDict sysDict=sysDictService.getSmSysDict("SM_PROVINCE", operModel.getProvinceId());
				if(sysDict!=null&&!StringUtil.isBlank(sysDict.getParamDesc())){
					operModel.setProvinceName(sysDict.getParamDesc());
					operModel.setRegionName(sysDict.getParamDesc());
				}
				operModel.setRegionId(operModel.getProvinceId());
			}else if(!StringUtil.isBlank(operModel.getAreaId())){
				SmSysDict sysDict=sysDictService.getSmSysDict("SM_AREA", operModel.getAreaId());
				if(sysDict!=null&&!StringUtil.isBlank(sysDict.getParamDesc())){
					operModel.setProvinceName(sysDict.getParamDesc());
				}
				if(!FrameConstants.SmRegion.PROVINCE_CENTER.equals(operModel.getAreaId())){
					operModel.setRegionId(operModel.getAreaId());
					if(sysDict!=null&&!StringUtil.isBlank(sysDict.getParamDesc())){
						operModel.setRegionName(sysDict.getParamDesc());
					}
				}
			}else if(!StringUtil.isBlank(operModel.getCountyId())){
				SmSysDict sysDict=sysDictService.getSmSysDict("SM_COUNTY", operModel.getCountyId());
				if(sysDict!=null&&!StringUtil.isBlank(sysDict.getParamDesc())){
					operModel.setProvinceName(sysDict.getParamDesc());
				}
				if(!FrameConstants.SmRegion.AREA_CENTER.equals(operModel.getCountyId())){
					operModel.setRegionId(operModel.getAreaId());
					if(sysDict!=null&&!StringUtil.isBlank(sysDict.getParamDesc())){
						operModel.setRegionName(sysDict.getParamDesc());
					}
				}
			}
			/**6.菜单信息*******************************************************************************/
			Map<String, Object> roleParamMap = new HashMap<String, Object>();
			roleParamMap.put("operId", operModel.getOperId());
			List<SmRole> roleList = roleMapper.operRoleData(roleParamMap);
			operModel.setRoleList(roleList);
			/**7.菜单权限信息*******************************************************************************/
			List<SmMenu> menuList=menuMapper.getUrlByOperId(operModel.getOperId().toString());
			Map<String,String> menuMap=new HashMap<String,String>();
			if(menuList!=null&&menuList.size()>0){
				for(SmMenu menu:menuList){
					menuMap.put(menu.getMenuUrl(), menu.getMenuId());
				}
			}
			operModel.setMenuRigthMap(menuMap);
			/**8.数据权限信息*******************************************************************************/
			List<Map<String,Object>> dataRightList=dataRightMapper.getDataRightByOperId(operModel.getOperId().toString());
			Map<String,List<String>> dataRightMap=new HashMap<String,List<String>>();
			if(dataRightList!=null&&dataRightList.size()>0){
				for(Map<String,Object> dataInfoMap:dataRightList){
					String data_code=dataInfoMap.get("DATA_CODE").toString();
					if(dataRightMap.containsKey(data_code)){
						dataRightMap.get(data_code).add(dataInfoMap.get("DATA_VALUE").toString());
					}else{
						dataRightMap.put(data_code, new ArrayList<String>());
						dataRightMap.get(data_code).add(dataInfoMap.get("DATA_VALUE").toString());
					}
				}
			}
			operModel.setDataRightMap(dataRightMap);
		}catch(Exception e){
			throw new SystemException(e,StatusCodeForFrame.INSIDE_ERROR);
		}
		return operModel;
	}
}
