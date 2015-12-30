package cn.cttic.sysframe.frame.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.cttic.sysframe.frame.domain.SmDocInfo;
import cn.cttic.sysframe.frame.domain.SmDocInfoExample;

/**
 * 文档信息服务接口
 * 
 * @author 付加朕
 * @date 2014-4-14 下午2:04:41
 */
public interface SmDocInfoService {

	/**
	 * 创建文档信息
	 * 
	 * @param documentName
	 *            原始文档文件名
	 * @param transDocName
	 *            存放ftp服务器上的文档文件名
	 * @param bizId
	 *            业务编码
	 * @param bizType
	 *            业务类型
	 * @param serialNo 流水号
	 * @param documentSize
	 *            文档大小
	 * @param state
	 *            状态,U：正常，E：删除
	 * @param orgId
	 *            操作部门ID
	 * @param operId
	 *            操作员工ID
	 * @param note
	 *            备注
	 * @return HashMap<String,String> ({RET_CODE="0000",RET_MSG="文档信息保存成功"})
	 * @author 付加朕
	 * @date 2014-4-14 下午3:19:29
	 */
	public HashMap<String, String> createSmDocInfo(String documentName, String transDocName, String bizId, String bizType, String serialNo,
	        long documentSize, String state, String orgId, String operId, String note);

	/**
	 * 插入文档信息
	 * 
	 * @param record
	 * @return
	 * @author 付加朕
	 * @date 2014-4-14 下午2:09:00
	 */
	public int insertSmDocInfo(SmDocInfo record);

	/**
	 * 按条件获取文档信息
	 * 
	 * @param example
	 * @return
	 * @author 付加朕
	 * @date 2014-4-14 下午2:09:03
	 */
	public List<SmDocInfo> selectByExample(SmDocInfoExample example);

	/**
	 * 根据文档信息编号（主键）获取文档信息
	 * 
	 * @param documentId
	 * @return
	 * @author 付加朕
	 * @date 2014-4-14 下午2:09:05
	 */
	public SmDocInfo selectByPrimaryKey(Long documentId);

	/**
	 * 根据文档信息编号（主键）删除文档信息
	 * 
	 * @param documentId
	 * @return
	 * @author 付加朕
	 * @date 2014-4-14 下午2:09:08
	 */
	public int deleteByPrimaryKey(Long documentId);

	/**
	 * 按条件更新指定的文档信息列
	 * 
	 * @param record
	 *            新的文档信息（要修改的文档信息列不能为null）
	 * @param example
	 *            更新条件
	 * @return
	 * @author 付加朕
	 * @date 2014-4-14 下午3:03:31
	 */
	public int updateByExampleSelective(@Param("record") SmDocInfo record, @Param("example") SmDocInfoExample example);

	/**
	 * 按条件更新文档信息全部列数据
	 * 
	 * @param record
	 *            新的文档信息
	 * @param example
	 *            更新条件
	 * @return
	 * @author 付加朕
	 * @date 2014-4-14 下午2:46:53
	 */
	public int updateByExample(@Param("record") SmDocInfo record, @Param("example") SmDocInfoExample example);

	/**
	 * 按主键更新指定文档信息列数据
	 * 
	 * @param record
	 *            更新后的文档信息
	 * @return
	 * @author 付加朕
	 * @date 2014-4-14 下午3:08:58
	 */
	public int updateByPrimaryKeySelective(SmDocInfo record);

	/**
	 * 按主键更新文档信息全部列数据
	 * 
	 * @param record
	 *            更新后的文档信息
	 * @return
	 * @author 付加朕
	 * @date 2014-4-14 下午3:09:01
	 */
	public int updateByPrimaryKey(SmDocInfo record);

	public PageList<SmDocInfo> selectByExample(SmDocInfoExample example, PageBounds pageBounds);
}
