package cn.com.util.type;

import cn.com.util.Util;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;

public class ClassType {

    public static Object typeConvert(Type type, String value) throws ParseException {
        Object obj = null;
        if(type == int.class){
            obj = Integer.parseInt(value);
        }else if(type == String.class){
            obj = value.toString();
        }else if(type == Date.class){
            obj = Util.strToDate(value);
        }else if(type == float.class){
            obj = Float.parseFloat(value);
        }else if(type == double.class){
            obj = Double.parseDouble(value);
        }else if(type == boolean.class){
            if(value.equals("true")) obj = true;
            if(value.equals("false")) obj = false;
        }
        return obj;
    }
}
