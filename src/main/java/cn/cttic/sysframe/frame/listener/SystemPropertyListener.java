package cn.cttic.sysframe.frame.listener;



import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SystemPropertyListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.setProperty("javax.xml.parsers.DocumentBuilderFactory", "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");
		System.setProperty("javax.xml.parsers.SAXParserFactory", "com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
		String domain_home = System.getenv("DOMAIN_HOME");
		if(domain_home!=null){
			System.out.println("Domain Home:"+domain_home);
			System.setProperty("domain.home", domain_home);
		}else{
			domain_home = System.getProperty("DOMAIN_HOME");
			if(domain_home!=null){
				System.setProperty("domain.home", domain_home);
			}else{
				System.err.println("获取不到DOMAIN_HOME!");
			}
		}
	}
	
	
}
