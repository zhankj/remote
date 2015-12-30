package cn.cttic.sysframe.frame.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.frame.dao.SmDocInfoMapper;
import cn.cttic.sysframe.frame.domain.SmDocFtpBizRel;
import cn.cttic.sysframe.frame.domain.SmDocInfo;
import cn.cttic.sysframe.frame.domain.SmDocInfoExample;
import cn.cttic.sysframe.frame.domain.SmFtpPath;
import cn.cttic.sysframe.frame.service.SmDocFtpBizRelService;
import cn.cttic.sysframe.frame.service.SmDocInfoService;
import cn.cttic.sysframe.frame.service.SmFtpPathService;
import cn.cttic.sysframe.frame.service.SystemService;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
public class SmDocInfoServiceImpl implements SmDocInfoService {

	@Autowired
	private SmDocInfoMapper smDocInfoMapper;

	@Autowired
	private SmDocFtpBizRelService smDocFtpBizRelService;

	@Autowired
	private SmFtpPathService smFtpPathService;

	@Autowired
	private SystemService systemService;
	
	@Override
	public int insertSmDocInfo(SmDocInfo record) {
		return smDocInfoMapper.insert(record);
	}

	@Override
	public List<SmDocInfo> selectByExample(SmDocInfoExample example) {
		return smDocInfoMapper.selectByExample(example);
	}

	@Override
	public SmDocInfo selectByPrimaryKey(Long documentId) {
		return smDocInfoMapper.selectByPrimaryKey(documentId);
	}

	@Override
	public int deleteByPrimaryKey(Long documentId) {
		return smDocInfoMapper.deleteByPrimaryKey(documentId);
	}

	@Override
	public int updateByExampleSelective(SmDocInfo record, SmDocInfoExample example) {
		return smDocInfoMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(SmDocInfo record, SmDocInfoExample example) {
		return smDocInfoMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(SmDocInfo record) {
		return smDocInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SmDocInfo record) {
		return smDocInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public HashMap<String, String> createSmDocInfo(String documentName, String transDocName, String bizId, String bizType, String serialNo,
	        long documentSize, String state, String orgId, String operId, String note) {

		HashMap<String, String> map = new HashMap<String, String>();
		if (StringUtils.isBlank(documentName)) {
			map.put("RET_CODE", "0001");
			map.put("RET_MSG", "原始文档文件名不能为空");
			return map;
		}
		if (StringUtils.isBlank(bizId)) {
			map.put("RET_CODE", "0002");
			map.put("RET_MSG", "业务编码不能为空");
			return map;
		}
		if (StringUtils.isBlank(bizType)) {
			map.put("RET_CODE", "0003");
			map.put("RET_MSG", "业务类型不能为空");
			return map;
		}

		SmDocFtpBizRel bizRel = smDocFtpBizRelService.selectSmDocFtpBizRelByBizType(bizType);
		if (bizRel == null) {
			map.put("RET_CODE", "0004");
			map.put("RET_MSG", "未知的业务类型");
			return map;
		}
		String ftpPathCode = bizRel.getFtpPathCode();
		List<SmFtpPath> smFtpPathList = smFtpPathService.getSmFtpPath(ftpPathCode);
		if (smFtpPathList != null && smFtpPathList.size() == 0) {
			map.put("RET_CODE", "0005");
			map.put("RET_MSG", "未知的FTP路径编码");
			return map;
		}
		SmFtpPath smFtpPath = smFtpPathList.get(0);
		SmDocInfo info = new SmDocInfo();
		info.setDocumentName(documentName);
		if (StringUtils.isBlank(transDocName)) {
			info.setTransDocName(documentName);
		} else {
			info.setTransDocName(transDocName);
		}
		String documentType = StringUtils.substringAfterLast(documentName, ".");
		Date currTime = systemService.getSystemDate();
		info.setDocumentType(documentType);
		info.setBizId(bizId);
		info.setBizType(bizType);
		info.setDocumentSize(documentSize);
		info.setPath(smFtpPath.getRemotePath());
		info.setFtpCode(smFtpPath.getFtpCode());
		info.setUploadDate(currTime);
		info.setSerialNo(serialNo);
		info.setState(state);
		info.setOrgId(orgId);
		info.setOperId(operId);
		info.setOperDate(currTime);
		info.setNote(note);
		int retCode = this.insertSmDocInfo(info);
		if (retCode > 0) {
			map.put("RET_CODE", "0000");
			map.put("RET_MSG", "保存文档信息成功");
			return map;
		}
		map.put("RET_CODE", "-1");
		map.put("RET_MSG", "保存文档信息失败");
		return map;
	}

	@Override
	public PageList<SmDocInfo> selectByExample(SmDocInfoExample example, PageBounds pageBounds) {
		return smDocInfoMapper.selectByExample(example, pageBounds);
	}
}
