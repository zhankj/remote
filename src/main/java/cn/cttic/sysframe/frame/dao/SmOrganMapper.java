package cn.cttic.sysframe.frame.dao;

import java.util.List;

import cn.cttic.sysframe.common.basedao.BaseMapper;
import cn.cttic.sysframe.frame.domain.SmOrgan;
import cn.cttic.sysframe.frame.domain.SmOrganExample;

public interface SmOrganMapper extends BaseMapper<String, SmOrgan, SmOrganExample> {
    public List<SmOrgan> queryTree(String orgId);
}