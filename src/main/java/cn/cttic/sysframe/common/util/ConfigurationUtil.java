package cn.cttic.sysframe.common.util;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrameCore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

/**
 * 配置文件加载工具类 <br/>
 * @author caohui@cttic.cn
 * @date Jun 4, 2014 10:00:03 AM
 * @copyright: Copyright (c) 2014 CTTIC
 */
public class ConfigurationUtil {

	private final static Logger LOG = LoggerFactory.getLogger(ConfigurationUtil.class);
	
	/**
	 * 将properties解析为具体的类对象
	 * @param configurationFile
	 * @param inputProperties
	 * @param configurationClass
	 * @return
	 */
	public static <E> E loadConfiguration(String configurationFile, Properties inputProperties, Class<E> configurationClass) {
		Properties fileProperties = new Properties();
		try {
			fileProperties.load(new ClassPathResource(configurationFile).getInputStream());
		}
		catch (Exception ex) {
			throw new SystemException(ex, "加载配置文件失败: " + configurationFile, StatusCodeForFrameCore.INSIDE_ERROR);
		}
		if (inputProperties != null) {
			fileProperties.putAll(inputProperties);
		}

		ObjectMapper mapper = new ObjectMapper();

		try {
	        return mapper.readValue(mapper.writeValueAsString(fileProperties), configurationClass);
        }
		catch (Exception e) {
	        throw new SystemException(e, "创建配置对象"+configurationClass.getSimpleName()+"失败", StatusCodeForFrameCore.INSIDE_ERROR);
        }
	}
	
	//单例辅助
	public static class LoadAllPropertiesHolder {
		Map<String, Properties> propsMap;
		static LoadAllPropertiesHolder instance = new LoadAllPropertiesHolder();

		public LoadAllPropertiesHolder() {
			try {
				propsMap = Maps.newHashMap();
				
				Resource resource = new ClassPathResource("/");
				System.out.println(resource.getFile().getPath());
				@SuppressWarnings("unchecked")
				Collection<File> filesList = FileUtils.listFiles(resource.getFile(), new String[]{"properties"}, true);
				for (File file : filesList) {
					System.out.println(file);
					Properties testProperties = PropertiesLoaderUtils.loadProperties(new FileSystemResource(file));
					propsMap.put(file.getName(), testProperties);
				}
			} catch (Exception ex) {
				throw new SystemException(ex, "加载配置文件失败: ", StatusCodeForFrameCore.INSIDE_ERROR);
			}
		}
	}
	
	/**
	 * 获取所有properties
	 * @return
	 */
	public static Map<String, Properties> loadAllProperties() {
		return LoadAllPropertiesHolder.instance.propsMap;
	}
	
	/**
	 * 按文件名获取properties
	 * @param filename
	 * @return
	 */
	public static Properties getPropertiesByFilename(String filename) {
		return loadAllProperties().get(filename);
	}
	
	/**
	 * 按文件名和key获取properties的具体值
	 * @param filename
	 * @param key
	 * @return
	 */
	public static String getValueOfPropertiesByFilenameAndKey(String filename, String key) {
		if (getPropertiesByFilename(filename) == null) return null;
		return getPropertiesByFilename(filename).getProperty(key);
	}

}
