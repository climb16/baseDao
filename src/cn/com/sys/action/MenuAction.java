package cn.com.sys.action;

import cn.com.controller.Action;
import cn.com.sys.model.Menu;
import cn.com.sys.service.MenuService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuAction extends Action{

    MenuService menuService = new MenuService();
    //获取当前节点所有子节点
    public String getChildMenusById(HttpServletRequest req, HttpServletResponse resp){
        Map map = new HashMap();
        List<Menu> menus = new ArrayList<Menu>();
        String id = req.getParameter("id");
        if(!"".equals(id) && id != null){
            map.put("id",id);
            menus = menuService.getChildMenusById(map);
        }
        req.setAttribute("menus", menus);
        return "list";
    }
    //添加节点初始化
    public String addInit(HttpServletRequest req, HttpServletResponse resp){
        Menu menu = new Menu();
        String id = req.getParameter("menuId");
        if(!"".equals(id) && id != null){
            menu = menuService.getMenuById(Integer.parseInt(id));
        }
        req.setAttribute("menu", menu);
        return "add_init";
    }
    //添加节点
    public void addMenu(HttpServletRequest req, HttpServletResponse resp){
        Map map = new HashMap();
        String pid = req.getParameter("pid");
        map.put("name", req.getParameter("name"));
        map.put("pid", Integer.parseInt(pid));
        map.put("type", Integer.parseInt(req.getParameter("type")));
        map.put("url", req.getParameter("url"));
        map.put("order", Integer.parseInt(req.getParameter("order")));
        menuService.addMenu(map);
    }


    //修改节点初始化
    public String updateInit(HttpServletRequest req, HttpServletResponse resp){
        Menu menu = new Menu();
        String id = req.getParameter("menuId");
        if(!"".equals(id) && id != null){
            menu = menuService.getMenuById(Integer.parseInt(id));
        }
        req.setAttribute("menu", menu);
        return "edit_init";
    }

    //更新节点
    public void updateMenu(HttpServletRequest req, HttpServletResponse resp){
        Map values = new HashMap();
        Map updateWhere = new HashMap();
        values.put("name", "'"+req.getParameter("name")+"'");
        values.put("pid", Integer.parseInt(req.getParameter("pid")));
        values.put("type", Integer.parseInt(req.getParameter("type")));
        values.put("url", "'"+req.getParameter("url")+"'");
        values.put("order", Integer.parseInt(req.getParameter("oder")));
        updateWhere.put("id", Integer.parseInt(req.getParameter("id")));

        menuService.updateMenuById(values, updateWhere);
    }

    //删除节点
    public void deleteMenuById(HttpServletRequest req, HttpServletResponse resp){
        int flag = menuService.deleteMenuById(Integer.parseInt(req.getParameter("id")));
        try {
            resp.getWriter().print(flag);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
