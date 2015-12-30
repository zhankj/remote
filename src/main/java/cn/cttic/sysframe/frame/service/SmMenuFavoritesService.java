package cn.cttic.sysframe.frame.service;

import java.util.List;

import cn.cttic.sysframe.frame.domain.SmMenuFavorites;
import cn.cttic.sysframe.frame.domain.SmMenuFavoritesExample;
import cn.cttic.sysframe.frame.model.SmMenuFavoritesModel;

public interface SmMenuFavoritesService {

	List<SmMenuFavorites> selectByExample(SmMenuFavoritesExample example);
	
	List<SmMenuFavoritesModel> updateFavoritesRight(String operId);
	
	int countByExample(SmMenuFavoritesExample example);
	
	int insert(SmMenuFavorites record);
	
	int updateByExampleSelective(SmMenuFavorites record, SmMenuFavoritesExample example);
	
	int delete(SmMenuFavorites record);
}
