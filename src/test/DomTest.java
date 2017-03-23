package test;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.com.util.XMLReader;

public class DomTest {
    public static void main(String[] arg){
        XMLReader xmlRead = new XMLReader();
        Document document = xmlRead.getDocument("cn/com/conf/action.xml");
        List<Element> list = xmlRead.getElementList(xmlRead.getRoot(document));        
        for(Element element : list){
        	if("user".equals(xmlRead.getAtrrValue(element, "name"))){
        		System.out.println(xmlRead.getAtrrValue(element, "class"));
        		System.out.println(xmlRead.getAtrrValue(element, "method"));
        	}
          
        }       
    }
}
