package cn.cttic.sysframe.frame.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.cttic.sysframe.common.configuration.SysConfiguration;
import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.common.util.SpringContextUtil;
import cn.cttic.sysframe.frame.dao.SystemMapper;
import cn.cttic.sysframe.frame.service.SystemService;
import freemarker.template.utility.StringUtil;

@Service
public class SystemServiceImpl implements SystemService {

	@Autowired
	private SystemMapper systemMapper;

	/**
	 * 根据表名和字段名来获取一个sequence来生成id
	 * 
	 * @param table
	 * @param column
	 * @return
	 */
	@Override
	public Long generateId(String tableName, String columnName) {

		Map<String, String> param = new HashMap<String, String>();
		param.put("tableName", tableName);
		param.put("columnName", columnName);
		Long id;
		try {
			String sequenceName = systemMapper.getSequenceName(param);
			id = getSequenceValueFromDB(sequenceName);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
		return id;
	}

	@Override
	public Date getSystemDate() {
		try {
			return systemMapper.getSystemDate();
		}
		catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}

	}

	@Override
	public String getCurrentSystemCode() {
		return SysConfiguration.INSTANCE.SYSTEM_ID;
	}

	@Override
	public String getCurrentSystemInstanceId() {
		return SysConfiguration.INSTANCE.INSTANCE_ID;
	}

	@Override
	public List<String> getTablePkColumnName(String tableName) {
		return systemMapper.getTablePkColumnName(tableName);
	}

	private Long getSequenceValueFromDB(String sequenceName){
		String getSeqValue = "SELECT ${sequenceName}.nextval FROM dual";
		getSeqValue = StringUtil.replace(getSeqValue, "${sequenceName}", sequenceName);
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			DataSource dataSource = (DataSource) SpringContextUtil.getBean("dataSource");
			conn = dataSource.getConnection();
			pst = conn.prepareStatement(getSeqValue);
			rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			throw new SystemException(e, StatusCodeForFrame.DB_OPERATION_ERROR);
		} finally {
			releaseDB(conn,pst,rs);
		}
		return null;
	}
	
	private void releaseDB(Connection conn,PreparedStatement pst,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pst!=null){
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
