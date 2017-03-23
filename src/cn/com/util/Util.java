package cn.com.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.List;
import java.util.Date;

public class Util {
    /**
     * 字符串转换（属性转换为字段）
     * @param:  例:userName
     * @return     user_name
     */
    public static String stringConvert(String s){
        StringBuffer sb = new StringBuffer();
        String str;
        if("".equals(s) || s == null){
            return "";
        }else{
            char[] chars = s.trim().toCharArray();
            for(char ch : chars){
                if(Character.isUpperCase(ch)){
                    sb.append("_"+Character.toLowerCase(ch));
                }else{
                    sb.append(ch);
                }
            }
            str = sb.toString();
            if("_".equals(str.substring(0, 1))){
                str = str.substring(1);
            }
            return str;
        }
    }
    //首字母小写
    public static String startToLower(String str){
        if(!"".equals(str) && str != null){
            if(Character.isUpperCase(str.charAt(0))){
                return Character.toString(Character.toLowerCase(str.charAt(0)))+str.substring(1);
            }
            return str.trim();
        }
        return "";
    }

    //从类加载器获取文件路径
    public static String getFileURL(String file){
        return  Class.class.getClassLoader().getResource(file).getPath().toString();
    }
    //追加list
    public static List appendList(List ls1, List ls2){
       if(!ls2.isEmpty()){
           for(Object obj : ls2){
               ls1.add(obj);
           }
       }
       return ls1;
    }

    //判断字符串为空
    public static String isNull(String str){
        if(isNuN(str)) return str;
        return "";
    }
    //判断字符串为空
    public static boolean isNuN(String str){
        if(null != str && !("").equals(str)) return true;
        return false;
    }
    public static boolean isNuN(Object obj){
        if(null != obj) return true;
        return false;
    }
    public static String objToString(Object obj){
        if(null != obj )return ""+obj;
        return "";
    }

    //以指定格式格式化当前日期
    public static String dateFormat(String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(new Date()).toString();
    }

    public static Date strToDate(String date) throws ParseException{
        DateFormat format = DateFormat.getDateInstance();
        return format.parse(date);
    }
    public static void main(String[] args) throws ParseException {
        System.out.println(Util.dateFormat("yyyyMMddhhmmss"));
        System.out.println(Util.strToDate("2012-11-22"));
    }
}
