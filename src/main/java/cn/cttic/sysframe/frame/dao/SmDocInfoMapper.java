package cn.cttic.sysframe.frame.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.cttic.sysframe.common.basedao.BaseMapper;
import cn.cttic.sysframe.frame.domain.SmDocInfo;
import cn.cttic.sysframe.frame.domain.SmDocInfoExample;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface SmDocInfoMapper extends BaseMapper {
    int countByExample(SmDocInfoExample example);

    int deleteByExample(SmDocInfoExample example);

    int deleteByPrimaryKey(Long documentId);

    int insert(SmDocInfo record);

    int insertSelective(SmDocInfo record);

    List<SmDocInfo> selectByExample(SmDocInfoExample example);

    SmDocInfo selectByPrimaryKey(Long documentId);

    int updateByExampleSelective(@Param("record") SmDocInfo record, @Param("example") SmDocInfoExample example);

    int updateByExample(@Param("record") SmDocInfo record, @Param("example") SmDocInfoExample example);

    int updateByPrimaryKeySelective(SmDocInfo record);

    int updateByPrimaryKey(SmDocInfo record);
    
    public PageList<SmDocInfo> selectByExample(SmDocInfoExample example, PageBounds pageBounds);
}