package cn.cttic.sysframe.frame.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.common.util.SessionInfo;
import cn.cttic.sysframe.frame.common.constants.FrameConstants;
import cn.cttic.sysframe.frame.dao.SmDataRightMapper;
import cn.cttic.sysframe.frame.dao.SmMenuMapper;
import cn.cttic.sysframe.frame.dao.SmOper2RoleMapper;
import cn.cttic.sysframe.frame.dao.SmOperMapper;
import cn.cttic.sysframe.frame.dao.SmOrganMapper;
import cn.cttic.sysframe.frame.dao.SmRoleMapper;
import cn.cttic.sysframe.frame.dao.SmStaffMapper;
import cn.cttic.sysframe.frame.domain.SmOper;
import cn.cttic.sysframe.frame.domain.SmOper2Role;
import cn.cttic.sysframe.frame.domain.SmOper2RoleKey;
import cn.cttic.sysframe.frame.domain.SmOperExample;
import cn.cttic.sysframe.frame.domain.SmOperExample.Criteria;
import cn.cttic.sysframe.frame.domain.SmOrgan;
import cn.cttic.sysframe.frame.domain.SmRole;
import cn.cttic.sysframe.frame.domain.SmStaff;
import cn.cttic.sysframe.frame.model.SmOperModel;
import cn.cttic.sysframe.frame.service.SmOperService;
import cn.cttic.sysframe.frame.service.SmOrganService;
import cn.cttic.sysframe.frame.service.SmSysDictService;
import cn.cttic.sysframe.frame.service.SystemService;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
public class SmOperServiceImpl implements SmOperService {

	@Autowired
	private SmOperMapper operMapper;
	@Autowired
	private SmStaffMapper staffMapper;
	@Autowired
	private SmOrganMapper organMapper;
	@Autowired
	private SmSysDictService sysDictService;
	@Autowired
	private SmMenuMapper menuMapper;
	@Autowired
	private SmDataRightMapper dataRightMapper;
	@Autowired
	private SmOrganService smOrganService;
	
	
	
	@Autowired
	private SmRoleMapper smRoleMapper;
	
	@Autowired
	private SmOper2RoleMapper smOper2RoleMapper;
	
