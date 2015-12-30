package cn.cttic.sysframe.frame.dao;

import cn.cttic.sysframe.frame.domain.SmDataRight;
import cn.cttic.sysframe.frame.domain.SmDataRightExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface SmDataRightMapper {
   public int countByExample(SmDataRightExample example);

   public int deleteByExample(SmDataRightExample example);

   public int deleteByPrimaryKey(String dataCode);

   public int insert(SmDataRight record);

   public int insertSelective(SmDataRight record);

   public List<SmDataRight> selectByExample(SmDataRightExample example);

   public SmDataRight selectByPrimaryKey(String dataCode);

   public int updateByExampleSelective(@Param("record") SmDataRight record, @Param("example") SmDataRightExample example);

   public int updateByExample(@Param("record") SmDataRight record, @Param("example") SmDataRightExample example);

   public int updateByPrimaryKeySelective(SmDataRight record);

   public int updateByPrimaryKey(SmDataRight record);
   
//   public List<Map<String, Object>> commQuery(String sql,Map paramMap);
   public List<Map<String, Object>> commQuery(Map paramMap);
   
   //功能集数据权限分配-可选区数据查询
   public PageList<Map> listSelectDatasForFS(Map params,PageBounds pageBounds);
   
   //工号数据权限分配-可选区数据查询
//   public PageList<Map> listSelectDatasForOper(Map params,PageBounds pageBounds);
   //功能集数据权限分配-已选区数据查询
   public PageList<Map> listSelectedDatasForFS(Map params,PageBounds pageBounds);
 //工号数据权限分配-已选区数据查询
//   public PageList<Map> listSelectedDatasForOper(Map params,PageBounds pageBounds);
   // 获取数据权限树
   public List<SmDataRight> getDataRightTree(Map paramMap); 
   // 根据用户标识获取用户权限
   public List<Map<String,Object>> getDataRightByOperId(String operId);
}