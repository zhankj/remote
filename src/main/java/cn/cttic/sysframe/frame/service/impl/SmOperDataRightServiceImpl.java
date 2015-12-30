package cn.cttic.sysframe.frame.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.frame.dao.SmOper2DataMapper;
import cn.cttic.sysframe.frame.service.SmOperDataRightService;

@Service
public class SmOperDataRightServiceImpl implements SmOperDataRightService {

	@Autowired
	private SmOper2DataMapper oper2DataMapper;

	/**
	 * 根据工号标识获取拥有的所有数据权限(不包含data_value)
	 * 
	 * @param operId
	 * @return List<Map> 其中Map的key：DATA_CODE、 DATA_NAME
	 * @throws Exception
	 */
	@Override
	public List<Map<String, String>> getSimpleDataRight1(String operId)
			throws Exception {

		Map paramMap = new HashMap();
		paramMap.put("operId", operId);
		List<Map<String, String>> operDateRightList = oper2DataMapper
				.getSimpleDataRight1(paramMap);
		if (operDateRightList == null || operDateRightList.size() < 1) {
			operDateRightList = new ArrayList<Map<String, String>>();
		}
		return operDateRightList;
	}

	/**
	 * 根据工号标识获取拥有的所有数据权限(包含data_value)
	 * 
	 * @param operId
	 * @return List<Map> 其中Map的key：DATA_CODE、 DATA_NAME、DATA_VALUE
	 * @throws Exception
	 */
	@Override
	public List<Map<String, String>> getSimpleDataRight2(String operId)
			throws Exception {

		Map paramMap = new HashMap();
		paramMap.put("operId", operId);
		List<Map<String, String>> operDateRightList = oper2DataMapper
				.getSimpleDataRight2(paramMap);
		if (operDateRightList == null || operDateRightList.size() < 1) {
			operDateRightList = new ArrayList<Map<String, String>>();
		}
		return operDateRightList;
	}

	/**
	 * 根据工号标识、数据权限编码获取拥有的数据权限
	 * 
	 * @param operId
	 * @param dataCode
	 * @return List<Map> 其中Map的key：DATA_CODE、 DATA_NAME、DATA_VALUE
	 * @throws Exception
	 */
	@Override
	public List<Map<String, String>> getDataRightByDataCode(String operId,
			String dataCode) throws Exception {
		Map paramMap = new HashMap();
		paramMap.put("operId", operId);
		paramMap.put("dataCode", dataCode);
		List<Map<String, String>> operDateRightList = oper2DataMapper
				.getDataRightByDataCode(paramMap);
		if (operDateRightList == null || operDateRightList.size() < 1) {
			operDateRightList = new ArrayList<Map<String, String>>();
		}
		return operDateRightList;
	}
}
