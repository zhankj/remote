package cn.cttic.sysframe.common.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class GeneratorCode {

	public static void main(String[] args) throws Exception {
//		System.setProperty("log4j.configuration",
//				"file:/D:/worksoftware/svn2/s4boss-frame/src/main/resources/log4j.properties");
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile = new File(
				"src/main/resources/data/mysqlGeneratorConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
				callback, warnings);
		myBatisGenerator.generate(null);
		
	}
}
