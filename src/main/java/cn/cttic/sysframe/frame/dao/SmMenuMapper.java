package cn.cttic.sysframe.frame.dao;

import java.util.List;
import java.util.Map;

import cn.cttic.sysframe.common.basedao.BaseMapper;
import cn.cttic.sysframe.frame.domain.SmMenu;
import cn.cttic.sysframe.frame.domain.SmMenuExample;

public interface SmMenuMapper extends BaseMapper<String, SmMenu, SmMenuExample> {
    public List<SmMenu> queryTree(String menuId);
    
    public List<SmMenu> getUrlByOperId(String operId);
    
    public List<SmMenu> selectMenuByOperId(String operId);
    
    public List<SmMenu> selectMenuByFsId(Map<String, Object> map);
    
    public List<SmMenu> selectMenuFavoritesByOperId(Map<String, Object> map);
}