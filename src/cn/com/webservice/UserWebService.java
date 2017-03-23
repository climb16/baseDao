package cn.com.webservice;

//~--- non-JDK imports --------------------------------------------------------

import annotation.WebService;

/**
 * @author xiao
 * @date 2015/9/8.
 */
@WebService
public class UserWebService {
    public static void print(Class c) {

        WebService person = (WebService) c.getAnnotation(WebService.class);

        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println(person.value());
        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println("----------------------");
    }

    public static void main(String[] args) {
        UserWebService.print(UserWebService.class);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
