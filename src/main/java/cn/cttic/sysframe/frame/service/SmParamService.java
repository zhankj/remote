package cn.cttic.sysframe.frame.service;

import cn.cttic.sysframe.frame.domain.SmParamDefine;
import cn.cttic.sysframe.frame.domain.SmParamDetail;
import cn.cttic.sysframe.frame.model.SmParamModel;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


public interface SmParamService {
	PageList<SmParamModel> queryParam(SmParamModel smParamModel, PageBounds pageBounds);
	void updateParam(boolean isAdd, SmParamDefine define, SmParamDetail detail);
	SmParamModel loadSmParamModel(SmParamModel param);
}
