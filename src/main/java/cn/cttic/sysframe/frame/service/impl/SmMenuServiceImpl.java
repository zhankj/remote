package cn.cttic.sysframe.frame.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.common.model.EasyTreeModel;
import cn.cttic.sysframe.common.model.EasyTreeModel.NodeState;
import cn.cttic.sysframe.common.model.TreeModel;
import cn.cttic.sysframe.common.util.SessionInfo;
import cn.cttic.sysframe.common.util.StringUtil;
import cn.cttic.sysframe.frame.common.constants.FrameConstants;
import cn.cttic.sysframe.frame.dao.SmFuncSetMapper;
import cn.cttic.sysframe.frame.dao.SmMenuMapper;
import cn.cttic.sysframe.frame.domain.SmFuncSet;
import cn.cttic.sysframe.frame.domain.SmMenu;
import cn.cttic.sysframe.frame.domain.SmMenuExample;
import cn.cttic.sysframe.frame.model.SmOperModel;
import cn.cttic.sysframe.frame.service.SmMenuService;
import cn.cttic.sysframe.frame.service.SmSysDictService;

import com.google.common.collect.Maps;

@Service
public class SmMenuServiceImpl implements SmMenuService {

	@Autowired
	private SmMenuMapper mapper;
	
	@Autowired
	private SmFuncSetMapper fsMapper;
	
	@Autowired
	private SmSysDictService smSysDictService;

	private String _ORDER = "menu_order, menu_id";

