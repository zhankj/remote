package cn.cttic.sysframe.frame.dao;

import cn.cttic.sysframe.frame.domain.SmFile;
import cn.cttic.sysframe.frame.domain.SmFileExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface SmFileMapper {
    int countByExample(SmFileExample example);

    int deleteByExample(SmFileExample example);

    int deleteByPrimaryKey(Long fileId);

    int insert(SmFile record);

    int insertSelective(SmFile record);

    List<SmFile> selectByExample(SmFileExample example);

    SmFile selectByPrimaryKey(Long fileId);

    int updateByExampleSelective(@Param("record") SmFile record, @Param("example") SmFileExample example);

    int updateByExample(@Param("record") SmFile record, @Param("example") SmFileExample example);

    int updateByPrimaryKeySelective(SmFile record);

    int updateByPrimaryKey(SmFile record);
    PageList<SmFile> selectByExample(SmFileExample example,PageBounds bounds);
}