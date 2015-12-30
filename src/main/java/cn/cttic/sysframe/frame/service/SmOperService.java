package cn.cttic.sysframe.frame.service;

import java.util.List;
import java.util.Map;

import cn.cttic.sysframe.frame.domain.SmOper;
import cn.cttic.sysframe.frame.domain.SmOperExample;
import cn.cttic.sysframe.frame.domain.SmRole;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface SmOperService {

	public List<SmOper> getBeans(SmOperExample example);
	
	public SmOper getBean(SmOperExample example);
	
	public SmOper getBean(String operId);
	
	public int insertBean(SmOper record);
	
	public int updateBean(SmOper record, SmOperExample example);
	
	public int updateByPrimaryKeySelective(SmOper record);
	
	public int updateByOrgan(String orgId);
	
	public int updateByStaff(String staffId);
	
	public PageList<SmOper> queryPage(SmOperExample example, PageBounds pageBounds);
	
	/**
	 * 根据orgId获取对应的工号
	 * @param example
	 * @param pageBounds
	 * @return
	 */
	public List<SmOper> getOperListByOrgId(String orgId);
	
	/**
	 * 根据主键更新
	 */
	public int updateByPrimaryKey(SmOper record);
	/**
	 * 查询工号人员信息
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	PageList<Map<String,Object>> queryStaffOper(Map<String,Object> map, PageBounds pageBounds);
	
	/**
	 * 根据虚拟组id获取人员工号
	 *
	 * @param gourpId
	 * @return 
	 * @author yangrui
	 * @date 2014-5-20 下午2:58:31
	 */
	List<Map<String,Object>> queryStaffOperByGroupId(String gourpId);
	
//	/**
//	 * 根据工号编码初始化操作员信息
//	 */
//	public SmOperModel initOperInfo(String operCode) ;

	public PageList<SmRole> operRoleData(Map<String, Object> map, PageBounds pageBounds);

	public void saveOperRole(Map<String, Object> map);

	/**
	 * 根据工号编码和密码查询操作员信息
	 *
	 * @param operCode
	 * @param loginPwd
	 * @return 
	 * @author caohui
	 * @date May 28, 2014 4:04:03 PM
	 */
	SmOper getByAuth(String operCode, String loginPwd);

	/**
	 * 根据工号编码获取工号信息
	 * @param operCode
	 * @return
	 */
	SmOper getByOperCode(String operCode);

}
