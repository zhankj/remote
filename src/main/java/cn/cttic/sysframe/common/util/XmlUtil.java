package cn.cttic.sysframe.common.util;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.google.common.collect.Lists;

public class XmlUtil {
	public static NodeList getNodeList(Element parent){
        return parent.getChildNodes();
    }
	
	public Document buildDocumentFromStr(String context)
    {
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        Document doc = null;
        try
        {
            DocumentBuilder builder=factory.newDocumentBuilder();
            
            InputSource is = new InputSource( new StringReader( context ));
            doc=builder.parse(is);    
            
        }catch(ParserConfigurationException e){
        	 e.printStackTrace();
        }catch(SAXException e){
        	 e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return doc;
    }
	
	
	public Document buildDocumentFromFile(String filePath)
    {
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		Document doc = null;
		try
        {
            DocumentBuilder builder=factory.newDocumentBuilder();
            doc=builder.parse(new File(filePath));            
        }catch(ParserConfigurationException e){
        	 e.printStackTrace();
        }catch(SAXException e){
        	 e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return doc;
    }
	
	public static Element [] getElementsByName(Element parent,String name)
    {

		List<Node> resList=Lists.newArrayList();
        NodeList nl=getNodeList(parent);
        for(int i=0;i<nl.getLength();i++)
        {
            Node nd=nl.item(i);
            if(nd.getNodeName().equals(name))
            {
                resList.add(nd);
            }
        }
        Element [] res=new Element [resList.size()];
        for(int i=0;i<resList.size();i++)
        {
            res[0]=(Element)resList.get(i);
        }        
       
        return res;
    }
	
	public static String getElementName(Element element)
    {
        return element.getNodeName();
    }
	
	public static String getElementValue(Element element)
    {
        NodeList nl=element.getChildNodes();
        for(int i=0;i<nl.getLength();i++)
        {
			if (nl.item(i).getNodeType() == Node.TEXT_NODE)// 是一个Text Node
            {            
                return element.getFirstChild().getNodeValue();
            }
        }   
        return null;
    }
	
	public static String getElementAttr(Element element,String attr)
    {
        return element.getAttribute(attr);
    }
	
	public static void setElementValue(Element element,String val)
    {
        Node node=element.getOwnerDocument().createTextNode(val);
        NodeList nl=element.getChildNodes();
        for(int i=0;i<nl.getLength();i++)
        {
            Node nd=nl.item(i);
			if (nd.getNodeType() == Node.TEXT_NODE)// 是一个Text Node
            {            
                  nd.setNodeValue(val);
                  return;
            }
        }   
        element.appendChild(node);        
    }
	
	public static void setElementAttr(Element element,
            String attr,String attrVal)
    {
        element.setAttribute(attr,attrVal);
    }
	
	public static void addElement(Element parent,Element child)
    {
        parent.appendChild(child);
    }
	
	 public static void addElement(Element parent,String tagName)
	 {        
	        Document doc=parent.getOwnerDocument();
	        Element child=doc.createElement(tagName);
	        parent.appendChild(child);
	 }
	 
	 public static void addElement(Element parent,String tagName,String text)
	 {
	        Document doc=parent.getOwnerDocument();
	        Element child=doc.createElement(tagName);
	        setElementValue(child,text);
	        parent.appendChild(child);
	 }
	 
	 public static void removeElement(Element parent,String tagName)
	 {
	        NodeList nl=parent.getChildNodes();
	        for(int i=0;i<nl.getLength();i++)
	        {
	            Node nd=nl.item(i);
	            if(nd.getNodeName().equals(tagName))
	            {
	                parent.removeChild(nd);
	            }
	        }
	 }
	 
	 public static NodeList SelectNodes(Document doc,String xmlpath){
		 	NodeList nodes = null;
		 	Object result;
			try {
				XPathFactory xFactory = XPathFactory.newInstance();  
			    XPath xpath = xFactory.newXPath();  
			        
			    XPathExpression expr;
				expr = xpath.compile(xmlpath);
				result = expr.evaluate(doc, XPathConstants.NODESET);
				nodes = (NodeList) result; 
			} catch (XPathExpressionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			
	        return nodes;
	 }
	 
	 public static Object xml2Bean(Class<?> zClass, String xml) {
			Object obj = null;
			JAXBContext context = null;
			if (null == xml || "".equals(xml) || "null".equalsIgnoreCase(xml) || xml.length() < 1)
				return obj;
			try {
				context = JAXBContext.newInstance(zClass);
				InputStream iStream = new ByteArrayInputStream(xml.getBytes());
				Unmarshaller um = context.createUnmarshaller();
				obj = (Object) um.unmarshal(iStream);
				return obj;
			}
			catch (JAXBException e) {
				e.printStackTrace();
			}
			return obj;
		}
	 
	 public static Object xml2BeanUtf8(Class<?> zClass, String xml) {
			Object obj = null;
			JAXBContext context = null;
			if (null == xml || "".equals(xml) || "null".equalsIgnoreCase(xml) || xml.length() < 1)
				return obj;
			try {
				context = JAXBContext.newInstance(zClass);
				InputStream iStream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
				Unmarshaller um = context.createUnmarshaller();
				obj = (Object) um.unmarshal(iStream);
				return obj;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return obj;
		}

		public static String bean2Xml(Object bean) {
			String xmlString = null;
			JAXBContext context;
			StringWriter writer;
			if (null == bean)
				return xmlString;
			try {
				context = JAXBContext.newInstance(bean.getClass());
				Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);//
				//m.setProperty(Marshaller.JAXB_ENCODING, "gb2312");//
				//m.setProperty(Marshaller.JAXB_ENCODING, "GBK");//
				m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");//
				m.setProperty(Marshaller.JAXB_FRAGMENT, false);//
				writer = new StringWriter();
				m.marshal(bean, writer);
				xmlString = writer.toString();
				return xmlString;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return xmlString;
		}

	public static String bean2Xml(Object bean, String codetype) {
		String xmlString = null;
		JAXBContext context;
		StringWriter writer;
		if (null == bean)
			return xmlString;
		try {
			context = JAXBContext.newInstance(bean.getClass());
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);//
			// m.setProperty(Marshaller.JAXB_ENCODING, "gb2312");//
			// m.setProperty(Marshaller.JAXB_ENCODING, "GBK");//
			m.setProperty(Marshaller.JAXB_ENCODING, codetype);//
			m.setProperty(Marshaller.JAXB_FRAGMENT, false);//
			writer = new StringWriter();
			m.marshal(bean, writer);
			xmlString = writer.toString();
			return xmlString;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return xmlString;
	}
}
