package cn.cttic.sysframe.frame.dao;

import cn.cttic.sysframe.frame.domain.SmJobLog;
import cn.cttic.sysframe.frame.domain.SmJobLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface SmJobLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SM_JOB_LOG
     *
     * @mbggenerated Fri Apr 04 09:33:38 CST 2014
     */
    int countByExample(SmJobLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SM_JOB_LOG
     *
     * @mbggenerated Fri Apr 04 09:33:38 CST 2014
     */
    int deleteByExample(SmJobLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SM_JOB_LOG
     *
     * @mbggenerated Fri Apr 04 09:33:38 CST 2014
     */
    int deleteByPrimaryKey(Long logId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SM_JOB_LOG
     *
     * @mbggenerated Fri Apr 04 09:33:38 CST 2014
     */
    int insert(SmJobLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SM_JOB_LOG
     *
     * @mbggenerated Fri Apr 04 09:33:38 CST 2014
     */
    int insertSelective(SmJobLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SM_JOB_LOG
     *
     * @mbggenerated Fri Apr 04 09:33:38 CST 2014
     */
    List<SmJobLog> selectByExample(SmJobLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SM_JOB_LOG
     *
     * @mbggenerated Fri Apr 04 09:33:38 CST 2014
     */
    SmJobLog selectByPrimaryKey(Long logId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SM_JOB_LOG
     *
     * @mbggenerated Fri Apr 04 09:33:38 CST 2014
     */
    int updateByExampleSelective(@Param("record") SmJobLog record, @Param("example") SmJobLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SM_JOB_LOG
     *
     * @mbggenerated Fri Apr 04 09:33:38 CST 2014
     */
    int updateByExample(@Param("record") SmJobLog record, @Param("example") SmJobLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SM_JOB_LOG
     *
     * @mbggenerated Fri Apr 04 09:33:38 CST 2014
     */
    int updateByPrimaryKeySelective(SmJobLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SM_JOB_LOG
     *
     * @mbggenerated Fri Apr 04 09:33:38 CST 2014
     */
    int updateByPrimaryKey(SmJobLog record);
    
    
    PageList<SmJobLog> selectByExample(SmJobLogExample example,PageBounds bounds);
}