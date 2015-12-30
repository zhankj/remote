package cn.cttic.sysframe.frame.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.common.util.StringUtil;
import cn.cttic.sysframe.frame.domain.SmOper;
import cn.cttic.sysframe.frame.domain.SmSysDict;
import cn.cttic.sysframe.frame.facade.ModPasswordFacadeService;
import cn.cttic.sysframe.frame.service.SmOperService;
import cn.cttic.sysframe.frame.service.SmSysDictService;
import cn.cttic.sysframe.frame.service.SystemService;
import cn.cttic.sysframe.remote.annotation.ReflectionInvokable;

@Service
@ReflectionInvokable(name = "modPassword")
public class ModPasswordFacadeServiceImpl implements ModPasswordFacadeService {

	@Autowired
	private SmOperService smOperService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private SmSysDictService sysDictService;

	/**
	 * 个人密码修改
	 * 
	 * @param operId 工号标识
	 * @param oldPassword 工号原密码（加密后）
	 * @param newpassword 工号新密码（加密后）
	 * @return
	 */
	@Override
	@ReflectionInvokable(name = "modPassword1")
	public void modPassword(String operId, String oldPassword, String newpassword, String systemId) {
		try {
			if (StringUtil.isBlank(operId)) {
				throw new SystemException("操作员编码不能为空！", StatusCodeForFrame.PROPERTY_IS_NULL);
			}
			if (StringUtil.isBlank(oldPassword)) {
				throw new SystemException("原密码不能为空！", StatusCodeForFrame.PROPERTY_IS_NULL);
			}
			if (StringUtil.isBlank(newpassword)) {
				throw new SystemException("新密码不能为空！", StatusCodeForFrame.PROPERTY_IS_NULL);
			}
			if (StringUtil.isBlank(systemId)) {
				throw new SystemException("系统标识不能为空！", StatusCodeForFrame.PROPERTY_IS_NULL);
			}

			SmOper smOper = smOperService.getBean(operId);
			if (smOper == null || smOper.getOperId() == null) {
				throw new SystemException("操作员数据不存在！", StatusCodeForFrame.RECORD_NOT_EXISTS);
			} else {
				if (!oldPassword.equals(smOper.getLoginPwd())) {
					throw new SystemException("原密码与系统中密码不一致！", StatusCodeForFrame.RECORD_NOT_EXISTS);
				}
				
				SmSysDict sysDict=sysDictService.getSmSysDict("SYSTEM_MODULE",systemId);
				SmOper smOperTemp = new SmOper();
				smOperTemp.setOperId(operId);
				smOperTemp.setLoginPwd(newpassword);
				smOperTemp.setModifyDate(systemService.getSystemDate());
				if(sysDict!=null&&!StringUtil.isBlank(sysDict.getParamDesc())){
					smOperTemp.setNote(sysDict.getParamDesc()+"个人密码修改");
				}else{
					smOperTemp.setNote("系统标识为空，无法确认个人修改密码系统入口");
				}
				smOperService.updateByPrimaryKey(smOperTemp);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new SystemException("修改密码出错：" + e.getMessage(), StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}
}
