package cn.cttic.sysframe.frame.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.cttic.sysframe.common.basedao.BaseMapper;
import cn.cttic.sysframe.frame.domain.SmGroup;
import cn.cttic.sysframe.frame.domain.SmGroupExample;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface SmGroupMapper extends BaseMapper {
    int countByExample(SmGroupExample example);

    int deleteByExample(SmGroupExample example);

    int deleteByPrimaryKey(Long groupId);

    int insert(SmGroup record);

    int insertSelective(SmGroup record);

    List<SmGroup> selectByExample(SmGroupExample example);

    SmGroup selectByPrimaryKey(Long groupId);

    int updateByExampleSelective(@Param("record") SmGroup record, @Param("example") SmGroupExample example);

    int updateByExample(@Param("record") SmGroup record, @Param("example") SmGroupExample example);

    int updateByPrimaryKeySelective(SmGroup record);

    int updateByPrimaryKey(SmGroup record);
    
    public PageList<SmGroup> query(SmGroupExample example, PageBounds pageBounds);
    
    public PageList<Map<String,Object>> queryPage(Map<String, Object> hashMap, PageBounds pageBounds);
    
    public List<SmGroup> queryGroup(Map<String, Object> hashMap);
    
}