package us.codecraft.webmagic.pipeline;

import java.io.Reader;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.cttic.icar.domain.CfgPartType;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

public class MybatisPipeline implements Pipeline {

	private static int count = 0;
	private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader; 
    private static String insertSmt = "cn.cttic.icar.dao.CfgPartTypeMapper.insert";
    static{
        try{
            reader    = Resources.getResourceAsReader("data/mybatis-icar.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }

	public MybatisPipeline() {
		super();
		
		// TODO Auto-generated constructor stub
	}

	
	public  void insertRow(String row , SqlSession session )
	{
		if (StringUtils.isBlank(row))
			return;
		String[] cols =  row.split(",");
		if (cols.length != 4)
		{
			return;
		}
		CfgPartType cfgPartType = new CfgPartType();
		cfgPartType.setPartType("2");
		cfgPartType.setSysType(cols[0]);
		cfgPartType.setSysSubType(cols[1]);
		cfgPartType.setSysPartType(cols[2]);
		cfgPartType.setUrl(cols[3]);
		session.insert(insertSmt,cfgPartType);
	}
	
	@Override
	public void process(ResultItems resultItems, Task task) {
		// TODO Auto-generated method stub

	}

}
