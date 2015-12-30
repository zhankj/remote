package cn.cttic.sysframe.common.util;

import org.jdom2.Element;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.JDom2Writer;

public class XStreamUtil {
	public static void parseObjectToXml (Object obj, Element container) {
		XStream xs = new XStream();
		xs.alias(obj.getClass().getSimpleName(), obj.getClass());
		xs.marshal(obj, new JDom2Writer(container));
	}
}
