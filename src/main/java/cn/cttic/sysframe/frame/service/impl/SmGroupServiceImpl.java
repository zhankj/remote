package cn.cttic.sysframe.frame.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.common.util.SessionInfo;
import cn.cttic.sysframe.frame.dao.SmGroup2OperMapper;
import cn.cttic.sysframe.frame.dao.SmGroupMapper;
import cn.cttic.sysframe.frame.domain.SmGroup;
import cn.cttic.sysframe.frame.domain.SmGroup2Oper;
import cn.cttic.sysframe.frame.domain.SmGroup2OperExample;
import cn.cttic.sysframe.frame.domain.SmGroup2OperExample.Criteria;
import cn.cttic.sysframe.frame.domain.SmGroup2OperKey;
import cn.cttic.sysframe.frame.domain.SmGroupExample;
import cn.cttic.sysframe.frame.model.SmOperModel;
import cn.cttic.sysframe.frame.service.SmGroupService;
import cn.cttic.sysframe.frame.service.SystemService;
import cn.cttic.sysframe.frame.util.LogUtil;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
public class SmGroupServiceImpl implements SmGroupService {

	@Autowired
	private SmGroupMapper smGroupMapper;

	@Autowired
	private SmGroup2OperMapper smGroup2OperMapper;
	
	@Autowired
	private SystemService systemService;

	@Override
	public PageList<Map<String,Object>> queryPage(Map<String,Object> map, PageBounds pageBounds) {
		try {
			return smGroupMapper.queryPage(map,pageBounds);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
	public SmGroup selectByPrimaryKey(Long groupId) {
		try {
			return smGroupMapper.selectByPrimaryKey(groupId);
		}
		catch (Exception ex) {
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
	public int insertSelective(SmGroup record) {
		try {
			return smGroupMapper.insertSelective(record);
		}
		catch (Exception ex) {
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
	public int updateByExampleSelective(SmGroup record, SmGroupExample example) {
		try {
			return smGroupMapper.updateByExampleSelective(record, example);
		}
		catch (Exception ex) {
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
	public int updateByPrimaryKeySelective(SmGroup record) {
		try {
			return smGroupMapper.updateByPrimaryKeySelective(record);
		}
		catch (Exception ex) {
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
	public void addMember(String operIds, String groupId, String isAdd) {
		String[] arr = operIds.split(",");
		try {
			if ("Y".equals(isAdd)) {
				for (String operId : arr) {
					SmGroup2Oper g2p = new SmGroup2Oper();
					g2p.setGroupId(Long.parseLong(groupId));
					g2p.setOperId(operId);
					g2p.setState("Y");
					g2p.setLeaderState("N");
					smGroup2OperMapper.insertSelective(g2p);
				}
			} else {
				for (String operId : arr) {
					SmGroup2OperKey skey = new SmGroup2OperKey();
					skey.setGroupId(Long.parseLong(groupId));
					skey.setOperId(operId);
					smGroup2OperMapper.deleteByPrimaryKey(skey);
				}
			}

		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}

	}

	@Override
    public void updateLeader(String operId, String groupId,String leaderState) {
		SmGroup2Oper s2 = new SmGroup2Oper();
		s2.setGroupId(Long.parseLong(groupId));
		s2.setOperId(operId);
		s2.setLeaderState(leaderState);
		smGroup2OperMapper.updateByPrimaryKeySelective(s2);
    }

	@Override
    public void deleteGroup(String groupId) {
		SmGroup old = smGroupMapper.selectByPrimaryKey(Long.parseLong(groupId));
		smGroupMapper.deleteByPrimaryKey(Long.parseLong(groupId));
		SmGroup2OperExample example = new SmGroup2OperExample();
		Criteria cr = example.createCriteria();
		cr.andGroupIdEqualTo(Long.parseLong(groupId));
		smGroup2OperMapper.deleteByExample(example);
		LogUtil.deleteLog(LogUtil.TARGET_GROUP, old);
    }
	
	@Override
    public List<SmGroup> queryGroupByOpIdAndGType(String operId, String groupType) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("groupType", groupType);
			map.put("operId", operId);
			return smGroupMapper.queryGroup(map);
		} catch (Exception ex) {
			throw new SystemException(ex,
					StatusCodeForFrame.DB_OPERATION_ERROR);
		}
    }

	@Override
    public void saveGroup(SmGroup smGroup) {
		Long id = smGroup.getGroupId();
		SmOperModel model = SessionInfo.getOperInfo();
		if (null == id) {
			Long groupId = systemService.generateId("SM_GROUP", "GROUP_ID");
			smGroup.setGroupId(groupId);
			smGroup.setCreateDate(systemService.getSystemDate());
			smGroup.setUpdateDate(systemService.getSystemDate());
			smGroup.setState("Y");
			smGroup.setOperId(model.getOperId());
			smGroupMapper.insertSelective(smGroup);
			LogUtil.createLog(LogUtil.TARGET_GROUP, smGroup);
		} else {
			SmGroup old = smGroupMapper.selectByPrimaryKey(id);
			smGroup.setOperId(model.getOperId());
			smGroupMapper.updateByPrimaryKeySelective(smGroup);
			SmGroup news = smGroupMapper.selectByPrimaryKey(id);
			LogUtil.modifyLog(LogUtil.TARGET_GROUP, old, news);
		}
    }

}
