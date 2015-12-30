package cn.cttic.sysframe.frame.service.impl;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.common.model.TreeModel;
import cn.cttic.sysframe.common.util.SessionInfo;
import cn.cttic.sysframe.common.util.StringUtil;
import cn.cttic.sysframe.frame.common.constants.FrameConstants;
import cn.cttic.sysframe.frame.dao.SmDataRightMapper;
import cn.cttic.sysframe.frame.dao.SmDataSourceMapper;
import cn.cttic.sysframe.frame.dao.SmFuncSetMapper;
import cn.cttic.sysframe.frame.dao.SmOperMapper;
import cn.cttic.sysframe.frame.domain.SmDataRight;
import cn.cttic.sysframe.frame.domain.SmDataSource;
import cn.cttic.sysframe.frame.domain.SmFuncSet;
import cn.cttic.sysframe.frame.domain.SmOper;
import cn.cttic.sysframe.frame.model.SmDataRightModel;
import cn.cttic.sysframe.frame.model.SmOperModel;
import cn.cttic.sysframe.frame.service.SmDataRightService;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

@Service
public class SmDataRightServiceImpl implements SmDataRightService {

	@Autowired
	private SmDataRightMapper dataRightMapper;

	@Autowired
	private SmDataSourceMapper dataSourceMapper;
	
	@Autowired
	private SmFuncSetMapper funcSetMapper;

	@Autowired
	private SmOperMapper operMapper;

	@Autowired
	private SmFuncSetMapper fsMapper;
	
	/** 数据权限数据源缓存 */
	public static Map<String, SmDataRightModel> allDataRightMap =new HashMap<String, SmDataRightModel>();

	/**
	 * 根据数据权限编码获取数据权限信息
	 * 
	 * @param data_code
	 * @return SmDataRightModel
	 * @throws Exception
	 */
	@Override
	public SmDataRightModel getDataRightInfo(String dataCode){
			SmDataRightModel dataRightModel = new SmDataRightModel();
			SmDataRight dataRight = dataRightMapper
					.selectByPrimaryKey(dataCode);
			if (dataRight != null
					&& !StringUtil.isBlank(dataRight.getDataCode())) {
				BeanUtils.copyProperties(dataRight, dataRightModel);
				SmDataSource dataSource = dataSourceMapper
						.selectByPrimaryKey(dataCode);
				dataRightModel.setDataSource(dataSource);
			}else{
				throw new SystemException("数据权限编码："+dataCode +"数据不存在！，请联系管理员！", StatusCodeForFrame.RECORD_CONFIG_ERROR);
			}
			return dataRightModel;
	}

