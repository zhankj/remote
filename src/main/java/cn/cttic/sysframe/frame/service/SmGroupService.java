package cn.cttic.sysframe.frame.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.cttic.sysframe.frame.domain.SmGroup;
import cn.cttic.sysframe.frame.domain.SmGroupExample;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


public interface SmGroupService {
	
	public PageList<Map<String,Object>> queryPage(Map<String,Object> map, PageBounds pageBounds);
	
	public SmGroup selectByPrimaryKey(Long groupId);
	
	
	public int insertSelective(SmGroup record);
	
	public int updateByExampleSelective(@Param("record") SmGroup record, @Param("example") SmGroupExample example);
	
	public int updateByPrimaryKeySelective(SmGroup record);

	public void addMember(String operIds, String groupId,String isAdd);

	public void updateLeader(String operId, String groupId,String leaderState);

	public void deleteGroup(String groupId);
	
	/**
	 * 根据工号id和虚拟组类型获取虚拟组
	 *
	 * @param operId
	 * @param groupType
	 * @return 
	 * @author yangrui
	 * @date 2014-5-21 上午9:57:02
	 */
	List<SmGroup> queryGroupByOpIdAndGType(String operId,String groupType);

	public void saveGroup(SmGroup smGroup);

}
