package cn.cttic.sysframe.frame.service;

import java.util.List;
import java.util.Map;

import cn.cttic.sysframe.frame.domain.SmOper;
import cn.cttic.sysframe.frame.domain.SmStaff;
import cn.cttic.sysframe.frame.domain.SmStaffExample;
import cn.cttic.sysframe.frame.model.SmOperModel;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface SmStaffService {

	public List<SmStaff> select(SmStaffExample example);
	
	public int insertBean(SmStaff record);
	
	public int update(SmStaff record, SmStaffExample example);
	
	public int updateByOrgan(String orgId);

	public int delete(SmStaffExample example);
	
	public int deleteByPrimaryKey(String example);
	
	public SmStaff selectByPrimaryKey(String staffId);
	
	public PageList<SmStaff> queryPage(PageBounds pageBounds, SmStaff searchModel, Map<String, String> param);
	
	public PageList<SmStaff> query(SmStaffExample  sc,PageBounds pageBounds);
	
	public int updateByPrimaryKey(SmStaff record);
	
	public int updateByPrimaryKeySelective(SmStaff record);
	
	public int countByExample(SmStaffExample example);

	public void saveStaff(SmStaff staff);

	public void deleteStaff(String staffId, String status);
	
	public List<SmStaff> getStaffListByOrgId(String orgId);
	/**
	 * @Description: 
	 *	是否客户中心权限
	 * @param operModel
	 * @return
	 * @author Super
	 * @date 2014年9月30日 上午10:31:44
	 */
	public boolean isCsRight(SmOperModel operModel);
}
