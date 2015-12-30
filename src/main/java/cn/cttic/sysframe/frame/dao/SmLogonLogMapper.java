package cn.cttic.sysframe.frame.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.cttic.sysframe.common.basedao.BaseMapper;
import cn.cttic.sysframe.frame.domain.SmLogonLog;
import cn.cttic.sysframe.frame.domain.SmLogonLogExample;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface SmLogonLogMapper extends BaseMapper {
    int countByExample(SmLogonLogExample example);

    int deleteByExample(SmLogonLogExample example);

    int insert(SmLogonLog record);

    int insertSelective(SmLogonLog record);

    List<SmLogonLog> selectByExample(SmLogonLogExample example);

    int updateByExampleSelective(@Param("record") SmLogonLog record, @Param("example") SmLogonLogExample example);

    int updateByExample(@Param("record") SmLogonLog record, @Param("example") SmLogonLogExample example);
    
    public PageList<SmLogonLog> query(SmLogonLogExample example, PageBounds pageBounds);

}