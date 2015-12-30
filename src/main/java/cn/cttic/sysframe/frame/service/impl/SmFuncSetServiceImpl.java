package cn.cttic.sysframe.frame.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.common.util.StringUtil;
import cn.cttic.sysframe.frame.dao.SmFuncSetMapper;
import cn.cttic.sysframe.frame.domain.SmFs2MenuExample;
import cn.cttic.sysframe.frame.domain.SmFs2MenuKey;
import cn.cttic.sysframe.frame.domain.SmFuncSet;
import cn.cttic.sysframe.frame.domain.SmFuncSetExample;
import cn.cttic.sysframe.frame.service.SmFs2DataService;
import cn.cttic.sysframe.frame.service.SmFs2MenuService;
import cn.cttic.sysframe.frame.service.SmFuncSetService;
import cn.cttic.sysframe.frame.service.SmRole2FsService;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
public class SmFuncSetServiceImpl implements SmFuncSetService {

	@Autowired
	private SmFuncSetMapper mapper;

	@Autowired
	private SmFs2MenuService smFs2MenuService;

	@Autowired
	private SmFs2DataService smFs2DataService;
	
	@Autowired
	private SmRole2FsService smRole2FsService;

	@Override
	public PageList<SmFuncSet> queryPage(SmFuncSetExample example, PageBounds pageBounds) {
		return mapper.queryPage(example, pageBounds);
	}
	
	@Override
	public PageList<SmFuncSet> queryPageByMap(Map qryMap, PageBounds pageBounds) {
		return mapper.queryPageByMap(qryMap, pageBounds);
	}


	@Override
	public SmFuncSet selectByPrimaryKey(String fsId) {
		return mapper.selectByPrimaryKey(fsId);
	}

	@Override
	public int insert(SmFuncSet record) {
		return mapper.insert(record);
	}

	@Override
	public int updateByPrimaryKeySelective(SmFuncSet record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int insertRel(SmFuncSet record, String[] menuNodeIds) {
		// 1、获取功能集ID
		String fsId = record.getFsId();
		// 2、建立功能集与菜单权限关系
		SmFs2MenuExample smFs2MenuExample = new SmFs2MenuExample();
		SmFs2MenuExample.Criteria smFs2MenuExampleCriteria = smFs2MenuExample.createCriteria();
		smFs2MenuExampleCriteria.andFsIdEqualTo(fsId);
		List<SmFs2MenuKey> smFs2MenuKeyRecords = new ArrayList<SmFs2MenuKey>();
		for (String menuNodeId : menuNodeIds) {
			if (!StringUtil.isBlank(menuNodeId)) {
				SmFs2MenuKey smFs2MenuKey = new SmFs2MenuKey();
				smFs2MenuKey.setFsId(fsId);
				smFs2MenuKey.setMenuId(menuNodeId);
				smFs2MenuKeyRecords.add(smFs2MenuKey);
			}
		}
		smFs2MenuService.insertBeans(smFs2MenuExample, smFs2MenuKeyRecords);
		// 3、建立功能集与数据权限关系

		// 4、保存功能集信息
		return mapper.insert(record);
	}

	@Override
	public int updateRel(SmFuncSet record, String[] menuNodeIds) {
		// 1、获取功能集ID
		String fsId = record.getFsId();
		// 2、建立功能集与菜单权限关系
		SmFs2MenuExample smFs2MenuExample = new SmFs2MenuExample();
		SmFs2MenuExample.Criteria smFs2MenuExampleCriteria = smFs2MenuExample.createCriteria();
		smFs2MenuExampleCriteria.andFsIdEqualTo(fsId);
		List<SmFs2MenuKey> smFs2MenuKeyRecords = new ArrayList<SmFs2MenuKey>();
		for (String menuNodeId : menuNodeIds) {
			SmFs2MenuKey smFs2MenuKey = new SmFs2MenuKey();
			smFs2MenuKey.setFsId(fsId);
			smFs2MenuKey.setMenuId(menuNodeId);
			smFs2MenuKeyRecords.add(smFs2MenuKey);
		}
		smFs2MenuService.insertBeans(smFs2MenuExample, smFs2MenuKeyRecords);
		// 3、建立功能集与数据权限关系

		// 4、保存功能集信息
		SmFuncSet smFuncSet = mapper.selectByPrimaryKey(fsId);
		smFuncSet.setFsName(record.getFsName());
		smFuncSet.setSystemId(record.getSystemId());
		smFuncSet.setFsDesc(record.getFsDesc());
		smFuncSet.setProvinceId(record.getProvinceId());
		smFuncSet.setAreaId(record.getAreaId());
		smFuncSet.setCountyId(record.getCountyId());
		smFuncSet.setFsLevel(record.getFsLevel());
		smFuncSet.setState(record.getState());
		return mapper.updateByPrimaryKey(smFuncSet);
	}
	public String validateBusi(String fsId){
		String msg="";
		if(StringUtil.isBlank(fsId)){
			//检查功能集是否分配了菜单
		    int menusCount =smFs2MenuService.checkMenusByFsId(fsId);
		    if(menusCount>0){
		    	if(StringUtil.isBlank(msg)){
		    		msg="该功能集分配了菜单";
		    	}
		    }
			//检查功能集是否分配了数据权限
		    int dataRightsCount =smFs2DataService.checkDataRightsByFsId(fsId);
		    if(dataRightsCount>0){
		    	if(StringUtil.isBlank(msg)){
		    		msg="该功能集分配了数据权限";
		    	}else{
		    		msg=msg+"，数据权限";
		    	}
		    }
			//检查功能集是否分配给了角色
		    int rolesCount =smRole2FsService.checkRole2FsByFsId(fsId);
		}
    	if(!StringUtil.isBlank(msg)){
    		msg=msg+"，不能做修改、删除操作！";
    	}
		return msg;
	}
}
