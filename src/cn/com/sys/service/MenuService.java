package cn.com.sys.service;
import cn.com.sys.dao.MenuDao;
import cn.com.sys.model.Menu;
import cn.com.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class MenuService {

    MenuDao menuDao = new MenuDao();

    public List getMenusByUid(int uid){
        return menuDao.getMenusByUser(uid);
    }

    List list = new ArrayList();
    //获取第一级菜单
    public List getChildMenusById(Map selectWhere){
        List<Menu> ls1 = menuDao.getByWhere(selectWhere);
        if(!ls1.isEmpty()){
            list = Util.appendList(list,ls1);
            getMenusByPid(ls1);
        }
        return list;
    }
    //递归获取所有子菜单
    public List getMenusByPid(List<Menu> menus){
        Map selectWhere = new HashMap();
        List m = new ArrayList();
        for(Menu menu : menus){
            selectWhere.put("pid", menu.getId());
            m = menuDao.getByWhere(selectWhere);
            list = Util.appendList(list, m);
            if(!m.isEmpty()){
                m = getMenusByPid(m);
            }
        }
       return m;
    }
    //根据Id查询菜单
    public Menu getMenuById(int id){
        Map selectWhere = new HashMap();
        selectWhere.put("id", id);
        List<Menu> menus = getMenusByWhere(selectWhere);
        if(!menus.isEmpty()){
            return menus.get(0);
        }
        return null;
    }
    //查询菜单
    public List<Menu> getMenusByWhere(Map selectWhere){
        return menuDao.getByWhere(selectWhere);
    }
    //添加菜单
    public void addMenu(Map values){
        menuDao.insert(values);
    }
    //根据Id修改菜单
    public void updateMenuById(Map values, Map updateWhere){
        if(!menuDao.getByWhere(updateWhere).isEmpty()){
            menuDao.update(values, updateWhere);
        }
    }
    //根据Id删除菜单
    public int deleteMenuById(int id){
        if(getMenuById(id) == null){
            return 0;
        }else{
            Map selectWhere = new HashMap();
            selectWhere.put("pid", id);
            if(!getMenusByWhere(selectWhere).isEmpty()){
                return 1;
            }else{
                Map deleteWhere = new HashMap();
                deleteWhere.put("id", id);
                menuDao.deleteByWhere(deleteWhere);
                return 2;
            }
        }
    }
}
