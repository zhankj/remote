package us.codecraft.webmagic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import cn.cttic.icar.dao.CfgPartTypeMapper;
import cn.cttic.icar.domain.CfgPartType;

public class jsonFileProcessor {
	
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
	//	CfgPartTypeMapper  cfgPartTypeMapper= session.getMapper(CfgPartTypeMapper.class);
		
	//	cfgPartTypeMapper.insert(cfgPartType);
		//cfgPartTypeMapper.insert(cfgPartType);
	}
	public static void main(String[] args) {
		
		//字节流
		/*
		FileInputStream fis = new FileInputStream("D:\\test.txt");
		            BufferedInputStream bis = new BufferedInputStream(fis); //默认的输入缓冲区是2048B
		            int ch = bis.read();
		           while (ch != -1) {
		                System.out.print((char)ch);
		               ch = bis.read();
		           }
	  */
		SqlSession session = sqlSessionFactory.openSession();
		//字符流 
		jsonFileProcessor  fp = new jsonFileProcessor();
		
		try {
			
			FileReader fr = new FileReader("C:\\Users\\zhankaijin_cttic\\Desktop\\andriod\\car\\用品.txt");
			BufferedReader br = new BufferedReader(fr);
			      
			String str = br.readLine();
			fp.insertRow(str,session);
			while (str != null) {			            
			    str = br.readLine();
			    fp.insertRow(str,session);
			    if (count%100 ==0)
			    {
			    	session.commit();
			    }
			}
			session.commit();
			br.close();
			fr.close();			          
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}

}
