package cn.cttic.sysframe.frame.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.common.util.CopyUtil;
import cn.cttic.sysframe.common.util.SessionInfo;
import cn.cttic.sysframe.common.util.StringUtil;
import cn.cttic.sysframe.frame.common.constants.FrameConstants;
import cn.cttic.sysframe.frame.dao.SmOrganMapper;
import cn.cttic.sysframe.frame.dao.SmStaffMapper;
import cn.cttic.sysframe.frame.domain.SmOrgan;
import cn.cttic.sysframe.frame.domain.SmStaff;
import cn.cttic.sysframe.frame.domain.SmStaffExample;
import cn.cttic.sysframe.frame.domain.SmStaffExample.Criteria;
import cn.cttic.sysframe.frame.model.SmOperModel;
import cn.cttic.sysframe.frame.service.SmOrganService;
import cn.cttic.sysframe.frame.service.SmStaffService;
import cn.cttic.sysframe.frame.service.SystemService;
import cn.cttic.sysframe.frame.util.LogUtil;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
public class SmStaffServiceImpl implements SmStaffService {

	@Autowired
	private SmStaffMapper smStaffMapper;
	
	@Autowired
	private SmOrganMapper organMapper;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private SmOrganService smOrganService;
	
	public boolean isCsRight(SmOperModel operModel) {
		if (operModel == null) {
			return false;
		}
		Map<String, List<String>> map = operModel.getDataRightMap();
		if (map == null) {
			return false;
		}
		if (map.containsKey("CS_CUSTOMER_SERVICE_RIGHT")) {
			List<String> csDataRight = new ArrayList<String>();
			csDataRight = map.get("CS_CUSTOMER_SERVICE_RIGHT");
			if (csDataRight!=null && csDataRight.size() >0) {
				for (String right : csDataRight) {
	                if (StringUtils.equals(right, "Y")) {
	                	return true;
	                }
                }
			}
		}
	    return false;
    }
	

