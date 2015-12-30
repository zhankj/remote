package cn.cttic.sysframe.frame.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.frame.dao.SmLogonLogMapper;
import cn.cttic.sysframe.frame.domain.SmLogonLog;
import cn.cttic.sysframe.frame.domain.SmLogonLogExample;
import cn.cttic.sysframe.frame.service.SmLogonLogService;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
public class SmLogonLogServiceImpl implements SmLogonLogService {
	
	@Autowired
	private SmLogonLogMapper smLogonLogMapper;

	@Override
    public PageList<SmLogonLog> query(SmLogonLogExample example, PageBounds pageBounds) {
		try {
			return smLogonLogMapper.query(example, pageBounds);
		} catch (Exception ex) {
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}
    }

	@Override
    public int insertSelective(SmLogonLog record) {
		try {
			return smLogonLogMapper.insertSelective(record);
		} catch (Exception ex) {
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}
    }
	
}
