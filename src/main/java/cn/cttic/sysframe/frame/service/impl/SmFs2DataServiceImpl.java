package cn.cttic.sysframe.frame.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cttic.sysframe.frame.dao.SmFs2DataMapper;
import cn.cttic.sysframe.frame.domain.SmFs2Data;
import cn.cttic.sysframe.frame.domain.SmFs2DataExample;
import cn.cttic.sysframe.frame.domain.SmFs2DataKey;
import cn.cttic.sysframe.frame.service.SmFs2DataService;

@Service
public class SmFs2DataServiceImpl implements SmFs2DataService {

	@Autowired
	private SmFs2DataMapper mapper;

	@Override
	public List<SmFs2Data> selectByExample(SmFs2DataExample example) {
		return mapper.selectByExample(example);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insertBeans(List<SmFs2Data> records) {
		for (SmFs2Data record : records) {
			mapper.insert(record);
		}
		return 0;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int deleteBeans(List<SmFs2DataKey> records) {
		for (SmFs2DataKey record : records) {
			mapper.deleteByPrimaryKey(record);
		}
		return 0;
	}
	public int checkDataRightsByFsId(String fs_id){
		return mapper.checkDataRightsByFsId(fs_id);
	}
}
