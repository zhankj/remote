package cn.cttic.sysframe.common.util;

import java.io.ByteArrayOutputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrameCore;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.JDom2Writer;

public class JDomUtil {
	private final static Logger LOG = LoggerFactory.getLogger(JDomUtil.class);
	
	public static void parseObjectToElement (Object obj, Element container) {
//		StaxDriver driver = new StaxDriver();
//	    driver.getQnameMap().setDefaultNamespace(Configuration.WS_NAMESPACE.getURI());
		XStream xs = new XStream(new DomDriver("utf-8"));
		xs.alias(obj.getClass().getSimpleName(), obj.getClass());
//		System.out.println(xs.toXML(obj));
		xs.marshal(obj, new JDom2Writer(container));
	}
	
	/**
	 * 递归全部子节点，设置Namespace
	 * @param element
	 * @param namespace
	 */
	public static void setAllNamespace(Element element, Namespace namespace) {
		if (element.getChildren().size() != 0 ) {
			for (Element item : element.getChildren()) setAllNamespace(item, namespace);
		}
		element.setNamespace(namespace);
	}
	
	public static String element2String(Element element) {
		Format format = Format.getPrettyFormat();
		format.setEncoding("UTF-8");
		XMLOutputter xmlout = new XMLOutputter(format);
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		try {
			xmlout.output(element, bo);
		} catch (Exception e) {
			throw new SystemException(e, StatusCodeForFrameCore.XML_PARSE_ERROR);
		}
		return bo.toString();
	}
	
	public static Map<String, List<String>> queryValues(String filename, String... xpaths) {
		SAXBuilder jdomBuilder = new SAXBuilder();
        Document jdomDocument;
		try {
			jdomDocument = jdomBuilder.build(JDomUtil.class.getClassLoader().getResourceAsStream(filename));
		} catch (Exception e) {
			LOG.error("Failed to parse file: " + filename);
			return null;
		}
        
		Map<String, List<String>> values = new LinkedHashMap<String, List<String>>();
        XPathFactory xFactory = XPathFactory.instance();
        for (String xpath : xpaths) {
        	XPathExpression<Element> expr = xFactory.compile(xpath, Filters.element());
        	List<Element> links = expr.evaluate(jdomDocument);
        	List<String> value = new LinkedList<String>();
        	for (Element linkElement : links) {
        		value.add(linkElement.getValue());
        	}
        	values.put(xpath, value);
		}
        
        return values;
		
	}
	
	public static String queryValue(String filename, String xpath) {
		Map<String, List<String>> values = queryValues(filename, xpath);
		return values.get(xpath).size() != 0 ? values.get(xpath).get(0) : null;
	}
	
}
