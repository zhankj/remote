package cn.cttic.sysframe.common.basedao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BaseMapper<DomainKey, Domain, Example> {

	int countByExample(Example example);

	int deleteByExample(Example example);

	int deleteByPrimaryKey(DomainKey key);

	int insert(Domain record);

	int insertSelective(Domain record);

	List<Domain> selectByExample(Example example);

	Domain selectByPrimaryKey(DomainKey key);

	int updateByExampleSelective(@Param("record") Domain record, @Param("example") Example example);

	int updateByExample(@Param("record") Domain record, @Param("example") Example example);

	int updateByPrimaryKeySelective(Domain record);

	int updateByPrimaryKey(Domain record);

}
