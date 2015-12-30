package cn.cttic.sysframe.frame.dao;

import java.util.List;

import cn.cttic.sysframe.common.basedao.BaseMapper;
import cn.cttic.sysframe.frame.domain.SmParamDetail;
import cn.cttic.sysframe.frame.domain.SmParamDetailExample;
import cn.cttic.sysframe.frame.domain.SmParamDetailKey;
import cn.cttic.sysframe.frame.model.SmParamModel;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface SmParamDetailMapper extends BaseMapper<SmParamDetailKey, SmParamDetail, SmParamDetailExample> {
	PageList<SmParamModel> selectBySelective(SmParamModel param, PageBounds pageBounds);
	SmParamModel selectSmParamModelByPrimaryKey(SmParamDetailKey key);
	
    List<SmParamDetail> selectByExample(SmParamDetailExample example);

}