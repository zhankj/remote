package cn.cttic.sysframe.frame.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.cttic.sysframe.common.basedao.BaseMapper;
import cn.cttic.sysframe.frame.domain.SmStaff;
import cn.cttic.sysframe.frame.domain.SmStaffExample;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface SmStaffMapper extends BaseMapper {
    int countByExample(SmStaffExample example);

    int deleteByExample(SmStaffExample example);

    int deleteByPrimaryKey(String staffId);

    int insert(SmStaff record);

    int insertSelective(SmStaff record);

    List<SmStaff> selectByExample(SmStaffExample example);

    SmStaff selectByPrimaryKey(String staffId);

    int updateByExampleSelective(@Param("record") SmStaff record, @Param("example") SmStaffExample example);

    int updateByExample(@Param("record") SmStaff record, @Param("example") SmStaffExample example);

    int updateByPrimaryKeySelective(SmStaff record);

    int updateByPrimaryKey(SmStaff record);
    
    public PageList<SmStaff> query(SmStaffExample example, PageBounds pageBounds);
}