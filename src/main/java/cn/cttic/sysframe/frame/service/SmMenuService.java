package cn.cttic.sysframe.frame.service;

import java.util.List;
import java.util.Map;

import cn.cttic.sysframe.common.model.EasyTreeModel;
import cn.cttic.sysframe.common.model.TreeModel;
import cn.cttic.sysframe.frame.domain.SmMenu;

public interface SmMenuService {

	public List<SmMenu> selectByExample();
	
	public TreeModel getTree(String state);
	
	public List<EasyTreeModel> getNavigationTree(String menuId);
	
	public List<EasyTreeModel> getMenuTree(String menuId);
	
	public SmMenu selectByPrimaryKey(String menuId);
	
	public int insert(SmMenu record);
	
	public int updateByPrimaryKeySelective(SmMenu record);
	
	public List<SmMenu> queryTree(String menuId);
	
	public List<EasyTreeModel> getNavigationTreeByOperId(String OperId);
	
	public TreeModel getTreeByFsId(String fsId);

	Map<String, EasyTreeModel> getSecondaryMenuByOperIdAndMenuParentId(String operId, String menuParentId);
	
	public List<EasyTreeModel> selectMenuFavoritesByOperId(String operId);
	
	public List<SmMenu> getMenuListByOperId(String operId);
}
