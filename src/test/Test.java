package test;

import java.lang.reflect.InvocationTargetException;

import cn.com.proxy.ProxyCglib;
import cn.com.proxy.UService;

public class Test {

    public static void main(String[] str) throws NoSuchMethodException,
            SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {

        /*BookProxy proxy = new BookProxy();
        UService s = (UService)proxy.bind(new UService());
        s.add();*/
      
//        System.out.println();
//        Method method = Date.class.getMethod("toString");
//        System.out.println(method + ""+method.invoke(new Date()));

    	
    	/*ProxyCglib lib = new ProxyCglib();
    	UService u = (UService) lib.getInstance(new UService());
    	u.add();*/
    }



}
