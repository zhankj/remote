package cn.cttic.sysframe.frame.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.frame.dao.SmDocDoneLogMapper;
import cn.cttic.sysframe.frame.domain.SmDocDoneLog;
import cn.cttic.sysframe.frame.domain.SmDocDoneLogExample;
import cn.cttic.sysframe.frame.service.SmDocDoneLogService;

@Service
public class SmDocDoneLogServiceImpl implements SmDocDoneLogService {

	@Autowired
	private SmDocDoneLogMapper smDocDoneLogMapper;

	@Override
	public int insert(SmDocDoneLog record) {
		return smDocDoneLogMapper.insert(record);
	}

	@Override
	public List<SmDocDoneLog> selectByExample(SmDocDoneLogExample example) {
		return smDocDoneLogMapper.selectByExample(example);
	}

}