	@Override
	public List<SmStaff> select(SmStaffExample example) {
		try {
			return smStaffMapper.selectByExample(example);
		} catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}
	//TODO
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int insertBean(SmStaff record) {
		try {
			record.setModifyDate(systemService.getSystemDate());
			record.setOperId(100l+"");
			record.setCreateDate(systemService.getSystemDate());
			return smStaffMapper.insert(record);
		} catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int update(SmStaff record, SmStaffExample example) {
		try {
			return smStaffMapper.updateByExampleSelective(record, example);
		} catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}
	
	@Override
	public int updateByOrgan(String orgId) {
		SmOrgan smOrgan = organMapper.selectByPrimaryKey(orgId);
		SmStaff smStaff = new SmStaff();
		smStaff.setProvinceId(smOrgan.getProvinceId());
		smStaff.setAreaId(smOrgan.getAreaId());
		smStaff.setCountyId(smOrgan.getCountyId());
		
		SmStaffExample staffExample = new SmStaffExample();
		SmStaffExample.Criteria criteria = staffExample.createCriteria();
		criteria.andOrgIdEqualTo(orgId);
		
		smStaffMapper.updateByExampleSelective(smStaff, staffExample);
		return 0;
	}

	@Override
	public int delete(SmStaffExample example) {
		try {
			return smStaffMapper.deleteByExample(example);
		} catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
	public PageList<SmStaff> queryPage(PageBounds pageBounds, SmStaff searchModel, Map<String, String> param) {
		try {
			String orgId = param.get("orgId");
			String orgRight = param.get("orgRight");
			SmStaffExample sc = new SmStaffExample();
			Criteria criteria = sc.or();
			if (!StringUtil.isBlank(searchModel.getStaffCode())) {
				criteria.andStaffCodeEqualTo(searchModel.getStaffCode());
			}
			if (!StringUtil.isBlank(searchModel.getStaffName())) {
				criteria.andStaffNameLike("%" + searchModel.getStaffName() + "%");
			}
			if (!StringUtil.isBlank(searchModel.getState())) {
				criteria.andStateEqualTo(searchModel.getState() );
			}
			SmOperModel operInfo = SessionInfo.getOperInfo();
			
			boolean isCs = isCsRight(operInfo);
			if(isCs){
				orgRight = "false";
			}
			
			if (!StringUtil.isBlank(orgId)) {
				// 根据组织编码查询出下级所有部门
				List<SmOrgan> organBeanList = smOrganService.queryTree(orgId);
				List<String> orgIdList = new ArrayList<String>();
				for (SmOrgan bean : organBeanList) {
					if (FrameConstants.SmOrgan.State.VALID.equals(bean.getState())) {
						orgIdList.add(bean.getOrgId());
					}
				}
				criteria.andOrgIdIn(orgIdList);
			} else {
				// 判断是否需要部门权限，默认为需要
				if (StringUtil.isBlank(orgRight)) {
					orgRight = "true";
				}
				// 如果需要
				if ("true".equals(orgRight)) {
					// 取操作员所在部门
					String rightOrgId = operInfo.getOrgId();
					List<SmOrgan> organBeanList = smOrganService.queryTree(rightOrgId);
					List<String> orgIdList = new ArrayList<String>();
					for (SmOrgan bean : organBeanList) {
						if (FrameConstants.SmOrgan.State.VALID.equals(bean.getState())) {
							orgIdList.add(bean.getOrgId());
						}
					}
					criteria.andOrgIdIn(orgIdList);
				}
			}
			if (!StringUtil.isBlank(searchModel.getStaffPost())) {
				criteria.andStaffPostEqualTo(searchModel.getStaffPost());
			}
			if (!StringUtil.isBlank(searchModel.getStaffType())) {
				criteria.andStaffTypeEqualTo(searchModel.getStaffType());
			}
			if (!StringUtil.isBlank(searchModel.getProvinceId())) {
				criteria.andProvinceIdEqualTo(searchModel.getProvinceId());
			}
			if (!StringUtil.isBlank(searchModel.getAreaId())) {
				criteria.andAreaIdEqualTo(searchModel.getAreaId());
			}
			if (!StringUtil.isBlank(searchModel.getCountyId())) {
				criteria.andCountyIdEqualTo(searchModel.getCountyId());
			}
			return smStaffMapper.query(sc, pageBounds);
		} catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}
	
	@Override
	public PageList<SmStaff> query(SmStaffExample  sc,PageBounds pageBounds) {
		try {
			return smStaffMapper.query(sc, pageBounds);
		} catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
	public int deleteByPrimaryKey(String staffId) {
		try {
			return smStaffMapper.deleteByPrimaryKey(staffId);
		} catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
	public SmStaff selectByPrimaryKey(String staffId) {
		try {
			return smStaffMapper.selectByPrimaryKey(staffId);
		} catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
	public int updateByPrimaryKey(SmStaff record) {
		try {
			return smStaffMapper.updateByPrimaryKey(record);
		} catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
	public int countByExample(SmStaffExample example) {
		try {
			return smStaffMapper.countByExample(example);
		} catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
	public int updateByPrimaryKeySelective(SmStaff record) {
		try {
			return smStaffMapper.updateByPrimaryKeySelective(record);
		} catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}
	@Override
    public void saveStaff(SmStaff staff) {
		String id = staff.getStaffId();
		if (null == id) {
			//检查人员编号是否重复
			SmStaffExample sc = new SmStaffExample();
			Criteria criteria = sc.or();
			criteria.andStaffCodeEqualTo(staff.getStaffCode());
			int count = smStaffMapper.countByExample(sc);
			if(count >= 1){
				throw new SystemException(StatusCodeForFrame.REPEAT_STAFF, staff.getStaffCode());
			}
			id = systemService.generateId("SM_STAFF", "STAFF_ID").toString();
			staff.setStaffId(id);
			staff.setState("Y");
			staff.setModifyDate(systemService.getSystemDate());
			smStaffMapper.insertSelective(staff);
			LogUtil.createLog(LogUtil.TARGET_STAFF, staff);
		} else {
			//检查人员编号是否重复
			SmStaffExample sc = new SmStaffExample();
			Criteria criteria = sc.or();
			criteria.andStaffCodeEqualTo(staff.getStaffCode());
			criteria.andStaffIdNotEqualTo(staff.getStaffId());
			int count = smStaffMapper.countByExample(sc);
			if(count >= 1){
				throw new SystemException(StatusCodeForFrame.REPEAT_STAFF, staff.getStaffCode());
			}
			SmStaff old = smStaffMapper.selectByPrimaryKey(id);
			smStaffMapper.updateByPrimaryKeySelective(staff);
			SmStaff news = smStaffMapper.selectByPrimaryKey(id);
			LogUtil.modifyLog(LogUtil.TARGET_STAFF, old, news);
		}
    }
	@Override
    public void deleteStaff(String staffId, String status) {
		SmStaff news = smStaffMapper.selectByPrimaryKey(staffId);
		SmStaff old = (SmStaff)CopyUtil.copy(news);
		news.setState(status);
		smStaffMapper.updateByPrimaryKey(news);
		LogUtil.modifyLog(LogUtil.TARGET_STAFF, old, news);
    }
	@Override
	public List<SmStaff> getStaffListByOrgId(String orgId) {
		List<SmOrgan> organBeanList = smOrganService.queryTree(orgId);
		List<String> orgIdList = new ArrayList<String>();
		for (SmOrgan bean : organBeanList) {
			if (FrameConstants.SmOrgan.State.VALID.equals(bean.getState())) {
				orgIdList.add(bean.getOrgId());
			}
		}
		SmStaffExample example = new SmStaffExample();
		Criteria criteria = example.or();
		criteria.andOrgIdIn(orgIdList);
		criteria.andStateEqualTo(FrameConstants.SmStaff.StaffStatus.NORMAL);
		List<SmStaff> list = smStaffMapper.selectByExample(example);
		return list;
	}

}
