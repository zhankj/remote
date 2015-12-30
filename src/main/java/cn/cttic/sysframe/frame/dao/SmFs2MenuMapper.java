package cn.cttic.sysframe.frame.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.cttic.sysframe.frame.domain.SmFs2MenuExample;
import cn.cttic.sysframe.frame.domain.SmFs2MenuKey;

public interface SmFs2MenuMapper {

	int countByExample(SmFs2MenuExample example);

	int deleteByExample(SmFs2MenuExample example);

	int deleteByPrimaryKey(SmFs2MenuKey key);

	int insert(SmFs2MenuKey record);

	int insertSelective(SmFs2MenuKey record);

	List<SmFs2MenuKey> selectByExample(SmFs2MenuExample example);

	int updateByExampleSelective(@Param("record") SmFs2MenuKey record, @Param("example") SmFs2MenuExample example);

	int updateByExample(@Param("record") SmFs2MenuKey record, @Param("example") SmFs2MenuExample example);
	
	public int checkMenusByFsId(String fs_id);
}