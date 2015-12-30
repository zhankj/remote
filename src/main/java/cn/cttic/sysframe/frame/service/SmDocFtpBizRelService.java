package cn.cttic.sysframe.frame.service;

import java.util.List;

import cn.cttic.sysframe.frame.domain.SmDocFtpBizRel;
import cn.cttic.sysframe.frame.domain.SmDocFtpBizRelExample;

/**
 * 文档ftp业务关系接口
 * 
 * @author 付加朕
 * @date 2014-4-14 下午2:22:01
 */
public interface SmDocFtpBizRelService {

	/**
	 * 新增文档ftp业务关系信息
	 * 
	 * @param record
	 *            文档ftp业务关系信息Bean
	 * @return
	 * @author 付加朕
	 * @date 2014-4-14 下午2:21:57
	 */
	public int insertSmDocFtpBizRel(SmDocFtpBizRel record);

	/**
	 * 按条件查询文档FTP业务关系信息
	 * 
	 * @param criteria
	 *            查询条件
	 * @return
	 * @author 付加朕
	 * @date 2014-4-14 下午2:21:54
	 */
	public List<SmDocFtpBizRel> selecebyExample(SmDocFtpBizRelExample criteria);

	/**
	 * 按条件删除文档FTP业务关系
	 * 
	 * @param criteria
	 *            拼接条件
	 * @return
	 * @author 付加朕
	 * @date 2014-4-14 下午2:22:04
	 */
	public int deleteByExample(SmDocFtpBizRelExample criteria);

	/**
	 * 按主键删除文档FTP业务关系
	 * 
	 * @param relId
	 *            业务关系ID
	 * @return
	 * @author 付加朕
	 * @date 2014-4-14 下午2:22:07
	 */
	public int deleteByPrimaryKey(long relId);

	/**
	 * 根据业务类型获取文档ftp业务关系
	 * 
	 * @param bizType
	 *            业务类型
	 * @return
	 * @author 付加朕
	 * @date 2014-4-14 下午4:00:28
	 */
	public SmDocFtpBizRel selectSmDocFtpBizRelByBizType(String bizType);

}
