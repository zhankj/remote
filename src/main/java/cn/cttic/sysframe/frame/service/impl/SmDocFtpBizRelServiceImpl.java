package cn.cttic.sysframe.frame.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.frame.dao.SmDocFtpBizRelMapper;
import cn.cttic.sysframe.frame.domain.SmDocFtpBizRel;
import cn.cttic.sysframe.frame.domain.SmDocFtpBizRelExample;
import cn.cttic.sysframe.frame.domain.SmDocFtpBizRelExample.Criteria;
import cn.cttic.sysframe.frame.service.SmDocFtpBizRelService;

@Service
public class SmDocFtpBizRelServiceImpl implements SmDocFtpBizRelService {

	@Autowired
	private SmDocFtpBizRelMapper smDocFtpBizRelMapper;

	@Override
	public int insertSmDocFtpBizRel(SmDocFtpBizRel record) {
		return smDocFtpBizRelMapper.insert(record);
	}

	@Override
	public List<SmDocFtpBizRel> selecebyExample(SmDocFtpBizRelExample criteria) {
		return smDocFtpBizRelMapper.selectByExample(criteria);
	}

	@Override
	public int deleteByExample(SmDocFtpBizRelExample criteria) {
		return smDocFtpBizRelMapper.deleteByExample(criteria);
	}

	@Override
	public int deleteByPrimaryKey(long relId) {
		return smDocFtpBizRelMapper.deleteByPrimaryKey(relId);
	}

	@Override
	public SmDocFtpBizRel selectSmDocFtpBizRelByBizType(String bizType) {
		SmDocFtpBizRelExample example = new SmDocFtpBizRelExample();
		Criteria criteria = example.createCriteria();
		criteria.andBizTypeEqualTo(bizType);
		List<SmDocFtpBizRel> smDocFtpBizRelList = this.selecebyExample(example);
		if (smDocFtpBizRelList != null && smDocFtpBizRelList.size() > 0) {
			return smDocFtpBizRelList.get(0);
		}
		return null;
	}

}
