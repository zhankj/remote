package cn.cttic.sysframe.frame.dao;

import cn.cttic.sysframe.frame.domain.SmRole2FsCriteria;
import cn.cttic.sysframe.frame.domain.SmRole2FsKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmRole2FsMapper {
    int countByExample(SmRole2FsCriteria example);

    int deleteByExample(SmRole2FsCriteria example);

    int deleteByPrimaryKey(SmRole2FsKey key);

    int insert(SmRole2FsKey record);

    int insertSelective(SmRole2FsKey record);

    List<SmRole2FsKey> selectByExample(SmRole2FsCriteria example);

    int updateByExampleSelective(@Param("record") SmRole2FsKey record, @Param("example") SmRole2FsCriteria example);

    int updateByExample(@Param("record") SmRole2FsKey record, @Param("example") SmRole2FsCriteria example);
    
	public int checkRole2FsByFsId(String fs_id);
}