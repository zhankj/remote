package cn.cttic.sysframe.frame.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.common.util.SpringContextUtil;
import cn.cttic.sysframe.common.util.bean.BeanCopyUtil;
import cn.cttic.sysframe.frame.dao.SmParamDefineMapper;
import cn.cttic.sysframe.frame.dao.SmParamDetailMapper;
import cn.cttic.sysframe.frame.domain.SmParamDefine;
import cn.cttic.sysframe.frame.domain.SmParamDefineExample;
import cn.cttic.sysframe.frame.domain.SmParamDetail;
import cn.cttic.sysframe.frame.domain.SmParamDetailKey;
import cn.cttic.sysframe.frame.model.SmParamModel;
import cn.cttic.sysframe.frame.service.SmParamService;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
public class SmParamServiceImpl implements SmParamService {
	
	@Autowired
	private SmParamDefineMapper smParamDefineMapper;
	
	@Autowired
	private SmParamDetailMapper smParamDetailMapper;

	@Override
	public PageList<SmParamModel> queryParam(SmParamModel param, PageBounds pageBounds) {
		if (param != null && param.getTypeDesc() != null && !param.getTypeDesc().trim().equals("")) 
			param.setTypeDesc(new StringBuilder("%").append(param.getTypeDesc()).append("%").toString());
		if (param != null && param.getTypeParamDesc() != null && !param.getTypeParamDesc().trim().equals("")) 
			param.setTypeParamDesc(new StringBuilder("%").append(param.getTypeParamDesc()).append("%").toString());
		
		try {
	        return smParamDetailMapper.selectBySelective(param, pageBounds);
        }
        catch (Exception e) {
        	throw new SystemException(e, StatusCodeForFrame.DB_OPERATION_ERROR);
        }
	}
	
	@Cacheable(value="smParamManageCache", key="'listAllTypeCode'")
	public List<SmParamDefine> listAllTypeCode() {
		SmParamDefineExample example = new SmParamDefineExample();
		try {
	        return smParamDefineMapper.selectByExample(example);
        }
        catch (Exception e) {
        	throw new SystemException(e, StatusCodeForFrame.DB_OPERATION_ERROR);
        }
	}
	
	private boolean isModifiedSmParamDefine(SmParamDefine define) {
		return !SpringContextUtil.getBean(getClass()).listAllTypeCode().contains(define);
	}
	
	@CacheEvict(value="smParamManageCache", key="'listAllTypeCode'")
	public void mergeSmParamDefine(SmParamDefine define) {
		define.setModifyDate(new Date());
		
		try {
	        if (smParamDefineMapper.selectByPrimaryKey(define.getTypeCode()) == null) {
	        	smParamDefineMapper.insertSelective(define);
	        } else {
	//        	BeanCopyUtil.setEmptyStringToNull(define, Arrays.asList("typeDesc"), true);
	        	smParamDefineMapper.updateByPrimaryKeySelective(define);
	        }
        }
        catch (Exception e) {
        	throw new SystemException(e, StatusCodeForFrame.DB_OPERATION_ERROR);
        }
	}

	@Override
	@CacheEvict(value = "smSysDictCache", key = "'MAP'")
	public void updateParam(boolean isAdd, SmParamDefine define, SmParamDetail detail) {
		if (isModifiedSmParamDefine(define)) {
			SpringContextUtil.getBean(getClass()).mergeSmParamDefine(define);
        }
		detail.setModifyDate(new Date());
		try {
			if (isAdd) {
				smParamDetailMapper.insertSelective(detail);
			} else {
				smParamDetailMapper.updateByPrimaryKeySelective(detail);
			}
        }
        catch (Exception e) {
        	throw new SystemException(e, StatusCodeForFrame.DB_OPERATION_ERROR);
        }
	}
	
	@Override
	public SmParamModel loadSmParamModel(SmParamModel param) {
		if (param == null ||  param.getLanguageType() == null || param.getTypeCode() == null || param.getTypeParamCode() == null) throw new SystemException(StatusCodeForFrame.PROPERTY_IS_NULL);
		try {
			SmParamDetailKey key = new SmParamDetailKey();
			BeanCopyUtil.copyProperties(key, param, Arrays.asList("typeCode", "typeParamCode", "languageType"));
	        return smParamDetailMapper.selectSmParamModelByPrimaryKey(key);
        }
        catch (Exception e) {
        	throw new SystemException(e, StatusCodeForFrame.DB_OPERATION_ERROR);
        }
	}

}
