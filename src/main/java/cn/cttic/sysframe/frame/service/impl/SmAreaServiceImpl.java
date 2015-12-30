package cn.cttic.sysframe.frame.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.frame.dao.SmAreaMapper;
import cn.cttic.sysframe.frame.domain.SmArea;
import cn.cttic.sysframe.frame.domain.SmAreaExample;
import cn.cttic.sysframe.frame.service.SmAreaService;

@Service
public class SmAreaServiceImpl implements SmAreaService {
	
	@Autowired
	private SmAreaMapper smAreaMapper;

	@Override
	public List<SmArea> selectByExample(SmAreaExample example) {
		try {
			return smAreaMapper.selectByExample(example);
		} catch (Exception ex) {
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
    public SmArea selectByPrimaryKey(String areaId) {
		try {
			return smAreaMapper.selectByPrimaryKey(areaId);
		} catch (Exception ex) {
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}
    }

}
