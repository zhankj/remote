package cn.cttic.sysframe.frame.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.frame.dao.SmFileMapper;
import cn.cttic.sysframe.frame.domain.SmFile;
import cn.cttic.sysframe.frame.domain.SmFileExample;
import cn.cttic.sysframe.frame.service.SmFileService;
@Service
public class SmFileServiceImpl implements SmFileService {

	@Autowired
	private SmFileMapper SmFileMapper;
	
	public  List<SmFile> selectByExample(SmFileExample example){
		  return SmFileMapper.selectByExample(example);
	}
	@Override
	public int insert(SmFile record) {
		return SmFileMapper.insert(record);
	}
	@Override
	public int updateByPrimaryKeySelective(SmFile record) {
		return SmFileMapper.updateByPrimaryKeySelective(record);
	}
}