	@Override
	@Cacheable(value = "smMenuCache", key = "'FLAT'")
	public List<SmMenu> selectByExample() {
		try {
			SmMenuExample smMenuCriteria = new SmMenuExample();
			smMenuCriteria.setOrderByClause(_ORDER);
			return mapper.selectByExample(smMenuCriteria);
		} catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
//	@Cacheable(value = "smMenuCache", key = "#state")
	public TreeModel getTree(String state) {
		/* 0、创建根节点 */
		TreeModel rootNode = new TreeModel();
		rootNode.setId("root");
		rootNode.setText("菜单根节点");
		List<TreeModel> nodeChildrens = new ArrayList<TreeModel>();
		rootNode.setChildren(nodeChildrens);
		/* 1、得到扁平结果结构功能集 */
		SmMenuExample smMenuCriteria = new SmMenuExample();
		SmMenuExample.Criteria criteria = smMenuCriteria.createCriteria();
		if (!StringUtil.isBlank(state)) {
			criteria.andStateEqualTo(state);
		}
		/* 1.1、增加操作员区域信息进行过滤 */
		SmOperModel operInfo = SessionInfo.getOperInfo();
		String province = operInfo.getProvinceId();
		String area = operInfo.getAreaId();
		String county = operInfo.getCountyId();
		if (!StringUtil.isBlank(province)) {
			if (FrameConstants.SmRegion.ZB.equals(province)) {
				if (FrameConstants.SmRegion.PROVINCE_CENTER.equals(area)) {
					criteria.andProvinceIdEqualTo(province);
				}
			} else {
				criteria.andProvinceIdEqualTo(province);
			}
		}
		if (!StringUtil.isBlank(area)) {
			criteria.andAreaIdEqualTo(area);
		}
		if (!StringUtil.isBlank(county)) {
			criteria.andCountyIdEqualTo(county);
		}

		smMenuCriteria.setOrderByClause(_ORDER);
		List<SmMenu> menuList = new ArrayList<SmMenu>();
		try {
			menuList = mapper.selectByExample(smMenuCriteria);
		} catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
		/* 2、构建树 */
		// 2.1、将扁平结果转换为树节点
		List<TreeModel> treeModelList = new ArrayList<TreeModel>();
		// 3、将List转换为Map，提高查找效率
		Map<String, TreeModel> treeModelMap = new HashMap<String, TreeModel>();
		treeModelMap.put("root", rootNode);
		for (SmMenu bean : menuList) {
			TreeModel treeModel = new TreeModel();
			treeModel.setId(StringUtil.toString(bean.getMenuId()));
			treeModel.setText(bean.getMenuName());
			if ("0".equals(bean.getState())) {
				treeModel.setText(bean.getMenuName());
			} else if ("1".equals(bean.getState())) {
				treeModel.setText("<S>" + bean.getMenuName() + "</S>");
			}
			treeModel.setParentId(StringUtil.toString(bean.getMenuPid()));
			List<TreeModel> treeModelChildrens = new ArrayList<TreeModel>();
			treeModel.setChildren(treeModelChildrens);
			treeModelList.add(treeModel);
			treeModelMap.put(StringUtil.toString(bean.getMenuId()), treeModel);
		}
		// 4、使用迭代构建每棵树
		for (TreeModel treeModel : treeModelList) {
			if (!treeModelMap.containsKey(treeModel.getParentId())) {
				rootNode.getChildren().add(treeModel);
				treeModelMap.put(treeModel.getId(), treeModel);
			} else {
				if (treeModelMap.containsKey(treeModel.getParentId())) {
					TreeModel parentNode = (TreeModel) treeModelMap
							.get(treeModel.getParentId());
					parentNode.getChildren().add(treeModel);
					treeModelMap.put(treeModel.getId(), treeModel);
				} else {
					treeModelMap.put(treeModel.getId(), treeModel);
				}
			}
		}
		return rootNode;
	}
	@Override
//	@Cacheable(value = "smMenuCache", key = "#menuStatus")
	public TreeModel getTreeByFsId(String fsId) {
		
		Map<String, Object> queryMap=new HashMap<String, Object>();
		
		TreeModel rootNode = new TreeModel();
		rootNode.setId("root");
		rootNode.setText("菜单根节点");
		List<TreeModel> nodeChildrens = new ArrayList<TreeModel>();
		rootNode.setChildren(nodeChildrens);
		
		if (!StringUtil.isBlank(fsId)) {
			SmFuncSet smFuncSet=fsMapper.selectByPrimaryKey(fsId);
			if(smFuncSet!=null&&FrameConstants.SmFsSet.State.NORMAL.equals(smFuncSet.getState())){
				queryMap.put("fsId", fsId);
				queryMap.put("provinceId", smFuncSet.getProvinceId());
				queryMap.put("areaId", smFuncSet.getAreaId());
				queryMap.put("countyId", smFuncSet.getCountyId());
				queryMap.put("fsLevel", smFuncSet.getFsLevel());
				
				 
				/* 0、创建根节点 */
				List<SmMenu> menuList = new ArrayList<SmMenu>();
				try {
					menuList = mapper.selectMenuByFsId(queryMap);
				} catch (Exception ex) {
					throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
				}
				/* 2、构建树 */
				// 2.1、将扁平结果转换为树节点
				List<TreeModel> treeModelList = new ArrayList<TreeModel>();
				// 3、将List转换为Map，提高查找效率
				Map<String, TreeModel> treeModelMap = new HashMap<String, TreeModel>();
				treeModelMap.put("root", rootNode);
				for (SmMenu bean : menuList) {
					TreeModel treeModel = new TreeModel();
					treeModel.setId(StringUtil.toString(bean.getMenuId()));
					treeModel.setText(bean.getMenuName());
					if ("0".equals(bean.getState())) {
						treeModel.setText(bean.getMenuName());
					} else if ("1".equals(bean.getState())) {
						treeModel.setText("<S>" + bean.getMenuName() + "</S>");
					}
					treeModel.setParentId(StringUtil.toString(bean.getMenuPid()));
					List<TreeModel> treeModelChildrens = new ArrayList<TreeModel>();
					treeModel.setChildren(treeModelChildrens);
					treeModelList.add(treeModel);
					treeModelMap.put(StringUtil.toString(bean.getMenuId()), treeModel);
				}
				// 4、使用迭代构建每棵树
				for (TreeModel treeModel : treeModelList) {
					if (!treeModelMap.containsKey(treeModel.getParentId())) {
						rootNode.getChildren().add(treeModel);
						treeModelMap.put(treeModel.getId(), treeModel);
					} else {
						if (treeModelMap.containsKey(treeModel.getParentId())) {
							TreeModel parentNode = (TreeModel) treeModelMap
									.get(treeModel.getParentId());
							parentNode.getChildren().add(treeModel);
							treeModelMap.put(treeModel.getId(), treeModel);
						} else {
							treeModelMap.put(treeModel.getId(), treeModel);
						}
					}
				}
			}
		}
		return rootNode;
	}

	@Override
	public List<EasyTreeModel> getNavigationTree(String menuId) {
		/* 0、得到扁平结果结构功能集 */
		SmMenuExample smMenuCriteria = new SmMenuExample();
		smMenuCriteria.setOrderByClause(_ORDER);
		SmMenuExample.Criteria criteria = smMenuCriteria.createCriteria();
		criteria.andStateEqualTo("0");
		criteria.andMenuPidEqualTo(menuId);
		List<SmMenu> menuList = mapper.selectByExample(smMenuCriteria);

		/* 1、创建根节点 */
		EasyTreeModel rootNode = new EasyTreeModel();
		rootNode.setId("0");
		rootNode.setText("菜单根节点");
		/* 2、构建树 */
		// 2.1、将扁平结果转换为树节点
		List<EasyTreeModel> treeModelList = new ArrayList<EasyTreeModel>();
		// 3、将List转换为Map，提高查找效率
		Map<String, EasyTreeModel> treeModelMap = new HashMap<String, EasyTreeModel>();
		treeModelMap.put("root", rootNode);
		for (SmMenu bean : menuList) {
			EasyTreeModel treeModel = new EasyTreeModel();
			treeModel.setId(bean.getMenuId());
			treeModel.setText(bean.getMenuName());
//			treeModel.setIconCls("icon-mini-add");
			treeModel.setParentId(bean.getMenuPid());
			treeModel.addAttribute("url", bean.getMenuUrl());
			treeModel.addAttribute("navigation", bean.getMenuName());
			treeModelList.add(treeModel);
			treeModelMap.put(StringUtil.toString(bean.getMenuId()), treeModel);
		}
		// 4、使用迭代构建每棵树
		for (EasyTreeModel treeModel : treeModelList) {
			if ("-1".equals(StringUtil.toString(treeModel.getParentId()))) {
				treeModel.setState(NodeState.closed);
				rootNode.addChildren(treeModel);
				treeModelMap.put(StringUtil.toString(treeModel.getId()),
						treeModel);
			} else {
				if (treeModelMap.containsKey(StringUtil.toString(treeModel
						.getParentId()))) {
					EasyTreeModel parentNode = (EasyTreeModel) treeModelMap
							.get(StringUtil.toString(treeModel.getParentId()));
					treeModel.addAttribute("navigation", parentNode
							.getAttributes().get("navigation")
							+ "->"
							+ treeModel.getAttributes().get("navigation"));
					parentNode.addChildren(treeModel);
					treeModelMap.put(StringUtil.toString(treeModel.getId()),
							treeModel);
				} else {
					treeModelMap.put(StringUtil.toString(treeModel.getId()),
							treeModel);
				}
			}
		}
		return rootNode.getChildren();
	}
	
	@Override
	public Map<String, EasyTreeModel> getSecondaryMenuByOperIdAndMenuParentId(String operId, String menuParentId) {
		
		 List<EasyTreeModel> all = (List<EasyTreeModel>) SessionInfo.getSessionInfo(SessionInfo.SESSION_SYSTEM_ID, "MENU_TREE");
		 if(all ==null ){
			 all = this.getNavigationTreeByOperId(operId);
			 SessionInfo.setSessionInfo(SessionInfo.SESSION_SYSTEM_ID, "MENU_TREE", all);
		 }	 
		 for (EasyTreeModel menu : all) {
	        if (menu.getId().equals(menuParentId)) {
	        	Map<String, EasyTreeModel> result = Maps.newLinkedHashMap();
	        	for (EasyTreeModel item : menu.getChildren()) {
	                result.put(item.getId(), item);
                }
	        	return result;
	        }
        }
		throw new SystemException(StatusCodeForFrame.NONEXISTENT_MENU, menuParentId);
	}
	
	@Override
	public List<EasyTreeModel> getNavigationTreeByOperId(String Oper_id) {
		
		/* 0、得到扁平结果结构功能集 */
		List<SmMenu> menuList = mapper.selectMenuByOperId(Oper_id);
		//菜单名称加载国际化语言
		for (SmMenu smMenu : menuList) {
	        String paramDesc = smSysDictService.getSmSysDict("SM_MENU.MENU_NAME", smMenu.getMenuId().toString()).getParamDesc();
	        if (paramDesc != null) {
	        	smMenu.setMenuName(paramDesc);
	        }
        }

		/* 1、创建根节点 */
		EasyTreeModel rootNode = new EasyTreeModel();
		rootNode.setId("0");
		rootNode.setText("菜单根节点");
		/* 2、构建树 */
		// 2.1、将扁平结果转换为树节点
		List<EasyTreeModel> treeModelList = new ArrayList<EasyTreeModel>();
		// 3、将List转换为Map，提高查找效率
		Map<String, EasyTreeModel> treeModelMap = new HashMap<String, EasyTreeModel>();
		treeModelMap.put("root", rootNode);
		for (SmMenu bean : menuList) {
			EasyTreeModel treeModel = new EasyTreeModel();
			treeModel.setId(bean.getMenuId());
			treeModel.setText(bean.getMenuName());
//			treeModel.setIconCls("icon-mini-add");
			treeModel.setIconCls(bean.getMenuPic());
			treeModel.setParentId(bean.getMenuPid());
			treeModel.addAttribute("url", bean.getMenuUrl());
			treeModel.addAttribute("navigation", bean.getMenuName());
			treeModelList.add(treeModel);
			treeModelMap.put(StringUtil.toString(bean.getMenuId()), treeModel);
		}
		// 4、使用迭代构建每棵树
		for (EasyTreeModel treeModel : treeModelList) {
			if ("-1".equals(StringUtil.toString(treeModel.getParentId()))) {
				treeModel.setState(NodeState.closed);
				rootNode.addChildren(treeModel);
				treeModelMap.put(StringUtil.toString(treeModel.getId()),
						treeModel);
			} else {
				if (treeModelMap.containsKey(StringUtil.toString(treeModel
						.getParentId()))) {
					EasyTreeModel parentNode = (EasyTreeModel) treeModelMap
							.get(StringUtil.toString(treeModel.getParentId()));
					treeModel.addAttribute("navigation", parentNode
							.getAttributes().get("navigation")
							+ "->"
							+ treeModel.getAttributes().get("navigation"));
					parentNode.addChildren(treeModel);
					treeModelMap.put(StringUtil.toString(treeModel.getId()),
							treeModel);
				} else {
					treeModelMap.put(StringUtil.toString(treeModel.getId()),
							treeModel);
				}
			}
		}
		return rootNode.getChildren();
	}

	@Override
	public List<EasyTreeModel> getMenuTree(String menuId) {
		List<SmMenu> menuList = mapper.queryTree(menuId);
		/* 1、创建根节点 */
		EasyTreeModel rootNode = new EasyTreeModel();
		/* 2、构建树 */
		// 2.1、将扁平结果转换为树节点
		List<EasyTreeModel> treeModelList = new ArrayList<EasyTreeModel>();
		// 3、将List转换为Map，提高查找效率
		Map<String, EasyTreeModel> treeModelMap = new HashMap<String, EasyTreeModel>();
		for (SmMenu bean : menuList) {
			EasyTreeModel treeModel = new EasyTreeModel();
			treeModel.setId(bean.getMenuId());
			treeModel.setText(bean.getMenuName());
//			treeModel.setIconCls("icon-mini-add");
			treeModel.setParentId(bean.getMenuPid());
			treeModel.addAttribute("url", bean.getMenuUrl());
			treeModel.addAttribute("navigation", bean.getMenuName());
			treeModelList.add(treeModel);
			treeModelMap.put(StringUtil.toString(bean.getMenuId()), treeModel);
		}
		// 4、使用迭代构建每棵树
		for (EasyTreeModel treeModel : treeModelList) {
			if (menuId.equals(treeModel.getId())) {
				rootNode = treeModel;
				treeModelMap.put(StringUtil.toString(treeModel.getId()),
						treeModel);
			} else {
				if (treeModelMap.containsKey(StringUtil.toString(treeModel
						.getParentId()))) {
					EasyTreeModel parentNode = (EasyTreeModel) treeModelMap
							.get(StringUtil.toString(treeModel.getParentId()));
					treeModel.addAttribute("navigation", parentNode
							.getAttributes().get("navigation")
							+ "->"
							+ treeModel.getAttributes().get("navigation"));
					parentNode.addChildren(treeModel);
					treeModelMap.put(StringUtil.toString(treeModel.getId()),
							treeModel);
				} else {
					treeModelMap.put(StringUtil.toString(treeModel.getId()),
							treeModel);
				}
			}
		}
		return rootNode.getChildren();
	}

	@Override
	public SmMenu selectByPrimaryKey(String menuId) {
		try {
			return mapper.selectByPrimaryKey(menuId);
		} catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
	@CacheEvict(value = "smMenuCache", allEntries = true)
	public int insert(SmMenu record) {
		try {
			return mapper.insert(record);
		} catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
	@CacheEvict(value = "smMenuCache", allEntries = true)
	public int updateByPrimaryKeySelective(SmMenu record) {
		try {
			String menuStatus = record.getState();
			// 如果当前菜单状态为不可用，则其下挂所有菜单都需要不可用
			if (FrameConstants.SmMenu.State.UNUSABLE.equals(menuStatus)) {
				List<SmMenu> subMenuList = mapper.queryTree(record.getMenuId());
				if (null != subMenuList && subMenuList.size() > 0) {
					for (SmMenu subMenuBean : subMenuList) {
						subMenuBean
								.setState(FrameConstants.SmMenu.State.UNUSABLE);
						mapper.updateByPrimaryKeySelective(subMenuBean);
					}
				}
			} 
			// 如果当前菜单状态为可用，则其下挂所有菜单都需要可用
			else if (FrameConstants.SmMenu.State.USABLE.equals(menuStatus)) {
				List<SmMenu> subMenuList = mapper.queryTree(record.getMenuId());
				if (null != subMenuList && subMenuList.size() > 0) {
					for (SmMenu subMenuBean : subMenuList) {
						subMenuBean
								.setState(FrameConstants.SmMenu.State.USABLE);
						mapper.updateByPrimaryKeySelective(subMenuBean);
					}
				}
			}
			return mapper.updateByPrimaryKeySelective(record);
		} catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	public List<SmMenu> queryTree(String menuId) {
		try {
			return mapper.queryTree(menuId);
		} catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	// private EasyTreeModel buildMenuTree(Long id, String title, String url) {
	// EasyTreeModel tree = new EasyTreeModel();
	// tree.setId(id);
	// tree.setText(title);
	// tree.setIconCls("icon-mini-add");
	// if (url != null) tree.addAttribute("url", url);
	// return tree;
	// }
	
	public List<EasyTreeModel> selectMenuFavoritesByOperId(String operId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("operId", operId);
		List<SmMenu> menuList = mapper.selectMenuFavoritesByOperId(map);
		/* 1、创建根节点 */
		EasyTreeModel rootNode = new EasyTreeModel();
		rootNode.setId("0");
		rootNode.setText("菜单根节点");
		/* 2、构建树 */
		// 2.1、将扁平结果转换为树节点
		List<EasyTreeModel> treeModelList = new ArrayList<EasyTreeModel>();
		// 3、将List转换为Map，提高查找效率
		Map<String, EasyTreeModel> treeModelMap = new HashMap<String, EasyTreeModel>();
		treeModelMap.put("root", rootNode);
		for (SmMenu bean : menuList) {
			EasyTreeModel treeModel = new EasyTreeModel();
			treeModel.setId(bean.getMenuId());
			treeModel.setText(bean.getMenuName());
//			treeModel.setIconCls("icon-mini-add");
			treeModel.setParentId(bean.getMenuPid());
			treeModel.addAttribute("url", bean.getMenuUrl());
			treeModel.addAttribute("navigation", bean.getMenuName());
			treeModelList.add(treeModel);
			treeModelMap.put(StringUtil.toString(bean.getMenuId()), treeModel);
		}
		// 4、使用迭代构建每棵树
		for (EasyTreeModel treeModel : treeModelList) {
			if ("-1".equals(StringUtil.toString(treeModel.getParentId()))) {
				treeModel.setState(NodeState.closed);
				rootNode.addChildren(treeModel);
				treeModelMap.put(StringUtil.toString(treeModel.getId()),
						treeModel);
			} else {
				if (treeModelMap.containsKey(StringUtil.toString(treeModel
						.getParentId()))) {
					EasyTreeModel parentNode = (EasyTreeModel) treeModelMap
							.get(StringUtil.toString(treeModel.getParentId()));
					treeModel.addAttribute("navigation", parentNode
							.getAttributes().get("navigation")
							+ "->"
							+ treeModel.getAttributes().get("navigation"));
					parentNode.addChildren(treeModel);
					treeModelMap.put(StringUtil.toString(treeModel.getId()),
							treeModel);
				} else {
					treeModelMap.put(StringUtil.toString(treeModel.getId()),
							treeModel);
				}
			}
		}
		return rootNode.getChildren();
	}
	
	@Override
	public List<SmMenu> getMenuListByOperId(String operId) {
		return mapper.selectMenuByOperId(operId);
	}

}
