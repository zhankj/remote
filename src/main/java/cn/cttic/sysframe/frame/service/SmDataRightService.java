package cn.cttic.sysframe.frame.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.cttic.sysframe.common.model.TreeModel;
import cn.cttic.sysframe.frame.domain.SmDataRight;
import cn.cttic.sysframe.frame.model.SmDataRightModel;

public interface SmDataRightService {

	/**
	 * 根据数据权限编码获取数据权限信息
	 * 
	 * @param data_code
	 * @return SmDataRight 
	 * @throws Exception
	 */
	public SmDataRight getDataRightInfo(String dataCode);
	
	/**
	 * 数据权限分配页面，查询区数据规则解析
	 * 
	 * @param data_code
	 * @return SmDataRight 
	 * @throws Exception
	 */
	public SmDataRightModel initQryDataRightArea(String dataCode,String objId,int objType);
	
	
    /**
	 * 获取数据权限树
	 * 
	 * @param config_aim  
	 * @param object_id
	 * @return 
	 * @throws Exception
	 */
	public List<TreeModel> getDataRightTree(int config_aim, String object_id);
	
	/**
	 * 获取数据权限数据
	 * 
	 * @param data_code
	 * @return 
	 * @throws Exception
	 */
	public SmDataRightModel getSmDataRightList(String dataCode, String selFlag,String qryCondition,String objId,int config_aim,PageBounds pageBounds);

	/**
	 * 获取数据权限查询区下拉列表数据
	 * 
	 * @param sql
	 * @param param
	 * @param sysId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> dataRightChangeDataList(String sql,String param, String sysId,String objId,int objType);
}
