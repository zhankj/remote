package cn.cttic.sysframe.frame.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.cttic.sysframe.frame.domain.IntfSms;
import cn.cttic.sysframe.frame.domain.IntfSmsExample;


public interface IntfSmsService {
	int countByExample(IntfSmsExample example);

    int deleteByExample(IntfSmsExample example);

    int insert(IntfSms record);
    
    int saveIntfSms(Short priority,Integer busiType,String objectId, String objectType,String svcNum,String phoneNum,String smsContent,Date sendDate ,String note);

    int insertSelective(IntfSms record);

    List<IntfSms> selectByExample(IntfSmsExample example);

    int updateByExampleSelective(@Param("record") IntfSms record, @Param("example") IntfSmsExample example);

    int updateByExample(@Param("record") IntfSms record, @Param("example") IntfSmsExample example);
}
