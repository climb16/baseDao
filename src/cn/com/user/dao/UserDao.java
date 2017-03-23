package cn.com.user.dao;

import cn.com.dao.impl.GenericDaoImpl;
import cn.com.user.model.User;

public class UserDao extends GenericDaoImpl<User>{
   public User getCount(){
        return getByWhere("select count(*) from user").get(0);
    }
	
	

}