	/**
	 * 数据权限分配页面，查询区数据规则解析
	 * 
	 * @param data_code
	 * @return SmDataRight
	 * @throws Exception
	 */
	@Override
	public SmDataRightModel initQryDataRightArea(String dataCode,String objId,int objType) {
			SmDataRightModel dataRightModel = getDataRightInfo(dataCode);
			
			if(StringUtil.isBlank(objId)){
				throw new SystemException("未传递分配对象编码!", StatusCodeForFrame.RECORD_NOT_EXISTS);
			}
			if(objType>3||objType<1){
				throw new SystemException("SmDataRightServiceImpl。initQryDataRightArea对象类型传入不正确!", StatusCodeForFrame.DB_OPERATION_ERROR);
			}
			if (dataRightModel != null
					&& !StringUtil.isBlank(dataRightModel.getDataCode())&&dataRightModel.getDataSource()!=null) {
				
				//定义查询区控件数量
				int qryObjNum =0;
			    List<Map<String,Object>> qryList = new ArrayList<Map<String,Object>>();
			    JSONArray qryArray = new JSONArray();
				
				if (!StringUtil.isBlank(dataRightModel.getDataSource().getSqlWhere())) {
					//解析查询区数据
					
//					http://www.open-open.com/lib/view/open1372569123894.html

					JSONArray sqlWhereArray = JSONArray.fromObject(dataRightModel.getDataSource().getSqlWhere());

					if (sqlWhereArray != null && sqlWhereArray.size() > 0) {

						JSONObject obj = null;

						Map qryInfoMap = null;
						JSONObject qryObj = null;
						
						qryObjNum = sqlWhereArray.size();

						for (int i = 0; i < qryObjNum; i++) {
							obj = sqlWhereArray.getJSONObject(i);

							qryInfoMap = new HashMap();
							qryObj = new JSONObject();

							if (obj.get(FrameConstants.DataRight.isDisplay)==null||obj.getString(FrameConstants.DataRight.isDisplay) == null) {
								obj.put(FrameConstants.DataRight.isDisplay,FrameConstants.DataRight.isDisplay_Y);
							}
							if (obj.getString(FrameConstants.DataRight.objectId) == null) {
								throw new SystemException(
										"sm_data_source.data_code=【" + dataCode
												+ "】配置解析查询条件字段出错,缺少别名配置 dataId!",StatusCodeForFrame.RECORD_CONFIG_ERROR);
							}
							if (obj.get(FrameConstants.DataRight.fuzzyQry)==null||obj.getString(FrameConstants.DataRight.fuzzyQry) == null) {
								obj.put(FrameConstants.DataRight.fuzzyQry, FrameConstants.DataRight.fuzzyQry_N);
							} 
							if (FrameConstants.DataRight.isDisplay_Y.equals(obj.getString(FrameConstants.DataRight.isDisplay))) {
								qryInfoMap.put(FrameConstants.DataRight.objectId, FrameConstants.DataRight.tabPrefix
										+ obj.getString(FrameConstants.DataRight.objectId));
								qryInfoMap.put(FrameConstants.DataRight.fuzzyQry, obj.getString(FrameConstants.DataRight.fuzzyQry));
								qryInfoMap.put(FrameConstants.DataRight.isDisplay, obj.getString(FrameConstants.DataRight.isDisplay));
								qryInfoMap.put(FrameConstants.DataRight.displayName, obj
										.getString(FrameConstants.DataRight.displayName));
								qryInfoMap.put(FrameConstants.DataRight.qryType, obj.getString(FrameConstants.DataRight.qryType));
								
								if (obj.get(FrameConstants.DataRight.parentId) != null&&!"".equals(obj.getString(FrameConstants.DataRight.parentId))) {
									qryInfoMap.put(FrameConstants.DataRight.parentId,obj.getString(FrameConstants.DataRight.parentId));
								} else {
									qryInfoMap.put(FrameConstants.DataRight.parentId, "");
								}
								if (obj.get(FrameConstants.DataRight.childId) != null&&!"".equals(obj.getString(FrameConstants.DataRight.childId))) {
									qryInfoMap.put(FrameConstants.DataRight.childId, obj.getString(FrameConstants.DataRight.childId));
									qryObj.put(FrameConstants.DataRight.childId, obj.getString(FrameConstants.DataRight.childId));
								} else {
									qryInfoMap.put(FrameConstants.DataRight.childId, "");
									qryObj.put(FrameConstants.DataRight.childId, "");
								}
								if (obj.get(FrameConstants.DataRight.clearId) != null&&!"".equals(obj.getString(FrameConstants.DataRight.clearId))) {
									qryInfoMap.put(FrameConstants.DataRight.clearId, obj.getString(FrameConstants.DataRight.clearId));
									qryObj.put(FrameConstants.DataRight.clearId, obj.getString(FrameConstants.DataRight.clearId));
								} else {
									qryInfoMap.put(FrameConstants.DataRight.clearId, "");
									qryObj.put(FrameConstants.DataRight.clearId, "");
								}
								if (obj.get(FrameConstants.DataRight.qrySql) != null) {
									qryInfoMap.put(FrameConstants.DataRight.qrySql, obj.getString(FrameConstants.DataRight.qrySql));
									qryObj.put(FrameConstants.DataRight.qrySql, obj.getString(FrameConstants.DataRight.qrySql));
								} else {
									qryInfoMap.put(FrameConstants.DataRight.qrySql, "");
									qryObj.put(FrameConstants.DataRight.qrySql, "");
								}
								if (obj.get(FrameConstants.DataRight.sysId) != null) {
									qryInfoMap.put(FrameConstants.DataRight.sysId, obj.getString(FrameConstants.DataRight.sysId));
									qryObj.put(FrameConstants.DataRight.sysId, obj.getString(FrameConstants.DataRight.sysId));
								} else {
									qryInfoMap.put(FrameConstants.DataRight.sysId, "");
									qryObj.put(FrameConstants.DataRight.sysId, "");
								}
								
								qryObj.put(FrameConstants.DataRight.qryId, FrameConstants.DataRight.tabPrefix + obj.getString(FrameConstants.DataRight.objectId));
								qryObj.put(FrameConstants.DataRight.qryType, obj.getString(FrameConstants.DataRight.qryType));
								qryObj.put(FrameConstants.DataRight.fuzzyQry, obj.getString(FrameConstants.DataRight.fuzzyQry));
								qryObj.put(FrameConstants.DataRight.displayName,obj.getString(FrameConstants.DataRight.displayName));

								if (FrameConstants.DataRight.qryType_S.equals(obj.getString(FrameConstants.DataRight.qryType))) {
									// 获取数据
									if (obj.get(FrameConstants.DataRight.parentId) == null
											|| obj.getString(FrameConstants.DataRight.parentId) == null
											|| "".equals(obj.getString(FrameConstants.DataRight.parentId))) {
										// String sqlInfoDatas = obj.getString(qrySql);
										if (obj.getString(FrameConstants.DataRight.qrySql) != null
												&& !"".equals(FrameConstants.DataRight.qrySql)) {
											
											String sql=obj.getString(FrameConstants.DataRight.qrySql);
											
											Map rightMap = new HashMap();
											if(obj.get(FrameConstants.DataRight.sysId) != null&&!"".equals(obj.get(FrameConstants.DataRight.sysId).toString())){
												
												/**加载操作员默认权限*****/
												sql=this.setOperRight(sql,objId,objType,rightMap);
												if(rightMap.get(FrameConstants.DataRight.DATA_RIGHT_SYSPROVINCEID)!=null){
													qryObj.put(FrameConstants.DataRight.DATA_RIGHT_SYSPROVINCEID, rightMap.get(FrameConstants.DataRight.DATA_RIGHT_SYSPROVINCEID));
													qryInfoMap.put(FrameConstants.DataRight.DATA_RIGHT_SYSPROVINCEID, rightMap.get(FrameConstants.DataRight.DATA_RIGHT_SYSPROVINCEID));
												}
												if(rightMap.get(FrameConstants.DataRight.DATA_RIGHT_SYSAREAID)!=null){
													qryObj.put(FrameConstants.DataRight.DATA_RIGHT_SYSAREAID, rightMap.get(FrameConstants.DataRight.DATA_RIGHT_SYSAREAID));
													qryInfoMap.put(FrameConstants.DataRight.DATA_RIGHT_SYSAREAID, rightMap.get(FrameConstants.DataRight.DATA_RIGHT_SYSAREAID));
												}
												if(rightMap.get(FrameConstants.DataRight.DATA_RIGHT_SYSCOUNTYID)!=null){
													qryObj.put(FrameConstants.DataRight.DATA_RIGHT_SYSCOUNTYID, rightMap.get(FrameConstants.DataRight.DATA_RIGHT_SYSCOUNTYID));
													qryInfoMap.put(FrameConstants.DataRight.DATA_RIGHT_SYSCOUNTYID, rightMap.get(FrameConstants.DataRight.DATA_RIGHT_SYSCOUNTYID));
												}
												if(rightMap.get(FrameConstants.DataRight.DATA_RIGHT_SYSOBJECTID)!=null){
													qryObj.put(FrameConstants.DataRight.DATA_RIGHT_SYSOBJECTID, rightMap.get(FrameConstants.DataRight.DATA_RIGHT_SYSOBJECTID));
													qryInfoMap.put(FrameConstants.DataRight.DATA_RIGHT_SYSOBJECTID, rightMap.get(FrameConstants.DataRight.DATA_RIGHT_SYSOBJECTID));
												}
											}
											Map qryMap = new HashMap();
											qryMap.put(FrameConstants.DataRight.sysExecSql, sql);
											List<Map<String, Object>> list = dataRightMapper
													.commQuery(qryMap);
											if(list!= null&&list.size()>0){
												Collections.sort(list, new SortByCHINESE());
											}
											if(obj.get(FrameConstants.DataRight.sysId)!=null&&!StringUtil.isBlank(obj.get(FrameConstants.DataRight.sysId).toString())){
												setListHead(list,rightMap,obj.get(FrameConstants.DataRight.sysId).toString());
											}else{
												setListHead(list,rightMap,"");
											}
											qryInfoMap.put(FrameConstants.DataRight.selDatas, list);
										}
									}
								}
							}
							qryList.add(qryInfoMap);
							qryArray.add(qryObj);
						}
						dataRightModel.setQryObjNum(qryObjNum);
						dataRightModel.setQryList(qryList);
						dataRightModel.setQryArray(qryArray);
					}
				}
			} else {
				throw new SystemException("数据权限编码："+dataCode +"数据不存在！，请联系管理员！", StatusCodeForFrame.RECORD_NOT_EXISTS);
			}
			return dataRightModel;
	}
	
	
	/**
	 * 获取数据权限数据
	 * 
	 * @param data_code
	 * @return 
	 * @throws Exception
	 */
	public SmDataRightModel getSmDataRightList(String dataCode, String selFlag,String qryCondition,String objId,int config_aim,PageBounds pageBounds) {

		if (StringUtil.isBlank(dataCode)) {
			throw new SystemException("数据编码不能为空!", StatusCodeForFrame.INSIDE_ERROR);
		}

		/**
		 * **1.获取配置信息***********************************************************
		 */
		SmDataRightModel dataRightModel = getDataRightInfo(dataCode);
		if(dataRightModel==null){
			dataRightModel=getDataRightInfo(dataCode);
		}
		SmDataRightModel dataRightModelRestlt = new SmDataRightModel();
		List<Map> resultList = new ArrayList<Map>();
		
		/**
		 * **2.获取查询语句 与
		 * 条件语句******************************************************
		 */
		if (dataRightModel!=null) {
			
			String sqlStr=dataRightModel.getSqlData();
			if(StringUtil.isBlank(sqlStr)){
				
				String sqlSel = dataRightModel.getDataSource().getSqlSelect();
				String sqlFrom = dataRightModel.getDataSource().getSqlFrom();
	
				if (StringUtil.isBlank(sqlSel)) {
					throw new SystemException("SmDataSource.Sql_select 不能为空!", StatusCodeForFrame.RECORD_CONFIG_ERROR);
				}
				if (StringUtil.isBlank(sqlFrom)) {
					throw new SystemException("SmDataSource.Sql_from 不能为空!", StatusCodeForFrame.RECORD_CONFIG_ERROR);
				}
	
				// 2.1解析展示结果
				String sqlSelResult = this.convertSqlSelObject(
						JSONArray.fromObject(sqlSel), dataRightModel, selFlag);
	
				sqlStr = sqlSelResult + " " + sqlFrom;
				dataRightModel.setSqlData(sqlStr);    //将解析规则保存
			}
			

			Map params = new HashMap();
			params.put(FrameConstants.DataRight.resultDataCode, dataCode);
			params.put(FrameConstants.DataRight.resultSqlData, sqlStr);
			params.put(FrameConstants.DataRight.resultObjId, objId);
//			params.put("objPid", objPid);
			
			//结果集排序
			if (!StringUtil.isBlank(dataRightModel.getDataSource().getSqlOrder())) {
				params.put(FrameConstants.DataRight.resultSqlOrder, dataRightModel.getDataSource().getSqlOrder());
			}
			//查询区查询条件
			params.put(FrameConstants.DataRight.resultSqlQry, qryCondition);

			System.out.println("params==="+params.toString());
			
			if (FrameConstants.DataRight.selFlag_select.equals(selFlag)) {
				if(FrameConstants.DataRight.CONFIG_AIM_FS==config_aim){
					resultList = dataRightMapper.listSelectDatasForFS(params,pageBounds);
				}else if(FrameConstants.DataRight.CONFIG_AIM_OPER==config_aim){
//					resultList = dataRightMapper.listSelectDatasForOper(params);
				}
				dataRightModelRestlt.setSelectResultList(resultList);
			} else if (FrameConstants.DataRight.selFlag_selected.equals(selFlag)) {
				if(FrameConstants.DataRight.CONFIG_AIM_FS==config_aim){
					resultList = dataRightMapper.listSelectedDatasForFS(params,pageBounds);
				}else if(FrameConstants.DataRight.CONFIG_AIM_OPER==config_aim){
//					resultList = dataRightMapper.listSelectedDatasForOper(params);
				}
				dataRightModelRestlt.setSelectedResultList(resultList);
			}
			dataRightModelRestlt.setSelectTableDatasMap(dataRightModel.getSelectTableDatasMap());
			dataRightModelRestlt.setSelectedTableDatasMap(dataRightModel.getSelectedTableDatasMap());
		}
		return dataRightModelRestlt;
	}
	 /**
     * 转换成SQL
     * 
     * @return
     * @throws
     */
    private String convertSqlSelObject(JSONArray sqlSelObject,SmDataRightModel dataRightModel, String selFlag) {

    	SmDataSource dataSource=dataRightModel.getDataSource();
    	if (dataSource == null|| StringUtil.isBlank( dataSource.getDataCode())) {
        	throw new SystemException("SmDataSource.data_code【" + dataRightModel.getDataCode() + "】未配置数据!", StatusCodeForFrame.RECORD_CONFIG_ERROR);
        } 
    	
        String dataCode = dataSource.getDataCode();

        String sqlResult = " select ";
        if (dataSource.getIsDistinct() != null && dataSource.getIsDistinct().intValue() == FrameConstants.DataRight.isDistinct_Y) {
            sqlResult = " select distinct ";
        }
        JSONArray headJSONArray = new JSONArray();
        JSONObject headJSON = new JSONObject();

        if (sqlSelObject != null && sqlSelObject.size() > 0) {

            JSONObject obj = null;

            boolean isMustId = false; // 默认没有配置id列
            int column = 0;

            for (int i = 0; i < sqlSelObject.size(); i++) {
                obj = sqlSelObject.getJSONObject(i);

                if (obj == null) {
                	throw new SystemException("SmDataSource.data_code【" + dataCode + "】未配置查询语句查询字段!", StatusCodeForFrame.RECORD_CONFIG_ERROR);
                } else if (obj.getString(FrameConstants.DataRight.isDisplay) == null) {
                    throw new SystemException("SmDataSource.data_code【" + dataCode + "】配置解析查询字段出错,缺少是否显示配置 isDisplay!", StatusCodeForFrame.RECORD_CONFIG_ERROR);
                } else if (obj.getString(FrameConstants.DataRight.objectId) == null) {
                    throw new SystemException("SmDataSource.data_code【" + dataCode + "】配置解析查询字段出错,缺少别名配置 dataId!", StatusCodeForFrame.RECORD_CONFIG_ERROR);
                } else if (obj.getString(FrameConstants.DataRight.objectValue) == null) {
                    throw new SystemException("SmDataSource.data_code【" + dataCode + "】配置解析查询字段出错,缺少显示列名配置 dataValue!", StatusCodeForFrame.RECORD_CONFIG_ERROR);
                }

                if (column > 0) {
                    sqlResult = sqlResult + " , ";
                }
                sqlResult = sqlResult + obj.getString(FrameConstants.DataRight.objectValue) + " as " + obj.getString(FrameConstants.DataRight.objectId);
                column++;
                if (FrameConstants.DataRight.mustId.equals(obj.getString(FrameConstants.DataRight.objectId))) {
                    isMustId = true;
                }
                    
				headJSON = new JSONObject();
				headJSON.put(FrameConstants.DataRight.displayId, obj.getString(FrameConstants.DataRight.objectId).toUpperCase());
				headJSON.put(FrameConstants.DataRight.displayName,  obj.getString(FrameConstants.DataRight.displayName));
				headJSON.put(FrameConstants.DataRight.isDisplay, obj.getString(FrameConstants.DataRight.isDisplay));
				
				headJSONArray.add(headJSON);
            }
            if (!isMustId) {
            	throw new SystemException("SmDataSource.data_code【" + dataCode + "】】配置解析查询字段出错,必须含有 {dataId:id} 配置!", StatusCodeForFrame.RECORD_CONFIG_ERROR);
            }
            if (FrameConstants.DataRight.selFlag_select.equals(selFlag)) {
            	dataRightModel.getSelectTableDatasMap().put(FrameConstants.DataRight.headJOSNArray, headJSONArray);
            } else if (FrameConstants.DataRight.selFlag_selected.equals(selFlag)) {
            	dataRightModel.getSelectedTableDatasMap().put(FrameConstants.DataRight.headJOSNArray, headJSONArray);
            }
        }

        return sqlResult;
    }
    /**
	 * 获取数据权限树
	 * 
	 * @param config_aim  
	 * @param object_id
	 * @return 
	 * @throws Exception
	 */
	public List<TreeModel> getDataRightTree(int config_aim, String object_id) {
		
		if(config_aim<1){
			throw new SystemException("获取数据权限树  对象类型  不能为空!", StatusCodeForFrame.PROPERTY_IS_NULL);
		}
		if(StringUtil.isBlank(object_id)){
			throw new SystemException("获取数据权限树 查询对象编码 不能为空!", StatusCodeForFrame.PROPERTY_IS_NULL);
		}
		
		Map conditionMap = new HashMap();
		conditionMap.put("config_aim", config_aim);
		if(config_aim==FrameConstants.DataRight.CONFIG_AIM_OPER){
			SmOper smOper = operMapper.selectByPrimaryKey(object_id);
			if(smOper!=null){
				conditionMap.put("right_level", smOper.getOperLevel());
				conditionMap.put("province_id", smOper.getProvinceId());
				conditionMap.put("area_id", smOper.getAreaId());
				conditionMap.put("county_id", smOper.getCountyId());
			}else{
				throw new SystemException("工号不存在!", StatusCodeForFrame.RECORD_NOT_EXISTS);
			}
			
		}else if(config_aim==FrameConstants.DataRight.CONFIG_AIM_FS){
			SmFuncSet  funcset=funcSetMapper.selectByPrimaryKey(object_id);
			if(funcset!=null){
				conditionMap.put("right_level", funcset.getFsLevel());
				conditionMap.put("province_id", funcset.getProvinceId());
				conditionMap.put("area_id", funcset.getAreaId());
				conditionMap.put("county_id", funcset.getCountyId());
			}else{
				throw new SystemException("功能集不存在!", StatusCodeForFrame.RECORD_NOT_EXISTS);
			}
		}else{
			throw new SystemException("SmDataRightServiceImpl.getDataRightList  config_aim越界!", StatusCodeForFrame.RECORD_NOT_EXISTS);
		}
		
		List<SmDataRight> dataRightList= dataRightMapper.getDataRightTree(conditionMap);
		
		//封装结果树
		List<TreeModel> dataRightTree = new ArrayList<TreeModel>();
		if(dataRightList!=null&&dataRightList.size()>0){
			TreeModel treeModel=null;
			for(SmDataRight dataRightInfo:dataRightList){
				treeModel = new TreeModel();
				treeModel.setId(dataRightInfo.getDataCode());
				treeModel.setText(dataRightInfo.getDataName());
				dataRightTree.add(treeModel);
			}
		}
		return dataRightTree;
	}
	class SortByCHINESE implements Comparator {
		public int compare(Object a, Object b) {
			int flag=0;
			if("09".equals(((Map<String, Object>) b).get("CODE"))
					||"000".equals(((Map<String, Object>) b).get("CODE"))
					||"00000".equals(((Map<String, Object>) b).get("CODE"))){
				flag=1;
			}else if("09".equals(((Map<String, Object>) a).get("CODE"))
					||"000".equals(((Map<String, Object>) a).get("CODE"))
					||"00000".equals(((Map<String, Object>) a).get("CODE"))){
				flag=-1;
			}else{
				flag=Collator.getInstance(Locale.CHINESE).compare(((Map<String, Object>) a).get("NAME"),((Map<String, Object>) b).get("NAME"));
			}
			return flag;
		}
	}

