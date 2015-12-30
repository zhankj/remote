package cn.cttic.sysframe.common.support;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

/**
 * 注解类扫描器
 * 用于在指定packge中扫描指定的注解的class
 * @author caohui
 *
 */
public class AnnotatedClassScaner {
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	private static final String RESOURCE_PATTERN = "/**/*.class";
	private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
	private List<String> packagesList= new LinkedList<String>();
	private Map<Class<? extends Annotation>, AnnotationTypeFilter> typeFilters = new HashMap<Class<? extends Annotation>, AnnotationTypeFilter>();
	private Map<Class<? extends Annotation>, List<Class<?>>> annotatedClassMap = new HashMap<Class<? extends Annotation>, List<Class<?>>>();
	
	public Map<Class<? extends Annotation>, List<Class<?>>> getAnnotatedClassMap() {
		return annotatedClassMap;
	}

	/**
	 * 
	 * @param packagesToScan 指定哪些包需要被扫描,支持多个包"package.a,package.b"并对每个包都会递归搜索
	 * @param annotationFilter 指定扫描包中含有特定注解标记的bean,支持多个注解
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public AnnotatedClassScaner(String[] packagesToScan, Class<? extends Annotation>... annotationFilter) throws IOException, ClassNotFoundException{
		if (packagesToScan != null) {
			for (String packagePath : packagesToScan) {
				this.packagesList.add(packagePath);
			}
		}
		if (annotationFilter != null){
			for (Class<? extends Annotation> annotation : annotationFilter) {
				typeFilters.put(annotation, new AnnotationTypeFilter(annotation, false));
			}
		}
		this.loadClassMap();
	}
	
	/**
	 * 加载符合条件的Bean以AnnotationClass,BeanClass的Map的形式返回
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void loadClassMap() throws IOException, ClassNotFoundException {
		this.annotatedClassMap.clear();
		if (!this.packagesList.isEmpty()) {
			for (String pkg : this.packagesList) {
				String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(pkg) + RESOURCE_PATTERN;
				Resource[] resources = this.resourcePatternResolver.getResources(pattern);
				MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);
				for (Resource resource : resources) {
					if (resource.isReadable()) {
						MetadataReader reader = readerFactory.getMetadataReader(resource);
						String className = reader.getClassMetadata().getClassName();
						
						//遍历指定的注解列表，检查当前扫描到的class含有指定的注解
						if (!this.typeFilters.isEmpty()) {
							for (Map.Entry<Class<? extends Annotation>, AnnotationTypeFilter> entry : this.typeFilters.entrySet()) {
								Class<? extends Annotation> anno = entry.getKey();
								AnnotationTypeFilter filter = entry.getValue();
								if (filter.match(reader, readerFactory)) {
									if (this.annotatedClassMap.get(anno) == null) {
										List<Class<?>> value = new LinkedList<Class<?>>();
										value.add(Class.forName(className));
										annotatedClassMap.put(anno, value);
									} else {
										this.annotatedClassMap.get(anno).add(Class.forName(className));
									}
								}
							}
						}
					}
				}
			}
		}
		//输出日志
		if (LOG.isInfoEnabled()){
			if (this.annotatedClassMap.size() == 0) {
				LOG.warn("没有找到任何被自定义注解的类");
			}
			for (Map.Entry<Class<? extends Annotation>,List<Class<?>>> entry : this.annotatedClassMap.entrySet()) {
				for (Class<?> annotatedClass : entry.getValue()) {
					LOG.info(String.format("Found an annotated class [%s] mapped to [%s]", annotatedClass.getName(), entry.getKey().getName()));
				}
			}
		}
	}

}
