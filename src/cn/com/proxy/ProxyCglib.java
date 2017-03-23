package cn.com.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ProxyCglib implements MethodInterceptor{

	 /** 
     * 创建代理对象 
     *  
     * @param target 
     * @return 
     */  
    public Object getInstance(Class<?> target) {
        Enhancer enhancer = new Enhancer();  
        enhancer.setSuperclass(target);
        // 回调方法  
        enhancer.setCallback(this);  
        // 创建代理对象  
        return enhancer.create();  
    }  
  
    @Override  
    // 回调方法  
    public Object intercept(Object obj, Method method, Object[] args,
            MethodProxy proxy) throws Throwable {  
        //System.out.println("事物开始");
        Object o = proxy.invokeSuper(obj, args);  
        //System.out.println("事物结束");
        return o;  
  
  
    }  
  

}
