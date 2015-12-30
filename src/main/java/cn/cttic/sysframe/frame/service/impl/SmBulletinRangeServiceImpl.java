package cn.cttic.sysframe.frame.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.frame.dao.SmBulletinRangeMapper;
import cn.cttic.sysframe.frame.domain.SmBulletinRange;
import cn.cttic.sysframe.frame.domain.SmBulletinRangeExample;
import cn.cttic.sysframe.frame.service.SmBulletinRangeService;
import cn.cttic.sysframe.frame.service.SystemService;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
public class SmBulletinRangeServiceImpl implements SmBulletinRangeService {

	@Autowired
	SmBulletinRangeMapper smBulletinRangeMapperDao;
	
	@Autowired
	private SystemService systemService;
	
	@Override
	public List<SmBulletinRange> queryBeans(Integer bulletinId) {
		SmBulletinRangeExample smBulletinRangeExample = new SmBulletinRangeExample();
		SmBulletinRangeExample.Criteria criteria = smBulletinRangeExample.createCriteria();
		criteria.andBulletinIdEqualTo(bulletinId);
		return smBulletinRangeMapperDao.selectByExample(smBulletinRangeExample);
	}
	
	@Override
	public PageList<Map<String,Object>> querySelector(Map<String,Object> map, PageBounds pageBounds) {
		return smBulletinRangeMapperDao.querySelector(map, pageBounds);
	}
	
	@Override
	public void save(Integer bulletinId, String releaseRange, String operType, List<String> list) {
		if ("_ORG".equals(releaseRange)) {
			// 先删除原有部门发布范围
			SmBulletinRangeExample smBulletinRangeExample = new SmBulletinRangeExample();
			SmBulletinRangeExample.Criteria criteria = smBulletinRangeExample.createCriteria();
			criteria.andBulletinIdEqualTo(bulletinId);
			criteria.andOrgIdIsNotNull();
			smBulletinRangeMapperDao.deleteByExample(smBulletinRangeExample);
			// 将记录在插入
			for (String orgId : list) {
				if (!StringUtils.isBlank(orgId)) {
					SmBulletinRange smBulletinRange = new SmBulletinRange();
					smBulletinRange.setRangeId(systemService.generateId("SM_BULLETIN_RANGE", "RANGE_ID").intValue());
					smBulletinRange.setBulletinId(bulletinId);
					smBulletinRange.setOrgId(orgId);
					smBulletinRangeMapperDao.insert(smBulletinRange);
				}
			}
		} else if ("_STAFF".equals(releaseRange)) {
			if ("ADD".equals(operType)) {
				for (String staffId : list) {
					SmBulletinRange smBulletinRange = new SmBulletinRange();
					smBulletinRange.setRangeId(systemService.generateId("SM_BULLETIN_RANGE", "RANGE_ID").intValue());
					smBulletinRange.setBulletinId(bulletinId);
					smBulletinRange.setStaffId(Long.parseLong(staffId));
					smBulletinRangeMapperDao.insert(smBulletinRange);
				}
			} else if ("DEL".equals(operType)) {
				for (String staffId : list) {
					SmBulletinRangeExample example = new SmBulletinRangeExample();
					SmBulletinRangeExample.Criteria criteria = example.createCriteria();
					criteria.andBulletinIdEqualTo(bulletinId);
					criteria.andStaffIdEqualTo(Long.parseLong(staffId));
					smBulletinRangeMapperDao.deleteByExample(example);
				}
			}
		}
		
		// TODO Auto-generated method stub

	}

}
