package cn.com.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PropertiesReader {

    private String file = "";
    public PropertiesReader(String file){
         this.file = file;
    }
    public Map<String, String> readProperties(){
        //从类加载器获取文件路径
        Util u = new Util();
        Properties properties = new Properties();
        Map<String, String> map = new HashMap<>();
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(u.getFileURL(file)));
            properties.load(in);
            in.close();
            map.put("URL",properties.getProperty("db.url"));
            map.put("DRIVER",properties.getProperty("db.driver"));
            map.put("USER",properties.getProperty("db.user"));
            map.put("PASS",properties.getProperty("db.pass"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }



}