	@Autowired
	private SystemService systemService;
	
	
	@Override
	public List<SmOper> getBeans(SmOperExample example) {
		try {
			return operMapper.selectByExample(example);
		} catch (Exception ex) {
			throw new SystemException(ex,
					StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
	public SmOper getByAuth(String operCode, String loginPwd) {
		try {
			SmOperExample example = new SmOperExample();
			example.createCriteria().andOperCodeEqualTo(operCode);
			List<SmOper> opers = operMapper.selectByExample(example);
			if (opers.size() == 0) throw new SystemException(StatusCodeForFrame.OPERATOR_NOT_EXIST_ERROR, operCode);
			if (opers.size() > 1) throw new SystemException("存在多个匹配的工号", StatusCodeForFrame.INSIDE_ERROR);
			if (!opers.get(0).getLoginPwd().equals(loginPwd)) throw new SystemException(StatusCodeForFrame.INVALID_PASSWORD_OF_OPERATOR_ERROR, operCode);
			if (!opers.get(0).getState().equals(FrameConstants.SmOper.State.NORMAL)) throw new SystemException(StatusCodeForFrame.INVALID_STATUS_OF_OPERATOR_ERROR, operCode);
			return opers.get(0);
		} catch (SystemException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}
	
	@Override
	public SmOper getBean(SmOperExample example) {
		try {
			SmOper bean = new SmOper();
			List<SmOper> beanList = getBeans(example);
			if (null != beanList && beanList.size() > 0) {
				bean = beanList.get(0);
			}
			return bean;
		} catch (Exception ex) {
			throw new SystemException(ex,
					StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}
	
	@Override
	public SmOper getBean(String operId) {
		return operMapper.selectByPrimaryKey(operId);
	}

	@Override
	public int insertBean(SmOper record) {
		try {
			return operMapper.insert(record);
		} catch (Exception ex) {
			throw new SystemException(ex,
					StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
	public int updateBean(SmOper record, SmOperExample example) {
		try {
			return operMapper.updateByExample(record, example);
		} catch (Exception ex) {
			throw new SystemException(ex,
					StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}
	
	@Override
	public int updateByPrimaryKeySelective(SmOper record) {
		try {
			return operMapper.updateByPrimaryKeySelective(record);
		} catch (Exception ex) {
			throw new SystemException(ex,
					StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}
	
	@Override
	public int updateByOrgan(String orgId) {
		try {
			SmOrgan smOrgan = organMapper.selectByPrimaryKey(orgId);
			SmOper smOper = new SmOper();
			smOper.setProvinceId(smOrgan.getProvinceId());
			smOper.setAreaId(smOrgan.getAreaId());
			smOper.setCountyId(smOrgan.getCountyId());
			smOper.setOperLevel(smOrgan.getOrgLevel());
			
			SmOperExample operExample = new SmOperExample();
			SmOperExample.Criteria criteria = operExample.createCriteria();
			criteria.andOrgIdEqualTo(orgId);
			
			return operMapper.updateByExampleSelective(smOper, operExample);
		} catch (Exception ex) {
			throw new SystemException(ex,
					StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}
	
	@Override
	public int updateByStaff(String staffId) {
		try {
			SmStaff smStaff = staffMapper.selectByPrimaryKey(staffId);
			
			SmOrgan smOrgan = organMapper.selectByPrimaryKey(smStaff.getOrgId());
			SmOper smOper = new SmOper();
			smOper.setOrgId(smStaff.getOrgId());
			smOper.setProvinceId(smOrgan.getProvinceId());
			smOper.setAreaId(smOrgan.getAreaId());
			smOper.setCountyId(smOrgan.getCountyId());
			smOper.setOperLevel(smOrgan.getOrgLevel());
			SmOperExample operExample = new SmOperExample();
			SmOperExample.Criteria criteria = operExample.createCriteria();
			criteria.andStaffIdEqualTo(staffId);
			
			return operMapper.updateByExampleSelective(smOper, operExample);
		} catch (Exception ex) {
			throw new SystemException(ex,
					StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
	public PageList<SmOper> queryPage(SmOperExample example, PageBounds pageBounds) {
		try {
			return operMapper.queryPage(example, pageBounds);
		} catch (Exception ex) {
			throw new SystemException(ex,
					StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}
	
	/**
	 * 个人密码修改
	 */
	@Override
	public int updateByPrimaryKey(SmOper record){
		try {
			
			return operMapper.updateByPrimaryKeySelective(record);
		} catch (Exception ex) {
			throw new SystemException(ex,
					StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
    public PageList<Map<String,Object>> queryStaffOper(Map<String,Object> map, PageBounds pageBounds) {
		try {
			return operMapper.queryStaffOper(map,pageBounds);
		} catch (Exception ex) {
			throw new SystemException(ex,
					StatusCodeForFrame.DB_OPERATION_ERROR);
		}
    }
	
	
	@Override
	public SmOper getByOperCode(String operCode) {
		SmOperExample operCriteria = new SmOperExample();
		SmOperExample.Criteria criteria = operCriteria.createCriteria();
		criteria.andOperCodeEqualTo(operCode);
		List<SmOper> smOperList = null;
		try {
			smOperList = operMapper.selectByExample(operCriteria);
		} catch (Exception e) {
			throw new SystemException(e, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
		
		if (smOperList == null || smOperList.size() < 1) 
			throw new SystemException("工号" + operCode + "不存在！", StatusCodeForFrame.RECORD_NOT_EXISTS);
		if (smOperList.size() > 1) 
			throw new SystemException("工号" + operCode + "记录多于一条！", StatusCodeForFrame.RECORD_NOT_UNIQUELY);
		
		SmOper smOper = smOperList.get(0);
		
		if (!FrameConstants.SmOper.State.NORMAL.equals(smOper.getState())) {
			throw new SystemException("工号" + operCode + "处于不可用状态", StatusCodeForFrame.RECORD_STATE_ERROR);
		}
		
		return smOper;
		
	}
	
	/**
	 * 根据工号编码初始化操作员信息
	 */
//	@Override
//	public SmOperModel initOperInfo(String operCode){
//		SmOperModel operModel = new SmOperModel();
//		
//		if(StringUtil.isBlank(operCode)){
//			throw new SystemException("工号不能为空！", StatusCodeForFrame.PROPERTY_IS_NULL);
//		}
//		
//		try{
//			/**1.加载工号信息**************************************************************/
//			SmOperExample operCriteria = new SmOperExample();
//			SmOperExample.Criteria criteria = operCriteria.createCriteria();
//			criteria.andOperCodeEqualTo(operCode);
//			List<SmOper> smOperList = null;
//			try{
//				smOperList =operMapper.selectByExample(operCriteria);
//			}catch(Exception e){
//				throw new SystemException(e,StatusCodeForFrame.DB_OPERATION_ERROR);
//			}
//			if(smOperList==null ||smOperList.size()<1){
//				throw new SystemException("工号"+operCode+"不存在！", StatusCodeForFrame.RECORD_NOT_EXISTS);
//			}else{
//				SmOper smOper =smOperList.get(0);
//				if(FrameConstants.SmOper.State.NORMAL.equals(smOper.getState())){
//					BeanUtils.copyProperties(smOper, operModel);
//				}
//			}
//
//			/**2.加载人员信息**************************************************************/
//			if(operModel.getStaffId()!=null){
//			   SmStaff staff = staffMapper.selectByPrimaryKey(operModel.getStaffId());
//			   if(staff!=null&&FrameConstants.SmStaff.StaffStatus.NORMAL.equals(staff.getState())){
//				   operModel.setStaff(staff);
//				   operModel.setStaffName(staff.getStaffName());
//			   }else{
//				   throw new SystemException("工号编码"+operCode+"对应人员不存在或人员已失效,请联系系统管理员！", StatusCodeForFrame.RECORD_NOT_EXISTS);
//			   }
//			}else{
//				throw new SystemException("工号编码"+operCode+"对应人员不存在或人员已失效,请联系系统管理员！", StatusCodeForFrame.RECORD_NOT_EXISTS);
//			}
//			/**3.加载部门信息**************************************************************/
//			if (operModel.getOrgId() != null) {
//				SmOrgan organ = organMapper.selectByPrimaryKey(operModel.getOrgId());
//				if (organ != null
//						&& FrameConstants.SmOrgan.State.VALID.equals(organ.getState())) {
//					operModel.setOrgan(organ);
//					operModel.setOrgName(organ.getOrgName());
//				} else {
//					throw new SystemException("工号编码" + operCode	+ "对应部门不存在或部门已失效,请联系系统管理员！",
//							StatusCodeForFrame.RECORD_NOT_EXISTS);
//				}
//			}else{
//				throw new SystemException("工号编码"+operCode+"对应部门不存在或部门已失效,请联系系统管理员！", StatusCodeForFrame.RECORD_NOT_EXISTS);
//			}
//			/**4.区域信息翻译***************************************************************************/
//			if(!StringUtil.isBlank(operModel.getProvinceId())){
//				SmSysDict sysDict=sysDictService.getSmSysDict("SM_PROVINCE", operModel.getProvinceId());
//				if(sysDict!=null&&!StringUtil.isBlank(sysDict.getParamDesc())){
//					operModel.setProvinceName(sysDict.getParamDesc());
//					operModel.setRegionName(sysDict.getParamDesc());
//				}
//				operModel.setRegionId(operModel.getProvinceId());
//			}else if(!StringUtil.isBlank(operModel.getAreaId())){
//				SmSysDict sysDict=sysDictService.getSmSysDict("SM_AREA", operModel.getAreaId());
//				if(sysDict!=null&&!StringUtil.isBlank(sysDict.getParamDesc())){
//					operModel.setProvinceName(sysDict.getParamDesc());
//				}
//				if(!FrameConstants.SmRegion.PROVINCE_CENTER.equals(operModel.getAreaId())){
//					operModel.setRegionId(operModel.getAreaId());
//					if(sysDict!=null&&!StringUtil.isBlank(sysDict.getParamDesc())){
//						operModel.setRegionName(sysDict.getParamDesc());
//					}
//				}
//			}else if(!StringUtil.isBlank(operModel.getCountyId())){
//				SmSysDict sysDict=sysDictService.getSmSysDict("SM_COUNTY", operModel.getCountyId());
//				if(sysDict!=null&&!StringUtil.isBlank(sysDict.getParamDesc())){
//					operModel.setProvinceName(sysDict.getParamDesc());
//				}
//				if(!FrameConstants.SmRegion.AREA_CENTER.equals(operModel.getCountyId())){
//					operModel.setRegionId(operModel.getAreaId());
//					if(sysDict!=null&&!StringUtil.isBlank(sysDict.getParamDesc())){
//						operModel.setRegionName(sysDict.getParamDesc());
//					}
//				}
//			}
//			/**5.菜单信息*******************************************************************************/
//			List<SmMenu> menuList=menuMapper.getUrlByOperId(operModel.getOperId().toString());
//			Map<String,Long> menuMap=new HashMap<String,Long>();
//			if(menuList!=null&&menuList.size()>0){
//				for(SmMenu menu:menuList){
//					menuMap.put(menu.getMenuUrl(), menu.getMenuId());
//				}
//			}
//			operModel.setMenuRigthMap(menuMap);
//			/**6.权限信息*******************************************************************************/
//			List<Map<String,Object>> dataRightList=dataRightMapper.getDataRightByOperId(operModel.getOperId().toString());
//			Map<String,List<String>> dataRightMap=new HashMap<String,List<String>>();
//			if(dataRightList!=null&&dataRightList.size()>0){
//				for(Map<String,Object> dataInfoMap:dataRightList){
//					String data_code=dataInfoMap.get("DATA_CODE").toString();
//					if(dataRightMap.containsKey(data_code)){
//						dataRightMap.get(data_code).add(dataInfoMap.get("DATA_VALUE").toString());
//					}else{
//						dataRightMap.put(data_code, new ArrayList<String>());
//						dataRightMap.get(data_code).add(dataInfoMap.get("DATA_VALUE").toString());
//					}
//				}
//			}
//			operModel.setDataRightMap(dataRightMap);
//		}catch(Exception e){
//			throw new SystemException(e,StatusCodeForFrame.INSIDE_ERROR);
//		}
//		return operModel;
//	}

	@Override
    public PageList<SmRole> operRoleData(Map<String, Object> map, PageBounds pageBounds) {
	    try {
	    	SmOperModel model = SessionInfo.getOperInfo();
	    	map.put("operLevel",model.getOperLevel());
	    	if(FrameConstants.SmOper.OperLevel.ZB.equals(model.getOperLevel()) || FrameConstants.SmOper.OperLevel.SF.equals(model.getOperLevel())){
	    		map.put("provinceId",model.getProvinceId());
	    		if(FrameConstants.SmRegion.PROVINCE_CENTER.equals(model.getAreaId())){
	    			map.put("provinceCenter",true);
	    		}
	    	}else if(FrameConstants.SmOper.OperLevel.DS.equals(model.getOperLevel())){
	    		map.put("provinceId",model.getProvinceId());
	    		map.put("areaId",model.getAreaId());
	    		if(FrameConstants.SmRegion.AREA_CENTER.equals(model.getCountyId())){
	    			map.put("areaCenter",true);
	    		}
	    	}else if(FrameConstants.SmOper.OperLevel.QX.equals(model.getOperLevel())){
	    		map.put("provinceId",model.getProvinceId());
	    		map.put("areaId",model.getAreaId());
	    		map.put("countyId",model.getCountyId());
	    	}
	    	return smRoleMapper.operRoleData(map, pageBounds);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new SystemException(ex,
					StatusCodeForFrame.DB_OPERATION_ERROR);
		}
    }

	@Override
    public void saveOperRole(Map<String, Object> map) {
	    String state = (String)map.get("state");
	    String roleIds = (String)map.get("roleIds");
	    String operId = (String)map.get("operId");
	    String[] arr = roleIds.split(",");
		try {
			if ("Y".equals(state)) {
				for (String roleId : arr) {
					SmOper2Role so2 = new SmOper2Role();
					so2.setOperId(operId);
					so2.setRoleId(roleId);
					so2.setExpDate(FrameConstants.SDF_YYYY_MM_DD_HH_MM_SS.parse(FrameConstants.IN_VALID_TIME));
					so2.setEffDate(systemService.getSystemDate());
					smOper2RoleMapper.insert(so2);
				}
			} else {
				for (String roleId : arr) {
					SmOper2RoleKey s2k = new SmOper2RoleKey();
					s2k.setOperId(operId);
					s2k.setRoleId(roleId);
					smOper2RoleMapper.deleteByPrimaryKey(s2k);
				}
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}

    }

	@Override
    public List<Map<String, Object>> queryStaffOperByGroupId(String gourpId) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("groupId", gourpId);
			map.put("needLeader", true);
			PageBounds pb = new PageBounds();
			return operMapper.queryStaffOper(map,pb);
		} catch (Exception ex) {
			throw new SystemException(ex,
					StatusCodeForFrame.DB_OPERATION_ERROR);
		}
    }

	@Override
	public List<SmOper> getOperListByOrgId(String orgId) {
		List<SmOrgan> organBeanList = smOrganService.queryTree(orgId);
		List<String> orgIdList = new ArrayList<String>();
		for (SmOrgan bean : organBeanList) {
			if (FrameConstants.SmOrgan.State.VALID.equals(bean.getState())) {
				orgIdList.add(bean.getOrgId());
			}
		}
		SmOperExample example = new SmOperExample();
		Criteria criteria = example.or();
		criteria.andOrgIdIn(orgIdList);
		criteria.andStateEqualTo(FrameConstants.SmOper.State.NORMAL);
		List<SmOper> list = operMapper.selectByExample(example);
		return list;
	}
}
