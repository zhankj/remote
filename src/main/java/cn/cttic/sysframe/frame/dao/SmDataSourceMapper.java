package cn.cttic.sysframe.frame.dao;

import cn.cttic.sysframe.frame.domain.SmDataSource;
import cn.cttic.sysframe.frame.domain.SmDataSourceCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmDataSourceMapper {
	public  int countByExample(SmDataSourceCriteria example);

	public int deleteByExample(SmDataSourceCriteria example);

	public int deleteByPrimaryKey(String dataCode);

	public int insert(SmDataSource record);

	public int insertSelective(SmDataSource record);

	public List<SmDataSource> selectByExample(SmDataSourceCriteria example);

	public SmDataSource selectByPrimaryKey(String dataCode);

	public int updateByExampleSelective(@Param("record") SmDataSource record, @Param("example") SmDataSourceCriteria example);

	public int updateByExample(@Param("record") SmDataSource record, @Param("example") SmDataSourceCriteria example);

	public int updateByPrimaryKeySelective(SmDataSource record);

	public int updateByPrimaryKey(SmDataSource record);
}