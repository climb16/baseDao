package cn.com.util;

import java.io.File;
import java.util.*;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLReader {
    //获取文档模型
    public Document getDocument(String xml){
        SAXReader saxReader = new SAXReader();
        Util u = new Util();
        Document document = null;
        try{
            document = saxReader.read(new File(u.getFileURL(xml)));
        }catch(DocumentException e){
            e.printStackTrace();
        }
        return document;
    }
    //获取文档根节点
    public Element getRoot(Document document){
        return document.getRootElement();
    }
    //该节点是否有子节点
    public boolean hasChild(Element element){
        return element.elementIterator().hasNext();
    }
    //该节点是否有属性
    public boolean hasAttr(Element element){
        return element.attributeIterator().hasNext();
    }
    /**
     * 获取该节点的子节点
     * @return 子节点List
     */
    public List<Element> getElementList(Element element){
        List<Element> eleList = new ArrayList<Element>();
        Iterator elements = element.elementIterator();
        while(elements.hasNext()){
            Element child = (Element)elements.next();
            eleList.add(child);
        }
        return eleList;
    }
    /**
     * 获取该节点的子节点
     * @return 子节点map
     */
    public Map<String, String> getElementMap(Element element){
        Map<String,String> elemMap = new HashMap<String, String>();
        Iterator elements = element.elementIterator();
        while(elements.hasNext()){
            Element child = (Element)elements.next();
            elemMap.put(child.getName(), child.getText());
        }

        return elemMap;
    }
    //获取该节点指定属性的值
    public String getAtrrValue(Element element, String name){
        Iterator attrs = element.attributeIterator();
        while(attrs.hasNext()){
            Attribute attr = (Attribute)attrs.next();
            if(name.equals(attr.getName())){
                return attr.getValue();
            }
        }
        return  "";
    }
    /**
     * 获取该节点的属性
     * @return 节点属性map
     */
    public Map<String, String> getAtrribute(Element element){
        Map<String,String> attrMap = new HashMap<String, String>();
        Iterator attrs = element.attributeIterator();
        while(attrs.hasNext()){
            Attribute attr = (Attribute)attrs.next();
            attrMap.put(attr.getName(), attr.getValue());
        }
        return  attrMap;
    }
}
