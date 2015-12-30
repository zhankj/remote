package cn.cttic.sysframe.frame.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.cttic.sysframe.common.constants.CommonConstants;
import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.frame.dao.SmFileMapper;
import cn.cttic.sysframe.frame.dao.SmJobConfigMapper;
import cn.cttic.sysframe.frame.dao.SmJobLogMapper;
import cn.cttic.sysframe.frame.domain.SmFile;
import cn.cttic.sysframe.frame.domain.SmFileExample;
import cn.cttic.sysframe.frame.domain.SmJobConfig;
import cn.cttic.sysframe.frame.domain.SmJobConfigExample;
import cn.cttic.sysframe.frame.domain.SmJobLog;
import cn.cttic.sysframe.frame.domain.SmJobLogExample;
import cn.cttic.sysframe.frame.domain.SmJobConfigExample.Criteria;
import cn.cttic.sysframe.frame.service.SmJobConfigService;
@Service
public class SmJobConfigServiceImpl implements SmJobConfigService {
	
	private transient static Log log = LogFactory.getLog(SmJobConfigServiceImpl.class);
	@Autowired
	private  SmJobConfigMapper SmJobConfigMapper;
	@Autowired
	private SmFileMapper  smFileMapper;
	@Autowired
	private SmJobLogMapper  smJobLogMapper;
	
	@Override
	public List<SmJobConfig> getSmJobConfig(String jobCode) {
		List<SmJobConfig> SmJobConfigList = new ArrayList<SmJobConfig>();
		String key = jobCode;
		try {
			HashMap<String, List<SmJobConfig>> SmJobConfigMap = loadSmJobConfigCache();
            if (SmJobConfigMap.containsKey(key)) {
                return (List<SmJobConfig>) SmJobConfigMap.get(key);
            }
        } catch (Exception e) {
//            throw new SystemException("获取系统字典参数出错[" + key + "]", e);
        	throw new SystemException(StatusCodeForFrame.FILE_UPLOAD_FAILURE, key);
        }
		return SmJobConfigList;
	}
	@Override
	@Cacheable(value="smJobConfigCache")
	public HashMap<String, List<SmJobConfig>> loadSmJobConfigCache() {
		log.debug("加载[SM_JOB_CONFIG配置]缓存数据......start......");
        HashMap<String, List<SmJobConfig>> cacheMap = new HashMap<String, List<SmJobConfig>>();
        try {
        	SmJobConfigExample smJobConfigExample=new SmJobConfigExample();
        	Criteria Criteria=smJobConfigExample.or();
        	Criteria.andStateEqualTo(CommonConstants.ftpState.FTP_STATE_U);
        	List<SmJobConfig> smFtpsList = SmJobConfigMapper.selectByExample(smJobConfigExample);
            if (cacheMap != null) {
                String key = null;
                for (int index = 0; index < smFtpsList.size(); index++) {
                	SmJobConfig bean = smFtpsList.get(index);
                    key = bean.getJobCode();
                    if (!cacheMap.containsKey(key)) {
                        cacheMap.put(key, new ArrayList<SmJobConfig>());
                    }
                    cacheMap.get(key).add(bean);
                }
            }
        } catch (Exception e) {
        	throw new SystemException(StatusCodeForFrame.FILE_JOB_CONFIG_FAILURE);
        }
        log.debug("加载[SM_JOB_CONFIG配置]缓存数据......end......");
        return cacheMap;
	}
	@Override
	public PageList<SmFile> selectByExample(SmFileExample example,
			PageBounds bounds) {
		return smFileMapper.selectByExample(example, bounds);
	}
	@Override
	public PageList<SmJobLog> selectByExample(SmJobLogExample example,
			PageBounds bounds) {
		return smJobLogMapper.selectByExample(example, bounds);
	}

}
