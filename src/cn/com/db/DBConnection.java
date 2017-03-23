package cn.com.db;
import cn.com.util.PropertiesReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.Map;

public class DBConnection {
    private static Connection conn = null;
    private static String URL;
    private static String DRIVER;
    private static String USER;
    private static String PASS;
    static{
        PropertiesReader pd = new PropertiesReader("cn/com/db/db.properties");
        Map<String, String> map = pd.readProperties();
        URL = map.get("URL");
        DRIVER = map.get("DRIVER");
        USER = map.get("USER");
        PASS = map.get("PASS");
    }
    public static Connection getConn(){
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL,USER,PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    public static void close(ResultSet rs, PreparedStatement ps, Connection conn){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
        }
        if(ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(PreparedStatement ps, Connection conn){
        if(ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
