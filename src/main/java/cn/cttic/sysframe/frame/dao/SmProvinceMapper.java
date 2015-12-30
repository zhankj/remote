package cn.cttic.sysframe.frame.dao;

import cn.cttic.sysframe.frame.domain.SmProvince;
import cn.cttic.sysframe.frame.domain.SmProvinceExample;
import cn.cttic.sysframe.frame.domain.SmStaff;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmProvinceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SM_PROVINCE
     *
     * @mbggenerated Thu Apr 10 10:57:59 CST 2014
     */
    int countByExample(SmProvinceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SM_PROVINCE
     *
     * @mbggenerated Thu Apr 10 10:57:59 CST 2014
     */
    int deleteByExample(SmProvinceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SM_PROVINCE
     *
     * @mbggenerated Thu Apr 10 10:57:59 CST 2014
     */
    int insert(SmProvince record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SM_PROVINCE
     *
     * @mbggenerated Thu Apr 10 10:57:59 CST 2014
     */
    int insertSelective(SmProvince record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SM_PROVINCE
     *
     * @mbggenerated Thu Apr 10 10:57:59 CST 2014
     */
    List<SmProvince> selectByExample(SmProvinceExample example);
    
    
    SmProvince selectByPrimaryKey(String provinceId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SM_PROVINCE
     *
     * @mbggenerated Thu Apr 10 10:57:59 CST 2014
     */
    int updateByExampleSelective(@Param("record") SmProvince record, @Param("example") SmProvinceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SM_PROVINCE
     *
     * @mbggenerated Thu Apr 10 10:57:59 CST 2014
     */
    int updateByExample(@Param("record") SmProvince record, @Param("example") SmProvinceExample example);
}