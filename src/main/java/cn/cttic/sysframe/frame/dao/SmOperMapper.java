package cn.cttic.sysframe.frame.dao;

import java.util.Map;

import cn.cttic.sysframe.common.basedao.BaseMapper;
import cn.cttic.sysframe.frame.domain.SmOper;
import cn.cttic.sysframe.frame.domain.SmOperExample;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface SmOperMapper extends BaseMapper<String, SmOper, SmOperExample> {
    PageList<SmOper> queryPage(SmOperExample example, PageBounds pageBounds);
    
    PageList<Map<String,Object>> queryStaffOper(Map<String,Object> map, PageBounds pageBounds);
}