	/**
	 * 获取数据权限查询区下拉列表数据
	 * 
	 * @param sql
	 * @param param
	 * @param sysId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> dataRightChangeDataList(String sql,
			String param, String sysId,String objId,int objType) {

		if (StringUtil.isBlank(sql)) {
			throw new SystemException("获取下拉列表数据失败，查询语句不能为空!",
					StatusCodeForFrame.PROPERTY_IS_NULL);
		}
//		if (StringUtil.isBlank(param)) {
//			throw new SystemException("获取下拉列表数据失败，上级联动值不能为空!",
//					StatusCodeForFrame.PROPERTY_IS_NULL);
//		}
		// 增加权限信息
		Map rightMap = new HashMap();
		sql=this.setOperRight(sql,objId,objType,rightMap);
		if(param!=null){
			sql = sql.replaceAll("\\{XXX\\}", "'" + param.trim() + "'");
		}else{
			sql = sql.replaceAll("\\{XXX\\}", "''");
		}
		
		Map qryMap = new HashMap();
		qryMap.put(FrameConstants.DataRight.sysExecSql, sql);
		List<Map<String, Object>> list = dataRightMapper.commQuery(qryMap);
		if (list != null && list.size() > 0) {
			Collections.sort(list, new SortByCHINESE());
		}
		setListHead(list,rightMap,sysId);
		return list;
	}
	private String setOperRight(String sql,String objId,int objType,Map rightMap) {
		if (!StringUtil.isBlank(sql)) {
			if(objType==FrameConstants.DataRight.CONFIG_AIM_OPER){
				SmOper smOper=operMapper.selectByPrimaryKey(objId);
				if(smOper!=null){
					sql = sql.replaceAll(FrameConstants.DataRight.DATA_RIGHT_SYSPROVINCEID,smOper.getProvinceId()!=null?"\'"+smOper.getProvinceId()+"\'":"\'\'")
						 .replaceAll(FrameConstants.DataRight.DATA_RIGHT_SYSAREAID,smOper.getAreaId() != null?"\'"+smOper.getAreaId()+"\'":"\'\'")
						 .replaceAll(FrameConstants.DataRight.DATA_RIGHT_SYSCOUNTYID,smOper.getCountyId()!=null?"\'"+smOper.getCountyId()+"\'":"\'\'")
						 .replaceAll(FrameConstants.DataRight.DATA_RIGHT_SYSOBJECTID,"\'"+objId+"\'")
						 ;
					rightMap.put(FrameConstants.DataRight.DATA_RIGHT_SYSPROVINCEID, smOper.getProvinceId());
					rightMap.put(FrameConstants.DataRight.DATA_RIGHT_SYSAREAID, smOper.getAreaId());
					rightMap.put(FrameConstants.DataRight.DATA_RIGHT_SYSCOUNTYID, smOper.getCountyId());
					rightMap.put(FrameConstants.DataRight.DATA_RIGHT_SYSOBJECTID, objId);
					
					String sss="";
					sss.replaceAll(FrameConstants.DataRight.DATA_RIGHT_SYSAREAID,smOper.getAreaId() != null?"":"\'"+smOper.getAreaId()+"\'");
				}
			}else if(objType==FrameConstants.DataRight.CONFIG_AIM_FS){
				SmFuncSet funcSet = funcSetMapper.selectByPrimaryKey(objId);
				if(funcSet!=null){
					sql = sql.replaceAll(FrameConstants.DataRight.DATA_RIGHT_SYSPROVINCEID,funcSet.getProvinceId()!=null?"\'"+funcSet.getProvinceId()+"\'":"\'\'")
							 .replaceAll(FrameConstants.DataRight.DATA_RIGHT_SYSAREAID,funcSet.getAreaId()!=null?"\'"+funcSet.getAreaId()+"\'":"\'\'")
							 .replaceAll(FrameConstants.DataRight.DATA_RIGHT_SYSCOUNTYID,funcSet.getCountyId()!=null?"\'"+funcSet.getCountyId()+"\'":"\'\'")
							 .replaceAll(FrameConstants.DataRight.DATA_RIGHT_SYSOBJECTID,"\'"+objId+"\'")
							 ;
					rightMap.put(FrameConstants.DataRight.DATA_RIGHT_SYSPROVINCEID, funcSet.getProvinceId());
					rightMap.put(FrameConstants.DataRight.DATA_RIGHT_SYSAREAID, funcSet.getAreaId());
					rightMap.put(FrameConstants.DataRight.DATA_RIGHT_SYSCOUNTYID, funcSet.getCountyId());
					rightMap.put(FrameConstants.DataRight.DATA_RIGHT_SYSOBJECTID, objId);
				}
			}
		}
		return sql;
	}
	private void setListHead(List<Map<String, Object>> list,Map rightMap,String sysId) {
		boolean headFlag=true;
		if(list!=null&&list.size()>0&&rightMap!=null&&!StringUtil.isBlank(sysId)){
			if(FrameConstants.DataRight.DATA_RIGHT_SYSPROVINCEID.equals(sysId)
					&& !FrameConstants.SmRegion.ZB.equals(rightMap.get(FrameConstants.DataRight.DATA_RIGHT_SYSPROVINCEID))){
				//省份
				headFlag=false;
			}else if(FrameConstants.DataRight.DATA_RIGHT_SYSAREAID.equals(sysId)
					&&rightMap.get(FrameConstants.DataRight.DATA_RIGHT_SYSAREAID)!=null
					&&!FrameConstants.SmRegion.PROVINCE_CENTER.equals(rightMap.get(FrameConstants.DataRight.DATA_RIGHT_SYSAREAID))){
				//地市 
				headFlag=false;
			}else if(FrameConstants.DataRight.DATA_RIGHT_SYSCOUNTYID.equals(sysId)
					&&rightMap.get(FrameConstants.DataRight.DATA_RIGHT_SYSCOUNTYID)!=null
					&&!FrameConstants.SmRegion.AREA_CENTER.equals(rightMap.get(FrameConstants.DataRight.DATA_RIGHT_SYSCOUNTYID))){
				//区县
				headFlag=false;
			}
		}
		if(headFlag){
			Map<String, Object> headMap=new HashMap<String, Object>();
			headMap.put("NAME", "--请选择--");
			headMap.put("CODE", "\'\'");
			list.add(0, headMap);
		}
	}
}
