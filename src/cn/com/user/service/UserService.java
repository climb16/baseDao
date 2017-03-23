package cn.com.user.service;

import cn.com.user.dao.UserDao;
import cn.com.user.model.User;

import java.util.List;
import java.util.Map;

public class UserService {

    UserDao userDao = new UserDao();

    public User getUserByNameAndPassword(Map<String, Object> map){
        return userDao.getByWhere(map).get(0);
    }

    public List<User> getUsers(){

        return userDao.get();
    }
}
