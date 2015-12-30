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
import cn.cttic.sysframe.frame.dao.SmFtpMapper;
import cn.cttic.sysframe.frame.domain.SmFtp;
import cn.cttic.sysframe.frame.domain.SmFtpExample;
import cn.cttic.sysframe.frame.domain.SmFtpExample.Criteria;
import cn.cttic.sysframe.frame.service.SmFtpService;

@Service
public class SmFtpServiceImpl implements SmFtpService {

	private transient static Log log = LogFactory.getLog(SmFtpServiceImpl.class);

	@Autowired
	private SmFtpMapper SmFtpMapper;

	@Override
	public List<SmFtp> getSmFtp(String ftpCode) {
		List<SmFtp> SmFtpList = new ArrayList<SmFtp>();
		String key = ftpCode;
		try {
			HashMap<String, List<SmFtp>> smFtpMap = loadSmFtpCache();
			if (smFtpMap.containsKey(key)) {
				return (List<SmFtp>) smFtpMap.get(key);
			}
		}
		catch (Exception e) {
			// throw new SystemException("获取系统字典参数出错[" + key + "]", e);
			throw new SystemException(StatusCodeForFrame.CACHE_NOT_EXIST_ERROR);
		}
		return SmFtpList;
	}

	@Override
	@Cacheable(value = "smFtpCache")
	public HashMap<String, List<SmFtp>> loadSmFtpCache() {
		log.debug("加载[FTP配置]缓存数据......start......");
		HashMap<String, List<SmFtp>> cacheMap = new HashMap<String, List<SmFtp>>();
		try {
			SmFtpExample smFtpExample = new SmFtpExample();
			Criteria c = smFtpExample.or();
			c.andStateEqualTo(CommonConstants.ftpState.FTP_STATE_U);
			List<SmFtp> smFtpsList = SmFtpMapper.selectByExample(smFtpExample);
			if (cacheMap != null) {
				String key = null;
				for (int index = 0; index < smFtpsList.size(); index++) {
					SmFtp bean = smFtpsList.get(index);
					key = bean.getFtpCode();
					if (!cacheMap.containsKey(key)) {
						cacheMap.put(key, new ArrayList<SmFtp>());
					}
					cacheMap.get(key).add(bean);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(StatusCodeForFrame.CACHE_INITIALIZE_ERROR);
		}
		log.debug("加载[FTP配置]缓存数据......end......");
		return cacheMap;
	}

}
