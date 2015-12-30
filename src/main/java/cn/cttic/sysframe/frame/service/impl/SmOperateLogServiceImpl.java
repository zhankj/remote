package cn.cttic.sysframe.frame.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.frame.dao.SmOperateLogMapper;
import cn.cttic.sysframe.frame.domain.SmOperateLogExample;
import cn.cttic.sysframe.frame.domain.SmOperateLogWithBLOBs;
import cn.cttic.sysframe.frame.service.SmOperateLogService;

@Service
public class SmOperateLogServiceImpl implements SmOperateLogService {
	
	@Autowired
	private SmOperateLogMapper smOperateLogMapper;

	@Override
    public List<SmOperateLogWithBLOBs> selectByExampleWithBLOBs(SmOperateLogExample example) {
		try {
			return smOperateLogMapper.selectByExampleWithBLOBs(example);
		} catch (Exception ex) {
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}
    }

	@Override
    public int insertSelective(SmOperateLogWithBLOBs record) {
		try {
			return smOperateLogMapper.insertSelective(record);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}
    }

	@Override
    public PageList<SmOperateLogWithBLOBs> query(SmOperateLogExample example, PageBounds pageBounds) {
		try {
			return smOperateLogMapper.query(example,pageBounds);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}
    }

	@Override
    public SmOperateLogWithBLOBs selectByPrimaryKey(Long logId) {
		try {
			return smOperateLogMapper.selectByPrimaryKey(logId);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}
    }
	

}
