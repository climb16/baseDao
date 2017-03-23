package cn.com.db.druid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class DruidConnection {

	 public static void openConnTest(String poolKey) {  
		 
		 DruidPoolInstence druidPoolInstence = new DruidPoolInstence();
		 try {
		 DataSource ds = druidPoolInstence.getDataSource();
	     Connection conn = ds.getConnection();  		     
		       
	     PreparedStatement statement = conn.prepareStatement("select 1");  
	     ResultSet rs = statement.executeQuery();  
	     rs.close();  
	     statement.close();  
			     
			       
	     conn.close();  
	     conn = null;  
		 }catch (SQLException e) {
			 
	     }	   
		      
	 }  
	  
	    public static void main(String[] args) {  
	       
	    }  
}
