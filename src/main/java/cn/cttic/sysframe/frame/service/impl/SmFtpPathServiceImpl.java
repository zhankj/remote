package cn.cttic.sysframe.frame.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.common.constants.CommonConstants;
import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.frame.dao.SmFtpPathMapper;
import cn.cttic.sysframe.frame.domain.SmFtpPath;
import cn.cttic.sysframe.frame.domain.SmFtpPathExample;
import cn.cttic.sysframe.frame.domain.SmFtpPathExample.Criteria;
import cn.cttic.sysframe.frame.service.SmFtpPathService;
@Service
public class SmFtpPathServiceImpl implements SmFtpPathService {
	@Autowired
	private  SmFtpPathMapper SmFtpPathMapper;
	
	private transient static Log log = LogFactory.getLog(SmFtpPathServiceImpl.class);
	@Override
	public List<SmFtpPath> getSmFtpPath(String ftpPathCode) {
		List<SmFtpPath> SmFtpPathList = new ArrayList<SmFtpPath>();
		String key = ftpPathCode;
		try {
			HashMap<String, List<SmFtpPath>> SmFtpPathMap = loadSmFtpPathCache();
            if (SmFtpPathMap.containsKey(key)) {
                return (List<SmFtpPath>) SmFtpPathMap.get(key);
            }
        } catch (Exception e) {
        	throw new SystemException(StatusCodeForFrame.CACHE_NOT_EXIST_ERROR);
        }
		return SmFtpPathList;
	}
	@Override
	@Cacheable(value="smFtpPathCache")
	public HashMap<String, List<SmFtpPath>> loadSmFtpPathCache() {
		log.debug("加载[FTP_PATH配置]缓存数据......start......");
        HashMap<String, List<SmFtpPath>> cacheMap = new HashMap<String, List<SmFtpPath>>();
        try {
        	SmFtpPathExample smFtpPathExample=	new SmFtpPathExample();
        	Criteria  cr=smFtpPathExample.or();
        	cr.andStateEqualTo(CommonConstants.ftpState.FTP_STATE_U);
        	List<SmFtpPath> smFtpsList = SmFtpPathMapper.selectByExample(smFtpPathExample);
            if (cacheMap != null) {
                String key = null;
                for (int index = 0; index < smFtpsList.size(); index++) {
                	SmFtpPath bean = smFtpsList.get(index);
                    key = bean.getFtpPathCode();
                    if (!cacheMap.containsKey(key)) {
                        cacheMap.put(key, new ArrayList<SmFtpPath>());
                    }
                    cacheMap.get(key).add(bean);
                }
            }
        } catch (Exception e) {
            throw new SystemException(StatusCodeForFrame.CACHE_INITIALIZE_ERROR);
        }
        log.debug("加载[FTP_PATH配置]缓存数据......end......");
        return cacheMap;
	}
}
