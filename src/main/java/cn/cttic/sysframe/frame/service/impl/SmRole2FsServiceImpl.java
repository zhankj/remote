package cn.cttic.sysframe.frame.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.frame.dao.SmRole2FsMapper;
import cn.cttic.sysframe.frame.service.SmFs2DataService;
import cn.cttic.sysframe.frame.service.SmRole2FsService;

@Service
public class SmRole2FsServiceImpl implements SmRole2FsService {
	
	@Autowired
	private SmRole2FsMapper mapper;
	
	public int checkRole2FsByFsId(String fs_id){
		return mapper.checkRole2FsByFsId(fs_id);
	}
}
