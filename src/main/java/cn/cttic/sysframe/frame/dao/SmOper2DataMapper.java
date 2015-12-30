package cn.cttic.sysframe.frame.dao;

import java.util.List;
import java.util.Map;

public interface SmOper2DataMapper {

	/**
	 * 根据工号标识获取拥有的所有数据权限(不包含data_value)
	 * 
	 * @param operId
	 * @return List<Map> 其中Map的key：DATA_CODE、 DATA_NAME
	 * @throws Exception
	 */
	List<Map<String, String>> getSimpleDataRight1(Map paramMap);

	/**
	 * 根据工号标识获取拥有的所有数据权限(包含data_value)
	 * 
	 * @param operId
	 * @return List<Map> 其中Map的key：DATA_CODE、 DATA_NAME、DATA_VALUE
	 * @throws Exception
	 */
	List<Map<String, String>> getSimpleDataRight2(Map paramMap)
			throws Exception;

	/**
	 * 根据工号标识、数据权限编码获取拥有的数据权限
	 * 
	 * @param operId
	 * @param dataCode
	 * @return List<Map> 其中Map的key：DATA_CODE、 DATA_NAME、DATA_VALUE
	 * @throws Exception
	 */
	List<Map<String, String>> getDataRightByDataCode(Map paramMap)
			throws Exception;
}