package cn.cttic.sysframe.frame.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import cn.cttic.sysframe.frame.domain.SmDataRight;
import cn.cttic.sysframe.frame.domain.SmDataSource;

public class SmDataRightModel extends SmDataRight {
	private SmDataSource dataSource;
	
	/**权限分配结果区规则解析数据 */
	private String sqlData;
	private Map selectTableDatasMap = new HashMap();
	private Map selectedTableDatasMap = new HashMap();
	
	/**权限分配 查询区规则解析数据 */
	private List<Map<String,Object>> qryList = new ArrayList<Map<String,Object>>();
	private JSONArray qryArray=new JSONArray();
	private int qryObjNum=0;
	
	/**权限分配业务数据 */
	private List<Map> selectResultList = new ArrayList<Map>();  
	
	private List<Map> selectedResultList = new ArrayList<Map>();
	
	public List<Map> getSelectedResultList() {
		return selectedResultList;
	}

	public void setSelectedResultList(List<Map> selectedResultList) {
		this.selectedResultList = selectedResultList;
	}

	public List<Map> getSelectResultList() {
		return selectResultList;
	}

	public void setSelectResultList(List<Map> selectResultList) {
		this.selectResultList = selectResultList;
	}

	public String getSqlData() {
		return sqlData;
	}

	public void setSqlData(String sqlData) {
		this.sqlData = sqlData;
	}
	
	public Map getSelectTableDatasMap() {
		return selectTableDatasMap;
	}

	public void setSelectTableDatasMap(Map selectTableDatasMap) {
		this.selectTableDatasMap = selectTableDatasMap;
	}

	public Map getSelectedTableDatasMap() {
		return selectedTableDatasMap;
	}

	public void setSelectedTableDatasMap(Map selectedTableDatasMap) {
		this.selectedTableDatasMap = selectedTableDatasMap;
	}

	public List<Map<String,Object>> getQryList() {
		return qryList;
	}

	public void setQryList(List<Map<String,Object>> qryList) {
		this.qryList = qryList;
	}

	public JSONArray getQryArray() {
		return qryArray;
	}

	public void setQryArray(JSONArray qryArray) {
		this.qryArray = qryArray;
	}

	public int getQryObjNum() {
		return qryObjNum;
	}

	public void setQryObjNum(int qryObjNum) {
		this.qryObjNum = qryObjNum;
	}

	public SmDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(SmDataSource dataSource) {
		this.dataSource = dataSource;
	}
}
