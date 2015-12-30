package cn.cttic.sysframe.frame.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cttic.sysframe.frame.dao.SmFs2MenuMapper;
import cn.cttic.sysframe.frame.domain.SmFs2MenuExample;
import cn.cttic.sysframe.frame.domain.SmFs2MenuKey;
import cn.cttic.sysframe.frame.service.SmFs2MenuService;

@Service
public class SmFs2MenuServiceImpl implements SmFs2MenuService {

	@Autowired
	private SmFs2MenuMapper mapper;

	@Override
	public List<SmFs2MenuKey> selectByExample(SmFs2MenuExample example) {
		return mapper.selectByExample(example);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insertBeans(SmFs2MenuExample example, List<SmFs2MenuKey> records) {
		mapper.deleteByExample(example);
		for (SmFs2MenuKey record : records) {
			mapper.insert(record);
		}
		return 0;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int delete(SmFs2MenuExample example) {
		return mapper.deleteByExample(example);
	}
	
	public int checkMenusByFsId(String fs_id){
		return mapper.checkMenusByFsId(fs_id);
	}
}
