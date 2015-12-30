package cn.cttic.sysframe.frame.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.frame.dao.IntfSmsMapper;
import cn.cttic.sysframe.frame.domain.IntfSms;
import cn.cttic.sysframe.frame.domain.IntfSmsExample;
import cn.cttic.sysframe.frame.service.IntfSmsService;
import cn.cttic.sysframe.frame.service.SystemService;

@Service
public class IntfSmsServiceImpl implements IntfSmsService {
	
	@Autowired
	private IntfSmsMapper intfSmsMapper;
	
	@Autowired
	private SystemService systemService;

	@Override
    public int countByExample(IntfSmsExample example) {
		try {
			return intfSmsMapper.countByExample(example);
		} catch (Exception ex) {
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}
    }

	@Override
    public int deleteByExample(IntfSmsExample example) {
		try {
			return intfSmsMapper.deleteByExample(example);
		} catch (Exception ex) {
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}
    }

	@Override
    public int insert(IntfSms record) {
		try {
			return intfSmsMapper.insert(record);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}
    }

	@Override
    public int insertSelective(IntfSms record) {
		try {
			return intfSmsMapper.insertSelective(record);
		} catch (Exception ex) {
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}
    }

	@Override
    public List<IntfSms> selectByExample(IntfSmsExample example) {
		try {
			return intfSmsMapper.selectByExample(example);
		} catch (Exception ex) {
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}
    }

	@Override
    public int updateByExampleSelective(IntfSms record, IntfSmsExample example) {
		try {
			return intfSmsMapper.updateByExampleSelective(record,example);
		} catch (Exception ex) {
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}
    }

	@Override
    public int updateByExample(IntfSms record, IntfSmsExample example) {
		try {
			return intfSmsMapper.updateByExample(record,example);
		} catch (Exception ex) {
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}
    }
	/**
	 * 
	 *
	 * @param priority  优先级：默认为1
	 * @param objectId  objectId：用户、客户、账户的编码
	 * @param busiType 业务类型
	 * @param objectType objectType：标识object是用户、客户、还是账户（码值等梁凯确认）
	 * @param svcNum 端口号
	 * @param phoneNum
	 * @param smsContent
	 * @param sendDate 指定发送时间（如果要求实时发送，指定为当前数据库时间）
	 * @return 
	 * @author yangrui
	 * @date 2014-6-12 下午5:29:18
	 */
	@Override
    public int saveIntfSms(Short priority,Integer busiType, String objectId, String objectType, String svcNum, String phoneNum, String smsContent, Date sendDate,String note) {
		IntfSms entity = new IntfSms();
		entity.setPriority(priority);
		entity.setObjectId(objectId);
		entity.setBusiType(busiType);
		entity.setObjectType(objectType);
		entity.setSvcNum(svcNum);
		entity.setPhoneNum(phoneNum);
		entity.setSmsContent(smsContent);
		entity.setSendDate(sendDate);
		
		Long id = systemService.generateId("INTF_SMS", "SO_NBR");
		entity.setSoNbr(id);
		Date date = systemService.getSystemDate();
		entity.setCreateDate(date);
		entity.setSmsSts((short)0);
		entity.setStsDate(date);
		entity.setNote(note);
		
	    return intfSmsMapper.insert(entity);
    }


}
