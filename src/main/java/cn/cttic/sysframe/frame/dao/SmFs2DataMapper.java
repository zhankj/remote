package cn.cttic.sysframe.frame.dao;

import cn.cttic.sysframe.common.basedao.BaseMapper;
import cn.cttic.sysframe.frame.domain.SmFs2Data;
import cn.cttic.sysframe.frame.domain.SmFs2DataExample;
import cn.cttic.sysframe.frame.domain.SmFs2DataKey;

public interface SmFs2DataMapper extends BaseMapper<SmFs2DataKey, SmFs2Data, SmFs2DataExample> {
	public int checkDataRightsByFsId(String fs_id);
}