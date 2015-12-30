package cn.cttic.sysframe.frame.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.frame.dao.SmProvinceMapper;
import cn.cttic.sysframe.frame.domain.SmProvince;
import cn.cttic.sysframe.frame.domain.SmProvinceExample;
import cn.cttic.sysframe.frame.service.SmProvinceService;

@Service
public class SmProvinceServiceImpl implements SmProvinceService {
	
	@Autowired
	private SmProvinceMapper smProvinceMapper;

	@Override
	public List<SmProvince> selectByExample(SmProvinceExample example) {
		try {
			return smProvinceMapper.selectByExample(example);
		} catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
    public SmProvince selectByPrimaryKey(String provinceId) {
		try {
			return smProvinceMapper.selectByPrimaryKey(provinceId);
		} catch (Exception ex) {
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}
    }


}
