package cn.cttic.sysframe.frame.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.frame.dao.SmCountyMapper;
import cn.cttic.sysframe.frame.domain.SmCounty;
import cn.cttic.sysframe.frame.domain.SmCountyExample;
import cn.cttic.sysframe.frame.service.SmCountyService;

@Service
public class SmCountyServiceImpl implements SmCountyService {
	
	@Autowired
	private SmCountyMapper smCountyMapper;

	@Override
	public List<SmCounty> selectByExample(SmCountyExample example) {
		try {
			return smCountyMapper.selectByExample(example);
		} catch (Exception ex) {
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
    public SmCounty selectByPrimaryKey(String countyId) {
		try {
			return smCountyMapper.selectByPrimaryKey(countyId);
		} catch (Exception ex) {
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}
    }

}
