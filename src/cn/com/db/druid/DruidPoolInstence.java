package cn.com.db.druid;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;

public class DruidPoolInstence {
	
	DruidDataSource dataSource = null;  
	  
    
    public String getConnectionPoolKey() {  
        return "druid";  
    }  
      
    protected DataSource getDataSource() {  
        if (dataSource == null) {  
            dataSource = new DruidDataSource();  
            dataSource.setMaxActive(100);  
            dataSource.setMaxIdle(30);  
            dataSource.setMinIdle(20);  
            dataSource.setInitialSize(10);  
            dataSource.setPoolPreparedStatements(true);  
            dataSource.setTestOnBorrow(false);  
            dataSource.setTestOnReturn(false);  
            dataSource.setMinEvictableIdleTimeMillis(30);  
            dataSource.setMaxWaitThreadCount(1000);  
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");  
            dataSource.setUrl("jdbc:mysql://localhost:3306/model");  
            dataSource.setUsername("root");  
            dataSource.setPassword("root");  
        }  
        return dataSource;  
    }  

}
