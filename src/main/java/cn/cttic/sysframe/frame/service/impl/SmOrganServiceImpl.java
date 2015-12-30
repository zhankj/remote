package cn.cttic.sysframe.frame.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.common.model.TreeModel;
import cn.cttic.sysframe.common.util.StringUtil;
import cn.cttic.sysframe.frame.common.constants.FrameConstants;
import cn.cttic.sysframe.frame.dao.SmOrganMapper;
import cn.cttic.sysframe.frame.domain.SmOper;
import cn.cttic.sysframe.frame.domain.SmOperExample;
import cn.cttic.sysframe.frame.domain.SmOrgan;
import cn.cttic.sysframe.frame.domain.SmOrganExample;
import cn.cttic.sysframe.frame.domain.SmStaff;
import cn.cttic.sysframe.frame.domain.SmStaffExample;
import cn.cttic.sysframe.frame.service.SmOperService;
import cn.cttic.sysframe.frame.service.SmOrganService;
import cn.cttic.sysframe.frame.service.SmStaffService;

@Service
public class SmOrganServiceImpl implements SmOrganService {

	@Autowired
	private SmOrganMapper smOrganMapper;

	@Autowired
	private SmStaffService smStaffService;

	@Autowired
	private SmOperService smOperService;

	private String _ORDER = "org_level, org_order_no";

