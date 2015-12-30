package cn.cttic.sysframe.frame.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.frame.dao.SmMenuFavoritesMapper;
import cn.cttic.sysframe.frame.domain.SmMenu;
import cn.cttic.sysframe.frame.domain.SmMenuFavorites;
import cn.cttic.sysframe.frame.domain.SmMenuFavoritesExample;
import cn.cttic.sysframe.frame.model.SmMenuFavoritesModel;
import cn.cttic.sysframe.frame.service.SmMenuFavoritesService;
import cn.cttic.sysframe.frame.service.SmMenuService;

@Service
public class SmMenuFavoritesServiceImpl implements SmMenuFavoritesService {

	@Autowired
	private SmMenuFavoritesMapper smMenuFavoritesMapper;
	
	@Autowired
	private SmMenuService smMenuService;
	
	@Override
	public List<SmMenuFavorites> selectByExample(SmMenuFavoritesExample example) {
		return smMenuFavoritesMapper.selectByExample(example);
	}
	
	@Override
	public List<SmMenuFavoritesModel> updateFavoritesRight(String operId) {
		// 获取操作员功能权限
		List<SmMenu> menuList = smMenuService.getMenuListByOperId(operId);
		Map<String, SmMenu> menuRightMap = new HashMap<String, SmMenu>();
		for (SmMenu bean : menuList) {
			menuRightMap.put(bean.getMenuId(), bean);
		}
		// 根据操作员ID查询已订阅常用菜单
		SmMenuFavoritesExample smMenuFavoritesExample = new SmMenuFavoritesExample();
		SmMenuFavoritesExample.Criteria smMenuFavoritesExampleCriteria = smMenuFavoritesExample.createCriteria();
		smMenuFavoritesExampleCriteria.andOperIdEqualTo(operId);
		smMenuFavoritesExample.setOrderByClause("menu_order");
		List<SmMenuFavorites> list = smMenuFavoritesMapper.selectByExample(smMenuFavoritesExample);
		// 定义刷新排序标志
		boolean refreshFlag = false;
		for (SmMenuFavorites favorite : list) {
			// 验证该菜单是否还有权限
			if (!menuRightMap.containsKey(favorite.getMenuId())) {
				// 如果已无权限，则将该收藏菜单删除
				SmMenuFavoritesExample example = new SmMenuFavoritesExample();
				SmMenuFavoritesExample.Criteria criteria = example.createCriteria();
				criteria.andOperIdEqualTo(operId);
				criteria.andMenuIdEqualTo(favorite.getMenuId());
				smMenuFavoritesMapper.deleteByExample(example);
				// 置刷新排序标志
				refreshFlag = true;
			}
		}
		// 如果需要刷新排序
		List<SmMenuFavorites> newList = smMenuFavoritesMapper.selectByExample(smMenuFavoritesExample);
		List<SmMenuFavoritesModel> modelList = new ArrayList<SmMenuFavoritesModel>();
		for (int cnt = 0; cnt < newList.size(); cnt ++) {
			SmMenuFavorites record = newList.get(cnt);
			if (refreshFlag) {
				record.setMenuOrder((short) (cnt + 1));
				SmMenuFavoritesExample example = new SmMenuFavoritesExample();
				SmMenuFavoritesExample.Criteria criteria = example.createCriteria();
				criteria.andOperIdEqualTo(record.getOperId());
				criteria.andMenuIdEqualTo(record.getMenuId());
				smMenuFavoritesMapper.updateByExampleSelective(record, example);
			}
			SmMenuFavoritesModel model = new SmMenuFavoritesModel();
			BeanUtils.copyProperties(record, model);
			SmMenu menu = smMenuService.selectByPrimaryKey(model.getMenuId());
			model.setMenuName(menu.getMenuName());
			model.setMenuUrl(menu.getMenuUrl());
			model.setMenuPic(menu.getMenuPic());
			modelList.add(model);
		}
		return modelList;
	}
	
	@Override
	public int countByExample(SmMenuFavoritesExample example) {
		return smMenuFavoritesMapper.countByExample(example);
	}

	@Override
	public int insert(SmMenuFavorites record) {
		return smMenuFavoritesMapper.insert(record);
	}

	@Override
	public int updateByExampleSelective(SmMenuFavorites record,
			SmMenuFavoritesExample example) {
		return smMenuFavoritesMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int delete(SmMenuFavorites record) {
		SmMenuFavoritesExample smMenuFavoritesExample = new SmMenuFavoritesExample();
		SmMenuFavoritesExample.Criteria smMenuFavoritesExampleCriteria = smMenuFavoritesExample.createCriteria();
		smMenuFavoritesExampleCriteria.andOperIdEqualTo(record.getOperId());
		smMenuFavoritesExampleCriteria.andMenuIdEqualTo(record.getMenuId());
		smMenuFavoritesMapper.deleteByExample(smMenuFavoritesExample);
		// 刷新排序
		// 根据操作员ID查询已订阅常用菜单
		SmMenuFavoritesExample example = new SmMenuFavoritesExample();
		SmMenuFavoritesExample.Criteria exampleCriteria = example.createCriteria();
		exampleCriteria.andOperIdEqualTo(record.getOperId());
		example.setOrderByClause("menu_order");
		List<SmMenuFavorites> newList = smMenuFavoritesMapper.selectByExample(example);
		for (int cnt = 0; cnt < newList.size(); cnt ++) {
			SmMenuFavorites _record = newList.get(cnt);
			_record.setMenuOrder((short) (cnt + 1));
			SmMenuFavoritesExample _example = new SmMenuFavoritesExample();
			SmMenuFavoritesExample.Criteria _criteria = _example.createCriteria();
			_criteria.andOperIdEqualTo(_record.getOperId());
			_criteria.andMenuIdEqualTo(_record.getMenuId());
			smMenuFavoritesMapper.updateByExampleSelective(_record, _example);
		}
		return 1;
	}

}
