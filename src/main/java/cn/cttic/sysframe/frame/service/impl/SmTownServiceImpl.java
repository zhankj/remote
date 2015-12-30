package cn.cttic.sysframe.frame.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.frame.dao.SmTownMapper;
import cn.cttic.sysframe.frame.domain.SmTown;
import cn.cttic.sysframe.frame.domain.SmTownExample;
import cn.cttic.sysframe.frame.service.SmTownService;

@Service
public class SmTownServiceImpl implements SmTownService {
	
	@Autowired
	
	private SmTownMapper smTownMapper;

	@Override
	public List<SmTown> selectByExample(SmTownExample example) {
		try {
			return smTownMapper.selectByExample(example);
		} catch (Exception ex) {
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
    public SmTown selectByPrimaryKey(String townId) {
		try {
			return smTownMapper.selectByPrimaryKey(townId);
		} catch (Exception ex) {
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}
    }

}
