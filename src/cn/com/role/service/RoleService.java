package cn.com.role.service;

import cn.com.role.dao.RoleDao;
import cn.com.role.model.Role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleService {
    RoleDao roleDao = new RoleDao();

    public List<Role> getRoles(){
        return roleDao.get();
    }

    public void addRole(Role role){
        roleDao.save(role);
    }
    public Role getRoleById(int rId){
        Map selectWhere = new HashMap();
        selectWhere.put("rId", rId);
        List<Role> list = roleDao.getByWhere(selectWhere);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    public void updateRole(Map values, Map updateWhere){
        if(!roleDao.getByWhere(updateWhere).isEmpty()){
            roleDao.update(values, updateWhere);
        }
    }


    public int deleteRoleById(int rId){
        if(getRoleById(rId) == null){
            return 0;
        }else{
             Map updateWhere = new HashMap();
             updateWhere.put("state", -1);
             Map values = new HashMap();
             values.put("rId", rId);
             roleDao.update(values, updateWhere);
             return 2;
        }
    }
}
