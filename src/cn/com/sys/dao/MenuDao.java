package cn.com.sys.dao;

import cn.com.dao.impl.GenericDaoImpl;
import cn.com.sys.model.Menu;

import java.util.List;

public class MenuDao extends GenericDaoImpl<Menu>{

    public List getMenusByUser(int uid){
        String sql = "SELECT * FROM menu WHERE id IN(SELECT m_id FROM role_menu o JOIN role r, USER u WHERE o.`r_id`=r.`r_id` AND r.`r_id`=u.`r_id` AND u.`u_id`='"+uid+"') order by oder";
        return select(sql);
    }
}