	@Override
	@Cacheable(value = "smOrganCache", key = "'FLAT'")
	public List<SmOrgan> selectByExample() {
		try {
			SmOrganExample smOrganCriteria = new SmOrganExample();
			smOrganCriteria.setOrderByClause(_ORDER);
			return smOrganMapper.selectByExample(smOrganCriteria);
		}
		catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	public List<SmOrgan> getBeans(SmOrganExample smOrganCriteria) {
		try {
			return smOrganMapper.selectByExample(smOrganCriteria);
		}
		catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
	@Cacheable(value = "smOrganCache", key = "#state+#chain")
	public TreeModel getTree(String state, boolean chain) {
		try {
			/* 0、创建根节点 */
			TreeModel rootNode = new TreeModel();
			rootNode.setId("root");
			rootNode.setText("部门根节点");
			List<TreeModel> nodeChildrens = new ArrayList<TreeModel>();
			rootNode.setChildren(nodeChildrens);
			/* 1、得到扁平结果结构功能集 */
			SmOrganExample smOrganCriteria = new SmOrganExample();
			SmOrganExample.Criteria criteria = smOrganCriteria.createCriteria();
			if (!StringUtil.isBlank(state)) {
				criteria.andStateEqualTo(state);
			}
			smOrganCriteria.setOrderByClause(_ORDER);
			List<SmOrgan> orgList = smOrganMapper.selectByExample(smOrganCriteria);
			/* 2、构建树 */
			// 2.1、将扁平结果转换为树节点
			List<TreeModel> treeModelList = new ArrayList<TreeModel>();
			// 3、将List转换为Map，提高查找效率
			Map<String, TreeModel> treeModelMap = new HashMap<String, TreeModel>();
			treeModelMap.put("root", rootNode);
			for (SmOrgan bean : orgList) {
				TreeModel treeModel = new TreeModel();
				treeModel.setId(bean.getOrgId());
				if (FrameConstants.SmOrgan.State.VALID.equals(bean.getState())) {
					treeModel.setText(bean.getOrgName());
				} else if (FrameConstants.SmOrgan.State.INVALID.equals(bean.getState())) {
					treeModel.setText("<S>" + bean.getOrgName() + "</S>");
				}
				treeModel.setParentId(bean.getOrgPid());
				List<TreeModel> treeModelChildrens = new ArrayList<TreeModel>();
				treeModel.setChildren(treeModelChildrens);
				treeModelList.add(treeModel);
				treeModelMap.put(StringUtil.toString(bean.getOrgId()), treeModel);
			}
			// 4、使用迭代构建每棵树
			if (true == chain) {
				for (TreeModel treeModel : treeModelList) {
					if ("-1".equals(StringUtil.toString(treeModel.getParentId()))) {
						rootNode.getChildren().add(treeModel);
						treeModelMap.put(treeModel.getId(), treeModel);
					} else {
						if (treeModelMap.containsKey(treeModel.getParentId())) {
							TreeModel parentNode = (TreeModel) treeModelMap.get(treeModel.getParentId());
							parentNode.getChildren().add(treeModel);
							treeModelMap.put(treeModel.getId(), treeModel);
						} else {
							treeModelMap.put(treeModel.getId(), treeModel);
						}
					}
				}
			} else if (false == chain) {
				for (TreeModel treeModel : treeModelList) {
					if ("-1".equals(StringUtil.toString(treeModel.getParentId()))) {
						rootNode.getChildren().add(treeModel);
						treeModelMap.put(treeModel.getId(), treeModel);
					}
				}
				for (TreeModel parentTreeModel : rootNode.getChildren()) {
					for (TreeModel treeModel : treeModelList) {
						if (treeModel.getParentId().equals(parentTreeModel.getId())) {
							parentTreeModel.getChildren().add(treeModel);
						}
					}
				}
			}
			return rootNode;
		}
		catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}
	
	@Override
	@Cacheable(value = "smOrganCache", key = "#state+#rootOrgId")
	public TreeModel getRightTree(String state, String rootOrgId) {
		try {
			/* 0、创建根节点 */
			TreeModel rootNode = new TreeModel();
			rootNode.setId("root");
			rootNode.setText("菜单根节点");
			List<TreeModel> nodeChildrens = new ArrayList<TreeModel>();
			rootNode.setChildren(nodeChildrens);
			/* 1、得到扁平结果结构功能集 */
			List<SmOrgan> orgList = smOrganMapper.queryTree(rootOrgId);
			/* 2、构建树 */
			// 2.1、将扁平结果转换为树节点
			List<TreeModel> treeModelList = new ArrayList<TreeModel>();
			// 3、将List转换为Map，提高查找效率
			Map<String, TreeModel> treeModelMap = new HashMap<String, TreeModel>();
			treeModelMap.put("root", rootNode);
			for (SmOrgan bean : orgList) {
				if (FrameConstants.SmOrgan.State.VALID.equals(state) && FrameConstants.SmOrgan.State.INVALID.equals(bean.getState())) {
					continue;
				}
				TreeModel treeModel = new TreeModel();
				treeModel.setId(bean.getOrgId());
				if (FrameConstants.SmOrgan.State.VALID.equals(bean.getState())) {
					treeModel.setText(bean.getOrgName());
				} else if (FrameConstants.SmOrgan.State.INVALID.equals(bean.getState())) {
					treeModel.setText("<S>" + bean.getOrgName() + "</S>");
				}
				treeModel.setParentId(bean.getOrgPid());
				List<TreeModel> treeModelChildrens = new ArrayList<TreeModel>();
				treeModel.setChildren(treeModelChildrens);
				treeModelList.add(treeModel);
				treeModelMap.put(StringUtil.toString(bean.getOrgId()), treeModel);
			}
			// 4、使用迭代构建每棵树
			for (TreeModel treeModel : treeModelList) {
				if (rootOrgId.equals(StringUtil.toString(treeModel.getId()))) {
					rootNode.getChildren().add(treeModel);
					treeModelMap.put(treeModel.getId(), treeModel);
				} else {
					if (treeModelMap.containsKey(treeModel.getParentId())) {
						TreeModel parentNode = (TreeModel) treeModelMap.get(treeModel.getParentId());
						parentNode.getChildren().add(treeModel);
						treeModelMap.put(treeModel.getId(), treeModel);
					} else {
						treeModelMap.put(treeModel.getId(), treeModel);
					}
				}
			}
			return rootNode;
		}
		catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}
	
	public TreeModel getRightTree(String rootOrgId, String orgPtype, String orgType, boolean chain) {
		try {
			/* 0、创建根节点 */
			TreeModel rootNode = new TreeModel();
			rootNode.setId("root");
			rootNode.setText("菜单根节点");
			List<TreeModel> nodeChildrens = new ArrayList<TreeModel>();
			rootNode.setChildren(nodeChildrens);
			/* 1、得到扁平结果结构功能集 */
			List<SmOrgan> orgList = smOrganMapper.queryTree(rootOrgId);
			/* 2、构建树 */
			// 2.1、将扁平结果转换为树节点
			List<TreeModel> treeModelList = new ArrayList<TreeModel>();
			// 3、将List转换为Map，提高查找效率
			Map<String, TreeModel> treeModelMap = new HashMap<String, TreeModel>();
			treeModelMap.put("root", rootNode);
			for (SmOrgan bean : orgList) {
				if (FrameConstants.SmOrgan.State.VALID.equals(bean.getState())) {
					if (!StringUtil.isBlank(orgPtype) && !orgPtype.equals(bean.getOrgPtype())) {
						continue;
					}
					if (!StringUtil.isBlank(orgType) && !orgType.equals(bean.getOrgType())) {
						continue;
					}
					TreeModel treeModel = new TreeModel();
					treeModel.setId(bean.getOrgId());
					treeModel.setText(bean.getOrgName());
					treeModel.setParentId(bean.getOrgPid());
					List<TreeModel> treeModelChildrens = new ArrayList<TreeModel>();
					treeModel.setChildren(treeModelChildrens);
					treeModelList.add(treeModel);
					treeModelMap.put(bean.getOrgId(), treeModel);
				}
			}
			// 4、使用迭代构建每棵树
			if (true == chain) {
				for (TreeModel treeModel : treeModelList) {
					if (!treeModelMap.containsKey(treeModel.getParentId())) {
						rootNode.getChildren().add(treeModel);
					} else {
						TreeModel parentNode = (TreeModel) treeModelMap.get(treeModel.getParentId());
						parentNode.getChildren().add(treeModel);
					}
				}
			} else if (false == chain) {
				for (TreeModel treeModel : treeModelList) {
					if (!treeModelMap.containsKey(treeModel.getParentId())) {
						rootNode.getChildren().add(treeModel);
					}
				}
				for (TreeModel parentTreeModel : rootNode.getChildren()) {
					for (TreeModel treeModel : treeModelList) {
						if (treeModel.getParentId().equals(parentTreeModel.getId())) {
							parentTreeModel.getChildren().add(treeModel);
						}
					}
				}
			}
			return rootNode;
		}
		catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
	public SmOrgan selectByPrimaryKey(String orgId) {
		try {
			return smOrganMapper.selectByPrimaryKey(orgId);
		}
		catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
	@CacheEvict(value = "smOrganCache", allEntries = true)
	@Transactional(rollbackFor=Exception.class)
	public int insert(SmOrgan record) {
		try {
			return smOrganMapper.insert(record);
		}
		catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
	@CacheEvict(value = "smOrganCache", allEntries = true)
	@Transactional(rollbackFor=Exception.class)
	public int updateByPrimaryKeySelective(SmOrgan record) {
		String orgStatus = record.getState();
		// 如果当前组织机构状态为无效，则其下挂所有组织机构都需要不可用，并组织机构下的人员和工号都失效
		if (FrameConstants.SmOrgan.State.INVALID.equals(orgStatus)) {
			List<SmOrgan> subOrganList = smOrganMapper.queryTree(record.getOrgId());
			if (null != subOrganList && subOrganList.size() > 0) {
				for (SmOrgan subOrganBean : subOrganList) {
					if (!record.getOrgId().equals(subOrganBean.getOrgId()) && FrameConstants.SmOrgan.State.VALID.equals(subOrganBean.getState())) {
						throw new SystemException(StatusCodeForFrame.USER_DEFINDES_ERROR, "该部门存在有效的下级部门，不允许做失效操作");
					}
				}
			}
			// 查询部门下的人员
			SmStaffExample smStaffExample = new SmStaffExample();
			SmStaffExample.Criteria smStaffExampleCriteria = smStaffExample.createCriteria();
			smStaffExampleCriteria.andOrgIdEqualTo(record.getOrgId());
			List<SmStaff> smStaffList = smStaffService.select(smStaffExample);
			if (null != smStaffList && smStaffList.size() > 0) {
				for (SmStaff staffBean : smStaffList) {
					if (FrameConstants.SmStaff.StaffStatus.NORMAL.equals(staffBean.getState())) {
						throw new SystemException(StatusCodeForFrame.USER_DEFINDES_ERROR, "该部门存在有效的人员，不允许做失效操作");
					}
				}
			}
		}
		// 更新部门
		smOrganMapper.updateByPrimaryKeySelective(record);
		// 更新该部门下的人员信息
		smStaffService.updateByOrgan(record.getOrgId());
		// 更新该部门下的工号信息
		smOperService.updateByOrgan(record.getOrgId());
		return 0;
	}
	
	public int updateByPrimaryKeySelective_old(SmOrgan record) {
		try {
			String orgStatus = record.getState();
			// 如果当前组织机构状态为无效，则其下挂所有组织机构都需要不可用，并组织机构下的人员和工号都失效
			if (FrameConstants.SmOrgan.State.INVALID.equals(orgStatus)) {
				List<SmOrgan> subOrganList = smOrganMapper.queryTree(record.getOrgId());
				if (null != subOrganList && subOrganList.size() > 0) {
					for (SmOrgan subOrganBean : subOrganList) {
						// 失效子部门
						subOrganBean.setState(FrameConstants.SmOrgan.State.INVALID);
						smOrganMapper.updateByPrimaryKeySelective(subOrganBean);
						// 查询子部门下的人员
						SmStaffExample smStaffExample = new SmStaffExample();
						SmStaffExample.Criteria smStaffExampleCriteria = smStaffExample.createCriteria();
						smStaffExampleCriteria.andOrgIdEqualTo(subOrganBean.getOrgId());
						List<SmStaff> smStaffList = smStaffService.select(smStaffExample);
						if (null != smStaffList && smStaffList.size() > 0) {
							for (SmStaff staffBean : smStaffList) {
								// 失效人员
								staffBean.setState(FrameConstants.SmStaff.StaffStatus.CANCEL);
								smStaffService.updateByPrimaryKey(staffBean);
								// 查询人员下工号
								SmOperExample smOperExample = new SmOperExample();
								SmOperExample.Criteria smOperExampleCriteria = smOperExample.createCriteria();
								smOperExampleCriteria.andStaffIdEqualTo(staffBean.getStaffId());
								List<SmOper> smOperList = smOperService.getBeans(smOperExample);
								if (null != smOperList && smOperList.size() > 0) {
									for (SmOper operBean : smOperList) {
										// 失效工号
										operBean.setState(FrameConstants.SmOper.State.CANCEL);
										smOperService.updateByPrimaryKey(operBean);
									}
								}
							}
						}
					}
				}
			}
			return smOrganMapper.updateByPrimaryKeySelective(record);
		}
		catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
	public List<SmOrgan> queryTree(String orgId) {
		try {
			return smOrganMapper.queryTree(orgId);
		}
		catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

}
