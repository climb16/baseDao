package cn.com.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.com.util.XMLReader;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {

	private Document document = null;    //配置文件document模型
	private static String encoding = "utf-8";
	private static final String TYPE = "text/html";
    private static String url = "config/action.xml";
	private XMLReader xmlRead = null;    //xml文件解析对象

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType(TYPE);
		req.setCharacterEncoding(encoding);
		resp.setCharacterEncoding(encoding);
        //获取请求uri
		String uri = req.getRequestURI().toString();
		uri = uri.substring(uri.lastIndexOf("/")+1, uri.indexOf(".do"));
		String name = "";  //请求action
		String methodName = "";   //请求action的方法
		String className = "";    //请求action的class
        //如果请求的uri中包含 "_" 则截取 "_"前面部分为请求的action，后面部分为请求action的方法名
        //如果不包含，则将请求uri作为请求的action，默认方法名为excute.
		if(uri.contains("_")){
			name = uri.substring(0, uri.indexOf("_"));
			methodName = uri.substring(uri.indexOf("_")+1);	
		}else{
			name = uri;
            methodName="execute";
		}
        //获取所有的action节点
		List<Element> elements = xmlRead.getElementList(xmlRead.getRoot(document));
        List<Element> results = new ArrayList<Element>();
		for(Element element : elements){
			if(name.equals(xmlRead.getAtrrValue(element, "name"))){
				className = xmlRead.getAtrrValue(element, "class");
                results = xmlRead.getElementList(element);
			}
		}
        String url = "";
        try {
            url = getActionURL(className, methodName, results, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(null != url && !"".equals(url)){

            req.getRequestDispatcher(url).forward(req, resp);
        }
	}
    //调用action请求方法
    private String getActionURL(
            String className, String methodName, List<Element> results,
            HttpServletRequest req, HttpServletResponse resp) throws Exception{
            String url = "";
            Class<?> action = Class.forName(className);
            Method[] methods = action.getMethods();
            for(Method method : methods){
                //调用请求方法
                if(method.getName().toString().equals(methodName)){
                    Object ret;
                    ret = method.invoke(action.newInstance(), new Object[]{req, resp});
                    if(null != ret && !"".equals(ret)){
                        for(Element result : results){
                            if(ret.toString().equals(xmlRead.getAtrrValue(result, "name"))){
                                url = result.getText();
                            }
                        }
                    }
                }
            }
          return url;
    }
	@Override
	public void init(ServletConfig config) throws ServletException {
		encoding = config.getInitParameter("encoding");
		xmlRead = new XMLReader();
        url = config.getInitParameter("url");
        //加载并获取action.xml的documen模型
        document = xmlRead.getDocument(url);
	}
	
	@Override
	public void destroy() {
        document = null;
		xmlRead = null;
	}
}